package Controller;

import Model.Car;
import Model.CarPark;
import View.Printer;

import java.util.LinkedList;


public class Main {

    public static void main(String[] args){


        CarPark carPark = new CarPark(2);
        LinkedList<Car> cars = new LinkedList<>();

        cars.add(new Car("Mazda", 5000, 15000, carPark));
        cars.add(new Car("Volga", 3000, 10000, carPark));
        cars.add(new Car("Porsche", 5000, 15000, carPark));
        cars.add( new Car("Ferrari", 7000, 1000, carPark));
        cars.add( new Car("Tesla", 3000, 1000, carPark));

        for (Car item : cars){
            item.getThread().start();

        }
        Printer.print("Main thread is ended!!!");
    }
}
