    package Model;

    import java.util.LinkedList;
    import java.util.List;

    public class CarPark {

        private volatile List<Car> carsOnParking;
        private int count;

        public CarPark() {
            carsOnParking = new LinkedList<>();

        }

        public CarPark(int count) {
            this();
            this.count = count;
        }

        public CarPark(List<Car> cars, int count) {
            this.count = count;
            carsOnParking = cars;

        }

        public synchronized void addCarOnParking(Car car) {
            if (isFreePlaceInPark()) {
                carsOnParking.add(car);
            }

        }

        public synchronized void removeCarFromParking(Car car) {
            carsOnParking.remove(car);
        }

        @Override
        public String toString() {
            StringBuilder msg = new StringBuilder();
            msg.append("\t\tParking: \n");
            if (!carsOnParking.isEmpty()) {
                for (Car car : carsOnParking) {
                    msg.append(car).append("\n");
                }
            } else {
                msg.append(" is empty");
            }
            msg.append("\n");
            return msg + "";
        }



        public synchronized List<Car> getCarsOnParking() {
            return carsOnParking;
        }

        public boolean isFreePlaceInPark() {

            return carsOnParking.size() <= count;
        }

        public  boolean isAllPlacesInRark() {

            return carsOnParking.size() == count;
        }

    }
