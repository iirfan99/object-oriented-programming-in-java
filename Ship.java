package scalaLab13;
	import java.util.Random;

	public class Ship {
	    private final int ID;
	    final private int tugs;
	    final private int dockTime;
	    final private int tugTime;
	    private boolean isDocked;

	    public Ship(int ID) {
	        this.ID = ID;
	        Random random = new Random();
	        this.tugs = random.nextInt(8) + 2;
	        this.dockTime = random.nextInt(8) + 2;
	        this.tugTime = random.nextInt(2) + 1;
	    }

	    @Override
	    public String toString() {
	        return "Ship(" +
	                "ID=" + ID +
	                ", tugs=" + tugs +
	                ", dockTime=" + dockTime +
	                ", tugTime=" + tugTime +
	                ", isDocked=" + isDocked +
	                ')';
	    }
	    
	    
	    
	    
	    public void setDocked(boolean docked) {
	        this.isDocked = docked;
	    }

	   
	    
	    
	    
	    public int getTugTime() {
	        return tugTime;
	    }

	    
	    
	    public int getTugs() {
	        return tugs;
	    }

	    public int getDockTime() {
	        return dockTime;
	    }

	    public boolean isDocked() {
	        return isDocked;
	    }

	  
	   
	}