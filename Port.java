package scalaLab13;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Port {
    private int existingTugs = 0;
    private final int maximumDocks;
    private boolean isOpen = true;
    private int existingDocks = 0;
    private final int maximumTugs;
   
    public Port(int maximumDocks, int maximumTugs) {
        this.maximumDocks = maximumDocks;
        this.maximumTugs = maximumTugs;
    }

   

    public boolean dock(Ship ship) {
        if (existingDocks < maximumDocks) {
            existingDocks++;
            return true;
        }
        return false;
    }
    public void close()
    
    
    {
        isOpen = false;
    
    
    }
    public boolean unDock(Ship ship) {
        if (existingTugs + ship.getTugs() < maximumTugs) {
            existingDocks -= 1;
            existingTugs += ship.getTugs();
            return true;
        }
        return false;
    }

    public void returnTugs(Ship ship) {
        existingTugs -= ship.getTugs();
    }

    @Override
    public String toString() {
        return "Port(" +
                "currentDocks=" + existingDocks +
                ", currentTugs=" + existingTugs +
                ')';
    }

    Runnable ships() {
        Random random = new Random();
        new Thread(info()).start();
        return () -> {
            int ID = 0;
            while (isOpen) {
                try {
                    TimeUnit.SECONDS.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Ship ship = new Ship(ID++);
                while (!dock(ship)) {
                    System.out.println("Port is full");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                new Thread(dockShip(ship)).start();
                System.out.println("DOCKED: "+ ship);
            }
        };
    }

    Runnable dockShip(Ship ship) {
        return () -> {
            try {
                TimeUnit.SECONDS.sleep(ship.getDockTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (!unDock(ship)) {
                System.out.println("Can't undock");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("UNDOCKED: " + ship);
            new Thread(tugShip(ship)).start();
        };
    }

    Runnable tugShip(Ship ship) {
        return () -> {
            try {
                TimeUnit.SECONDS.sleep(ship.getTugTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("FREED TUGS: " + ship);
            returnTugs(ship);
        };
    }

    Runnable info() {
        return () -> {
            while (isOpen) {
                System.out.println(this);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

}
