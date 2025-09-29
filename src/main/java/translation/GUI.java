package translation;

import javax.swing.*;
import java.awt.event.*;

// TODO Task D: Update the GUI for the program to align with UI shown in the README example.
public class GUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // =======================
            // Country Panel
            // =======================
            JPanel countryPanel = new JPanel();
            String[] countries = {
                    "Canada", "USA", "Mexico", "France", "Germany", "Italy", "Spain", "China", "Brazil", "Japan"
            }; // add more countries as needed
            JList<String> countryList = new JList<>(countries);
            countryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            countryList.setSelectedIndex(0); // default selection
            JScrollPane countryScrollPane = new JScrollPane(countryList);
            countryPanel.add(new JLabel("Country:"));
            countryPanel.add(countryScrollPane);

            // =======================
            // Language Panel
            // =======================
            JPanel languagePanel = new JPanel();
            String[] languages = {
                    "en", "fr", "es", "de", "it", "pt", "zh", "ja", "ru"
            }; // add more language codes as needed
            JComboBox<String> languageDropdown = new JComboBox<>(languages);
            languagePanel.add(new JLabel("Language:"));
            languagePanel.add(languageDropdown);

            // =======================
            // Button & Result Panel
            // =======================
            JPanel buttonPanel = new JPanel();
            JButton submit = new JButton("Submit");
            buttonPanel.add(submit);

            JLabel resultLabelText = new JLabel("Translation:");
            buttonPanel.add(resultLabelText);
            JLabel resultLabel = new JLabel("\t\t\t\t\t\t\t");
            buttonPanel.add(resultLabel);

            // =======================
            // Submit Button Listener
            // =======================
            submit.addActionListener(e -> translate(countryList, languageDropdown, resultLabel));

            // =======================
            // Dynamic Update Listener
            // =======================
            languageDropdown.addActionListener(e -> translate(countryList, languageDropdown, resultLabel));
            countryList.addListSelectionListener(e -> translate(countryList, languageDropdown, resultLabel));

            // =======================
            // Main Frame
            // =======================
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.add(countryPanel);
            mainPanel.add(languagePanel);
            mainPanel.add(buttonPanel);

            JFrame frame = new JFrame("Country Name Translator");
            frame.setContentPane(mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }

    // =======================
    // Helper method to translate
    // =======================
    private static void translate(JList<String> countryList, JComboBox<String> languageDropdown, JLabel resultLabel) {
        String language = (String) languageDropdown.getSelectedItem();
        String country = countryList.getSelectedValue();
        Translator translator = new CanadaTranslator(); // replace with JSON translator later

        String result = translator.translate(country, language);
        if (result == null) {
            result = "no translation found!";
        }
        resultLabel.setText(result);
    }
}
