package scalaLab13;

public class Ship implements Runnable  {

	private int id;
	private int secondsToUnload;
	private int secondsToLoad;
	private int secondsToTravel;
	private int secondsToDock;
	private int tugsNeededToDock;

	private Port port;


	Ship(int id, int secondsToUnload, int secondsToLoad, int secondsToTravel, int tugsNeededToDock, int secondsToDock) {

		this.id = id;
		this.secondsToUnload = secondsToUnload;
		this.secondsToLoad = secondsToLoad;
		this.secondsToTravel = secondsToTravel;
		this.tugsNeededToDock = tugsNeededToDock;
		this.secondsToDock = secondsToDock;
	}



	int getSecondsToUnload() {

		return secondsToUnload;
	}

	void setSecondsToUnload(int secondsToUnload) {

		this.secondsToUnload = secondsToUnload;
	}

	int getSecondsToLoad() {

		return secondsToLoad;
	}

	void setSecondsToLoad(int secondsToLoad) {

		this.secondsToLoad = secondsToLoad;
	}

	int getSecondsToTravel() {

		return secondsToTravel;
	}

	void setSecondsToTravel(int secondsToTravel) {

		this.secondsToTravel = secondsToTravel;
	}

	int getTugsNeededToDock() {

		return tugsNeededToDock;
	}

	void setTugsNeededToDock(int tugsNeededToDock) {

		this.tugsNeededToDock = tugsNeededToDock;
	}

	int getId() {

		return id;
	}

	int getSecondsToDock() {

		return secondsToDock;
	}

	void setSecondsToDock(int secondsToDock) {

		this.secondsToDock = secondsToDock;
	}

	Port getPort() {

		return port;
	}

	void setPort(Port port) {

		this.port = port;
	}

	@Override
	public void run() {

		try {
			Thread.sleep(this.secondsToDock * 1000);
			System.out.println("Ship id "+this.getId()+" is unloading");
			Thread.sleep(this.secondsToUnload * 1000);
			System.out.println("Ship id "+this.getId()+" is leaving the dock");
			Thread.sleep(this.secondsToDock * 1000);
			this.port.freeTugs(this.getTugsNeededToDock());
			this.port.freeDock();
			System.out.println("Ship id "+this.getId()+" is left the dock");
			Thread.sleep(this.secondsToTravel * 1000);
			System.out.println("Ship id "+this.getId()+" is loading");
			Thread.sleep(this.secondsToLoad * 1000);
			System.out.println("Ship id "+this.getId()+" is back in queue");
			this.port.getQueue().put(this);
		}
		catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
