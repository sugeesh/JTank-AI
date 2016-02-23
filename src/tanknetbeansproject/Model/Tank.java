package tanknetbeansproject.Model;

public class Tank extends Cell {

    private Player player = null;
    private int damageLevel;

    public Tank(Player player) {
        this.player = player;

    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public String toString() {
        return "T";
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the damageLevel
     */
    public int getDamageLevel() {
        return damageLevel;
    }

    /**
     * @param damageLevel the damageLevel to set
     */
    public void setDamageLevel(int damageLevel) {
        this.damageLevel = damageLevel;
    }

}
