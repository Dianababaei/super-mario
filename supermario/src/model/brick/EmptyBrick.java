package model.brick;

import java.awt.image.BufferedImage;

 public class EmptyBrick extends Brick {

     public EmptyBrick(double x, double y, BufferedImage style) {
         super(x, y, style);
         setBreakable(false);
         setEmpty(true);
     }
 }
