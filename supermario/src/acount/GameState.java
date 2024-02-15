package acount;

public class GameState {
    private int remainingLives;
    private int remainingTime ;
    private int coins;
    private int totalScore ;
    private boolean place;
    private int killedEnemy;
    private int Score;

    public GameState(int remainingLives, int remainingTime, int coins, int Score) {
        this.remainingTime = remainingTime;
        this.remainingLives = remainingLives;
        this.coins = coins;
        this.Score = Score;
    }


   /* public GameState(int highestScore, int totalScore) {
        this.highestScore = highestScore;
        this.totalScore = totalScore;
    } */

    public int getRemainingLives() {
        return remainingLives;
    }

    public void setLives(int lives) {
        this.remainingLives = lives;
    }


    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public boolean getPlace() {
        return place;
    }

    public void setPlace(int place) {
        //this.place = Mario.getisInFirstSection();
    }

    public int getKilledEnemy() {
        return killedEnemy;
    }

    public void setKilledEnemy(int killedEnemy) {
        this.killedEnemy = killedEnemy;
    }

    public void setRemainingLives(int remainingLives) {
       this.remainingLives = remainingLives;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }


    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int totalScore (int remainingLives, int coins, int remainingTime, int points) {
        totalScore = remainingTime + points + remainingLives * 20;

        return totalScore;
    }


    public boolean isPlace() {
        return place;
    }

    public void setPlace(boolean place) {
        this.place = place;
    }
}
