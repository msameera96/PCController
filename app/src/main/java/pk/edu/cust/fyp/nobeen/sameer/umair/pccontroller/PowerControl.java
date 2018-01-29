package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PowerControl extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_control);
        Button shutdownBtn = (Button) findViewById(R.id.shutdownBtn);
        Button restartbtn = (Button) findViewById(R.id.restartBtn);
        Button signoutBtn = (Button) findViewById(R.id.signoutBtn);
        Button hibernateBtn = (Button) findViewById(R.id.hibernateBtn);
        Button sleepBtn = (Button) findViewById(R.id.sleepBtn);
        shutdownBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.sendMessageToServer("Shutdown_PC");

            }
        });
        restartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.sendMessageToServer("Restart_PC");

            }
        });
        signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.sendMessageToServer("Sign_out");

            }
        });
        hibernateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.sendMessageToServer("Hibernate_PC");

            }
        });
        sleepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.sendMessageToServer("SLEEP_PC");

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainActivity.sendMessageToServer("log_ended");

    }
}
