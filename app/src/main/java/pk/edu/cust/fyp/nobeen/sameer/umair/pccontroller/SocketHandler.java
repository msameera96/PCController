package pk.edu.cust.fyp.nobeen.sameer.umair.pccontroller;

import java.net.Socket;

/**
 * Created by M_Sameer on 29-May-17.
 */

public class SocketHandler {
    private static Socket socket;

    public static synchronized Socket getSocket(){
        return socket;
    }

    public static synchronized void setSocket(Socket socket){
        SocketHandler.socket = socket;
    }
}
