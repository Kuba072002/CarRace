import java.util.ArrayList;
import java.util.List;

public class Race {
    private final List<Car> cars = new ArrayList<>();
    private final List<Thread> threads = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    public void startRace() {
        long startTime = System.currentTimeMillis();

        for (Car car : cars) {
            Thread thread = new Thread(car);
            threads.add(thread);
            thread.start();
        }

        new Thread(() -> {
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            long endTime = System.currentTimeMillis();
        }).start();
    }
}
