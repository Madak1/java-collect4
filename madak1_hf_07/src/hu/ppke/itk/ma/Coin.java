package hu.ppke.itk.ma;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Coin extends Circle{

    private final boolean isRed;

    /**
     * Coin constructor
     *  - Create a red or yellow circle
     *  - Set the center to the center of the circle
     *  - Set the color
     * @param isRed The color is red or not (If not than yellow)
     * @param radius The radius of the circle
     */
    public Coin(boolean isRed, double radius){
        super(radius, isRed ? Color.FIREBRICK : Color.GOLD);
        this.setCenterX(radius);
        this.setCenterY(radius);
        this.isRed = isRed;
    }

    /**
     * Getter for isRed
     * @return The color is red or not (boolean)
     */
    public boolean isRed() {
        return isRed;
    }

}
