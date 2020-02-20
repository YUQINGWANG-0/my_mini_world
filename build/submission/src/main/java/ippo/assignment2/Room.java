package ippo.assignment2;

import java.util.HashMap;

public class Room {


    HashMap <String,Room> exits = new HashMap<>();
    HashMap <String,String> images = new HashMap<>();
    HashMap <String,String> itemsindirection = new HashMap<>();
    HashMap <String,Room> itemsinroom = new HashMap<>();


    /**
     * connect the wall to next location
     * @param wall a string of each wall's name
     * @param nextroom a string of the next room's name
     */
    public void setExits(String wall,Room nextroom){

        exits.put(wall,nextroom);
    }

    /**
     * returns the name of next location
     * @param currentDirection a string of the current wall's name
     * @return a string of the next room's name
     */
    public Room getExits(String currentDirection){

        return exits.get(currentDirection);
    }

    /**
     * check whether there is an exit in current direction
     * @param currentDirection a string of the current wall's name
     * @return true/false, whether there is an exit in current direction
     */
    public boolean checkExits(String currentDirection){

        return exits.containsKey(currentDirection);
    }

    /**
     * connect each room/direction to its image's name
     * @param direction a string of the wall's name
     * @param picturename a string of the wall's image's name
     */
    public void setPicture(String direction,String picturename){

        images.put(direction,picturename);
    }

    /**
     * return the image name of the location
     * @param direction a a string of the wall's name
     * @return a string of the wall's image's name
     */
    public String getPicture(String direction){

        return images.get(direction);
    }

    /**
     * store an item to one room/direction
     * @param room a string of the room's name
     * @param direction as tring of the direction's name
     * @param item a string of the item's name
     */
    public void setItem(Room room, String direction,String item){
        itemsindirection.put(item,direction);
        itemsinroom.put(item,room);
    }

    /**
     * check whether the room/direction has the wanted item
     * @param room a string of the room's name
     * @param direction as tring of the direction's name
     * @param item a string of the item's name
     * @return
     */
    public boolean checkItem(Room room, String direction, String item) {
        if (itemsinroom.get(item)==room && itemsindirection.get(item) == direction){
            return  true;
        } else {
            return false;
        }
    }


    /**
     * remove an item from room/direction
     * @param item the string of the item's name
     */

    public void removeItem(String item){
        itemsindirection.remove(item);
        itemsinroom.remove(item);
    }
}
