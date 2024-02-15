package manager;

import acount.User;
import model.EndFlag;
import model.Map;
import model.brick.*;
import model.enemy.*;
import model.hero.Mario;
import model.prize.*;
import view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

class MapCreator {

    private ImageLoader imageLoader;

    private BufferedImage backgroundImage;
    private BufferedImage superMushroom, star, fireFlower, coin;
    private BufferedImage ordinaryBrick, surpriseBrick, groundBrick,coinBrick, pipe;
    private BufferedImage goombaLeft, goombaRight, koopaLeft, koopaRight, endFlag, hidePipe;
    private BufferedImage enemyFlower, checkPoint,spinyRight,spinyLeft,emptyBrick,bird,slimeBlock;

    GameEngine engine;


    MapCreator(ImageLoader imageLoader) {

        this.imageLoader = imageLoader;
        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage EnemyFlower = imageLoader.loadImage("/enemy.flower.png");
        BufferedImage checkPoint = imageLoader.loadImage("/Checkpoint.png");
        BufferedImage hidePipe= imageLoader.loadImage("/PipeAssets.png");

        this.backgroundImage = imageLoader.loadImage("/background.png");
        this.spinyLeft = imageLoader.loadImage("/Spiny.png");
        this.spinyRight = imageLoader.loadImage("/Spiny.png");
        this.superMushroom = imageLoader.getSubImage(sprite, 2, 5, 48, 48);
        this.enemyFlower = imageLoader.loadImage("/enemy.flower.png");
        this.bird = imageLoader.loadImage("/NukeBird.png");
        this.slimeBlock = imageLoader.loadImage("/Slime.png");
        this.checkPoint = imageLoader.loadImage("/Checkpoint.png");
        this.star= imageLoader.getSubImage(sprite, 5, 5, 48, 48);
        //this.hidePipe=hidePipe.getSubimage( 0, 0, 96, 96);
        this.hidePipe = imageLoader.loadImage("/PipeAssets.png");
        this.fireFlower= imageLoader.getSubImage(sprite, 4, 5, 48, 48);
        this.emptyBrick= imageLoader.getSubImage(sprite, 1, 2, 48, 48);
        this.coin = imageLoader.getSubImage(sprite, 1, 5, 48, 48);
        this.ordinaryBrick = imageLoader.getSubImage(sprite, 1, 1, 48, 48);
        this.coinBrick = imageLoader.getSubImage(sprite, 1, 1, 48, 48);
        this.surpriseBrick = imageLoader.getSubImage(sprite, 2, 1, 48, 48);
        this.groundBrick = imageLoader.getSubImage(sprite, 2, 2, 48, 48);
        this.pipe = imageLoader.getSubImage(sprite, 3, 1, 96, 96);
        this.goombaLeft = imageLoader.getSubImage(sprite, 2, 4, 48, 48);
        this.goombaRight = imageLoader.getSubImage(sprite, 5, 4, 48, 48);
        this.koopaLeft = imageLoader.getSubImage(sprite, 1, 3, 48, 64);
        this.koopaRight = imageLoader.getSubImage(sprite, 4, 3, 48, 64);
        this.endFlag = imageLoader.getSubImage(sprite, 5, 1, 48, 48);

    }

    Map createMap(String mapPath, boolean isResumeGame, User user) {
        BufferedImage mapImage = imageLoader.loadImage(mapPath);

        if (mapImage == null) {
            System.out.println("Given path is invalid...");
            return null;
        }
        double timeLimit = 300;
        if (isResumeGame){
            if (user.getRunningGames().size() != 0){
            timeLimit = user.getRunningGames().get(user.getRunningGames().size() - 1).getRemainingTime();
        }}


        Map createdMap = new Map(timeLimit, backgroundImage);
        String[] paths = mapPath.split("/");
        createdMap.setPath(paths[paths.length-1]);

        int pixelMultiplier = 48;

        int mario = new Color(160, 160, 160).getRGB();
        int emptyBrick = new Color(200, 191, 231).getRGB();
        int ordinaryBrick = new Color(0, 0, 255).getRGB();
        int checkPoint = new Color(112, 146, 190).getRGB();
        int surpriseBrick = new Color(255, 255, 0).getRGB();
        int groundBrick = new Color(255, 0, 0).getRGB();
        int pipe = new Color(0, 255, 0).getRGB();
        int goomba = new Color(0, 255, 255).getRGB();
        int hidePipe = new Color(255, 127, 39).getRGB();
        int koopa = new Color(255, 0, 255).getRGB();
        int bird = new Color(136, 0, 21).getRGB();
        int slimeBlock = new Color(239, 228, 176).getRGB();
        int end = new Color(160, 0, 160).getRGB();
        int spiny = new Color(163, 73, 164).getRGB();

        for (int x = 0; x < mapImage.getWidth(); x++) {
            for (int y = 0; y < mapImage.getHeight(); y++) {

                int currentPixel = mapImage.getRGB(x, y);
                int xLocation = x*pixelMultiplier;
                int yLocation = y*pixelMultiplier;


                if (currentPixel == ordinaryBrick) {
                    int random = (int)(Math.random() * 6);
                    if (random == 1){
                        OrdinaryBrick.emptyBrick = true;
                        OrdinaryBrick.setEmptyBrick(engine);
                        //TODO WHATS HAPPENING?

                    }
                    else if (random == 0 ){
                        Prize prize = generateRandomCoin(xLocation, yLocation);
                        Brick brick = new OrdinaryBrick(xLocation, yLocation, this.ordinaryBrick,prize);
                        createdMap.addBrick(brick);
                    }
                    else {
                        Prize prize = generateRandomCoin(xLocation, yLocation);
                        Brick brick = new OrdinaryBrick(xLocation, yLocation, this.ordinaryBrick, prize);
                        createdMap.addBrick(brick);

                    }
                }
                else if (currentPixel == checkPoint) {
                    Brick brick1 = new CheckPoint(xLocation,yLocation-80,this.checkPoint);
                    createdMap.addBrick(brick1);

                } else if (currentPixel == slimeBlock) {
                    Brick brick = new SlimeBlock(xLocation, yLocation, this.slimeBlock);
                    createdMap.addBrick(brick);

                } else if (currentPixel == bird) {
                    Enemy enemy = new NukeBird(xLocation, yLocation-700, this.bird);
                    createdMap.addEnemy(enemy);

                } else if (currentPixel == emptyBrick) {
                    Brick brick = new EmptyBrick(xLocation, yLocation, this.emptyBrick);
                    createdMap.addBrick(brick);

                } else if (currentPixel == spiny) {
                    Enemy enemy = new Spiny(xLocation, yLocation, this.spinyLeft);
                    ((Spiny)enemy).setRightImage(spinyRight);
                    createdMap.addEnemy(enemy);

                } else if (currentPixel == surpriseBrick) {
                    Prize prize = generateRandomPrize(xLocation, yLocation);
                    Brick brick = new SurpriseBrick(xLocation, yLocation, this.surpriseBrick, prize);
                    createdMap.addBrick(brick);
                }
                else if (currentPixel == pipe) {
                    Brick brick = new Pipe(xLocation, yLocation, this.pipe);
                    createdMap.addGroundBrick(brick);
                    int random = (int)(Math.random() * 4);
                    if (random == 1){
                        Enemy enemy = new Flower(xLocation+30,yLocation,this.enemyFlower);
                        createdMap.addEnemy(enemy);
                    }
                }
                else if (currentPixel == groundBrick) {
                    Brick brick = new GroundBrick(xLocation, yLocation, this.groundBrick);
                    createdMap.addGroundBrick(brick);
                }
                else if (currentPixel == groundBrick) {
                    Brick brick = new GroundBrick(xLocation, yLocation, this.groundBrick);
                    createdMap.addGroundBrick(brick);
                }
                else if (currentPixel == goomba) {
                    Enemy enemy = new Goomba(xLocation, yLocation, this.goombaLeft);
                    ((Goomba)enemy).setRightImage(goombaRight);
                    createdMap.addEnemy(enemy);
                }
                else if (currentPixel == koopa) {
                    Enemy enemy = new KoopaTroopa(xLocation, yLocation, this.koopaLeft);
                    ((KoopaTroopa)enemy).setRightImage(koopaRight);
                    createdMap.addEnemy(enemy);
                }
                else if (currentPixel == mario) {
                    Mario marioObject = new Mario(xLocation, yLocation,isResumeGame,user);
                    createdMap.setMario(marioObject);
                }
                else if(currentPixel == end){
                    EndFlag endPoint= new EndFlag(xLocation+24, yLocation, endFlag);
                    createdMap.setEndPoint(endPoint);
                }
            }
        }

        return createdMap;
    }


    private Prize generateRandomPrize(double x, double y){
        Prize generated;
        int random = (int)(Math.random() * 12);

        if(random == 0){ //super mushroom
            generated = new SuperMushroom(x, y, this.superMushroom);
        }
        else if(random == 1){ //fire flower
            generated = new Coin(x, y, this.coin, 50);
        }
        else if(random == 2){ //one up mushroom
            generated = new Star(x, y, this.star);
        }
        else{ //coin
            generated = new FireFlower(x, y, this.fireFlower);

        }

        return generated;
    }

    private Prize generateRandomCoin(double x, double y){
        Prize generated;
        //int random = (int)(Math.random() * 3);


      //  if (random == 1){ //coin
            generated = new Coin(x, y, this.coin, 50);
      //  }
      //  else {
         // generated = null;
      //  }

        return generated;
    }




}
