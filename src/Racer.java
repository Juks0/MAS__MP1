import java.io.Serializable;

public class Racer implements Serializable {
    private String wheel;
    private String baseNm;
    private String pedals;
    private int lvl;

    Account account; // atr. złożony
    private static String handbrake; // atr. opcjonalny


    public Racer(String wheel, String baseNm, String pedals, int lvl) {
        setWheel(wheel);
        setBaseNm(baseNm);
        setPedals(pedals);
        setLvl(lvl);
    }
    public void setWheel(String wheel) {
        if(wheel == null || wheel.isBlank()){
            throw new IllegalArgumentException("Wheel cannot be null or blank");
        }
        this.wheel = wheel;
    }

    public void setBaseNm(String baseNm) {
        if(baseNm == null || baseNm.isBlank()){
            throw new IllegalArgumentException("Base name cannot be null or blank");
        }
        this.baseNm = baseNm;
    }

    public void setPedals(String pedals) {
        if(pedals == null || pedals.isBlank()){
            throw new IllegalArgumentException("Pedals cannot be null or blank");
        }
        this.pedals = pedals;
    }

    public void setLvl(int lvl) {
        if(lvl < 0){
            throw new IllegalArgumentException("Level cannot be negative");
        }
        this.lvl = lvl;
    }

    public String getWheel() {
        return wheel;
    }

    public String getBaseNm() {
        return baseNm;
    }

    public String getPedals() {
        return pedals;
    }

    public int getLvl() {
        return lvl;
    }
}
