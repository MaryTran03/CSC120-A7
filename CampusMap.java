import java.util.ArrayList;

public class CampusMap {

    ArrayList<Building> buildings;

    /* Default constructor, initializes empty ArrayList */
    public CampusMap() {
        buildings = new ArrayList<Building>();
    }

    /**
     * Adds a Building to the map
     * @param b the Building to add
     */
    public void addBuilding(Building b) {
        System.out.println("Adding building...");
        buildings.add(b);
        System.out.println("-->Successfully added " + b.getName() + " to the map.");
    }

    /**
     * Removes a Building from the map
     * @param b the Building to remove
     * @return the removed Building
     */
    public Building removeBuilding(Building b) {
        System.out.println("Removing building...");
        buildings.remove(b);
        System.out.println("-->Successfully removed " + b.getName() + " to the map.");
        return b;
    }

    public String toString() {
        String mapString = "DIRECTORY of BUILDINGS";

        for (int i = 0; i < this.buildings.size(); i ++) {
            mapString += "\n  " + (i+1) + ". "+ this.buildings.get(i).getName() + " (" + this.buildings.get(i).getAddress() + ")";
        }
        return mapString;
    }

    public static void main(String[] args) {
        CampusMap myMap = new CampusMap();

        // Add different buildings to the map, demonstrating overloaded constructors and methods

        // Using overloaded constructor in House (without elevator)
        myMap.addBuilding(new House("Albright House", "34 Elm Street", 3, true));
        // Using overloaded constructor in House (with elevator)
        myMap.addBuilding(new House("Chase House", "12 Elm Street", 4, true, true));

        // Using overloaded constructor in Library (default single floor)
        myMap.addBuilding(new Library("Hillyer Art Library", "20 Elm Street"));
        // Using overloaded constructor in Library (multi-floor, with elevator)
        myMap.addBuilding(new Library("Young Science Library", "44 Elm Street", 4, true));

        // Using overloaded constructor in Cafe (default sugar and cream inventory)
        myMap.addBuilding(new Cafe("Campus Cafe", "5 Chapin Way", 1, 100, 50));
        // Using full constructor in Cafe
        myMap.addBuilding(new Cafe("Nielson Cafe", "7 Neilson Drive", 2, 150, 60, 60, 80));

        // Add more buildings using standard constructors in Building
        myMap.addBuilding(new Building("Ford Hall", "100 Green Street", 4));
        myMap.addBuilding(new Building("Bass Hall", "4 Tyler Court", 4));
        myMap.addBuilding(new Building("McConnell Hall", "2 College Lane", 5));
        myMap.addBuilding(new Building("Burton Hall", "1 Henshaw Avenue", 3));
        myMap.addBuilding(new Building("Seelye Hall", "101 Seelye Drive", 3));

        // Demonstrate usage of overloaded methods
        House chaseHouse = new House("Chase House", "12 Elm Street", 4, true, true);
        ArrayList<String> residents = new ArrayList<>();
        residents.add("Alice");
        residents.add("Bob");
        chaseHouse.moveIn(residents); // Using overloaded moveIn method to add multiple residents

        Library youngLibrary = new Library("Young Science Library", "44 Elm Street", 4, true);
        ArrayList<String> newBooks = new ArrayList<>();
        newBooks.add("Biology 101");
        newBooks.add("Physics for Scientists");
        youngLibrary.addTitle(newBooks); // Using overloaded addTitle method to add multiple books

        Cafe campusCafe = new Cafe("Campus Cafe", "5 Chapin Way", 1, 100, 50);
        campusCafe.sellCoffee(12); // Using overloaded sellCoffee method with default sugar and cream

        // Print the Campus Map
        System.out.println(myMap);
    }
}
