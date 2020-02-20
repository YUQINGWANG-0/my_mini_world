package ippo.assignment2;



import java.util.ArrayList;

public class World {

    private Room Outside, Reception, Staircase, Laundry;
    private Player player;
    private MobileItem mobileItem;

    private Room CurrentRoom;
    private String CurrentDirection;
    private String NextDirection;
    private Room NextRoom;
    ArrayList<String> itemlist = new ArrayList<>();


    public World() {

        //set all locations
        Outside = new Room();
        Reception = new Room();
        Staircase = new Room();
        Laundry = new Room();
        player = new Player();

        //set exits in each wall
        Outside.setExits("WestWall", Reception);
        Outside.setExits("EastWall", Laundry);
        Reception.setExits("EastWall", Outside);
        Reception.setExits("WestWall", Staircase);
        Staircase.setExits("EastWall", Reception);
        Laundry.setExits("WestWall", Outside);
        //connect each wall to the image name
        Outside.setPicture("NorthWall", "outsideN.JPG");
        Outside.setPicture("EastWall", "outsideE.JPG");
        Outside.setPicture("SouthWall", "outsideS.JPG");
        Outside.setPicture("WestWall", "outsideW.JPG");

        Reception.setPicture("NorthWall", "receptionN.JPG");
        Reception.setPicture("SouthWall", "receptionS.JPG");
        Reception.setPicture("WestWall", "receptionW.JPG");
        Reception.setPicture("EastWall", "receptionE.JPG");

        Staircase.setPicture("NorthWall", "stairN.JPG");
        Staircase.setPicture("SouthWall", "stairS.JPG");
        Staircase.setPicture("WestWall", "stairW.JPG");
        Staircase.setPicture("EastWall", "stairE.JPG");

        Laundry.setPicture("NorthWall", "lanN.JPG");
        Laundry.setPicture("SouthWall", "lanS.JPG");
        Laundry.setPicture("WestWall", "lanW.JPG");
        Laundry.setPicture("EastWall", "lanE.JPG");

        //create the items and its matched ImageView
        mobileItem = new MobileItem();
        mobileItem.setPicture("Duck", "duck.png");
        itemlist.add("Duck");

        mobileItem.setPicture("Tree", "tree.png");
        itemlist.add("Tree");

        mobileItem.setPicture("UFO", "ufo.png");
        itemlist.add("UFO");

        mobileItem.setPicture("Cloud", "cloud.png");
        itemlist.add("Cloud");

        // store each item in the "player's pocket"
        player.setItem(player, "Duck");
        player.setItem(player, "Tree");
        player.setItem(player, "UFO");
        player.setItem(player, "Cloud");
        //set the player's initial room and direction
        player.setNextDirectionRight();
        player.setNextDirectionLeft();
        player.setCurrentRoom(Outside);
        player.setCurrentDirection("NorthWall");
        CurrentRoom = player.getCurrentRoom();
        CurrentDirection = player.getCurrentDirection();

    }

    /**
     * initialise the whole world
     * set up all the rooms
     * set up all the walls and exits in each room
     * set up the items
     * specify current room/wall/item-in-the-pocket
     */
    public String start() {
        //show the image of the initial position
        String imageName = CurrentRoom.getPicture(CurrentDirection);
        return imageName;
    }

    /**
     * check whether the current room/location has an exit
     * @return true/false
     */
    public boolean hasExit() {
        if (CurrentRoom.checkExits(CurrentDirection) == true) { //check whether there is an exit
            return true;
        } else {
            return false;
        }
    }

    /**
     * move to the next room
     */
    public String getNextRoomImage() {
        NextRoom = CurrentRoom.getExits(CurrentDirection);
        player.setCurrentRoom(NextRoom);
        CurrentRoom = player.getCurrentRoom();
        return NextRoom.getPicture(CurrentDirection);
    }

    /**
     * turn to the right
     * but still in the same room
     */
    public String getRightRoomImage() {
        NextDirection = player.getNextDirectionRight(CurrentDirection);
        player.setCurrentDirection(NextDirection);
        CurrentDirection = player.getCurrentDirection();
        return CurrentRoom.getPicture(NextDirection);
    }

    /**
     * turn to the left
     * but still in the same room
     */
    public String getLeftRoomImage() {
        NextDirection = player.getNextDirectionLeft(CurrentDirection);
        player.setCurrentDirection(NextDirection);
        CurrentDirection = player.getCurrentDirection();
        return CurrentRoom.getPicture(NextDirection);
    }

    /**
     * check whether the player has the item
     * @param item the name of the item
     * @return true/false
     */
    public boolean checkItemInPlayer( String item){
        if (player.hasItem(item)) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * show a list of all items
     * @return An arrylist with all the items
     */
    public ArrayList getAllItems(){
        return itemlist;
    }

    /**
     * check whether the room has the item
     * @param item item name
     * @return true/false
     */
    public boolean checkItemInRoom(String item){
        if (CurrentRoom.checkItem(CurrentRoom,CurrentDirection,item)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * return the image name of the wanted item
     * @param item the name of the item
     * @return the name of the image name
     */
    public void storeItem(String item){
        CurrentRoom.setItem(player.getCurrentRoom(), player.getCurrentDirection(),item);
        player.removeItem(item);
    }

    public String getItemImage(String item){
        return mobileItem.getPicture(item);
    }

    /**
     * remove the item in the room/direction
     * @param item the name of the item
     */
    public void removeItemInRoom(String item){
        CurrentRoom.removeItem(item);
        player.setItem(player,item);
    }
}



