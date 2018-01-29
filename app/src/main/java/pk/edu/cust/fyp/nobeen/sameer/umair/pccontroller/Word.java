package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Word extends AppCompatActivity  implements View.OnClickListener{
    private Button keyboardBtn;
    private Button mouseBtn;
    private Button ctrlABtn, ctrlSBtn,ctrlBBtn, ctrlIBtn,ctrlUBtn,ctrlCBtn, ctrlVBtn,ctrlXBtn,shiftLeftBtn;
    private Button arrowLeftBtn,arrowRightBtn,arrowUpBtn,arrowDownBtn,shiftUpBtn,shiftDownBtn,shiftRightBtn;
    private Intent intent;

    public Word(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        keyboardBtn = (Button) findViewById(R.id.remoteKeybrd);
        mouseBtn = (Button) findViewById(R.id.remotemouse);
        ctrlABtn = (Button) findViewById(R.id.ctrla);
        ctrlSBtn = (Button) findViewById(R.id.ctrls);
        ctrlBBtn = (Button) findViewById(R.id.ctrlb);
        ctrlIBtn = (Button) findViewById(R.id.ctrli);
        ctrlUBtn = (Button) findViewById(R.id.ctrlu);
        ctrlCBtn = (Button) findViewById(R.id.ctrlc);
        ctrlVBtn = (Button) findViewById(R.id.ctrlv);
        ctrlXBtn = (Button) findViewById(R.id.ctrlx);
        shiftDownBtn = (Button) findViewById(R.id.shiftdown);
        shiftUpBtn = (Button) findViewById(R.id.shiftup);
        shiftLeftBtn = (Button) findViewById(R.id.shiftleft);
        shiftRightBtn = (Button) findViewById(R.id.shiftRight);
        arrowLeftBtn = (Button) findViewById(R.id.arrowleft);
        arrowRightBtn = (Button) findViewById(R.id.arrowRight);
        arrowUpBtn = (Button) findViewById(R.id.arrowup);
        arrowDownBtn = (Button) findViewById(R.id.arrowdown);

        ctrlABtn.setOnClickListener(this);
        ctrlSBtn.setOnClickListener(this);
        ctrlBBtn.setOnClickListener(this);
        ctrlIBtn.setOnClickListener(this);
        ctrlUBtn.setOnClickListener(this);
        ctrlCBtn.setOnClickListener(this);
        ctrlVBtn.setOnClickListener(this);
        ctrlXBtn.setOnClickListener(this);

        shiftDownBtn.setOnClickListener(this);
        shiftUpBtn.setOnClickListener(this);
        shiftRightBtn.setOnClickListener(this);
        shiftLeftBtn.setOnClickListener(this);

        arrowUpBtn.setOnClickListener(this);
        arrowDownBtn.setOnClickListener(this);
        arrowRightBtn.setOnClickListener(this);
        arrowLeftBtn.setOnClickListener(this);

        keyboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Word.this,Keyboard.class);
                startActivity(intent);
            }
        });

        mouseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Word.this,Mouse.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        String message = null;
        switch (id)
        {
            case R.id.ctrla:
                message = "CTRL_A";
                break;
            case R.id.ctrlb:
                message = "CTRL_B";
                break;

            case R.id.ctrlc:
                message = "CTRL_C";
                break;
            case R.id.ctrli:
                message ="CTRL_I";
                break;
            case R.id.ctrls:
                message ="CTRL_S";
                break;
            case R.id.ctrlu:
                message = "CTRL_U";
                break;
            case R.id.ctrlv:
                message =  "CTRL_V";
                break;
            case R.id.ctrlx:
                message = "CTRL_X";
                break;

            case R.id.shiftleft:
                message = "SHIFT_LEFT";
                break;
            case R.id.shiftdown:
                message = "SHIFT_DOWN";
                break;
            case R.id.shiftRight:
                message = "SHIFT_RIGHT";
                break;
            case R.id.shiftup:
                message = "SHIFT_UP";
                break;

            case R.id.arrowleft:
                message="LEFT_ARROW_KEY";
                break;
            case R.id.arrowdown:
                message = "DOWN_ARROW_KEY";
                break;
            case R.id.arrowRight:
                message = "RIGHT_ARROW_KEY";
                break;
            case R.id.arrowup:
                message = "UP_ARROW_KEY";
                break;
        }
        MainActivity.sendMessageToServer(message);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainActivity.sendMessageToServer("log_ended");

    }
}
