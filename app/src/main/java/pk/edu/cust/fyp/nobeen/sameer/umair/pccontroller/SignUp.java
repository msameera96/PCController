package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;


import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ObjectInputStream;

public class SignUp extends AppCompatActivity{
    EditText usernameET;
    EditText passET;
    //OutputStreamSocketInitializer ossi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        TextView loginLink = (TextView) findViewById(R.id.loginSignUpActTextView);
        Button sigUpBtn = (Button) findViewById(R.id.signUpBtn);
        usernameET = (EditText) findViewById(R.id.usernameSignUpActEditText);
        passET = (EditText) findViewById(R.id.passSignUpActEditText);
        //ossi = new OutputStreamSocketInitializer();
        //ossi.setSocketToOOS();


        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent;
                intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });
        sigUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usern = usernameET.getText().toString();
                String pas = passET.getText().toString();
                if ((usern.matches("")) && (pas.matches(""))) {
                    Toast.makeText(getApplicationContext(), "Please fill the fields first", Toast.LENGTH_LONG).show();
                } else

                {
                    MainActivity.sendMessageToServer("DB_Mob_Detail");
                    MainActivity.sendMessageToServer(getMobileInfoForDB());
                    MainActivity.sendMessageToServer(getAndroidID());

                    MainActivity.sendMessageToServer("DB_User_Insert");
                    MainActivity.sendMessageToServer(usern);
                    MainActivity.sendMessageToServer(pas);
                    new GetMessageNotification(getApplicationContext()).execute();

                    //Toast.makeText(getApplicationContext(), "Mob name"+getMobileInfoForDB()+"Mob ID"+getAndroidID(), Toast.LENGTH_LONG).show();
                }


            }
        });


    }

    String getMobileInfoForDB() {

        String mob_name = android.os.Build.MODEL;
        return mob_name;
    }

    String getAndroidID() {

        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

        return android_id;
    }


}
