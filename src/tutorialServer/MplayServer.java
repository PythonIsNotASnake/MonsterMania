package tutorialServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

public class MplayServer {

    private Map clients;
    private int amountOfClients;
    private ServerSocket server;

    public MplayServer(int port) throws IOException {
        HashMap hashMap = new HashMap(clients);
        amountOfClients = 0;
        this.server = new ServerSocket(port);
    }

    public static void main(String[] args) {

    }
}
