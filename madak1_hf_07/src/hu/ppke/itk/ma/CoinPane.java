package hu.ppke.itk.ma;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Arrays;

public class CoinPane extends Pane{

    private final int tileSize;
    private final double spaceSize;

    protected enum fieldType {EMPTY, RED, YELLOW}
    private final fieldType[][] fields;

    /**
     * CoinPane constructor
     *  - Set the tile size
     *  - Set the space between tiles
     *  - Create a matrix and fill with field types
     * @param tileSize The size of a tile
     * @param spaceSize The size of the space between tiles
     */
    public CoinPane(int tileSize, double spaceSize){
        this.tileSize = tileSize;
        this.spaceSize = spaceSize;

        this.fields = new fieldType[GameCF.ROW_NUM][GameCF.COLUMN_NUM];
        for(fieldType[] row : fields) Arrays.fill(row, fieldType.EMPTY);
    }

    /**
     * Getter of the fields matrix
     * @return The matrix of the fields (fieldType[][])
     */
    public fieldType[][] getFields() {
        return fields;
    }

    /**
     * Coin inserter
     *  - Find the first empty row
     *  - Update the field matrix
     *  - Add falling animation to the coin
     *  - Add the coin to the coinPane
     * @param coin The inserted coin
     * @param column The column where the coin will insert
     * @return The insertion is successful (boolean)
     */
    public boolean addCoin(Coin coin, int column){
        int row = findFirstPlace(column);
        if(row < 0) return false;

        if(coin.isRed()) fields[row][column] = fieldType.RED;
        else fields[row][column] = fieldType.YELLOW;

        coin.setTranslateX((this.tileSize + this.spaceSize) * column + this.spaceSize);
        TranslateTransition fallAnimation = new TranslateTransition(Duration.millis(300), coin);
        fallAnimation.setToY((this.tileSize + this.spaceSize) * row + this.spaceSize);
        fallAnimation.play();

        getChildren().add(coin);

        return true;
    }

    /**
     * First place finder
     *  - Start the search from the button and go up while find a place
     * @param column The column where the func will search
     * @return The first empty place
     */
    public int findFirstPlace(int column){
        int row = GameCF.ROW_NUM - 1;
        while(row >= 0 && !this.fields[row][column].equals(fieldType.EMPTY)) row--;
        return row;
    }

}
