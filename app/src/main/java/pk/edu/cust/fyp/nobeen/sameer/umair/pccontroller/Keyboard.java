package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Keyboard extends AppCompatActivity implements View.OnKeyListener {
    String TAG = "debug";
    Socket socket;

    SocketHandler socketHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        EditText keyBoardEditTxt = (EditText) findViewById(R.id.keyboardEditText);
        socketHandler = new SocketHandler();
        keyBoardEditTxt.addTextChangedListener(editTextWatcher);
    }
    private TextWatcher editTextWatcher = new TextWatcher() {

        private void sendTextToServer(String send) {
            final String val=send;


            Thread conThread =new Thread(new Runnable() {
                @Override
                public void run() {
                    String textChange = val;
                    BufferedWriter bw = null;

                    try {
                        socket = socketHandler.getSocket();
                        bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        bw.write(textChange);
                        bw.newLine();
                        bw.flush();

                    } catch (IOException e) {
                    }
                }
            });
            conThread.start();


        }




        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.d(TAG,"Keyboard"+s + " Character Read");
            Log.d(TAG,"Keyboard"+ count);

            String test = s.toString();
            this.sendTextToServer(test);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }
};






