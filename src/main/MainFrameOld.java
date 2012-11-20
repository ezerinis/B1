// Interfeiso klase - cia isdelioti naudotojo grafinio interfeiso elementai
// Pagrindinis langas

package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import utilities.MatrixGenerator;

public class MainFrameOld extends JFrame {

    private JTextField qField = new JTextField("2", 11);
    private JTextField kField = new JTextField("5", 11);
    private JTextField nField = new JTextField("5", 11);

    private ParameterDialog pd = new ParameterDialog(this, qField.getText(),
            nField.getText(), kField.getText());

    private MatrixGenerator mg = new MatrixGenerator();

    public MainFrameOld() {
        JPanel fieldPanel = new JPanel(new BorderLayout());
        fieldPanel.add(createFieldPanel("q: ", qField), BorderLayout.NORTH);
        fieldPanel.add(createFieldPanel("k: ", kField), BorderLayout.CENTER);
        fieldPanel.add(createFieldPanel("n: ", nField), BorderLayout.SOUTH);

        JButton changeParButton = new JButton("Pakeisti parametrus");
        changeParButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                pd.showDialog();
                qField.setText(pd.getQ());
                kField.setText(pd.getK());
                nField.setText(pd.getN());
            }
        });

        JPanel parameterPanel = new JPanel(new BorderLayout());
        parameterPanel.add(fieldPanel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(changeParButton);
        parameterPanel.add(buttonPanel, BorderLayout.CENTER);

        JPanel parameterPanel2 = new JPanel();
        parameterPanel2.add(parameterPanel);

        JRadioButton directMode = new JRadioButton("Tiesioginis perrinkimas", true);
        JRadioButton macWilliamsMode = new JRadioButton("MacWilliams tapatybe", false);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(directMode);
        buttonGroup.add(macWilliamsMode);
        JButton calculate = new JButton("Skaiciuoti");

        JPanel radioPanel = new JPanel(new BorderLayout());
        radioPanel.add(directMode, BorderLayout.NORTH);
        radioPanel.add(macWilliamsMode, BorderLayout.CENTER);
        radioPanel.add(calculate, BorderLayout.SOUTH);

        JPanel radioPanel2 = new JPanel();
        radioPanel2.add(radioPanel);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(parameterPanel2, BorderLayout.WEST);
        mainPanel.add(radioPanel2, BorderLayout.CENTER);

        JPanel mainPanel2 = new JPanel(new BorderLayout());
        mainPanel2.add(mainPanel, BorderLayout.WEST);

        setContentPane(mainPanel2);

        setSize(600, 450);
        setTitle("B1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createFieldPanel(String label, JTextField textField) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(label));
        textField.setEnabled(false);
        textField.setFocusable(false);
        panel.add(textField);
        return panel;
    }
}
