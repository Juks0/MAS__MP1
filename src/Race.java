import java.util.List;

public class Race {
    private final List<Player> Racers; //atr. powtarzalny

    private final double avgRaceTime ; // atr. pochodny

    public Race(List<Player> racers, double avgRaceTime) {
        Racers = racers;
        this.avgRaceTime = avgRaceTime;
    }

    public void addRacer(Player player) {
        Racers.add(player);
    }
}
