package manager;

import acount.GameState;
import acount.User;

public class ResumeGame extends GameEngine {
    private  GameState previousGame;
    public ResumeGame(User user) {
        super(user);
        this.setResumeGame(true);
        if (user.getRunningGames().size() != 0){
        previousGame = user.getRunningGames().get(user.getRunningGames().size() - 1);
    }}

    public GameState getPreviousGame() {
        return previousGame;
    }
}
