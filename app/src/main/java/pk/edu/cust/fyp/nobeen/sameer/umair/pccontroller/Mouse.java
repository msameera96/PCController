package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.Socket;

public class Mouse extends AppCompatActivity {
    private Button leftClickButton, rightClickButton;
    private TextView touchPadTextView;
    private int initX, initY, disX, disY;
    boolean mouseMoved = false, moultiTouch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse);
        leftClickButton = (Button) findViewById(R.id.leftBtn);
        rightClickButton = (Button) findViewById(R.id.rightBtn);
        touchPadTextView = (TextView) findViewById(R.id.mousePad);
        leftClickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simulateLeftClick();

            }
        });
        rightClickButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                simulateRightClick();

            }
        });
        touchPadTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                simulateLeftClick();

            }
        });
        touchPadTextView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Socket socket = SocketHandler.getSocket();
                if (socket != null) {
                    switch(event.getAction() & MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_DOWN:
                            //save X and Y positions when user touches the TextView
                            initX = (int) event.getX();
                            initY = (int) event.getY();
                            mouseMoved = false;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            if(moultiTouch == false) {
                                disX = (int) event.getX()- initX; //Mouse movement in x direction
                                disY = (int) event.getY()- initY; //Mouse movement in y direction
                                /*set init to new position so that continuous mouse movement
                                is captured*/
                                initX = (int) event.getX();
                                initY = (int) event.getY();
                                if (disX != 0 || disY != 0) {
                                    MainMenu.sendMessageToServer("MOUSE_MOVE");
                                    //send mouse movement to server
                                    MainMenu.sendMessageToServer(disX);
                                    MainMenu.sendMessageToServer(disY);
                                    mouseMoved=true;
                                }
                            }
                            else {
                                disY = (int) event.getY()- initY; //Mouse movement in y direction
                                disY = (int) disY / 2;//to scroll by less amount
                                initY = (int) event.getY();
                                if(disY != 0) {
                                    MainMenu.sendMessageToServer("MOUSE_WHEEL");
                                    MainMenu.sendMessageToServer(disY);
                                    mouseMoved=true;
                                }
                            }
                            break;
                        case MotionEvent.ACTION_CANCEL:
                        case MotionEvent.ACTION_UP:
                            //consider a tap only if user did not move mouse after ACTION_DOWN
                            if(!mouseMoved){
                                MainMenu.sendMessageToServer("LEFT_CLICK");
                            }
                            break;
                        case MotionEvent.ACTION_POINTER_DOWN:
                            initY = (int) event.getY();
                            mouseMoved = false;
                            moultiTouch = true;
                            break;
                        case MotionEvent.ACTION_POINTER_UP:
                            if(!mouseMoved) {
                                MainMenu.sendMessageToServer("LEFT_CLICK");
                            }
                            moultiTouch = false;
                            break;
                    }
                }
                return true;
            }
        });
    }
    private void simulateLeftClick() {
        String message = "LEFT_CLICK";
        MainMenu.sendMessageToServer(message);
    }

    private void simulateRightClick() {
        String message = "RIGHT_CLICK";
        MainMenu.sendMessageToServer(message);
    }

}
