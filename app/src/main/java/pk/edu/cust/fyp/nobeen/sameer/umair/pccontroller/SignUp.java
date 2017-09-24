package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        TextView loginLink = (TextView) findViewById(R.id.loginSignUpActTextView);
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent;
                intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
