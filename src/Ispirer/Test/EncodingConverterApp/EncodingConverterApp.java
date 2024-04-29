package Ispirer.Test.EncodingConverterApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class EncodingConverterApp extends JFrame {
    private JTextField inputFilePathField;
    private JTextField outputFilePathField;
    private JComboBox<String> inputEncodingComboBox;
    private JComboBox<String> outputEncodingComboBox;

    public EncodingConverterApp() {
        setTitle("File Encoding Converter");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        JLabel inputLabel = new JLabel("Input File Path:");
        inputFilePathField = new JTextField(20);
        add(inputLabel);
        add(inputFilePathField);

        JLabel outputLabel = new JLabel("Output File Path:");
        outputFilePathField = new JTextField(20);
        add(outputLabel);
        add(outputFilePathField);

        JLabel inputEncodingLabel = new JLabel("Input Encoding:");
        String[] encodings = {"windows-1251", "UTF-8"};
        inputEncodingComboBox = new JComboBox<>(encodings);
        add(inputEncodingLabel);
        add(inputEncodingComboBox);

        JLabel outputEncodingLabel = new JLabel("Output Encoding:");
        outputEncodingComboBox = new JComboBox<>(encodings);
        add(outputEncodingLabel);
        add(outputEncodingComboBox);

        JButton convertButton = new JButton("Convert File");
        add(convertButton);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertFile();
            }
        });

        setVisible(true);
    }

    private void convertFile() {
        String inputFilePath = inputFilePathField.getText();
        String outputFilePath = outputFilePathField.getText();
        String inputEncoding = (String) inputEncodingComboBox.getSelectedItem();
        String outputEncoding = (String) outputEncodingComboBox.getSelectedItem();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilePath), inputEncoding));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilePath), outputEncoding)) ) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            JOptionPane.showMessageDialog(this, "File successfully converted", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error converting file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EncodingConverterApp());
    }
}
