package hu.ppke.itk.ma;

import javafx.scene.control.Label;

public class WinCheck extends Thread{

    private final CoinPane.fieldType[][] actFields;
    private final int xPos;
    private final int yPos;

    private final Label winLabel;

    /**
     * Win checker constructor
     *  - Set the initial values
     * @param fields The field what te program will check
     * @param xPos The x coordinate of the new coin
     * @param yPos The y coordinate of the new coin
     * @param label The win label
     */
    public WinCheck(CoinPane.fieldType[][] fields, int xPos, int yPos, Label label){
        this.actFields = fields;
        this.xPos = xPos;
        this.yPos = yPos;

        this.winLabel = label;
    }

    /**
     * Win checker
     *  - If someone wins, then change the label
     */
    @Override
    public void run() {
        if(findWinner(0, 1) ||
           findWinner(1, 1) ||
           findWinner(1, 0) ||
           findWinner(1, -1) ) winLabel.setText(this.actFields[this.xPos][this.yPos] + " wins");
    }

    /**
     * Winner finder
     *  - Check the line in the actual direction (the direction can be horizontal, vertical, or diagonal)
     * @param xDir The x direction (-1, 0, 1)
     * @param yDir The y direction (-1, 0, 1)
     * @return Find winner or not (boolean)
     */
    private boolean findWinner(int xDir, int yDir){
        CoinPane.fieldType fieldType = this.actFields[this.xPos][this.yPos];
        int back = 4;
        int count = 1;

        for(int i = 1; i < 4; i++){
            if(legalPos(this.xPos + i*xDir, this.yPos - i*yDir)){
                if(this.actFields[this.xPos + i*xDir][this.yPos - i*yDir] == fieldType) {
                    back--;
                    count++;
                }
                else break;
            }
        }

        for(int j = 1; j < back; j++){
            if(legalPos(this.xPos - j*xDir, this.yPos + j*yDir)){
                if(this.actFields[this.xPos - j*xDir][this.yPos + j*yDir] == fieldType){
                    count++;
                }
                else break;
            }
        }

        return count > 3;
    }

    /**
     * Legal position checker
     * @param x The x position
     * @param y The y position
     * @return Legal position or not (boolean)
     */
    private boolean legalPos(int x, int y){
        if(x >= 0 && x < GameCF.ROW_NUM) return y >= 0 && y < GameCF.COLUMN_NUM;
        return false;
    }
}
