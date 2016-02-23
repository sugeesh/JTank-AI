package tanknetbeansproject.Model;

public class Cell {
	int xValue;
	int yValue;
	private String coin;
	private int lifePack;

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
	public String toString() {
		if(lifePack!=0){
			return "L";		// return "L"+lifepack;
		}else if(getCoin()==null){
			return "0";	
		}
		return "C"+getCoin();		//return coin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getCoin() == null) ? 0 : getCoin().hashCode());
		result = prime * result + lifePack;
		result = prime * result + xValue;
		result = prime * result + yValue;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		String ob = (String)obj;
		if(ob.equals(this.toString()))
			return true;
		return false;
	}

	public void addCoin(final String time, final String value) {
		Thread t = null;
		t = new Thread(new Runnable() {
            @Override
            public void run() {
        		setCoin(value);
                try {
                    Thread.sleep(Integer.parseInt(time));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                setCoin(null);
              
            }
        });
		
		t.start();
		
		
		// TODO Auto-generated method stub
		
	}

	public void addLifePack(final String time) {
		Thread t = null;
		t = new Thread(new Runnable() {
            @Override
            public void run() {
            	lifePack = Integer.parseInt(time);
                try {
                    Thread.sleep(Integer.parseInt(time));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                lifePack = 0;
              
            }
        });
		
		t.start();
		// TODO Auto-generated method stub
		
	}

    /**
     * @return the coin
     */
    public String getCoin() {
        return coin;
    }

    /**
     * @param coin the coin to set
     */
    public void setCoin(String coin) {
        this.coin = coin;
    }

	
}
