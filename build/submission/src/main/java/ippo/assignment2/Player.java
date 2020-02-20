package ippo.assignment2;

import java.util.HashMap;

public class Player {

    private Room CurrentRoom;
    private String CurrentDirection;
    HashMap<String,String> rightImage = new HashMap<>();
    HashMap<String,String> leftImage = new HashMap<>();
    HashMap<String,Player> items = new HashMap<>();

    /**
     * set the player's current in which room
     * @param room current room name
     */
    public void setCurrentRoom(Room room){
        CurrentRoom = room;
    }

    /**
     * return the information of which room player's in
     * @return Room current room
     */
    public Room getCurrentRoom(){
        return CurrentRoom;
    }

    /**
     * set the player's current direction
     * @param direction current direction name
     */
    public void setCurrentDirection(String direction){
        CurrentDirection = direction;
    }

    /**
     * @return String current direction
     */
    public  String  getCurrentDirection(){
        return CurrentDirection;
    }

    /**
     * connect each direction using hashmap in clockwise direction
     */
    public void setNextDirectionRight(){
        rightImage.put("NorthWall","EastWall");
        rightImage.put("EastWall","SouthWall");
        rightImage.put("SouthWall","WestWall");
        rightImage.put("WestWall","NorthWall");
    }

    /**
     * return the name of next direction in clockwise diretion
     * @param direction current direction
     * @return String next direction
     */
    public String getNextDirectionRight(String direction){
        return rightImage.get(direction);
    }

    /**
     * connect each direction using hashmap in anti-clockwise direction
     */
    public void setNextDirectionLeft(){
        leftImage.put("EastWall","NorthWall");
        leftImage.put("SouthWall","EastWall");
        leftImage.put("WestWall","SouthWall");
        leftImage.put("NorthWall","WestWall");
    }

    /**
     * returen the name of next direction in anti-clockwise direction
     * @param direction current direction name
     * @return String next direction name
     */
    public String getNextDirectionLeft(String direction){
        return leftImage.get(direction);
    }

    /**
     * store the item to player's pocket
     * @param player current player name
     * @param item to be saves item name
     */
    public void setItem(Player player,String item){

        items.put(item,player);
    }

    /**
     * check whether an item is in player's pocket
     * @param item wanted item name
     * @return true/false
     */
    public boolean hasItem(String item){
        return items.containsKey(item);
    }

    /**
     * remove item from player's pocket
     * @param item to be removed item name
     */
    public void removeItem(String item){

        items.remove(item);
    }
}
