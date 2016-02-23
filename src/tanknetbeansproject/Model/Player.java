package tanknetbeansproject.Model;

import tanknetbeansproject.view.ClientUI;

public class Player {
	private String number;
	private int xValue;
	private int yValue;
	private int direction;
	private int coin;
	private int health;
	private int point;

	public Player(String number, int xValue, int yValue, int direction) {
		super();
		this.number = number;
		this.xValue = xValue;
		this.yValue = yValue;
		this.direction = direction;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getxValue() {
		return xValue;
	}

	public void setxValue(int xValue) {
		this.xValue = xValue;
	}

	public int getyValue() {
		return yValue;
	}

	public void setyValue(int yValue) {
		this.yValue = yValue;
	}

	@Override
	public boolean equals(Object obj) {
		if (((String) obj).equals(getNumber())) {
			return true;
		}
		return false;
	}

        
        public String toString(){
            return this.getNumber();
        }
        
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getPoint() {
		return point;
	}

	public void setPoints(int point) {
            this.setPoint(point);
	}

	public void updateLocation(Map map, int xValue, int yValue) {
		if (!(this.getxValue() == xValue && this.getyValue() == yValue)) {
			map.getFullMap()[xValue][yValue] = new Tank(this);
                        map.getFullMap()[this.getxValue()][this.getyValue()] = new Cell();
			this.setxValue(xValue);
			this.setyValue(yValue);
                        System.err.println("Inside Method :"+map.getFullMap()[xValue][yValue]);
		}
	}

	public void placeTank(Map map, int xValue, int yValue) {
		map.getFullMap()[xValue][yValue] = new Tank(this);
	}
	
	
	public void updateDirectionCoinHelthPoints(ClientUI clientUI,String direction,String coin,String health,String point){
            this.setDirection(Integer.parseInt(direction));
                this.setCoin(Integer.parseInt(coin));
                this.setHealth(Integer.parseInt(health));
                this.setPoint(Integer.parseInt(point));
		clientUI.updateHelthCoinPoint(coin,health,point,direction);
	}

    /**
     * @param point the point to set
     */
    public void setPoint(int point) {
        this.point = point;
    }

}
