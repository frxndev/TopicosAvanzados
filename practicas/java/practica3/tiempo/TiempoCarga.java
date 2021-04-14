package practica3.tiempo;

import java.net.*;

public class TiempoCarga {
    public int carga(String host){
        try {
            Socket socket = new Socket(host, 80);
            int time = socket.getSoTimeout();
            return time;
        } catch (Exception e) {
        }
        return 0;
    }    
}
