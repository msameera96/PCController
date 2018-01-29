package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CMD extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmd);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainActivity.sendMessageToServer("log_ended");

    }


}
