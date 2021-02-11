package scalaLab13;


import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Port implements Runnable {

	private int docks;
	private int occupiedDocks =0;
	private int tugs;
	private int occupiedTugs =0;

	private BlockingQueue<Ship> queue;

	public Port(int docks, int tugs) {
		this.docks = docks;
		this.tugs = tugs;
		this.queue = new LinkedBlockingQueue<>();
	}

	public synchronized boolean isThereAnyFreeDocks(){
		return docks > occupiedDocks;
	}

	public synchronized void occupyDock(){
		occupiedDocks++;
	}

	public synchronized void freeDock(){
		occupiedDocks--;
	}

	public synchronized boolean isThereFreeTugsToUse(int neededTugs){
		return tugs - occupiedTugs >= neededTugs;
	}

	public synchronized void occupyTugs(int neededTugs){
		occupiedTugs += neededTugs;
	}

	public synchronized void freeTugs(int neededTugs){
		occupiedTugs -= neededTugs;
	}

	public BlockingQueue<Ship> getQueue() {

		return queue;
	}

	public void setQueue(BlockingQueue<Ship> queue) {

		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			while (true) {
				if(isThereAnyFreeDocks()){
					Ship ship = queue.take();
					System.out.println("Ship id "+ship.getId()+" retrieved from queue");
					ship.setPort(this);
					while(!isThereFreeTugsToUse(ship.getTugsNeededToDock())){
						Thread.currentThread().sleep(1000);
						System.out.println("Ship id "+ship.getId()+" waiting for a dock to be freed");
					}
					occupyTugs(ship.getTugsNeededToDock());
					occupyDock();
					System.out.println("Ship id "+ship.getId()+" docked");
					new Thread(ship).start();
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
