package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    Button connectBtn;
    EditText ipAddressEditTxt;
    int port=4444;
    Socket socket = null;
    SocketHandler socketHandler = null;
    private static final String TAG="debug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectBtn = (Button) findViewById(R.id.connect);
        ipAddressEditTxt = (EditText) findViewById(R.id.ipEditText);
        socketHandler = new SocketHandler();
        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread conThread =new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try
                        {

                            Log.i(TAG, "Attempting to connect to server");
                            socket = new Socket(ipAddressEditTxt.getText().toString(), port);
                            Log.i(TAG, "Connection established");
                            socketHandler.setSocket(socket);
                            Log.i(TAG,"Socket is Connected: "+socket.isConnected());


                        } catch (
                                Exception ex)

                        {
                            Toast.makeText(getApplicationContext(), "Exception occur" + ex, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                conThread.start();
                if(true)
                {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

                    Intent intent;
                    intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);

                } else
                    Toast.makeText(getApplicationContext(), "Connection Failed", Toast.LENGTH_SHORT).show();




            }
        });
    }


}

