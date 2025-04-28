
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RaceExecutor extends Race {
    private ExecutorService executor;

    @Override
    public void startRace() {
        executor = Executors.newFixedThreadPool(cars.size());

        new Thread(() -> {
            for (Car car : cars) {
                executor.execute(car);
            }
        }).start();

    }
}
