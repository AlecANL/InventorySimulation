import java.util.concurrent.atomic.AtomicInteger;

class Inventory {
    private final AtomicInteger inventory;
    private final int initialInventory;
    private int totalOfDeliveries = 0;
    private int totalOfSales = 0;


    public Inventory(int initialInventory) {
        this.initialInventory = initialInventory;
        this.inventory = new AtomicInteger(initialInventory);
    }

    public synchronized void addToInventory() {
        inventory.getAndIncrement();
        this.totalOfDeliveries++;
    }

    public synchronized void executeSale() {
        if (this.inventory.get() > 0) {
            inventory.getAndDecrement();
            this.totalOfSales++;
        }
    }

    public int getTotalOfDeliveries() {
        return this.totalOfDeliveries;
    }

    public int getTotalOfSales() {
        return this.totalOfSales;
    }

    public int getInitialInventory() {
        return this.initialInventory;
    }

    public int getFinalInventory() {
        return (this.getInitialInventory() + this.getTotalOfDeliveries()) - this.getTotalOfSales();
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int SALES = 100;
        final int DELIVERIES = 100;
        Inventory inventory = new Inventory(5);

        Runnable runnableDeliveries = () -> {
            for (int i = 0; i < DELIVERIES; i++) {
                inventory.addToInventory();
            }
        };

        Runnable runnableSales = () -> {
            for (int i = 0; i < SALES; i++) {
                inventory.executeSale();
            }
        };

        Thread t1_d = new Thread(runnableDeliveries);
        Thread t2_d = new Thread(runnableDeliveries);
        Thread t3_d = new Thread(runnableDeliveries);

        Thread t1_s = new Thread(runnableSales);
        Thread t2_s = new Thread(runnableSales);

        t1_d.start();
        t2_d.start();
        t3_d.start();

        t1_s.start();
        t2_s.start();

        t1_d.join();
        t2_d.join();
        t3_d.join();

        t1_s.join();
        t2_s.join();

        System.out.println("Inventario inicial: " + inventory.getInitialInventory());
        System.out.println("Total de entregas realizadas: " + inventory.getTotalOfDeliveries());
        System.out.println("Total de ventas realizadas: " + inventory.getTotalOfSales());
        System.out.println("Inventario Final: " + inventory.getFinalInventory());

    }
}