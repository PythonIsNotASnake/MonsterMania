package monster;

public class Attack {

    private String attackName;
    private int attackDmg;
    private int attackPoints;

    public Attack(String attackName, int attackDmg, int attackPoints) {
        this.attackName = attackName;
        this.attackDmg = attackDmg;
        this.attackPoints = attackPoints;
    }

    public void reduceAttackPoints() {
        this.attackPoints = this.attackPoints - 1;
    }

    public String getAttackName() {
        return attackName;
    }

    public void setAttackName(String attackName) {
        this.attackName = attackName;
    }

    public int getAttackDmg() {
        return attackDmg;
    }

    public void setAttackDmg(int attackDmg) {
        this.attackDmg = attackDmg;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }
}
