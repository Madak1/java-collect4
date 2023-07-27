package hu.ppke.itk.ma;

import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MenuScreenCF extends StackPane{

    /**
     * Menu screen constructor
     *  - Set the background color
     *  - Load the images
     *  - Play some animation
     */
    public MenuScreenCF(){
        this.setBackground(new Background(new BackgroundFill(Color.STEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        ImageView coinYView = new ImageView(new Image("CoinY.png"));
        ImageView coinRView = new ImageView(new Image("CoinR.png"));
        ImageView titleView = new ImageView(new Image("Title.png"));
        ImageView holeView = new ImageView(new Image("Hole.png"));

        this.rotateAnimation(titleView);
        this.scaleAnimation(coinYView);
        this.scaleAnimation(coinRView);
        this.scaleAnimation(titleView);
        this.fadeAnimation(coinRView);

        getChildren().addAll(coinYView, coinRView, titleView, holeView);
    }

    /**
     * Rotate animation settings
     * @param node Add rotate animation to this node
     */
    private void rotateAnimation(Node node){
        RotateTransition rotate = new RotateTransition(Duration.seconds(8), node);
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setToAngle(360);
        rotate.play();
    }

    /**
     * Scale animation settings
     * @param node Add scale animation to this node
     */
    private void scaleAnimation(Node node){
        ScaleTransition scale = new ScaleTransition(Duration.millis(250), node);
        scale.setCycleCount(TranslateTransition.INDEFINITE);
        scale.setInterpolator(Interpolator.LINEAR);
        scale.setAutoReverse(true);
        scale.setToX(0.95);
        scale.setToY(0.95);
        scale.play();
    }

    /**
     * Fade animation settings
     * @param node Add fade animation to this node
     */
    private void fadeAnimation(Node node){
        FadeTransition fade = new FadeTransition(Duration.seconds(8), node);
        fade.setCycleCount(TranslateTransition.INDEFINITE);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setAutoReverse(true);
        fade.setToValue(0);
        fade.play();
    }

}
