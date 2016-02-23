package tanknetbeansproject.Model;

public class BrickCell extends Cell {
	private int damageLevel;
	
	public int getDamageLevel() {
		return damageLevel;
	}

	public void setDamageLevel(int damageLevel) {
		this.damageLevel = damageLevel;
	}

	public BrickCell(int xValue, int yValue) {
		this.xValue = xValue;
		this.yValue = yValue;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "B"+getDamageLevel();
	}
	

}
