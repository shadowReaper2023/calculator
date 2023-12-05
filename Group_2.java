import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

public class Group_2 {
    private JFrame frame;
    private JPanel panel;
    private JTextField textField;
    private JTextField resultTextField;
    private JComboBox<String> conversionTypes;
    private JComboBox<String> conversionTo;
    private JLabel convertFrom;
    private JLabel convertToTypes;
    private JLabel inputValue;
    private JTextField readOnlyTextField;
    private JLabel readOnlyResult;
    private JButton clear;
    private JButton calculate;
    private Font newFont;
    private String[] conversionOptions = {"Square meters", "Square foot", "Square inches", "Acre", "Hectare"};

    public Group_2() {
        frame = new JFrame();
        panel = new JPanel();
        convertFrom = new JLabel("Convert from:");
        convertToTypes = new JLabel("Convert to:");
        inputValue = new JLabel("Value to convert:");
        readOnlyTextField = new JTextField("");
        readOnlyResult = new JLabel("Result:");
        textField = new JTextField(20);
        resultTextField = new JTextField(20);
        resultTextField.setEditable(false);
        clear = new JButton("Clear");
        calculate = new JButton("Calculate");
        newFont = new Font("Palatino Linotype", Font.BOLD, 13);
                
        panel.setLayout(null);
        convertFrom.setBounds(45, 20, 120, 25);
        conversionTypes = new JComboBox<>(conversionOptions);

        conversionTypes.setBounds(45, 40, 130, 25);
        convertToTypes.setBounds(45, 80, 80, 25);
        conversionTo = new JComboBox<>(conversionOptions);

        conversionTo.setBounds(45, 100, 130, 25);
        inputValue.setBounds(250, 20, 150, 25);
        textField.setBounds(250, 40, 175, 25);
        resultTextField.setBounds(250, 100, 175, 25);
        clear.setBounds(115, 180, 80, 25);
        calculate.setBounds(240, 180, 115, 25);

        convertFrom.setFont(newFont);
        convertToTypes.setFont(newFont);
        inputValue.setFont(newFont);
        readOnlyTextField.setFont(newFont);
        readOnlyResult.setFont(newFont);
        textField.setFont(newFont);
        resultTextField.setFont(newFont);
        
        panel.add(calculate);
        panel.add(clear);
        panel.add(readOnlyResult);
        panel.add(readOnlyTextField);
        panel.add(inputValue);
        panel.add(resultTextField);
        panel.add(convertToTypes);
        panel.add(convertFrom);
        panel.add(textField);
        panel.add(conversionTypes);
        panel.add(conversionTo);
        panel.setBackground(new Color(0, 173, 181));
        
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\user\\Desktop\\BSIT - 2B Files\\OOP-Activities\\Logo.png");
        frame.setIconImage(icon);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Area Converter");
        frame.setSize(500, 300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
       
        conversionTypes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateConvertToComboBox();
            }
        });
        updateConvertToComboBox();

        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double inputValue = Double.parseDouble(textField.getText());
                    String fromType = (String) conversionTypes.getSelectedItem();
                    String toType = (String) conversionTo.getSelectedItem();

                    double result = performConversion(inputValue, fromType, toType);

                    resultTextField.setText(String.valueOf(result));
                } catch (NumberFormatException ex) {
                    resultTextField.setText("Invalid input");
                }
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                textField.setText("");
                resultTextField.setText("");
                conversionTypes.setSelectedIndex(0);
                conversionTo.setSelectedIndex(0);
            }
        });
    }

    private void updateConvertToComboBox() {
        // Get the selected item in "Convert from"
        String selectedType = (String) conversionTypes.getSelectedItem();

        // Remove the selected item from "Convert to"
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(conversionOptions);
        model.removeElement(selectedType);
        conversionTo.setModel(model);
    }

    private double performConversion(double inputValue, String fromType, String toType) {
        double squareMetersConversionFactors[] = {1.0, 0.092903, 0.00064516, 4046.86, 10000.0};

        int fromIndex = Arrays.asList(conversionOptions).indexOf(fromType);
        int toIndex = Arrays.asList(conversionOptions).indexOf(toType);

        double result = inputValue * squareMetersConversionFactors[fromIndex] / squareMetersConversionFactors[toIndex];

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        result = Double.parseDouble(decimalFormat.format(result));

        return result;
    }

    public static void main(String[] args) {
        new Group_2();
    }
}