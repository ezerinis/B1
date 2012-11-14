package main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import utilities.MathOperations;

public class ParameterDialog extends JDialog {

    public static final int OK_OPTION = 0;
    public static final int CANCEL_OPTION = 1;

    private static final int MAX = 2147483647;

    private int result = -1;

    private JTextField qField = new JTextField(3);
    private JTextField nField = new JTextField(3);
    private JTextField kField = new JTextField(3);

    private String qValue;
    private String nValue;
    private String kValue;

    MathOperations mo = new MathOperations();

    public ParameterDialog(JFrame owner, String qInput, String nInput, String kInput) {
        super(owner, "Parametru ivedimas", ModalityType.APPLICATION_MODAL);

        qValue = qInput;
        nValue = nInput;
        kValue = kInput;

        JPanel fieldsPanel = new JPanel(new BorderLayout());
        fieldsPanel.add(createFieldPanel("q: ", qField), BorderLayout.WEST);
        fieldsPanel.add(createFieldPanel("n: ", nField), BorderLayout.CENTER);
        fieldsPanel.add(createFieldPanel("k: ", kField), BorderLayout.EAST);

        JButton confirmButton = new JButton("Patvirtinti");
        confirmButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    checkValue(qField.getText(), "q");
                    checkValue(nField.getText(), "n");
                    checkValue(kField.getText(), "k");
                    if (mo.isPrime(Integer.parseInt(qField.getText()))) {
                        qValue = qField.getText();
                        nValue = nField.getText();
                        kValue = kField.getText();
                        result = OK_OPTION;
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(ParameterDialog.this, "'q' privalo buti pirminis",
                                "Klaida", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ParameterDialog.this, ex.getMessage(),
                            "Klaida", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void checkValue(String value, String field) throws Exception {
                if (value.equals("")) {
                    throw new Exception("'" + field + "' negali buti tuscias");
                }

                int intValue;
                try {
                    intValue = Integer.parseInt(value);
                } catch (Exception ex) {
                    throw new Exception("'" + field + "' negali virsyti " + MAX);
                }

                if (intValue == 0) {
                    throw new Exception("'" + field + "' negali buti lygus 0");
                } else if (intValue > MAX) {
                    throw new Exception("'" + field + "' negali virsyti " + MAX);
                }
            }
        });

        JButton cancelButton = new JButton("Atmesti");
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                result = CANCEL_OPTION;
                setVisible(false);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(fieldsPanel, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        setSize(250, 120);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public int showDialog() {
        qField.setText(qValue);
        nField.setText(nValue);
        kField.setText(kValue);
        setLocationRelativeTo(null);
        setVisible(true);
        return result;
    }

    public String getQ() {
        return qValue;
    }

    public String getN() {
        return nValue;
    }

    public String getK() {
        return kValue;
    }

    private JPanel createFieldPanel(String label, JTextField textField) {
        addValidation(textField);
        JPanel panel = new JPanel();
        panel.add(new JLabel(label));
        panel.add(textField);
        return panel;
    }

    private void addValidation(JTextField field) {
        field.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9')
                        || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
    }
}