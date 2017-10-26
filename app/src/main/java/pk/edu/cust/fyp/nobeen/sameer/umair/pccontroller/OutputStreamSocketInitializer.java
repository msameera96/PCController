package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by sameer on 24-Oct-17.
 */

public class OutputStreamSocketInitializer {
    OutputStreamSocketInitializer(){}
    void setSocketToOOS() {

        Thread conThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket;

                    SocketHandler socketHandler = null;
                    socket = socketHandler.getSocket();
                    MainActivity.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                } catch (Exception ex) {
                }
            }
        });
        conThread.start();
    }
}
