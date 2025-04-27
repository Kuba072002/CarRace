import java.util.ArrayList;
import java.util.List;

public abstract class Race {
    protected final List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    public void startRace() {
    }
}
