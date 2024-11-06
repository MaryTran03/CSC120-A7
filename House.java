import java.util.ArrayList;

/**
 * A House can contain a list of residents and might have a dining room.
 * This class provides methods to manage residents moving in and out, 
 * as well as checking the presence and number of residents.
 */
public class House extends Building {

  private ArrayList<String> residents;
  private boolean hasDiningRoom;

  /**
   * Constructs a new House object with the specified name, address, number of floors, 
   * and whether it has a dining room.
   *
   * @param name         the name of the house
   * @param address      the address of the house
   * @param nFloors      the number of floors in the house
   * @param hasDiningRoom true if the house has a dining room; false otherwise
   */
  public House(String name, String address, int nFloors, boolean hasDiningRoom) {
      super(name, address, nFloors);
      this.hasDiningRoom = hasDiningRoom;
      this.residents = new ArrayList<>();
      System.out.println("You have built a house!");
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

public static void main(String[] args) {
    House Ziskind = new House("Ziskind", "1 Henshaw Avenue",3, true);
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
  }

}