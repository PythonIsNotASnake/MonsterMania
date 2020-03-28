package server;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Commander {

    private Socket soc;

    public Commander(String ip) throws IOException {
        this.soc = new Socket(ip, 1337);
    }

    public void sendCommand(String command) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(soc.getOutputStream());
            writer.println(command);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
