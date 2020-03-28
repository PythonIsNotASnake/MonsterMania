package monster;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Monster {

    private int lifePoints;
    private JLabel lifeBar;
    private String image;
    private JLabel monsterImage;
    private Attack attackOne;
    private Attack attackTwo;
    private Attack attackThree;
    private Attack attackFour;
    private JButton moveOne;
    private JButton moveTwo;
    private JButton moveThree;
    private JButton moveFour;

    private Image bild;
    private JProgressBar lifeProgress;

    public Monster(String image, int lifePoints, Attack attackOne, Attack attackTwo, Attack attackThree, Attack attackFour) throws IOException {
        Font schrift = new Font("Comic Sans MS", Font.BOLD, 20);
        this.lifePoints = lifePoints;

        this.attackOne = attackOne;
        this.attackTwo = attackTwo;
        this.attackThree = attackThree;
        this.attackFour = attackFour;

        //lifeBar = new JLabel("" + this.lifePoints);
        this.lifeProgress = new JProgressBar(0, this.lifePoints);
        this.lifeProgress.setString(this.lifePoints + "");
        this.lifeProgress.setValue(this.lifePoints);
        this.lifeProgress.setForeground(Color.GREEN);
        this.lifeProgress.setBackground(Color.decode("#1BBF85"));
        //Border border = BorderFactory.createLineBorder(Color.GRAY);
        //this.lifeProgress.setBorder(border);
        this.lifeProgress.setBorderPainted(false);
        this.lifeProgress.setFont(schrift);
        this.lifeProgress.setStringPainted(true);

        this.image = image;
        this.bild = this.loadImageResource(image);
        //ImageIcon icon = new ImageIcon(this.image);
        ImageIcon icon = new ImageIcon(this.bild);
        monsterImage = new JLabel(icon);

        moveOne = new JButton("<html><u>" + this.attackOne.getAttackName() + "</u><br>Damage: " + this.attackOne.getAttackDmg() + "<br>Points: " + this.attackOne.getAttackPoints() + "</html>");
        moveTwo = new JButton("<html>" + this.attackTwo.getAttackName() + "<br>Damage: " + this.attackTwo.getAttackDmg() + "<br>Points: " + this.attackTwo.getAttackPoints() + "</html>");
        moveThree = new JButton("<html>" + this.attackThree.getAttackName() + "<br>Damage: " + this.attackThree.getAttackDmg() + "<br>Points: " + this.attackThree.getAttackPoints() + "</html>");
        moveFour = new JButton("<html>" + this.attackFour.getAttackName() + "<br>Damage: " + this.attackFour.getAttackDmg() + "<br>Points: " + this.attackFour.getAttackPoints() + "</html>");

        moveOne.setFont(schrift);
        moveTwo.setFont(schrift);
        moveThree.setFont(schrift);
        moveFour.setFont(schrift);
    }

    public Monster(ImageIcon image, int lifePoints) {
        this.lifePoints = lifePoints;
        this.lifeBar = new JLabel("" + this.lifePoints);
        monsterImage = new JLabel(image);
    }

    public Monster() {
        Font schrift = new Font("Comic Sans MS", Font.BOLD, 20);
        this.lifeBar = new JLabel("");
        this.lifeProgress = new JProgressBar();
        this.lifeProgress.setFont(schrift);
        //this.lifeProgress.setBorderPainted(false);
        Border border = BorderFactory.createLineBorder(Color.decode("#1BBF85"));
        this.lifeProgress.setBorder(border);
        this.monsterImage = new JLabel();
    }

    //private InputStream getResourceStream(String pkgname, String fname) {
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

    public void blockateAttacks(boolean b) {
        if (attackOne.getAttackPoints() <= 0) {
            moveOne.setEnabled(false);
        } else {
            moveOne.setEnabled(b);
        }

        if (attackTwo.getAttackPoints() <= 0) {
            moveTwo.setEnabled(false);
        } else {
            moveTwo.setEnabled(b);
        }

        if (attackThree.getAttackPoints() <= 0) {
            moveThree.setEnabled(false);
        } else {
            moveThree.setEnabled(b);
        }

        if (attackFour.getAttackPoints() <= 0) {
            moveFour.setEnabled(false);
        } else {
            moveFour.setEnabled(b);
        }
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public JLabel getLifeBar() {
        return lifeBar;
    }

    public void setLifeBar(JLabel lifeBar) {
        this.lifeBar = lifeBar;
    }

    public JLabel getMonsterImage() {
        return monsterImage;
    }

    public void setMonsterImage(JLabel monsterImage) {
        this.monsterImage = monsterImage;
    }

    public JButton getMoveOne() {
        return moveOne;
    }

    public void setMoveOne(JButton moveOne) {
        this.moveOne = moveOne;
    }

    public JButton getMoveTwo() {
        return moveTwo;
    }

    public void setMoveTwo(JButton moveTwo) {
        this.moveTwo = moveTwo;
    }

    public JButton getMoveThree() {
        return moveThree;
    }

    public void setMoveThree(JButton moveThree) {
        this.moveThree = moveThree;
    }

    public JButton getMoveFour() {
        return moveFour;
    }

    public void setMoveFour(JButton moveFour) {
        this.moveFour = moveFour;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Attack getAttackOne() {
        return attackOne;
    }

    public Attack getAttackTwo() {
        return attackTwo;
    }

    public Attack getAttackThree() {
        return attackThree;
    }

    public Attack getAttackFour() {
        return attackFour;
    }

    public Image getBild() {
        return bild;
    }

    public void setBild(Image bild) {
        this.bild = bild;
    }

    public JProgressBar getLifeProgress() {
        return lifeProgress;
    }

    public void setLifeProgress(JProgressBar lifeProgress) {
        this.lifeProgress = lifeProgress;
    }
}
