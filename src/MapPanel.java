import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MapPanel extends JPanel {
    private final List<Car> cars;

    private static final int TRACK_WIDTH = 450;
    private static final int TRACK_HEIGHT = 250;
    private static final int LANE_SPACING = 15;
    private static final int TRACK_MARGIN = 30;
    private static final int MAX_PROGRESS = 600;

    public MapPanel() {
        this.cars = new ArrayList<>();
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(600, 400));
    }

    public void addCar(Car car) {
        cars.add(car);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawTrack(g);

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            Point pos = calculatePositionOnLane(car.getProgress(), i);
            drawCar(g, pos, car);
        }
    }

    private void drawTrack(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setStroke(new BasicStroke(3));

        for (int i = 0; i < cars.size(); i++) {
            int laneOffset = i * LANE_SPACING;
            int x = TRACK_MARGIN + laneOffset;
            int y = TRACK_MARGIN + laneOffset;
            int width = TRACK_WIDTH - 2 * laneOffset;
            int height = TRACK_HEIGHT - 2 * laneOffset;
            g2d.drawRect(x, y, width, height);
        }
    }

    private void drawCar(Graphics g, Point position, Car car) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(car.getColor());
        int carSize = 12;

        g2d.fillOval(position.x - carSize / 2, position.y - carSize / 2, carSize, carSize);
    }

    private Point calculatePositionOnLane(int progress, int laneIndex) {
        int innerWidth = TRACK_WIDTH - laneIndex * 2 * LANE_SPACING;
        int innerHeight = TRACK_HEIGHT - laneIndex * 2 * LANE_SPACING;
        int perimeter = 2 * (innerWidth + innerHeight);

        double distance = (progress / (double) MAX_PROGRESS) * perimeter;

        int startX = TRACK_MARGIN + laneIndex * LANE_SPACING;
        int startY = TRACK_MARGIN + laneIndex * LANE_SPACING;

        int x = startX;
        int y = startY;

        if (distance <= innerWidth) {
            x += (int) distance;
        } else if (distance <= innerWidth + innerHeight) {
            x += innerWidth;
            y += (int) (distance - innerWidth);
        } else if (distance <= 2 * innerWidth + innerHeight) {
            x += innerWidth - (int) (distance - (innerWidth + innerHeight));
            y += innerHeight;
        } else {
            y += innerHeight - (int) (distance - (2 * innerWidth + innerHeight));
        }

        return new Point(x, y);
    }
}
