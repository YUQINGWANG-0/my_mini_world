package ippo.assignment2;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class WorldController {
    private World myworld;
    private ArrayList<String> itemList;
    HashMap<String,ImageView> itemImageView = new HashMap<>();
    HashMap<String,ImageView> itemImageViewInPlayer = new HashMap<>();
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView item1;
    @FXML
    private ImageView item2;
    @FXML
    private ImageView item3;
    @FXML
    private ImageView item4;
    @FXML
    private ImageView item11;
    @FXML
    private ImageView item22;
    @FXML
    private ImageView item33;
    @FXML
    private ImageView item44;
    @FXML
    private TextField messageBox;

    /**
     * initialise the whole world
     * set up all the rooms
     * set up all the walls and exits in each room
     * set up the items
     * specify current room/wall/item-in-the-pocket
     */
    public void Initialise() {
        myworld = new World();
        Image image = new Image(myworld.start());
        imageView.setImage(image);
        messageBox.setVisible(false);
        itemList = myworld.getAllItems();

        // connect each item with matched image view
        // show the original items in the player's pocket
        for (String item : itemList) {
            if(item == "Duck") {
                itemImageView.put(item, item1);
                itemImageViewInPlayer.put(item,item11);
            }
            if (item == "Tree"){
                itemImageView.put(item,item2);
                itemImageViewInPlayer.put(item,item22);
            }
            if (item == "UFO"){
                itemImageView.put(item,item3);
                itemImageViewInPlayer.put(item,item33);
            }
            if (item == "Cloud"){
                itemImageView.put(item,item4);
                itemImageViewInPlayer.put(item,item44);
            }
            Image image2 = new Image(myworld.getItemImage(item));
            itemImageViewInPlayer.get(item).setImage(image2);
            itemImageViewInPlayer.get(item).setVisible(true);
        }
    }

    /**
     * move to the next room
     */
    public void moveForward() {
        if (myworld.hasExit()) {
            messageBox.setVisible(false);
            Image image = new Image(myworld.getNextRoomImage());
            imageView.setImage(image);
        } else {
            messageBox.setVisible(true);
            messageBox.setText("There is no exit in this direction");
            // set a time for showing the messagebox
            PauseTransition delay =  new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(e -> messageBox.setVisible(false));
            delay.play();
        }

        //check whether there are some item in current room/direction
        for (String item : itemList){
            if (myworld.checkItemInRoom(item)){
                Image images = new Image(myworld.getItemImage(item));
                itemImageView.get(item).setImage(images);
                itemImageView.get(item).setVisible(true);
            }else{
                itemImageView.get(item).setVisible(false);
            }
        }

    }

    /**
     * turn to the right
     * but still in the same room
     */
    public void turnRight() {
        messageBox.setVisible(false);
        Image image = new Image(myworld.getRightRoomImage());
        imageView.setImage(image);

        for (String item : itemList){
            if (myworld.checkItemInRoom(item)){
                Image images = new Image(myworld.getItemImage(item));
                itemImageView.get(item).setImage(images);
                itemImageView.get(item).setVisible(true);
            }else{
                itemImageView.get(item).setVisible(false);
            }
        }
    }

    /**
     * turn to the left
     * but still in the same room
     */
    public void turnLeft() {
        messageBox.setVisible(false);
        Image image = new Image(myworld.getLeftRoomImage());
        imageView.setImage(image);

        for (String item : itemList){
            if (myworld.checkItemInRoom(item)){
                Image images = new Image(myworld.getItemImage(item));
                itemImageView.get(item).setImage(images);
                itemImageView.get(item).setVisible(true);
            }else{
                itemImageView.get(item).setVisible(false);
            }
        }
    }

    /**
     * put an item in the current room/direction
     * @param event
     */
    public void putItem(ActionEvent event) {
        //get the Text information from the pressed MenuItem
        MenuItem mItem =  (MenuItem) event.getSource();
        String ItemName = mItem.getText();
        if (myworld.checkItemInPlayer(ItemName)) { //check whether the player still has the item
            //fetch the image from resources
            //display them on the matched ImageView
            myworld.storeItem(ItemName);
            Image image = new Image(myworld.getItemImage(ItemName));
            itemImageView.get(ItemName).setImage(image);
            itemImageView.get(ItemName).setVisible(true);
            itemImageViewInPlayer.get(ItemName).setVisible(false);
        } else{
            // show a warning message that the item is not in the "player's pocket"
            messageBox.setVisible(true);
            messageBox.setText("There is no "+ ItemName + " in your pocket. You must have left it somewhere");
            // set a time for showing the messagebox
            PauseTransition delay =  new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(e -> messageBox.setVisible(false));
            delay.play();
        }
    }

    /**
     * pick up an item in the current room/direction
     * @param event
     */
    public void pickUpItem(ActionEvent event) {
        //get the information from the pressed MenuItem
        MenuItem mItem =  (MenuItem) event.getSource();
        String ItemName = mItem.getText();
        if(myworld.checkItemInRoom(ItemName)){
            //check whether current room has the wanted item
            //if there is one, make the matched ImageView invisible
            itemImageView.get(ItemName).setVisible(false);
            itemImageViewInPlayer.get(ItemName).setVisible(true);
            myworld.removeItemInRoom(ItemName);
        }else{
            //show a warning message that wanted item is not in current room/direction
            messageBox.setVisible(true);
            messageBox.setText( ItemName + " is not found in current scene");
            // set a time for showing the messagebox
            PauseTransition delay =  new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(e -> messageBox.setVisible(false));
            delay.play();
        }
    }


}



