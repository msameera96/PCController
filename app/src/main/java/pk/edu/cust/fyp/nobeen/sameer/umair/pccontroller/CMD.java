package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CMD extends AppCompatActivity {


    EditText cmdET;
   public static TextView cmdOutputTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmd);

        cmdET = (EditText) findViewById(R.id.cmdEditText);
        cmdOutputTV = (TextView) findViewById(R.id.textView2);

        cmdET.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (event.getAction() == KeyEvent.ACTION_UP) {
                        if(cmdET.getText().toString().length()>0)
                        sendCommand(cmdET.getText().toString());
                        else
                            Toast.makeText(getApplicationContext(),"No command typed",Toast.LENGTH_SHORT).show();
                        new CommandsDisplay(getApplicationContext()).execute();

                    }
                    return true;
                }
                return false;
            }
        });

    }

    void sendCommand(String cmnd)
    {
        MainActivity.sendMessageToServer("CMD");
        MainActivity.sendMessageToServer(cmnd);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainActivity.sendMessageToServer("log_ended");

    }
}
