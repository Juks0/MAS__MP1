public class Account {
    private double balance;
    private int lvl;


    public void setBalance(double balance) {
        if (balance <0){
            throw new IllegalArgumentException("Balance cannot be negative or NaN");
        }
        this.balance = balance;
    }


    public void setLvl(int lvl) {
        if (lvl < 1){
            throw new IllegalArgumentException("Level cannot be negative");
        }
        this.lvl = lvl;
    }

    public double getBalance() {
        return balance;
    }
    public int getLvl() {
        return lvl;
    }
}
