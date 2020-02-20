package ippo.assignment2;

import javafx.scene.image.ImageView;

import java.awt.*;
import java.util.HashMap;

public class MobileItem {
    HashMap <String,String> images = new HashMap<>();
    HashMap <String,ImageView> imageView = new HashMap<>();

    /**
     * connect each item name to the image's name
     * @param item a string of the item's name
     * @param picturename a string of the item's picture's name
     */
    public void setPicture (String item,String picturename){

        images.put(item,picturename);
    }

    /**
     * returns the String ImageName of the item
     * @param item a string of the items's name
     * @return a string of the item's picture's name
     */
    public String getPicture (String item){
        return images.get(item);
    }



}
