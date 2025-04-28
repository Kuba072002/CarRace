import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Properties;

public class RaceFrame extends JFrame {

    private final Race race = new RaceThreads();
//    private final Race race = new RaceExecutor();
    private final MapPanel mapPanel = new MapPanel();

    public RaceFrame() {
        Properties properties = Utils.loadConfig();
        int numberOfCars = Integer.parseInt(properties.getProperty("number_of_cars", "4"));
    
        setTitle("PWIO - Car Race Simulation - Threads & Runnable");
        setSize(1200, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.decode(properties.getProperty("background.color", "GRAY")));
        setLayout(new BorderLayout());
    
        Image icon = Toolkit.getDefaultToolkit().getImage("images/icon.jpg");
        setIconImage(icon);
    
        JPanel panel = new JPanel(new GridLayout(numberOfCars, 1, 10, 10));
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));
        add(panel, BorderLayout.NORTH);
    
        createCars(numberOfCars, properties, panel);
    
        add(mapPanel, BorderLayout.CENTER);
    
        JButton startButton = createButton(properties);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        add(buttonPanel, BorderLayout.SOUTH);
    
        setVisible(true);
    }

    private JButton createButton(Properties properties) {
        JButton startButton = new JButton("Start Race");

        startButton.setBackground(Color.decode(properties.getProperty("button.color", "#4CAF50")));
        startButton.setForeground(Color.decode(properties.getProperty("text.color", "#FFFFFF")));
        startButton.setFocusPainted(false);
        startButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        startButton.setPreferredSize(new Dimension(200, 60));
        startButton.setFont(new Font("Arial", Font.BOLD, 22));

        startButton.addActionListener((ActionEvent e) -> {
            startButton.setEnabled(false);
            race.startRace();
        });
        return startButton;
    }

    private void createCars(int numberOfCars, Properties properties, JPanel panel) {
        for (int i = 1; i <= numberOfCars; i++) {
            String name = properties.getProperty("car." + i + ".name", "Car " + i);
            String colorName = properties.getProperty("car." + i + ".color", "GRAY");
            int enginePower = Integer.parseInt(properties.getProperty("car." + i + ".engine_power", "1"));

            JProgressBar progressBar = new JProgressBar();
            panel.add(progressBar);
            Car car = new Car(name, colorName, enginePower, progressBar, mapPanel);
            mapPanel.addCar(car);
            race.addCar(car);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RaceFrame::new);
    }
}
