import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class RegistrationForm extends JFrame {

    public RegistrationForm() {
        setTitle("User Registration Form");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main container
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel("User Information Form", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);
        gbc.gridwidth = 1;

        // Name
        gbc.gridy++;
        panel.add(new JLabel("Name:"), gbc);
        JTextField nameField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        // Mobile
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Mobile:"), gbc);
        JTextField mobileField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(mobileField, gbc);

        // Gender
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Gender:"), gbc);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        JRadioButton other = new JRadioButton("Other");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);
        genderPanel.add(male);
        genderPanel.add(female);
        genderPanel.add(other);
        gbc.gridx = 1;
        panel.add(genderPanel, gbc);

        // DOB
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Date of Birth:"), gbc);
        // DOB using dropdowns
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) days[i] = String.valueOf(i + 1);

        String[] months = { "January", "February", "March", "April", "May", "June",
                            "July", "August", "September", "October", "November", "December" };

        String[] years = new String[100];
        for (int i = 0; i < 100; i++) years[i] = String.valueOf(2025 - i); // From 2025 to 1926

        JComboBox<String> dayBox = new JComboBox<>(days);
        JComboBox<String> monthBox = new JComboBox<>(months);
        JComboBox<String> yearBox = new JComboBox<>(years);

        // Make all boxes the same size
        dayBox.setPreferredSize(new Dimension(60, 25));
        monthBox.setPreferredSize(new Dimension(100, 25));
        yearBox.setPreferredSize(new Dimension(80, 25));

        // Group them in one horizontal line
        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        dobPanel.add(dayBox);
        dobPanel.add(monthBox);
        dobPanel.add(yearBox);

        gbc.gridx = 1;
        panel.add(dobPanel, gbc);


        // Address
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Address:"), gbc);
        JTextArea addressArea = new JTextArea(3, 20);
        JScrollPane addressScroll = new JScrollPane(addressArea);
        gbc.gridx = 1;
        panel.add(addressScroll, gbc);

        // Submit button
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton submitButton = new JButton("Submit");
        panel.add(submitButton, gbc);
        


        // Action Listener
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String gender = male.isSelected() ? "Male" :
                                female.isSelected() ? "Female" : 
                                other.isSelected() ? "Other" : "Not Selected";

                String message = "Name: " + nameField.getText() +
                                 "\nMobile: " + mobileField.getText() +
                                 "\nGender: " + gender +
                                 "\nDOB: " + dayBox.getSelectedItem() + " " + monthBox.getSelectedItem() + " " + yearBox.getSelectedItem() +    
                                 "\nAddress: " + addressArea.getText();

                JOptionPane.showMessageDialog(RegistrationForm.this, message, "Submitted Information", JOptionPane.INFORMATION_MESSAGE);
                // Save to file
                // Save to file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("userdata.txt", true))) {
                    writer.write("----- User Entry -----\n");
                    writer.write(message + "\n\n");
                    writer.flush();
                    JOptionPane.showMessageDialog(RegistrationForm.this, "Data saved to userdata.txt", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(RegistrationForm.this, "Error saving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }


                }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm());
    }
}
