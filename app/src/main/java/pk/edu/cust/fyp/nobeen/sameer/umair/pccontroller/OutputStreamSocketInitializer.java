package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by sameer on 24-Sep-17.
 */

public class OutputStreamSocketInitializer {
    OutputStreamSocketInitializer(){}
    void setSocketToOOS() {

        Thread conThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket clientsocket;
                    clientsocket = SocketHandler.getSocket();
                    MainActivity.objectOutputStream = new ObjectOutputStream(clientsocket.getOutputStream());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        conThread.start();
    }
}
