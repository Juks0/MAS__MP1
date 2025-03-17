import java.util.ArrayList;
import java.util.List;

public class Race extends ObjectPlus{
    private final List<Racer> racers = new ArrayList<>(); //atr. powtarzalny
    private int raceId;
    private static int raceCounter = 0;
    private String trackName;
    private  double avgBestLapTime ; // atr. pochodny


    public Race(String trackName, List<Racer> racers) {
        setRaceId();
        setTrackName(trackName);
        addRacers(racers);
        setRecentRaceForPlayers(racers);
    }

    public static Race getRaceById(int id) {
       return ObjectPlus.getExtentFromClass(Race.class).stream()
               .filter(race -> race.getRaceId()==id)
               .findFirst().orElse(null);
    }

    public int getRaceId() {
        return raceId;
    }

    public void addRacer(Racer Racer) {
        racers.add(Racer);
        countAvgBestLapTime();

    }
    public void addRacers(List<Racer> racers) {
        this.racers.addAll(racers);
        countAvgBestLapTime();
    }
    public void setRecentRaceForPlayers(List<Racer> racers) {
        for (Racer racer : racers) {
            racer.setRecenetRace(this);
        }
    }
    public void setTrackName(String trackName) {
        if(trackName == null || trackName.isBlank()) {
            throw new IllegalArgumentException("Track name cannot be null or blank");
        }
        this.trackName = trackName;
    }
    public void removeRacer(Racer Racer) {
        if(racers.contains(Racer)) {
            racers.remove(Racer);
        }else
            throw new IllegalArgumentException("Racer not found in that race");
    }

    public void setRaceId() {
        raceCounter++;
        if (raceId < 0){
            throw new IllegalArgumentException("Race ID cannot be negative");
        }

        raceId = raceCounter;
    }


    public void countAvgBestLapTime() { // atr. pochodny
        double avgBestLapTimeTemp = 0;
        int counter = 0;
        for(Racer racer : racers) {
            counter++;
            avgBestLapTimeTemp+= racer.getFastestLapTime();
        }
        avgBestLapTime= avgBestLapTimeTemp/counter;
    }

    public List<Racer> getRacers() {
        return racers;
    }

    public double getAvgBestLapTime() {
        return avgBestLapTime;
    }

    public String getTrackName() {
        return trackName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Race ").append(raceId).append(": ").append("\n");
        sb.append("Total Racers: ").append(racers.size()).append("\n");
        for (Racer racer : racers) {
            sb.append(racer.getNickname()).append(", ");  // Avoid calling racer.toString() directly
        }
        sb.append("\n");
        sb.append("Avg Best Lap Time: ").append(avgBestLapTime).append("\n");
        return sb.toString();
    }
}
