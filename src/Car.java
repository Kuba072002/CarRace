import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Car implements Runnable {
    private final MapPanel mapPanel;
    private static final int MAX_POSITION = 400;
    private static final int SLEEP = 50;
    private static final int MIN_DISTANCE = 1;
    private static final int MAX_DISTANCE = 4;

    private final JProgressBar progressBar;
    private final String carName;
    private final Random random = new Random();

    public Car(String carName, String colorName, JProgressBar progressBar, MapPanel mapPanel) {
        this.carName = carName;
        this.progressBar = progressBar;
        this.mapPanel = mapPanel;
        this.progressBar.setMaximum(MAX_POSITION);
        this.progressBar.setStringPainted(true);
        this.progressBar.setString(carName);
        Font currentFont = progressBar.getFont();
        this.progressBar.setFont(new Font(currentFont.getName(), currentFont.getStyle(), 24));
        this.progressBar.setForeground(Color.decode(colorName));
        this.progressBar.setBackground(Color.decode("#B9B7BD"));
    }

    @Override
    public void run() {
        int position = 0;
        while (position < MAX_POSITION) {
            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            position += MIN_DISTANCE + random.nextInt(MAX_DISTANCE - MIN_DISTANCE);
            final int currentPosition = position;
            SwingUtilities.invokeLater(() -> {
                progressBar.setValue(currentPosition);
                mapPanel.repaint();
            });
        }
        System.out.println(carName + " finished the race!");
    }

    public int getProgress() {
        return progressBar.getValue();
    }
    
    public Color getColor() {
        return progressBar.getForeground();
    }
}
