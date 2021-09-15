import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Period;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * This class creates all the GUI and handling of dates, age calculation.
 */
public class CalculatorGUI implements Runnable {
    CalculatorGUI() {
        JFrame frame = new JFrame("CMPT 213 Age Calculator");
        frame.setSize(450, 200);
        JPanel panel = new JPanel();

        JPanel textPanel = new JPanel();
        textPanel.setPreferredSize(new Dimension(410, 100));
        textPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20), BorderFactory.createTitledBorder("Your age as of today")));

        JLabel textLabel = new JLabel("Enter your date of birth and press the \"Check Age\" button");
        textPanel.add(textLabel);

        JPanel bottomPanel = new JPanel();
        JButton checkButton = new JButton("Check Age");
        DatePicker datePicker = new DatePicker();
        DatePickerSettings settings = new DatePickerSettings();

        datePicker.setSettings(settings);
        settings.setDateRangeLimits(null, LocalDate.now());

        checkButton.addActionListener(e -> {
            LocalDate now = LocalDate.now();
            LocalDate date = datePicker.getDate();
            if (date == null) {
                JOptionPane.showMessageDialog(null, "Error: no date.");
                return;
            }
            int age = calculateAge(date);
            textLabel.setText("You are " + age + " years old!");
            if (now.getMonth() == date.getMonth() && now.getDayOfMonth() == date.getDayOfMonth()) {
                textLabel.setText("You are " + age + " years old! Happy Birthday!");
            }
            textPanel.revalidate();
            textPanel.repaint();

        });

        bottomPanel.add(checkButton);
        bottomPanel.add(datePicker);
        panel.add(textPanel);
//        frame.add(Box.createRigidArea(new Dimension(0,20))); //WORKS FINE WITHOUT RIGIDAREA SO IM ASSUMING TO COMMENT IT OUT
        panel.add(bottomPanel);
        frame.add(panel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
//referenced from this site: https://www.geeksforgeeks.org/period-between-method-in-java-with-examples/
    int calculateAge(LocalDate date) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(date,now);
        return period.getYears();
    }


    @Override
    public void run() {

    }
}
