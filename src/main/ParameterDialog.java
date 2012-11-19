// Interfeiso klase - cia isdelioti naudotojo grafinio interfeiso elementai
// Parametru 'k', 'n' ir 'q' ivedimo dialogas

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

public class ParameterDialog extends JDialog {

    public static final int OK_OPTION = 0;
    public static final int CANCEL_OPTION = 1;

    private static final int MAX = 2147483647;

    private int result = -1;

    private JTextField qField = new JTextField(3);
    private JTextField kField = new JTextField(3);
    private JTextField nField = new JTextField(3);

    private String qValue;
    private String kValue;
    private String nValue;

    public ParameterDialog(JFrame owner, String qInput, String nInput, String kInput) {
        super(owner, "Parametru ivedimas", ModalityType.APPLICATION_MODAL);

        qValue = qInput;
        kValue = kInput;
        nValue = nInput;

        JPanel fieldsPanel = new JPanel(new BorderLayout());
        fieldsPanel.add(createFieldPanel("q: ", qField), BorderLayout.WEST);
        fieldsPanel.add(createFieldPanel("k: ", kField), BorderLayout.CENTER);
        fieldsPanel.add(createFieldPanel("n: ", nField), BorderLayout.EAST);

        JButton confirmButton = new JButton("Patvirtinti");
        confirmButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    checkValue(qField.getText(), "q");
                    checkValue(kField.getText(), "k");
                    checkValue(nField.getText(), "n");
                    if (!isPrime(Integer.parseInt(qField.getText()))) {
                        JOptionPane.showMessageDialog(ParameterDialog.this, "'q' privalo buti pirminis",
                                "Klaida", JOptionPane.ERROR_MESSAGE);
                    } else if (Integer.parseInt(kField.getText()) > Integer.parseInt(nField.getText())) {
                        JOptionPane.showMessageDialog(ParameterDialog.this, "Eiluciu "
                                + "skaicius 'k' negali buti daugiau uz stulpeliu skaiciu 'n'",
                                "Klaida", JOptionPane.ERROR_MESSAGE);
                    } else {
                        qValue = qField.getText();
                        kValue = kField.getText();
                        nValue = nField.getText();
                        result = OK_OPTION;
                        setVisible(false);
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
        kField.setText(kValue);
        nField.setText(nValue);
        setLocationRelativeTo(null);
        setVisible(true);
        return result;
    }

    public String getQ() {
        return qValue;
    }

    public String getK() {
        return kValue;
    }

    public String getN() {
        return nValue;
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

    // Tikrina ar skaicius pirminis
    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if ((num % i) == 0) {
                return false;
            }
        }
        return true;
   }
}