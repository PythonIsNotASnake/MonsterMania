package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer implements Runnable {

    private ServerSocket gameServer;
    private Socket gamer;

    public GameServer() {
        this.gameServer = null;
        this.gamer = null;
    }

    @Override
    public void run() {
        try {
            gameServer = new ServerSocket(1337);

        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                gamer = gameServer.accept();
            } catch (IOException e) {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Thread gaming = new Thread(new GameServer());
        gaming.start();
    }
}
