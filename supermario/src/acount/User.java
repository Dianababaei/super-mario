package acount;

import java.util.ArrayList;

public class User {

    private String name;
    private String password;
    private int remainingLives;
    private int remainingTime;
    private int coins;
    private int totalScore;
    private int killedEnemy;
    private int Score;
    private boolean place;
    private ArrayList<GameState> runningGames = new ArrayList<>();

    private int highestScore;
    private String PathJsonFile;


    public User(String name, String password, ArrayList<GameState> runningGames) {
        this.name = name;
        this.password = password;
        this.runningGames = runningGames;
    }

    public User() {
    }

    public int getRemainingLives() {
        return remainingLives;
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

    public int getKilledEnemy() {
        return killedEnemy;
    }

    public void setKilledEnemy(int killedEnemy) {
        this.killedEnemy = killedEnemy;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public boolean isPlace() {
        return place;
    }

    public void setPlace(boolean place) {
        this.place = place;
    }


    public String getPathJsonFile() {
        return PathJsonFile;
    }

    public void setPathJsonFile(String pathJsonFile) {
        PathJsonFile = pathJsonFile;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<GameState> getRunningGames() {
        return runningGames;
    }

    public void setRunningGames(ArrayList<GameState> runningGames) {
        this.runningGames = runningGames;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", remainingLives=" + remainingLives +
                ", remainingTime=" + remainingTime +
                ", coins=" + coins +
                ", totalScore=" + totalScore +
                ", killedEnemy=" + killedEnemy +
                ", Score=" + Score +
                ", place=" + place +
                ", runningGames=" + runningGames +
                ", highestScore=" + highestScore +
                ", PathJsonFile='" + PathJsonFile + '\'' +
                '}';
    }
}
