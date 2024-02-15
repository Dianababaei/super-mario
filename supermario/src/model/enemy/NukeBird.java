package model.enemy;

import java.awt.image.BufferedImage;

public class NukeBird extends Enemy {

        public NukeBird(double x, double y, BufferedImage style) {
            super(x, y, style);
            setGravityAcc(0.0);
            setJumping(false);
            setFalling(false);
            setVelY(0);

        }
    }


