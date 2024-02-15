package manager;

import acount.User;
import model.GameObject;
import model.Map;
import model.brick.*;
import model.enemy.Enemy;
import model.hero.Fireball;
import model.hero.Mario;
import model.prize.BoostItem;
import model.prize.Coin;
import model.prize.Prize;
import view.CheckpointCheck;
import view.ImageLoader;

import java.awt.*;
import java.util.ArrayList;

public class MapManager {

    private static Map map;
    private User user;
    int counter = 0 ;
    public MapManager() {

    }

    public void updateLocations() {
        if (map == null)
            return;

        map.updateLocations();
    }


    //TODO for resume you have to make this right
    //TODO you change this again
    private User MAPUser;
    public void resetCurrentMap(GameEngine engine, User user) {
        this.MAPUser = user;
        Mario mario = getMario();
        mario.resetLocation();
        engine.resetCamera();
        //TODO check this you change this
        createMap(engine.getImageLoader(), map.getPath(), false, user);
        map.setMario(mario);
    }


    public void fire(GameEngine engine) {
        Fireball fireball = getMario().fire();
        if (fireball != null) {
            map.addFireball(fireball);
            engine.playFireball();
        }
    }


    //TODO you change this variable for testing
    //TODO you add user to the constructor again
    public boolean createMap(ImageLoader loader, String path, boolean isResumeGame, User user) {
        MapCreator mapCreator = new MapCreator(loader);
        //TODO this is the section

        map = mapCreator.createMap("/maps/" + path, isResumeGame , user);

        return map != null;
    }

    public void acquirePoints(int point) {
        map.getMario().acquirePoints(point);
    }

    public Mario getMario() {
        return map.getMario();
    }


    public boolean isGameOver() {
        return getMario().getRemainingLives() == 0 || map.isTimeOver();
    }

    public int getScore() {
        return getMario().getPoints();
    }
   /* public void setScore(int points) {
        getMario().setPoints(points);
    } */

    public int getRemainingLives() {
        return getMario().getRemainingLives();
    }

    public int getCoins() {
        return getMario().getCoins();
    }

    public void drawMap(Graphics2D g2) {
        map.drawMap(g2);
    }

    public int passMission() {
        if(getMario().getX() >= map.getEndPoint().getX() && !map.getEndPoint().isTouched()){
            map.getEndPoint().setTouched(true);
            int height = (int)getMario().getY();
            return height * 2;
        }
        else
            return -1;
    }

    public boolean endLevel(){
        return getMario().getX() >= map.getEndPoint().getX() + 320;
    }

    public void checkCollisions(GameEngine engine) {
        if (map == null) {
            return;
        }

        checkBottomCollisions(engine);
        checkTopCollisions(engine);
        checkMarioHorizontalCollision(engine);
        checkEnemyCollisions();
        checkPrizeCollision();
        checkPrizeContact(engine);
        checkFireballContact();
    }

    private void checkBottomCollisions(GameEngine engine) {
        Mario mario = getMario();
        ArrayList<Brick> bricks = map.getAllBricks();
        ArrayList<Enemy> enemies = map.getEnemies();
        ArrayList<GameObject> toBeRemoved = new ArrayList<>();
        Rectangle marioBottomBounds = mario.getBottomBounds();

        if (!mario.isJumping())
            mario.setFalling(true);

        for (Brick brick : bricks) {
            Rectangle brickTopBounds = brick.getTopBounds();
            if (marioBottomBounds.intersects(brickTopBounds)) {

                if (brick instanceof HidePipe){
                    counter++;
                    if (counter == 1){
                        mario.resetLocation();
                        engine.resetCamera();
                        createMap(engine.getImageLoader(),"Map 3.png",false, user);
                        map.setMario(mario);
                        //  time = (int)map.getRemainingTime();

                    }
                    if (counter == 2){

                        createMap(engine.getImageLoader(),"Map 2,r.png",false,user);
                        map.setMario(mario);
                        mario.setLocation(4600, 500);
                        // map.setRemainingTime(time);
                    }

                }
                else if (brick instanceof CheckPoint) {
                    CheckpointCheck checkpointCheck = new CheckpointCheck();

                } else if (brick instanceof SlimeBlock) {
                    mario.setY(brick.getY() - mario.getDimension().height + 1);
                    mario.setFalling(false);
                    mario.jump(engine,20);
                    engine.playJump();
                } else{

                    mario.setY(brick.getY() - mario.getDimension().height + 1);
                    mario.setFalling(false);
                    mario.setVelY(0);
                }
            }
        }


        if (mario.getY() + mario.getDimension().height >= map.getBottomBorder()) {
            mario.setY(map.getBottomBorder() - mario.getDimension().height);
            mario.setFalling(false);
            mario.setVelY(0);
        }

        removeObjects(toBeRemoved);
    }

    private void checkTopCollisions(GameEngine engine) {
        Mario mario = getMario();
        ArrayList<Brick> bricks = map.getAllBricks();

        Rectangle marioTopBounds = mario.getTopBounds();
        for (Brick brick : bricks) {
            Rectangle brickBottomBounds = brick.getBottomBounds();
            if (marioTopBounds.intersects(brickBottomBounds)) {
                mario.setVelY(0);
                mario.setY(brick.getY() + brick.getDimension().height);
                Prize prize = brick.reveal(engine);
                if(prize != null)
                    map.addRevealedPrize(prize);
            }
        }
    }

    private void checkMarioHorizontalCollision(GameEngine engine){
        Mario mario = getMario();
        ArrayList<Brick> bricks = map.getAllBricks();
        ArrayList<GameObject> toBeRemoved = new ArrayList<>();
        ArrayList<Enemy> enemies = map.getEnemies();


        boolean marioDies = false;
        boolean toRight = mario.getToRight();

        Rectangle marioBounds = toRight ? mario.getRightBounds() : mario.getLeftBounds();

        for (Brick brick : bricks) {
            Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
            if (marioBounds.intersects(brickBounds)) {
                mario.setVelX(0);
                if(toRight)
                    mario.setX(brick.getX() - mario.getDimension().width);
                else
                    mario.setX(brick.getX() + brick.getDimension().width);
            }
        }
        for (Enemy enemy : enemies){
            Rectangle enemyBounds = !toRight ? enemy.getRightBounds() : enemy.getLeftBounds();
            if (marioBounds.intersects(enemyBounds)){
                marioDies = mario.onTouchEnemy(engine);
                toBeRemoved.add(enemy);
            }
        }
        removeObjects(toBeRemoved);


        if (mario.getX() <= engine.getCameraLocation().getX() && mario.getVelX() < 0) {
            mario.setVelX(0);
            mario.setX(engine.getCameraLocation().getX());
        }

        if(marioDies) {
            resetCurrentMap(engine, MAPUser);
        }
    }

    private void checkEnemyCollisions() {
        ArrayList<Brick> bricks = map.getAllBricks();
        ArrayList<Enemy> enemies = map.getEnemies();

        for (Enemy enemy : enemies) {
            boolean standsOnBrick = false;

            for (Brick brick : bricks) {
                Rectangle enemyBounds = enemy.getLeftBounds();
                Rectangle brickBounds = brick.getRightBounds();

                Rectangle enemyBottomBounds = enemy.getBottomBounds();
                Rectangle brickTopBounds = brick.getTopBounds();

                if (enemy.getVelX() > 0) {
                    enemyBounds = enemy.getRightBounds();
                    brickBounds = brick.getLeftBounds();
                }

                if (enemyBounds.intersects(brickBounds)) {
                    enemy.setVelX(-enemy.getVelX());
                }

                if (enemyBottomBounds.intersects(brickTopBounds)){
                    enemy.setFalling(false);
                    enemy.setVelY(0);
                    enemy.setY(brick.getY()-enemy.getDimension().height);
                    standsOnBrick = true;
                }
            }

            if(enemy.getY() + enemy.getDimension().height > map.getBottomBorder()){
                enemy.setFalling(false);
                enemy.setVelY(0);
                enemy.setY(map.getBottomBorder()-enemy.getDimension().height);
            }

            if (!standsOnBrick && enemy.getY() < map.getBottomBorder()){
                enemy.setFalling(true);
            }
        }
    }

    private void checkPrizeCollision() {
        ArrayList<Prize> prizes = map.getRevealedPrizes();
        ArrayList<Brick> bricks = map.getAllBricks();

        for (Prize prize : prizes) {
            if (prize instanceof BoostItem) {
                BoostItem boost = (BoostItem) prize;
                Rectangle prizeBottomBounds = boost.getBottomBounds();
                Rectangle prizeRightBounds = boost.getRightBounds();
                Rectangle prizeLeftBounds = boost.getLeftBounds();
                boost.setFalling(true);

                for (Brick brick : bricks) {
                    Rectangle brickBounds;

                    if (boost.isFalling()) {
                        brickBounds = brick.getTopBounds();

                        if (brickBounds.intersects(prizeBottomBounds)) {
                            boost.setFalling(false);
                            boost.setVelY(0);
                            boost.setY(brick.getY() - boost.getDimension().height + 1);
                            if (boost.getVelX() == 0)
                                boost.setVelX(2);
                        }
                    }

                    if (boost.getVelX() > 0) {
                        brickBounds = brick.getLeftBounds();

                        if (brickBounds.intersects(prizeRightBounds)) {
                            boost.setVelX(-boost.getVelX());
                        }
                    } else if (boost.getVelX() < 0) {
                        brickBounds = brick.getRightBounds();

                        if (brickBounds.intersects(prizeLeftBounds)) {
                            boost.setVelX(-boost.getVelX());
                        }
                    }
                }

                if (boost.getY() + boost.getDimension().height > map.getBottomBorder()) {
                    boost.setFalling(false);
                    boost.setVelY(0);
                    boost.setY(map.getBottomBorder() - boost.getDimension().height);
                    if (boost.getVelX() == 0)
                        boost.setVelX(2);
                }

            }
        }
    }

    private void checkPrizeContact(GameEngine engine) {
        ArrayList<Prize> prizes = map.getRevealedPrizes();
        ArrayList<GameObject> toBeRemoved = new ArrayList<>();

        Rectangle marioBounds = getMario().getBounds();
        for(Prize prize : prizes){
            Rectangle prizeBounds = prize.getBounds();
            if (prizeBounds.intersects(marioBounds)) {
                prize.onTouch(getMario(), engine);
                toBeRemoved.add((GameObject) prize);
            } else if(prize instanceof Coin){
                prize.onTouch(getMario(), engine);
            }
        }

        removeObjects(toBeRemoved);
    }

    private void checkFireballContact() {
        ArrayList<Fireball> fireballs = map.getFireballs();
        ArrayList<Enemy> enemies = map.getEnemies();
        ArrayList<Brick> bricks = map.getAllBricks();
        ArrayList<GameObject> toBeRemoved = new ArrayList<>();

        for(Fireball fireball : fireballs){
            Rectangle fireballBounds = fireball.getBounds();

            for(Enemy enemy : enemies){
                Rectangle enemyBounds = enemy.getBounds();
                if (fireballBounds.intersects(enemyBounds)) {
                    acquirePoints(100);
                    toBeRemoved.add(enemy);
                    toBeRemoved.add(fireball);
                }
            }

            for(Brick brick : bricks){
                Rectangle brickBounds = brick.getBounds();
                if (fireballBounds.intersects(brickBounds)) {
                    toBeRemoved.add(fireball);
                }
            }
        }

        removeObjects(toBeRemoved);
    }

    private void removeObjects(ArrayList<GameObject> list){
        if(list == null)
            return;

        for(GameObject object : list){
            if(object instanceof Fireball){
                map.removeFireball((Fireball)object);
            }
            else if(object instanceof Enemy){
                map.removeEnemy((Enemy)object);
            }
            else if(object instanceof Coin || object instanceof BoostItem){
                map.removePrize((Prize)object);
            }
        }
    }


    public void addRevealedBrick(OrdinaryBrick ordinaryBrick) {
        map.addRevealedBrick(ordinaryBrick);
    }

    public void updateTime(){
        if(map != null)
            map.updateTime(1);
    }

    public int getRemainingTime() {
        return (int)map.getRemainingTime();
    }
}
