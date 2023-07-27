package hu.ppke.itk.ma;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class GameCF extends AnchorPane{

    protected final static int ROW_NUM = 6;
    protected final static int COLUMN_NUM = 7;

    private final int tileSize;
    private final double spaceSize;

    private boolean redTurn;

    private final TimeLabel timer;
    private final ImageView pause;
    private final Label next;
    private final ButtonHBox buttons;
    private Shape board;
    private final CoinPane coins;
    private final StackPane boardAndCoins;
    private final VBox buttonsAndBoardAndCoins;
    private final Label winLabel;

    private final DropShadow shadowEffect;

    /**
     * Game constructor
     *  - Set the initial values
     * @param tileSize The size of the tiles
     */
    public GameCF(int tileSize){
        this.tileSize = tileSize;
        this.spaceSize = tileSize / 5.0;

        double boardX = (this.tileSize + this.spaceSize) * GameCF.COLUMN_NUM + this.spaceSize;
        double boardY = (this.tileSize + this.spaceSize) * GameCF.ROW_NUM + this.spaceSize;

        this.redTurn = true;

        this.timer = new TimeLabel();
        this.pause = new ImageView();
        this.next = new Label("The first coin is RED");
        this.buttons = new ButtonHBox(this.tileSize);
        this.board = new Rectangle(boardX, boardY);
        this.coins = new CoinPane(this.tileSize, this.spaceSize);
        this.boardAndCoins = new StackPane();
        this.buttonsAndBoardAndCoins = new VBox();
        this.winLabel = new Label();

        this.shadowEffect = new DropShadow(10.0, 3.0, 3.0, Color.BLACK);

        this.createField();

    }

    /**
     * Game creator
     *  - Create the field of the game
     */
    private void createField(){

        this.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        this.timerSetup();
        this.pauseSetup();
        this.nextSetup();
        this.buttonsSetup();
        this.boardSetup();
        this.coinsSetup();
        this.boardAndCoinsSetup();
        this.buttonsAndBoardAndCoinsSetup();
        this.winLabelSetup();

        this.getChildren().addAll(this.buttonsAndBoardAndCoins, this.timer, this.pause, this.next);

    }

    /**
     * Timer options
     *  - Set the timer alignment, font style and color
     *  - Set the timer position
     */
    private void timerSetup(){
        this.timer.setAlignment(Pos.CENTER);
        this.timer.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        this.timer.setTextFill(Color.WHITESMOKE);
        this.timer.setEffect(shadowEffect);

        AnchorPane.setTopAnchor(this.timer, 20.0);
        AnchorPane.setLeftAnchor(this.timer, 250.0);
        AnchorPane.setRightAnchor(this.timer, 250.0);
    }

    /**
     * Pause button options
     *  - Set the buttons look
     *  - Set the position
     *  - Set the pause label
     *  - Set the label position
     *  - Add click event to the pause button
     */
    private void pauseSetup(){
        this.pause.setImage(new Image("PauseButton.png"));

        this.pause.setFitWidth(40);
        this.pause.setFitHeight(40);
        this.pause.setEffect(this.shadowEffect);

        AnchorPane.setTopAnchor(this.pause, 20.0);
        AnchorPane.setRightAnchor(this.pause, 20.0);

        Label pauseLabel = new Label("PAUSE");
        pauseLabel.setFont(Font.font("Arial", FontWeight.BOLD, 100));
        pauseLabel.setAlignment(Pos.CENTER);
        pauseLabel.setTextFill(Color.WHITE);
        pauseLabel.setEffect(this.shadowEffect);

        AnchorPane.setTopAnchor(pauseLabel, 20.0);
        AnchorPane.setBottomAnchor(pauseLabel, 20.0);
        AnchorPane.setLeftAnchor(pauseLabel, 20.0);
        AnchorPane.setRightAnchor(pauseLabel, 20.0);

        this.pause.setOnMouseClicked(mouseEvent -> {
            if(this.timer.isStopped()){
                this.timer.resumeTimer();
                this.pause.setImage(new Image("PauseButton.png"));
                this.getChildren().set(0, this.buttonsAndBoardAndCoins);
            }
            else{
                this.timer.stopTimer();
                this.pause.setImage(new Image("ResumeButton.png"));
                this.getChildren().set(0, pauseLabel);
            }
        });
    }

    /**
     * Next player label options
     *  - Set the label look
     *  - Set the position
     */
    private void nextSetup(){
        this.next.setAlignment(Pos.CENTER);
        this.next.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        AnchorPane.setBottomAnchor(this.next, 30.0);
        AnchorPane.setLeftAnchor(this.next, 100.0);
        AnchorPane.setRightAnchor(this.next, 100.0);
    }

    /**
     * Buttons options
     *  - Set the buttons look
     *  - Add the buttons to the pane
     *  - Add events to the buttons
     */
    private void buttonsSetup(){
        this.buttons.setAlignment(Pos.CENTER);
        this.buttons.setSpacing(this.spaceSize);

        this.buttons.fillWithButtons();

        for(int column = 0; column < GameCF.COLUMN_NUM; column++){

            int columnIndex = column;
            Button button = (Button) this.buttons.getChildren().get(columnIndex);

            button.setOnMouseClicked(mouseEvent -> {
                Coin coin = new Coin(this.redTurn, this.tileSize/2.0);
                if(this.coins.addCoin(coin, columnIndex)){
                    int rowIndex = this.coins.findFirstPlace(columnIndex) + 1;
                    WinCheck winCheck = new WinCheck(this.coins.getFields(), rowIndex, columnIndex, this.winLabel);
                    winCheck.start();

                    try { winCheck.join(); }
                    catch (InterruptedException e) { throw new RuntimeException(e); }

                    if(!this.winLabel.getText().isEmpty()){
                        this.timer.stopTimer();

                        FadeTransition fade = new FadeTransition(Duration.seconds(3), this.winLabel);
                        fade.setFromValue(0);
                        fade.setToValue(1);
                        fade.play();

                        this.getChildren().removeAll(this.pause, this.next);
                        this.buttonsAndBoardAndCoins.getChildren().set(0, this.winLabel);
                    }

                    this.redTurn = !this.redTurn;
                    if(this.redTurn) this.next.setText("The next coin is RED");
                    else this.next.setText("The next coin is YELLOW");
                }
                button.setGraphic(new Circle(this.tileSize/3.0, this.redTurn ? Color.FIREBRICK : Color.GOLD));
            });

            button.setOnMouseEntered(mouseEvent -> {
                button.setOpacity(1);
                button.setEffect(this.shadowEffect);
                button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                button.setGraphic(new Circle(this.tileSize/3.0, this.redTurn ? Color.FIREBRICK : Color.GOLD));
            });

            button.setOnMouseExited(mouseEvent -> {
                button.setOpacity(0.4);
                button.setEffect(null);
                button.setContentDisplay(ContentDisplay.BOTTOM);
                button.setGraphic(new Circle(this.tileSize/4.0));
            });

        }

    }

    /**
     * Board options
     *  - Generate the board
     *  - Set the board look
     */
    private void boardSetup(){
        for(int row = 0; row < GameCF.ROW_NUM; row++){
            for(int column = 0; column < GameCF.COLUMN_NUM; column++){
                Circle hole = new Circle(this.tileSize / 2.0);
                hole.setCenterX(this.tileSize / 2.0);
                hole.setCenterY(this.tileSize / 2.0);
                hole.setTranslateX((this.tileSize + this.spaceSize) * column + this.spaceSize);
                hole.setTranslateY((this.tileSize + this.spaceSize) * row + this.spaceSize);
                this.board = Shape.subtract(this.board, hole);
            }
        }

        this.board.setFill(Color.STEELBLUE);
        this.board.setStrokeWidth(2);
        this.board.setStroke(Color.BLACK);
        this.board.setEffect(this.shadowEffect);
    }

    /**
     * Coins options
     *  - Translate the coins to the right place
     */
    private void coinsSetup(){
        this.coins.setTranslateX(this.spaceSize / 4.0);
        this.coins.setTranslateY(this.spaceSize / 4.0);
    }

    /**
     * Board and coins pane options
     *  - Set the max width
     *  - Add the coins and the board to the pane
     */
    private void boardAndCoinsSetup(){
        this.boardAndCoins.setMaxWidth((this.tileSize + this.spaceSize) * GameCF.COLUMN_NUM + this.spaceSize);
        this.boardAndCoins.getChildren().addAll(this.coins, this.board);
    }

    /**
     * Buttons and board and coins pane options
     *  - Set the pane look
     *  - Set the pane position
     *  - Add the buttons and the boardAndCoins pane to the pane
     */
    private void buttonsAndBoardAndCoinsSetup(){
        this.buttonsAndBoardAndCoins.setAlignment(Pos.CENTER);
        this.buttonsAndBoardAndCoins.setSpacing(this.spaceSize);

        AnchorPane.setTopAnchor(this.buttonsAndBoardAndCoins, 50.0);
        AnchorPane.setBottomAnchor(this.buttonsAndBoardAndCoins, 50.0);
        AnchorPane.setLeftAnchor(this.buttonsAndBoardAndCoins, 50.0);
        AnchorPane.setRightAnchor(this.buttonsAndBoardAndCoins, 50.0);

        this.buttonsAndBoardAndCoins.getChildren().addAll(this.buttons, this.boardAndCoins);
    }

    /**
     * Win label options
     *  - Set the style of the win label
     *  - Set the label to the center
     */
    private void winLabelSetup(){
        this.winLabel.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        this.winLabel.setAlignment(Pos.CENTER);
    }

}
