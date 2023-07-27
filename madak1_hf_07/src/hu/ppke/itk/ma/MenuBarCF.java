package hu.ppke.itk.ma;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBarCF extends MenuBar{

    /**
     * Menu bar constructor
     *  - It Adds some menu items for the menus
     *  - Then add these menus to the menu bar
     */
    public MenuBarCF(){
        Menu fileMenu = new Menu("File");
        MenuItem[] fileMenuItems = {
                new MenuItem("New Game"),
                new MenuItem("Description"),
                new MenuItem("Exit")
        };

        Menu audioMenu = new Menu("Audio");
        MenuItem[] audioMenuItems = {
                new MenuItem("Mute/Unmute")
        };

        fileMenu.getItems().addAll(fileMenuItems);
        audioMenu.getItems().addAll(audioMenuItems);
        this.getMenus().addAll(fileMenu, audioMenu);
    }

    /**
     * Action setter
     *  - This can add some action to the menu items
     * @param menuIndex The index of the menu what we want to change
     * @param menuItemIndex The index of the menu item what we want to change
     * @param eventHandler The event what we want to happen when we click the menu item
     */
    public void setAction(int menuIndex, int menuItemIndex, EventHandler<ActionEvent> eventHandler){
        this.getMenus().get(menuIndex).getItems().get(menuItemIndex).setOnAction(eventHandler);
    }

}
