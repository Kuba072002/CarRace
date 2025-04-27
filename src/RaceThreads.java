import java.util.ArrayList;
import java.util.List;

public class RaceThreads extends Race {
    private final List<Thread> threads = new ArrayList<>();

    @Override
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
