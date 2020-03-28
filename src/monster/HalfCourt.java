package monster;

import javax.swing.JLabel;

// enthaelt das Monster und die Lebensanzeige eines Spielers
public class HalfCourt {

	private Monster monster;
	private JLabel[] court;

	public HalfCourt(Monster monster) {
		this.monster = monster;
		court = new JLabel[2];
		court[0] = monster.getLifeBar();
		court[1] = monster.getMonsterImage();
	}

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	public JLabel[] getCourt() {
		return court;
	}

	public void setCourt(JLabel[] court) {
		this.court = court;
	}

}
