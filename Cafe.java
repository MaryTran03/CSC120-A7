/**
 * A Cafe manages an inventory of coffee, sugar packets, cream, and cups.
 * It allows selling coffee, checking inventory levels, and restocking when supplies are low.
 */

 public class Cafe extends Building {
    private int nCoffeeOunces; // The number of ounces of coffee remaining in inventory
    private int nSugarPackets; // The number of sugar packets remaining in inventory
    private int nCreams; // The number of "splashes" of cream remaining in inventory
    private int nCups; // The number of cups remaining in inventory

    /**
     * Constructs a new Cafe object with the specified name, address, number of floors,
     * and initial inventory of coffee, sugar, cream, and cups.
     *
     * @param name     the name of the cafe
     * @param address  the address of the cafe
     * @param nFloors  the number of floors in the cafe
     * @param coffee   initial ounces of coffee in inventory
     * @param sugar    initial number of sugar packets in inventory
     * @param creams   initial number of cream portions in inventory
     * @param cups     initial number of cups in inventory
     */
    public Cafe(String name, String address, int nFloors, int coffee, int sugar, int creams, int cups) {
        super(name, address, nFloors);
        this.nCoffeeOunces = coffee;
        this.nSugarPackets = sugar;
        this.nCreams = creams;
        this.nCups = cups;

        System.out.println("You have built a cafe!");
    }

    /**
     * Sells a coffee with specified size, sugar packets, and cream portions.
     * If inventory is insufficient, it triggers restocking of required items.
     *
     * @param size         the number of ounces of coffee for the sale
     * @param nSugarPackets the number of sugar packets requested
     * @param nCreams       the number of cream portions requested
     * @throws RuntimeException if inventory is insufficient and cannot be restocked
     */
    public void sellCoffee(int size, int nSugarPackets, int nCreams) {
        // Check if there is enough inventory. If not, restock.
        if (!hasInventory(size, nSugarPackets, nCreams)) {
            restock(size, nSugarPackets, nCreams, 1);
        }

        // Decrease the inventory
        this.nCoffeeOunces -= size;
        this.nSugarPackets -= nSugarPackets;
        this.nCreams -= nCreams;
        this.nCups -= 1;
        System.out.println("Coffee sold!");
    }

    /**
     * Checks if there is enough inventory to fulfill a coffee order.
     *
     * @param size         the required ounces of coffee
     * @param nSugarPackets the required sugar packets
     * @param nCreams       the required cream portions
     * @return true if there is sufficient inventory for the order, false otherwise
     */
    public boolean hasInventory(int size, int nSugarPackets, int nCreams) {
        return this.nCoffeeOunces >= size &&
               this.nSugarPackets >= nSugarPackets &&
               this.nCreams >= nCreams &&
               this.nCups >= 1;
    }

    /**
     * Restocks the cafe's inventory with additional coffee, sugar packets, cream, and cups
     * if the current inventory for specific items is below the specified levels. 
     * Restock 3 times the required input to make one cup to save time. 
     *
     * @param nCoffeeOunces the additional ounces of coffee to restock
     * @param nSugarPackets the additional sugar packets to restock
     * @param nCreams       the additional cream portions to restock
     * @param nCups         the additional cups to restock
     */
    private void restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) {
        if (this.nCoffeeOunces < nCoffeeOunces) {
            this.nCoffeeOunces += nCoffeeOunces * 3;
            System.out.println("Coffee restocked!");
        }
        if (this.nSugarPackets < nSugarPackets) {
            this.nSugarPackets += nSugarPackets * 3;
            System.out.println("Sugar restocked!");
        }
        if (this.nCups < nCups) {
            this.nCups += nCups * 3;
            System.out.println("Cups restocked!");
        }
        if (this.nCreams < nCreams) {
            this.nCreams += nCreams * 3;
            System.out.println("Creams restocked!");
        }
    }
    public static void main(String[] args) {
        Cafe CC = new Cafe("Campus Center","100 Elm Street",2,30,10,10,30);
        CC.sellCoffee(12, 2, 2);
        CC.sellCoffee(16, 0, 2);
        CC.sellCoffee(18, 0, 2);

    }

}