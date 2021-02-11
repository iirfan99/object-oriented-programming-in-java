package scalaLab13;



import java.util.concurrent.TimeUnit;

public class Testing {


    public static void main(String[] args) throws InterruptedException {
        Port port = new Port(10, 50);
        new Thread(port.ships()).start();
        TimeUnit.SECONDS.sleep(400);
        port.close();
    }


}