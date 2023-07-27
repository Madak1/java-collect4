package hu.ppke.itk.ma;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class DescriptionCF extends VBox {

    /**
     * Description constructor
     *  - Set the alignment, spacing and padding for the VBox
     *  - Give the titles and the descriptions (And the source)
     *  - Add some extra settings for the texts
     */
    public DescriptionCF(){
        this.setAlignment(Pos.CENTER);
        this.setSpacing(30);
        this.setPadding(new Insets(30));

        Label title = new Label("About the game");
        Label description = new Label("Four in a Row is a two-player connection board game, " +
                "in which the players choose a color and then take turns dropping colored tokens " +
                "into a seven-column, six-row vertically suspended grid. The pieces fall straight down, " +
                "occupying the lowest available space within the column. The objective of the game is to be " +
                "the first to form a horizontal, vertical, or diagonal line of four of one's own tokens. " +
                "The first player can always win by playing the right moves.");

        Label title2 = new Label("How to Play");
        Label description2 = new Label("Above the board, there are seven buttons. The players can drop coins " +
                "into the table by press that button which is above that column where the player want to place it. " +
                "When the game begins the time starts ticking, but if any player click on the pause button, " +
                "the clock will stop and the game bord will hide. In the bottom of the screen there are a text " +
                "which shows who is the next.");

        this.makeTitle(title);
        this.makeDescription(description);
        this.makeSource(new Label("Source: Wikipedia"));
        this.makeTitle(title2);
        this.makeDescription(description2);
    }

    /**
     * Title maker
     *  - Set title font type and size
     *  - Add the title to the VBox
     * @param title The title label
     */
    private void makeTitle(Label title){
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        this.getChildren().add(title);
    }

    /**
     * Description maker
     *  - Set description font type and size
     *  - Set the text wrap option to true
     *  - Add the description to the VBox
     * @param description The description label
     */
    private void makeDescription(Label description){
        description.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        description.setWrapText(true);
        this.getChildren().add(description);
    }

    /**
     * Source maker
     *  - Set source font type and size
     *  - Add the source to the VBox
     * @param source The source label
     */
    private void makeSource(Label source){
        source.setFont(Font.font("Arial", FontPosture.ITALIC, 15));
        this.getChildren().add(source);
    }
}
