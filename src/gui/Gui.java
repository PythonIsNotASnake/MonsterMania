package gui;

import monster.Creator;
import monster.HalfCourt;
import monster.Monster;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import server.Commander;
import server.Connection;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class Gui {

    private JPanel mainPanel;
    private JPanel playerOnePanel;
    private JPanel playerTwoPanel;
    private JPanel attackPanel;
    private Monster monsterOwn;
    private Monster monsterEnemy;
    private HalfCourt courtOne;
    private HalfCourt courtTwo;
    private JFrame frame;
    private Connection connect;
    private Commander command;

    public Gui() throws IOException, ParserConfigurationException, SAXException, InterruptedException {

        /*
        1. Server starten
        2. XML-Datei einlesen
        3. Mit Gegner verbinden
        4. Informationen an Gegner senden
         */


        Thread server = new Thread(connect = new Connection());
        server.start();

        this.monsterOwn = this.initializePlayer();
        //this.monsterEnemy = this.initializePlayer();

        command = new Commander(this.retrieveIP());
        //com.sendCommand("Attack 1");

        connect.setMonsterOwn(monsterOwn);

        command.sendCommand("Lifepoints" + monsterOwn.getLifePoints());
        Thread.sleep(2000);
        command.sendCommand("Image" + monsterOwn.getImage());
        Thread.sleep(2000);

        JOptionPane.showMessageDialog(this.frame, "Are you ready to fight?");

        //while (connect.getMonsterEnemy().getImage() == "" || connect.getMonsterEnemy().getLifePoints() != 1000) {

        //}
        //Thread waitRoom = new Thread();
        //waitRoom.wait(1000);
        //Thread.sleep(5000);


        //--------------------------------------------------------------------------------------------------------------
        this.frame = new JFrame("Monster Mania");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
/*
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Datei");
        JMenuItem reload = new JMenuItem("Neu laden");
        reload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.repaint();
            }
        });
        menu.add(reload);
        bar.add(menu);
        frame.setJMenuBar(bar);
*/
        mainPanel = new JPanel();

        monsterOwn.getMoveOne().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command.sendCommand("Attack" + monsterOwn.getAttackOne().getAttackDmg());
                connect.getMonsterEnemy().setLifePoints(connect.getMonsterEnemy().getLifePoints() - monsterOwn.getAttackOne().getAttackDmg());
                if (connect.getMonsterEnemy().getLifePoints() <= 0) {
                    connect.getMonsterEnemy().setLifePoints(0);
                    connect.getMonsterEnemy().getLifeProgress().setValue(connect.getMonsterEnemy().getLifeProgress().getMaximum());
                    connect.getMonsterEnemy().getLifeProgress().setString(connect.getMonsterEnemy().getLifePoints() + "");
                    JOptionPane.showMessageDialog(frame, "Victory! You have defeat your enemy.");
                } else {
                    connect.getMonsterEnemy().getLifeProgress().setValue(connect.getMonsterEnemy().getLifeProgress().getValue() + monsterOwn.getAttackOne().getAttackDmg());
                    connect.getMonsterEnemy().getLifeProgress().setString(connect.getMonsterEnemy().getLifePoints() + "");
                }
                //connect.getMonsterEnemy().getLifeBar().setText(connect.getMonsterEnemy().getLifePoints() + "");
                //connect.getMonsterEnemy().getLifeProgress().setValue(connect.getMonsterEnemy().getLifeProgress().getValue() + monsterOwn.getAttackOne().getAttackDmg());
                //connect.getMonsterEnemy().getLifeProgress().setString(connect.getMonsterEnemy().getLifePoints() + "");
                monsterOwn.getAttackOne().reduceAttackPoints();
                monsterOwn.getMoveOne().setText("<html><u>" + monsterOwn.getAttackOne().getAttackName() + "</u><br>Damage: " + monsterOwn.getAttackOne().getAttackDmg() + "<br>Points: " + monsterOwn.getAttackOne().getAttackPoints() + "</html>");
                System.out.println("MoveOne was pressed!");
                monsterOwn.blockateAttacks(false);
            }
        });

        monsterOwn.getMoveTwo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command.sendCommand("Attack" + monsterOwn.getAttackTwo().getAttackDmg());
                connect.getMonsterEnemy().setLifePoints(connect.getMonsterEnemy().getLifePoints() - monsterOwn.getAttackTwo().getAttackDmg());
                if (connect.getMonsterEnemy().getLifePoints() <= 0) {
                    connect.getMonsterEnemy().setLifePoints(0);
                    connect.getMonsterEnemy().getLifeProgress().setValue(connect.getMonsterEnemy().getLifeProgress().getMaximum());
                    connect.getMonsterEnemy().getLifeProgress().setString(connect.getMonsterEnemy().getLifePoints() + "");
                    JOptionPane.showMessageDialog(frame, "Victory! You have defeat your enemy.");
                } else {
                    connect.getMonsterEnemy().getLifeProgress().setValue(connect.getMonsterEnemy().getLifeProgress().getValue() + monsterOwn.getAttackTwo().getAttackDmg());
                    connect.getMonsterEnemy().getLifeProgress().setString(connect.getMonsterEnemy().getLifePoints() + "");
                }
                //connect.getMonsterEnemy().getLifeBar().setText(connect.getMonsterEnemy().getLifePoints() + "");
                //connect.getMonsterEnemy().getLifeProgress().setValue(connect.getMonsterEnemy().getLifeProgress().getValue() + monsterOwn.getAttackTwo().getAttackDmg());
                //connect.getMonsterEnemy().getLifeProgress().setString(connect.getMonsterEnemy().getLifePoints() + "");
                monsterOwn.getAttackTwo().reduceAttackPoints();
                monsterOwn.getMoveTwo().setText("<html><u>" + monsterOwn.getAttackTwo().getAttackName() + "</u><br>Damage: " + monsterOwn.getAttackTwo().getAttackDmg() + "<br>Points: " + monsterOwn.getAttackTwo().getAttackPoints() + "</html>");
                System.out.println("MoveTwo was pressed!");
                monsterOwn.blockateAttacks(false);
            }
        });

        monsterOwn.getMoveThree().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command.sendCommand("Attack" + monsterOwn.getAttackThree().getAttackDmg());
                connect.getMonsterEnemy().setLifePoints(connect.getMonsterEnemy().getLifePoints() - monsterOwn.getAttackThree().getAttackDmg());
                if (connect.getMonsterEnemy().getLifePoints() <= 0) {
                    connect.getMonsterEnemy().setLifePoints(0);
                    connect.getMonsterEnemy().getLifeProgress().setValue(connect.getMonsterEnemy().getLifeProgress().getMaximum());
                    connect.getMonsterEnemy().getLifeProgress().setString(connect.getMonsterEnemy().getLifePoints() + "");
                    JOptionPane.showMessageDialog(frame, "Victory! You have defeat your enemy.");
                } else {
                    connect.getMonsterEnemy().getLifeProgress().setValue(connect.getMonsterEnemy().getLifeProgress().getValue() + monsterOwn.getAttackThree().getAttackDmg());
                    connect.getMonsterEnemy().getLifeProgress().setString(connect.getMonsterEnemy().getLifePoints() + "");
                }
                //connect.getMonsterEnemy().getLifeBar().setText(connect.getMonsterEnemy().getLifePoints() + "");
                //connect.getMonsterEnemy().getLifeProgress().setValue(connect.getMonsterEnemy().getLifePoints());
                //connect.getMonsterEnemy().getLifeProgress().setValue(connect.getMonsterEnemy().getLifeProgress().getValue() + monsterOwn.getAttackThree().getAttackDmg());
                //connect.getMonsterEnemy().getLifeProgress().setString(connect.getMonsterEnemy().getLifePoints() + "");
                monsterOwn.getAttackThree().reduceAttackPoints();
                monsterOwn.getMoveThree().setText("<html><u>" + monsterOwn.getAttackThree().getAttackName() + "</u><br>Damage: " + monsterOwn.getAttackThree().getAttackDmg() + "<br>Points: " + monsterOwn.getAttackThree().getAttackPoints() + "</html>");
                System.out.println("MoveThree was pressed!");
                monsterOwn.blockateAttacks(false);
            }
        });

        monsterOwn.getMoveFour().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command.sendCommand("Attack" + monsterOwn.getAttackFour().getAttackDmg());
                connect.getMonsterEnemy().setLifePoints(connect.getMonsterEnemy().getLifePoints() - monsterOwn.getAttackFour().getAttackDmg());
                if (connect.getMonsterEnemy().getLifePoints() <= 0) {
                    connect.getMonsterEnemy().setLifePoints(0);
                    connect.getMonsterEnemy().getLifeProgress().setValue(connect.getMonsterEnemy().getLifeProgress().getMaximum());
                    connect.getMonsterEnemy().getLifeProgress().setString(connect.getMonsterEnemy().getLifePoints() + "");
                    JOptionPane.showMessageDialog(frame, "Victory! You have defeat your enemy.");
                } else {
                    connect.getMonsterEnemy().getLifeProgress().setValue(connect.getMonsterEnemy().getLifeProgress().getValue() + monsterOwn.getAttackFour().getAttackDmg());
                    connect.getMonsterEnemy().getLifeProgress().setString(connect.getMonsterEnemy().getLifePoints() + "");
                }
                //connect.getMonsterEnemy().getLifeBar().setText(connect.getMonsterEnemy().getLifePoints() + "");
                monsterOwn.getAttackFour().reduceAttackPoints();
                monsterOwn.getMoveFour().setText("<html><u>" + monsterOwn.getAttackFour().getAttackName() + "</u><br>Damage: " + monsterOwn.getAttackFour().getAttackDmg() + "<br>Points: " + monsterOwn.getAttackFour().getAttackPoints() + "</html>");
                System.out.println("MoveFour was pressed!");
                monsterOwn.blockateAttacks(false);
            }
        });

        mainPanel.setLayout(new GridLayout(4, 2));
        Dimension dim = new Dimension(800, 600);
        mainPanel.setPreferredSize(dim);
        mainPanel.setBackground(Color.decode("#1BBF85"));
        //mainPanel.add(monsterOwn.getLifeBar());
        mainPanel.add(monsterOwn.getLifeProgress());

        //mainPanel.add(connect.getMonsterEnemy().getLifeBar());
        mainPanel.add(connect.getMonsterEnemy().getLifeProgress());

        mainPanel.add(monsterOwn.getMonsterImage());

        mainPanel.add(connect.getMonsterEnemy().getMonsterImage());
        mainPanel.add(monsterOwn.getMoveOne());
        mainPanel.add(monsterOwn.getMoveTwo());
        mainPanel.add(monsterOwn.getMoveThree());
        mainPanel.add(monsterOwn.getMoveFour());


        frame.pack();
        Image bild = this.loadImageResource("/images/pokeball.jpg");
        frame.setIconImage(bild);
        frame.add(mainPanel);
        frame.setSize(800, 600);
        frame.setPreferredSize(dim);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.repaint();
    }

    private Monster initializePlayer() throws IOException, SAXException, ParserConfigurationException {
        Creator create = new Creator();
        create.buildDocument(create.chooseFile());
        create.findRoot();
        String imageText = create.findOneElement(create.getRoot(), "image");
        //ImageIcon image = new ImageIcon(imageText);
        String lifePointsText = create.findOneElement(create.getRoot(), "life");
        Integer lifePoints = Integer.valueOf(lifePointsText);

        NodeList nList = create.searchByTagname("attack");

        return new Monster(imageText, lifePoints, create.findAttack(nList, 1), create.findAttack(nList, 2), create.findAttack(nList, 3), create.findAttack(nList, 4));
    }

    private String retrieveIP() {
        return (String) JOptionPane.showInputDialog(this.frame, "Geben Sie die IP Ihres Gegners ein:", "Gegnersuche", JOptionPane.QUESTION_MESSAGE);
    }

    private InputStream getResourceStream(String pkgname) {
        //String resname = "/" + pkgname.replace('.', '/') + "/" + fname;
        Class clazz = getClass();
        //InputStream is = clazz.getResourceAsStream(resname);
        InputStream is = clazz.getResourceAsStream(pkgname);
        return is;
    }

    //public Image loadImageResource(String pkgname, String fname) throws IOException {
    public Image loadImageResource(String pkgname) throws IOException {
        Image ret = null;
        //InputStream is = getResourceStream(pkgname, fname);
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

}
