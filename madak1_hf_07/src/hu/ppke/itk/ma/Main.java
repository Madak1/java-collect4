package hu.ppke.itk.ma;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{

    /**
     * If we want music to the game...
     *  - We must complete the VM options with the 'javafx.media'
     *  - After that we can uncomment the media player lines
     * TODO: Edit configurations --> VM options: --module-path ${PATH_TO_FX} --add-modules javafx.controls,javafx.fxml,javafx.media
     */
    // private BackgroundMusic music;
    private BorderPane root;

    /**
     * Create the content
     *  - Start the music in the background
     *  - Set the menu bar to the top of the window
     *  - Set the menu screen to the center of the window
     * @return The root pane (BorderPane)
     */
    private Parent createContent(){
        // this.music = new BackgroundMusic("music/8bit.wav");

        this.root = new BorderPane();
        this.root.setTop(this.makeMenuBar());
        this.root.setCenter(new MenuScreenCF());

        return this.root;
    }

    /**
     * Make the menu bar
     *  - Create the menu bar
     *  - Set the actions for each menu items
     * @return The menu bar (MenuBarCF)
     */
    private MenuBar makeMenuBar(){
        MenuBarCF menuBar = new MenuBarCF();

        menuBar.setAction(0, 0, actionEvent -> this.root.setCenter(new GameCF(70)));
        menuBar.setAction(0, 1, actionEvent -> this.root.setCenter(new DescriptionCF()));
        menuBar.setAction(0, 2, actionEvent -> System.exit(0));
        // menuBar.setAction(1, 0, actionEvent -> this.music.muteSwitch());

        return menuBar;
    }

    /**
     * The Start
     *  - Set the title of the game
     *  - Set the icon for the game
     *  - Add the minimum size of the window
     * @param stage The primary stage (Stage)
     */
    @Override
    public void start(Stage stage){
        stage.setTitle("4 in A ROW");
        stage.getIcons().add(new Image("Icon.png"));
        stage.setMinWidth(900);
        stage.setMinHeight(850);

        stage.setScene(new Scene(this.createContent()));
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}
