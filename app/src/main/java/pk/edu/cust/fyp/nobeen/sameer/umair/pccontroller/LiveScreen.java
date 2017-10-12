package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class LiveScreen extends AppCompatActivity {

    private int xCord, yCord, initX, initY;
    boolean mouseMoved = false, moultiTouch = false;
    private ImageView screenshotImageView;
    private Timer timer;
    private int screenshotImageViewX, screenshotImageViewY;
    long currentPressTime, lastPressTime;
    Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_screen);
        screenshotImageView = (ImageView) findViewById(R.id.liveScreenImageView);
        ViewTreeObserver vto = screenshotImageView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
            public void onGlobalLayout()
            {
                screenshotImageViewX = screenshotImageView.getHeight();
                screenshotImageViewY = screenshotImageView.getWidth();
                ViewTreeObserver obs = screenshotImageView.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
            }

        });
        screenshotImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               socket = SocketHandler.getSocket();
                if(socket!=null)
                {
                    switch(event.getAction() & MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_DOWN:
                            xCord = screenshotImageViewX - (int) event.getY();
                            yCord = (int) event.getX();
                            initX = xCord;
                            initY = yCord;
                            MainMenu.sendMessageToServer("MOUSE_MOVE_LIVE");
                            //send mouse movement to server by adjusting coordinates
                            MainMenu.sendMessageToServer((float) xCord / screenshotImageViewX);
                            MainMenu.sendMessageToServer((float) yCord / screenshotImageViewY);
                            mouseMoved = false;
                            /*startTime = System.currentTimeMillis();
                            clickCount++;*/
                            break;
                        case MotionEvent.ACTION_MOVE:
                            if (moultiTouch == false) {
                                xCord = screenshotImageViewX - (int) event.getY();
                                yCord = (int) event.getX();
                                if ((xCord - initX) != 0 && (yCord - initY) != 0) {
                                    initX = xCord;
                                    initY = yCord;
                                    MainMenu.sendMessageToServer("MOUSE_MOVE_LIVE");
                                    //send mouse movement to server by adjusting coordinates
                                    MainMenu.sendMessageToServer((float) xCord / screenshotImageViewX);
                                    MainMenu.sendMessageToServer((float) yCord / screenshotImageViewY);
                                    mouseMoved=true;
                                }
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                            // supporting only single click
                            currentPressTime = System.currentTimeMillis();
                            long interval = currentPressTime - lastPressTime;
                            if (interval >= 500 && !mouseMoved) {
                                MainMenu.sendMessageToServer("LEFT_CLICK");
                                delayedUpdateScreenshot();
                            }
                            lastPressTime = currentPressTime;
                            break;
                     }

                }
                return true;
            }
        });
            timer = new Timer();
            updateScreenshot();
    }

    private void updateScreenshot() {
        socket = SocketHandler.getSocket();
        if (socket != null) {
            MainMenu.sendMessageToServer("SCREENSHOT_REQUEST");
            new UpdateScreen() {
                public void receiveData(Object result) {
                    String path = (String) result;
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    Matrix matrix = new Matrix();
                    matrix.postRotate(-90);
                    try {
                        Bitmap rotated = Bitmap.createBitmap(bitmap ,0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                        screenshotImageView.setImageBitmap(rotated);
                    } catch(Exception e) {
                       e.printStackTrace();
                    }
                }
            }.execute();
        }
    }
    private void delayedUpdateScreenshot() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateScreenshot();
            }
        }, 500);
    }
}
