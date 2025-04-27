import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class RaceFrame extends JFrame {
    private static final String CONFIG_FILE = "cars.properties";
    private final Race race = new Race();

    public RaceFrame() {
        setTitle("PROB - Car Race Simulation - Threads & Runnable");
        setSize(1500, 1200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Image icon = Toolkit.getDefaultToolkit().getImage("images/icon.jpg");
        setIconImage(icon);

        Properties properties = loadConfig();
        int numberOfCars = Integer.parseInt(properties.getProperty("number_of_cars", "4"));

        JPanel panel = new JPanel(new GridLayout(numberOfCars, 1, 10, 10));
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));
        add(panel, BorderLayout.CENTER);

        setBackground(Color.decode(properties.getProperty("background.color", "GRAY")));

        for (int i = 1; i <= numberOfCars; i++) {
            String name = properties.getProperty("car." + i + ".name", "Car " + i);
            String colorName = properties.getProperty("car." + i + ".color", "GRAY");

            JProgressBar progressBar = new JProgressBar();
            panel.add(progressBar);
            Car car = new Car(name, colorName, progressBar);
            race.addCar(car);
        }

        JButton startButton = new JButton("Start Race");

        startButton.setBackground(Color.decode(properties.getProperty("button.color", "#4CAF50")));
        startButton.setForeground(Color.decode(properties.getProperty("text.color", "#FFFFFF")));
        startButton.setFocusPainted(false);
        startButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        startButton.setPreferredSize(new Dimension(200, 60));
        startButton.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);

        startButton.addActionListener((ActionEvent e) -> {
            startButton.setEnabled(false);
            race.startRace();
        });
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private Properties loadConfig() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RaceFrame::new);
    }
}
