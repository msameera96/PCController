package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        TextView signUpLink = (TextView) findViewById(R.id.signUpLoginActTextView);
        final EditText usern = (EditText) findViewById(R.id.usernameLoginActEditText);
        final EditText pas = (EditText) findViewById(R.id.passLoginActEditText);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String un = usern.getText().toString();
                String p =  pas.getText().toString();
                if(un.matches("") && p.matches(""))
                {
                    Toast.makeText(getApplicationContext(),"Please fill the fields first",Toast.LENGTH_LONG);
                }
                else{
                    MainActivity.sendMessageToServer("DB_Login");
                    MainActivity.sendMessageToServer(usern.getText().toString());
                    MainActivity.sendMessageToServer(pas.getText().toString());
                    new GetMessageNotification(getApplicationContext()).execute();
                }







            }
        });
        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent;
                intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
}
