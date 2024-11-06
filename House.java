import java.util.ArrayList;

/**
 * A House can contain a list of residents and might have a dining room.
 * This class provides methods to manage residents moving in and out,
 * as well as checking the presence and number of residents.
 */
public class House extends Building {

   private ArrayList<String> residents;
   private boolean hasDiningRoom;
   private boolean hasElevator;

   /**
    * Constructs a new House object with the specified attributes.
    *
    * @param name          the name of the house
    * @param address       the address of the house
    * @param nFloors       the number of floors in the house
    * @param hasDiningRoom whether the house has a dining room
    * @param hasElevator   whether the house has an elevator
    */
   public House(String name, String address, int nFloors, boolean hasDiningRoom, boolean hasElevator) {
       super(name, address, nFloors);
       this.hasDiningRoom = hasDiningRoom;
       this.hasElevator = hasElevator;
       this.residents = new ArrayList<>();
       System.out.println("You have built a house!");
   }

   /**
    * Overloaded constructor that defaults `hasElevator` to false.
    *
    * @param name          the name of the house
    * @param address       the address of the house
    * @param nFloors       the number of floors in the house
    * @param hasDiningRoom whether the house has a dining room
    */
   public House(String name, String address, int nFloors, boolean hasDiningRoom) {
       this(name, address, nFloors, hasDiningRoom, false);
   }

   /**
    * Moves a group of residents into the house at once.
    *
    * @param newResidents the list of resident names to move in
    */
   public void moveIn(ArrayList<String> newResidents) {
       for (String resident : newResidents) {
           if (!this.isResident(resident)) {
               this.residents.add(resident);
           }
       }
   }

   /**
    * Displays available options for actions that can be performed in the house.
    */
   @Override
   public void showOptions() {
       super.showOptions();
       System.out.println(" + moveIn(name) \n + moveOut(name) \n + isResident(name) \n + nResidents() \n + hostParty()");
   }

   /**
    * Moves to the specified floor in the house. If the house has an elevator,
    * it allows non-adjacent floor access.
    *
    * @param floorNum the floor number to navigate to
    * @throws RuntimeException if the floor transition is not valid without an elevator
    */
   @Override
   public void goToFloor(int floorNum) {
       if (hasElevator || Math.abs(this.activeFloor - floorNum) == 1) {
           super.goToFloor(floorNum);
       } else {
           throw new RuntimeException("This house does not have an elevator. You can only move to adjacent floors.");
       }
   }

   /**
    * Checks if the house has a dining room.
    *
    * @return true if the house has a dining room; false otherwise
    */
   public boolean hasDiningRoom() {
       return this.hasDiningRoom;
   }

   /**
    * Gets the number of residents currently living in the house.
    *
    * @return the number of residents
    */
   public int nResidents() {
       return this.residents.size();
   }

   /**
    * Checks if a person is a resident of the house.
    *
    * @param name the name of the person to check
    * @return true if the person is a resident; false otherwise
    */
   public boolean isResident(String name) {
       return this.residents.contains(name);
   }

   /**
    * Moves a new resident into the house. If the person is already a resident, throws an exception.
    *
    * @param name the name of the person to move in
    * @throws RuntimeException if the person is already a resident
    */
   public void moveIn(String name) {
       if (isResident(name)) {
           throw new RuntimeException("The resident is already in the house. Can't be moved in.");
       } else {
           this.residents.add(name);
       }
   }

   /**
    * Moves a resident out of the house. If the person is not a resident, throws an exception.
    *
    * @param name the name of the person to move out
    * @return the name of the person who moved out
    * @throws RuntimeException if the person is not a resident
    */
   public String moveOut(String name) {
       if (!isResident(name)) {
           throw new RuntimeException("The resident is not in the house. Can't be moved out.");
       } else {
           this.residents.remove(name);
           return name;
       }
   }

   /**
    * Main method to test the functionality of the House class.
    *
    * @param args command-line arguments
    */
   public static void main(String[] args) {
       // Create a House instance and test individual methods
       House Ziskind = new House("Ziskind", "1 Henshaw Avenue", 3, true, true);
       Ziskind.moveIn("Trang");
       Ziskind.moveIn("Chi");
       Ziskind.moveIn("Handi");

       try {
           Ziskind.moveOut("Jenny");
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }

       try {
           Ziskind.moveIn("Handi");
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }

       Ziskind.moveOut("Trang");
       Ziskind.isResident("Duong");

       System.out.println(Ziskind);

       // Create a House object with an elevator
       House Cutter = new House("Cutter", "1 Henshaw Avenue", 3, true, true);
       
       // Enter the house and display options
       Cutter.enter();
       Cutter.showOptions();

       // Test moving between floors
       Cutter.goToFloor(2); // Should work as the house has an elevator
       Cutter.goToFloor(3); // Move to the top floor
       Cutter.goDown();     // Go down one floor
       Cutter.goToFloor(1); // Go back to the ground floor

       // Test overloaded methods
       House Emerson = new House("Cutter", "1 Henshaw Avenue", 3, true);
       ArrayList<String> group = new ArrayList<>();
       group.add("Alice");
       group.add("Bob");
       Emerson.moveIn(group); // Using overloaded moveIn

       // Exit the house
       Cutter.exit();
   }
}
