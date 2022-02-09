package Model;

import View.Printer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Car implements Runnable {

    public static final int WAIT_PERIOD_TIME = 1000;// milsecs
    private Thread thread;
    private CarPark carPark;
    private int parkingTime; // milsecs
    private int waitingTime; // milsecs
    private String name;

    private static Lock lock;

    static {
        lock = new ReentrantLock();
    }

    public Car() {

    }

    public Car(String name, int parkingTime, int waitingTime, CarPark carPark) {

        this.name = name;
        this.parkingTime = parkingTime;
        this.waitingTime = waitingTime;
        this.carPark = carPark;

        thread = new Thread(this);

    }

    public int getParkingTime() {
        return parkingTime;
    }

    public Thread getThread() {
        return thread;
    }

    public void waitTime(int time) {
        try {
            Printer.print(this + " is waiting " + time + "... \n");
            TimeUnit.MILLISECONDS.sleep(WAIT_PERIOD_TIME);

        } catch (InterruptedException ex) {
            Printer.print(ex.getMessage());
        }
    }

    public void beOnParking(int time) {
        try {
            Printer.print(this + " has entered to carPark at "
                    + time + " milliseconds of waiting time. "
                    + " Now is on the carPark!!!");
            TimeUnit.MILLISECONDS.sleep(getParkingTime());
            Printer.print(carPark.toString());
            carPark.removeCarFromParking(this);
            Printer.print(this + " has left the carPark!!!");
        } catch (InterruptedException ex) {
            Printer.print(ex.getMessage());
        }

    }

    @Override
    public String toString() {
        return name + "  ---  " + parkingTime + " // " + waitingTime;
    }

    @Override
    public void run() {

        int secCounter = 0;
        while (secCounter <= waitingTime) {
            if ( !(carPark.isAllPlacesInRark()) && lock.tryLock() ) {
                try {
                    carPark.addCarOnParking(this);

                } finally {
                    lock.unlock();
                    beOnParking(secCounter);
                    break;
                }
            } else {
                secCounter += WAIT_PERIOD_TIME;
                waitTime(secCounter);
                if (secCounter == waitingTime) {
                    Printer.print(this + " has left carPark queue!!!");
                    return;
                }

            }

        }

    }

}
