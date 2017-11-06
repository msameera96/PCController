package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PowerPoint extends AppCompatActivity {

    Button startPresentationBtn;
    Button backBtn;
    Button nextBtn;
    Button stopPresentationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_point);

        startPresentationBtn = (Button) findViewById(R.id.strtPrsnt);
        backBtn = (Button) findViewById(R.id.backBtn);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        stopPresentationBtn = (Button) findViewById(R.id.stopPrsntBtn);

        startPresentationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendActionToServer("F5_KEY");
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendActionToServer("LEFT_ARROW_KEY");
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendActionToServer("RIGHT_ARROW_KEY");
            }
        });


        startPresentationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendActionToServer("F5_KEY");
            }
        });
        stopPresentationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String action = "TYPE_KEY";
                int keycode = 27;
                sendActionToServer(action,keycode);
            }
        });
    }

    private void sendActionToServer(String action, int keyCode)
    {
        MainActivity.sendMessageToServer(action);
        MainActivity.sendMessageToServer(keyCode);
    }

    private void sendActionToServer(String action) {
        MainActivity.sendMessageToServer(action);
    }
}
