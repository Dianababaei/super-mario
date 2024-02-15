package model.hero;

import acount.User;
import manager.Camera;
import manager.GameEngine;
import view.Animation;
import model.GameObject;
import view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mario extends GameObject{

    private int remainingLives;
    private int coins;
    private int points;
    private double invincibilityTimer;
    private MarioForm marioForm;
    private boolean toRight = true;
    int progressRate;
    public static double PR;


    public Mario(double x, double y, boolean isResumeGame, User user){
        super(x, y, null);
        setDimension(48,48);

        remainingLives = 3;
        if (isResumeGame){
            if (user.getRunningGames().size() != 0){
            remainingLives = user.getRunningGames().get(user.getRunningGames().size() - 1).getRemainingLives();
        }}

        points = 0;
        if (isResumeGame){
            if (user.getRunningGames().size() != 0){
            points = user.getRunningGames().get(user.getRunningGames().size() - 1).getScore();
        }}

        coins = 0;
        if (isResumeGame){
            if (user.getRunningGames().size() != 0){
            coins = user.getRunningGames().get(user.getRunningGames().size() - 1).getCoins();
        }}

        invincibilityTimer = 0;

        double progressRate = getX();
        double PR = progressRate * coins;

        ImageLoader imageLoader = new ImageLoader();
        BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.SMALL);
        BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.SMALL);

        ImageLoader imageLoader1 = new ImageLoader();
        BufferedImage[] leftFrames1 = imageLoader.getLeftFrames(MarioForm.SUPER);
        BufferedImage[] rightFrames1 = imageLoader.getRightFrames(MarioForm.SUPER);

        Animation animation = new Animation(leftFrames, rightFrames);

        marioForm = new MarioForm(animation, false, false);
        setStyle(marioForm.getCurrentStyle(toRight, false, false));
    }

    @Override
    public void draw(Graphics g){
        boolean movingInX = (getVelX() != 0);
        boolean movingInY = (getVelY() != 0);

        setStyle(marioForm.getCurrentStyle(toRight, movingInX, movingInY));

        super.draw(g);
    }

    public void jump(GameEngine engine, int i) {
        if(!isJumping() && !isFalling()){
            setJumping(true);
            setVelY(10);
            engine.playJump();
        }
    }

    public void move(boolean toRight, Camera camera) {
        if(toRight){
            setVelX(5);
        }
        else if(camera.getX() < getX()){
            setVelX(-5);
        }

        this.toRight = toRight;
    }

    public boolean onTouchEnemy(GameEngine engine){

        if(!marioForm.isSuper() && !marioForm.isFire()){
            remainingLives--;
            engine.playMarioDies();
            return true;
        }
        else if (marioForm.isFire()) {
            engine.shakeCamera();

            marioForm = marioForm.onTouchEnemy(engine.getImageLoader());
            setDimension(100, 100);
            ImageLoader imageLoader = new ImageLoader();
            BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.SUPER);
            BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.SUPER);

            Animation animation = new Animation(leftFrames, rightFrames);

            marioForm = new MarioForm(animation, true, false);


        }
        else if (marioForm.isSuper()){
            engine.shakeCamera();
            marioForm = marioForm.onTouchEnemy(engine.getImageLoader());
            setDimension(48, 48);

        }
        return false;
    }


    public Fireball fire(){
        return marioForm.fire(toRight, getX(), getY());
    }

    public void acquireCoin() {
        coins++;
    }

    public void acquirePoints(int point){
        points = points + point;
    }

    public int getRemainingLives() {
        return remainingLives;
    }

    public void setRemainingLives(int remainingLives) {
        this.remainingLives = remainingLives;
    }

    public int getPoints() {
        return points;
    }

    public int getCoins() {
        return coins;
    }

    public MarioForm getMarioForm() {
        return marioForm;
    }

    public void setMarioForm(MarioForm marioForm) {
        this.marioForm = marioForm;
    }

    public boolean isSuper() {
        return marioForm.isSuper();
    }

    public boolean getToRight() {
        return toRight;
    }

    public void resetLocation() {
        setVelX(0);
        setVelY(0);
        setX(50);
        setJumping(false);
        setFalling(true);
    }
}
