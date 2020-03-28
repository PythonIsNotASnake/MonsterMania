package server;

import monster.Monster;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection extends Thread {

    private ServerSocket sSoc;
    private Socket serverListener;
    private Monster monsterOwn;
    private Monster monsterEnemy;

    public Connection() throws IOException {

        //this.soc = new Socket("localhost", 1337);
        this.monsterOwn = null;
        this.monsterEnemy = new Monster();
    }

    public void attacked(String command) {
        String dmgText = command.substring(6);
        System.out.println("Server Damage: " + dmgText);
        Integer dmg = Integer.valueOf(dmgText);
        System.out.println("Server Damage Int: " + dmg);
        String dialog = "Enemy hurt you with " + dmg + " Points of Damage.";
        JOptionPane.showMessageDialog(new JFrame(), dialog);
        monsterOwn.setLifePoints(monsterOwn.getLifePoints() - dmg);
        if (monsterOwn.getLifePoints() <= 0) {
            monsterOwn.setLifePoints(0);
            this.monsterOwn.getLifeProgress().setString("" + monsterOwn.getLifePoints());
            this.monsterOwn.getLifeProgress().setValue(monsterOwn.getLifePoints());
            JOptionPane.showMessageDialog(new JFrame(), "Lose! Your enemy has you defeat.");
            this.monsterOwn.blockateAttacks(false);
        } else {
            this.monsterOwn.getLifeProgress().setString("" + monsterOwn.getLifePoints());
            this.monsterOwn.getLifeProgress().setValue(monsterOwn.getLifePoints());
            this.monsterOwn.blockateAttacks(true);
        }
        //monsterOwn.getLifeBar().setText(monsterOwn.getLifePoints() + "");

        //System.out.println("Sie wurden angegriffen!");
    }

    public void lifepoints(String command) {
        String lifepointsText = command.substring(10);
        Integer lifepoints = Integer.valueOf(lifepointsText);
        this.monsterEnemy.setLifePoints(lifepoints);
        //this.monsterEnemy.getLifeBar().setText("" + lifepoints);
        this.monsterEnemy.getLifeProgress().setMinimum(0);
        this.monsterEnemy.getLifeProgress().setMaximum(lifepoints);
        this.monsterEnemy.getLifeProgress().setValue(0);
        this.monsterEnemy.getLifeProgress().setString("" + lifepoints);

        this.monsterEnemy.getLifeProgress().setForeground(Color.decode("#1BBF85"));
        this.monsterEnemy.getLifeProgress().setBackground(Color.RED);
        //Border border = BorderFactory.createLineBorder(Color.GRAY);
        //this.monsterEnemy.getLifeProgress().setBorder(border);
        this.monsterEnemy.getLifeProgress().setStringPainted(true);
    }

    public void image(String command) throws IOException {
        String image = command.substring(5);
        Image bild = this.loadImageResource(image);
        //ImageIcon imageIcon = new ImageIcon(image);
        ImageIcon imageIcon = new ImageIcon(bild);
        JLabel imageLabel = new JLabel(imageIcon);
        this.monsterEnemy.setImage(image);
        this.monsterEnemy.setMonsterImage(imageLabel);
    }

    private InputStream getResourceStream(String pkgname) {
        Class clazz = getClass();
        InputStream is = clazz.getResourceAsStream(pkgname);
        return is;
    }

    public Image loadImageResource(String pkgname) throws IOException {
        Image ret = null;
        InputStream is = getResourceStream(pkgname);
        if (is != null) {
            byte[] buffer = new byte[0];
            byte[] tmpbuf = new byte[1024];
            while (true) {
                int len = is.read(tmpbuf);
                if (len <= 0) {
                    break;
                }
                byte[] newbuf = new byte[buffer.length + len];
                System.arraycopy(buffer, 0, newbuf, 0, buffer.length);
                System.arraycopy(tmpbuf, 0, newbuf, buffer.length, len);
                buffer = newbuf;
            }
            //create image
            ret = Toolkit.getDefaultToolkit().createImage(buffer);
            is.close();
        }
        return ret;
    }


    @Override
    public void run() {
        try {
            this.sSoc = new ServerSocket(1337);
            System.out.println("Status: Online");
            serverListener = sSoc.accept();
            System.out.println("Server IP: " + serverListener.getRemoteSocketAddress());
            System.out.println("Warte auf Gegner...");

            while (true) {
                //while (serverListener.getInputStream().available() < 1) {

                InputStreamReader input = new InputStreamReader(serverListener.getInputStream());
                BufferedReader reader = new BufferedReader(input);
                String command = reader.readLine();
                if (command.startsWith("Attack") == true) {
                    this.attacked(command);
                } else if (command.startsWith("Lifepoints") == true) {
                    this.lifepoints(command);
                } else if (command.startsWith("Image") == true) {
                    this.image(command);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMonsterOwn(Monster monsterOwn) {
        this.monsterOwn = monsterOwn;
    }

    public Monster getMonsterEnemy() {
        return monsterEnemy;
    }

    public void setMonsterEnemy(Monster monsterEnemy) {
        this.monsterEnemy = monsterEnemy;
    }
}
