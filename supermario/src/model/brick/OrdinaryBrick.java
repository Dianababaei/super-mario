package model.brick;

import manager.GameEngine;
import manager.MapManager;
import model.Map;
import model.prize.Prize;
import view.Animation;
import view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OrdinaryBrick extends Brick {

    public static boolean emptyBrick;
    private Animation animation;
    private boolean breaking;
    private int frames;
    private Prize prize;

    public OrdinaryBrick(double x, double y, BufferedImage style, Prize prize){
        super(x, y, style);
        setBreakable(true);
        setEmpty(false);
        this.prize = prize;

        setAnimation();
        breaking = false;
        frames = animation.getLeftFrames().length;
    }
    public static void setEmptyBrick(GameEngine engine){
        if( emptyBrick == true){

            BufferedImage newStyle = engine.getImageLoader().loadImage("/sprite.png");
            newStyle = engine.getImageLoader().getSubImage(newStyle, 1, 2, 48, 48);
        }
        //TODO bbin cheshe
    }

   private void setAnimation(){
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage[] leftFrames = imageLoader.getBrickFrames();
        animation = new Animation(leftFrames, leftFrames);
    }

   /* @Override
    public Prize reveal(GameEngine engine){
        MapManager manager = engine.getMapManager();
        if(!manager.getMario().isSuper())
            return null;

        breaking = true;
        manager.addRevealedBrick(this);

        double newX = getX() - 27, newY = getY() - 27;
        setLocation(newX, newY);

        return null;
    } */

    public int getFrames(){
        return frames;
    }

    public void animate(){
        if(breaking){
            setStyle(animation.animate(3, true));
            frames--;
        }
    }
  public Prize reveal(GameEngine engine){
      BufferedImage newStyle = engine.getImageLoader().loadImage("/sprite.png");
      newStyle = engine.getImageLoader().getSubImage(newStyle, 1, 2, 48, 48);

      if(prize != null){
          prize.reveal();
      }

      setEmpty(true);
      setStyle(newStyle);

      Prize toReturn = this.prize;
      this.prize = null;
      return toReturn;
  }

    @Override
    public Prize getPrize(){
        return prize;
    }
}
