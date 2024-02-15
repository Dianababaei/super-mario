package model.brick;

import java.awt.image.BufferedImage;

public class SlimeBlock extends Brick {

    public SlimeBlock(double x, double y, BufferedImage style) {
        super(x, y, style);
        setBreakable(false);
        setEmpty(true);
    }
}
