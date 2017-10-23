package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class Keyboard extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener, TextWatcher {

    private TextView keyTxt;
    private EditText keyboardEditText;
    private Button ctrlButton, altButton, shiftButton, enterButton, tabButton, escButton, printScrButton, backspaceButton;
    private Button deleteButton, clearTextButton;
    private Button ctrlAltTButton, ctrlShiftZButton, altF4Button;
    private String previousText = "";

    public Keyboard() {
        // Required empty public constructor
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);


        keyboardEditText = (EditText) findViewById(R.id.keyboardEditText);
        keyTxt = (TextView) findViewById(R.id.keboardTextView);
        ctrlButton = (Button) findViewById(R.id.ctrlButton);
        altButton = (Button) findViewById(R.id.altButton);
        shiftButton = (Button) findViewById(R.id.shiftButton);
        enterButton = (Button) findViewById(R.id.enterButton);
        tabButton = (Button) findViewById(R.id.tabButton);
        escButton = (Button) findViewById(R.id.escButton);
        printScrButton = (Button) findViewById(R.id.printScrButton);
        backspaceButton = (Button) findViewById(R.id.backspaceButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        clearTextButton = (Button) findViewById(R.id.clearTextButton);
        ctrlAltTButton = (Button) findViewById(R.id.ctrlAltTButton);
        ctrlShiftZButton = (Button) findViewById(R.id.ctrlShiftZButton);
        altF4Button = (Button) findViewById(R.id.altF4Button);
        ctrlButton.setOnTouchListener(this);
        altButton.setOnTouchListener(this);
        shiftButton.setOnTouchListener(this);
        backspaceButton.setOnClickListener(this);
        enterButton.setOnClickListener(this);
        tabButton.setOnClickListener(this);
        escButton.setOnClickListener(this);
        printScrButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        clearTextButton.setOnClickListener(this);
        ctrlAltTButton.setOnClickListener(this);
        ctrlShiftZButton.setOnClickListener(this);
        altF4Button.setOnClickListener(this);
        keyboardEditText.addTextChangedListener(this);
    }

    public boolean onTouch(View v, MotionEvent event) {
        String action = "KEY_PRESS";
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            action = "KEY_PRESS";
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            action = "KEY_RELEASE";
        }
        int keyCode = 17;//dummy initialization
        switch (v.getId()) {
            case  R.id.ctrlButton:
                keyCode = 17;
                break;
            case  R.id.altButton:
                keyCode = 18;
                break;
            case  R.id.shiftButton:
                keyCode = 16;
                break;
        }
        sendKeyCodeToServer(action, keyCode);
        return false;
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id ==  R.id.clearTextButton) {
            keyTxt.setText("");
        } else if (id ==  R.id.ctrlAltTButton || id ==  R.id.ctrlShiftZButton || id ==  R.id.altF4Button) {
            String message = "CTRL_SHIFT_Z";
            switch (id) {
                case  R.id.ctrlAltTButton:
                    message = "CTRL_ALT_T";
                    break;
                case  R.id.ctrlShiftZButton:
                    message = "CTRL_SHIFT_Z";
                    break;
                case  R.id.altF4Button:
                    message = "ALT_F4";
                    break;
            }
            MainActivity.sendMessageToServer(message);
        } else {
            int keyCode = 17;//dummy initialization
            String action = "TYPE_KEY";
            switch (id) {
                case  R.id.enterButton:
                    keyCode = 10;
                    break;
                case  R.id.tabButton:
                    keyCode = 9;
                    break;
                case  R.id.escButton:
                    keyCode = 27;
                    break;
                case  R.id.printScrButton:
                    keyCode = 154;
                    break;
                case  R.id.deleteButton:
                    keyCode = 127;
                    break;

                case  R.id.backspaceButton:
                    keyCode = 8;
                    break;
            }
            sendKeyCodeToServer(action, keyCode);
        }

    }
    private void sendKeyCodeToServer(String action, int keyCode) {
        MainActivity.sendMessageToServer(action);
        MainActivity.sendMessageToServer(keyCode);
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        char ch = newCharacter(s, previousText);
        if (ch == 0) {
            return;
        }
        MainActivity.sendMessageToServer("TYPE_CHARACTER");
        MainActivity.sendMessageToServer(Character.toString(ch));
        previousText = s.toString();
    }
    @Override
    public void afterTextChanged(Editable s) {
    }

    private char newCharacter(CharSequence currentText, CharSequence previousText) {
        char ch = 0;
        int currentTextLength = currentText.length();
        int previousTextLength = previousText.length();
        int difference = currentTextLength - previousTextLength;
        if (currentTextLength > previousTextLength) {
            if (1 == difference) {
                ch = currentText.charAt(previousTextLength);
            }
        } else if (currentTextLength < previousTextLength) {
            if (-1 == difference) {
                ch = '\b';
            } else {
                ch = ' ';
            }
        }
        return ch;
    }

}







