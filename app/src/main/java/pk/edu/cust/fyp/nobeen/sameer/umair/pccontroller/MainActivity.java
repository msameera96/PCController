package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class MainActivity extends AppCompatActivity  implements Serializable{
    Button connectBtn;
    EditText ipAddressEditTxt;
    int port=4444;
    private static final String TAG="debug";
    public static Socket clientsocket;
    public static ObjectOutputStream objectOutputStream = null;
    public static ObjectInputStream objectInputStream = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //For objectOutputStream


        connectBtn = (Button) findViewById(R.id.connect);
        ipAddressEditTxt = (EditText) findViewById(R.id.ipEditText);


        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ValidateIP.validateIP(ipAddressEditTxt.getText().toString())) {


                    Conection conection = new Conection();
                    if (conection.connectionEstablishing(ipAddressEditTxt.getText().toString(), port)) {

                            if (true) {

                                Toast.makeText(getApplicationContext(), "Success \n"+clientsocket+"\n"+objectOutputStream, Toast.LENGTH_SHORT).show();

                                Intent intent;
                                intent = new Intent(MainActivity.this, Login.class);
                                startActivity(intent);
                            }


                    } else
                        Toast.makeText(getApplicationContext(), "Connection Failed", Toast.LENGTH_SHORT).show();

                }else
                {
                    Toast.makeText(getApplicationContext(), "Invalid IP Format", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public static void sendMessageToServer(String message) {
        Socket clientSocket=SocketHandler.getSocket();
        if (clientSocket != null) {
            try {
                MainActivity.objectOutputStream.writeObject(message);
                MainActivity.objectOutputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
                socketException();
            }
        }
    }

    public static void sendMessageToServer(int message) {
        Socket clientSocket=SocketHandler.getSocket();
        if (clientSocket != null) {
            try{
                MainActivity.objectOutputStream.writeObject(message);
                MainActivity.objectOutputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
                socketException();
            }
        }
    }

    static void socketException() {

        Socket clientSocket=SocketHandler.getSocket();
        if (clientSocket != null) {
            try{
                clientSocket.close();
                MainActivity.objectOutputStream.close();
                // MainMenu.clientSocket = null;
            } catch(Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void sendMessageToServer(float message) {
        Socket clientSocket=SocketHandler.getSocket();
        if (clientSocket != null) {
            try {
                MainActivity.objectOutputStream.writeObject(message);
                MainActivity.objectOutputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
                socketException();
            }
        }
    }

    public static void sendMessageToServer(long message) {
        Socket clientSocket=SocketHandler.getSocket();
        if (clientSocket != null) {
            try {
                MainActivity.objectOutputStream.writeObject(message);
                MainActivity.objectOutputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
                socketException();
            }
        }
    }

    public static void closeServer() {
        try {
            if (clientsocket != null) {
                clientsocket.close();
            }

            if (objectInputStream != null) {
                objectInputStream.close();
            }
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }

        } catch(Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

}





