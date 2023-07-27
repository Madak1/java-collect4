package hu.ppke.itk.ma;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

public class TimeLabel extends Label{

    private final AnimationTimer animationTimer;

    private long stopTime;
    private long sumPausedTime;

    private boolean isStopped;

    /**
     * Time label constructor
     *  - Set the isStopped variable to false
     *  - Set the start time to actual time
     *  - Add the elapsed time in the correct format to the label
     *  - Start the timer
     */
    public TimeLabel(){
        this.isStopped = false;
        long startTime = System.currentTimeMillis();

        this.animationTimer = new AnimationTimer(){
            @Override
            public void handle(long now) {
                long elapsedTime = System.currentTimeMillis() - startTime + TimeLabel.this.sumPausedTime;

                int intSec = Integer.parseInt(Long.toString(elapsedTime / 1000));
                int minute = intSec / 60;
                int second = intSec % 60;

                String sMinute;
                if(minute < 10) sMinute = "0" + minute;
                else sMinute = String.valueOf(minute);

                String sSecond;
                if(second < 10) sSecond = "0" + second;
                else sSecond = String.valueOf(second);

                TimeLabel.this.setText(sMinute + ":" +  sSecond);
            }
        };
        this.animationTimer.start();
    }

    /**
     * Resume timer
     *  - Set the isStopped variable to false
     *  - Calculate the elapsed time in the break
     *  - Add this number to the sum of the pause time
     *  - Start the timer
     */
    public void resumeTimer(){
        this.isStopped = false;
        long resumeTime = System.currentTimeMillis();
        this.sumPausedTime += this.stopTime - resumeTime;
        this.animationTimer.start();
    }

    /**
     * Stop the timer
     *  - Set the isStopped variable to true
     *  - Save the time when the timer has stopped
     *  - Stop the timer
     */
    public void stopTimer(){
        this.isStopped = true;
        this.stopTime = System.currentTimeMillis();
        this.animationTimer.stop();
    }

    /**
     * Getter for the timer status
     * @return The timer is stopped or not (boolean)
     */
    public boolean isStopped() {
        return this.isStopped;
    }

}
