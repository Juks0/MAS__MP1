import java.io.Serializable;
import java.util.List;

public class Racer extends Account implements Serializable  {
    private String wheel;
    private String pedals;
    private double fastestLapTime;
    private Race recenetRace; // atr. złożony
    private static String handbrake; // atr. opcjonalny


    public Racer(String nickname,String wheel, String pedals) {
        super(nickname);
        setWheel(wheel);
        setPedals(pedals);
    }
    public Racer(String nickname,String wheel, String pedals, String handbrake) { // atr. opcjonalny
        super(nickname);
        setWheel(wheel);
        setPedals(pedals);
        setHandbrake(handbrake);
    }
    public void setWheel(String wheel) {
        if(wheel == null || wheel.isBlank()){
            throw new IllegalArgumentException("Wheel cannot be null or blank");
        }
        this.wheel = wheel;
    }

    public void setPedals(String pedals) {
        if(pedals == null || pedals.isBlank()){
            throw new IllegalArgumentException("Pedals cannot be null or blank");
        }
        this.pedals = pedals;
    }

    public static void setHandbrake(String handbrake) {
        if(handbrake == null || handbrake.isBlank()){
            throw new IllegalArgumentException("Handbrake cannot be null or blank");
        }
        Racer.handbrake = handbrake;
    }

    @Override
    public void removeMoney(double money){
        if (money <0){
            throw new IllegalArgumentException("Money cannot be negative or NaN");
        }
        if (balance - money < 0){
            throw new IllegalArgumentException("Not enough money");
        }
        balance -= money*0.9; // Racers have 10% disscount
    }

    public String getWheel() {
        return wheel;
    }

    public String getPedals() {
        return pedals;
    }

    public static String getHandbrake() {
        return handbrake;
    }

    public double getFastestLapTime() {
        return fastestLapTime;
    }

    public void setFastestLapTime(double fastestLapTime) {
        this.fastestLapTime = fastestLapTime;
    }

    public void setRecenetRace(Race recenetRace) {
        if (recenetRace == null){
            throw new IllegalArgumentException("RecenetRace cannot be null");
        }
        this.recenetRace = recenetRace;
    }

    @Override
    public String toString() {
        return "Racer: " + getNickname() + "\n" +
                "Wheel: " + wheel + "\n" +
                "Pedals: " + pedals + "\n" +
                "Fastest Lap Time: " + fastestLapTime + "\n" +
                (recenetRace != null ? "Recent Race: " + recenetRace.getTrackName() + "\n" : "") +
                (handbrake != null ? "Handbrake: " + handbrake + "\n" : "")+
                "Balance: " + getBalance() + "\n";
    }
}
