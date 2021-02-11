package scalaLab13;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

	static BlockingQueue<Ship> ships ;//= new LinkedBlockingQueue<>();

	//static List<Ship> ships = new ArrayList<>();


	public static void main(String args[]) throws InterruptedException {
		generateShipsManually();
		Port port = createPortForShips();
		port.setQueue(ships);
		new Thread(port).start();
		System.out.println("Finished running.");

	}

	public static void generateShipsManually() throws InterruptedException {
		Ship ship1 = new Ship(1,3, 4,5, 3, 3);
		Ship ship2 = new Ship(2,2, 2,3, 1,3);
		Ship ship3 = new Ship(3,4, 5,2, 4,2);
		Ship ship4 = new Ship(4,3, 3,4, 3,2);
		Ship ship5 = new Ship(5,3, 3,3, 3,5);
		Ship ship6 = new Ship(6,3, 3,3, 3,3);
		ships = new LinkedBlockingQueue<>(6);
		ships.put(ship1);
		ships.put(ship2);
		ships.put(ship3);
		ships.put(ship4);
		ships.put(ship5);
		ships.put(ship6);
	}

	public static Port createPortForShips(){
		int numberOfDocksNeededByShips =0, numberOfTugsNeededByShips =0;
		if(ships != null &&  !ships.isEmpty()){
			for (Ship s : ships){
				numberOfDocksNeededByShips++;
				numberOfTugsNeededByShips += s.getTugsNeededToDock();
			}
		}
		return new Port(numberOfDocksNeededByShips-1, numberOfTugsNeededByShips-3);
	}

}
