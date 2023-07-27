package hu.ppke.itk.ma;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class ButtonHBox extends HBox {

    private final int tileSize;

    /**
     * ButtonHBox constructor
     *  - Set the tile size (Button size)
     * @param tileSize The size of a button
     */
    public ButtonHBox(int tileSize){
        this.tileSize = tileSize;
    }

    /**
     * The ButtonHBox filler
     *  - Create buttons
     *  - Set the buttons size
     *  - Remove the blue border on the buttons
     *  - Add some graphic
     *  - Add each button to HBox
     */
    public void fillWithButtons(){
        for(int column = 0; column < GameCF.COLUMN_NUM; column++){
            Button button = new Button("Insert");
            button.setMinSize(this.tileSize, this.tileSize);
            button.setMaxSize(this.tileSize, this.tileSize);
            button.setFocusTraversable(false);
            button.setGraphic(new Circle(this.tileSize/4.0));
            button.setOpacity(0.4);
            button.setContentDisplay(ContentDisplay.BOTTOM);
            this.getChildren().add(button);
        }
    }
}
