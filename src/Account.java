import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Account extends ObjectPlus{

    private static List<Account> allPlayers; // atr. powtarzalny
    protected double balance=0;
    private int lvl=1;

    private int gamerID;
    private String nickname;
    private static final int maxLVL =21; // atr. klasowy


    public Account(String nickname) {
        try{
        setNickname(nickname);
        if(allPlayers == null){
            allPlayers = new ArrayList<>();
        }
        setGamerID(allPlayers.size()+1);
        allPlayers.add(this);
    }catch (Exception e){
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public static Account findPlayerByNickname(String nickname) throws ClassNotFoundException, IOException { //metoda klasowa
       return ObjectPlus.getExtentFromClass(Racer.class)
               .stream().filter(account -> account.getNickname().equals(nickname))
               .findFirst().orElse(null);
    }

    public void setBalance(double balance) {
        if (balance <0){
            throw new IllegalArgumentException("Balance cannot be negative or NaN");
        }
        this.balance = balance;
    }

    public void lvlUp(){
        if (lvl < maxLVL)
            lvl++;
        else
            throw new IllegalArgumentException("You are already at max level");
    }

    public void addMoney(double money){
        if (money <0){
            throw new IllegalArgumentException("Money cannot be negative or NaN");
        }
        balance += money;
    }
    public void removeMoney(double money){
        if (money <0){
            throw new IllegalArgumentException("Money cannot be negative or NaN");
        }
        if (balance - money < 0){
            throw new IllegalArgumentException("Not enough money");
        }
        balance -= money;
    }
    public void setNickname(String nickname) {
        if (nickname == null || nickname.isBlank()){
            throw new IllegalArgumentException("Nickname cannot be null or blank");
        }
        this.nickname = nickname;
    }

    public void setGamerID(int gamerID) {
        if(gamerID < 0){
            throw new IllegalArgumentException("GamerID cannot be negative");
        }
        this.gamerID = gamerID;
    }
    public double getBalance() {
        return balance;
    }
    public int getLvl() {
        return lvl;
    }

    public int getGamerID() {
        return gamerID;
    }

    public String getNickname() {
        return nickname;
    }

    public static List<Account> getAllPlayers() {
        return allPlayers;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", lvl=" + lvl +
                ", gamerID=" + gamerID +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
