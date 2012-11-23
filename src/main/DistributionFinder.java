package main;

import direct_method.DirectMethodCalculator;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import mac_williams_method.MacWilliamsMethodCalculator;
import mac_williams_method.MatrixFinder;
import structures.Matrix;
import structures.Vector;
import utilities.CodeGenerator;

public class DistributionFinder extends SwingWorker<int[], Void> {

    private CodeGenerator cg;
    private DirectMethodCalculator dmc = new DirectMethodCalculator();
    private MatrixFinder cmf = new MatrixFinder();
    private MacWilliamsMethodCalculator wmc = new MacWilliamsMethodCalculator();

    private String inputMatrix;
    private int qValue;
    private JRadioButton button;
    private JTextArea textArea;

    protected DistributionFinder(String inputMatrix, int qValue, JRadioButton button, JTextArea textArea) {
        this.inputMatrix = inputMatrix;
        this.qValue = qValue;
        this.button = button;
        this.textArea = textArea;
    }

    @Override
    protected int[] doInBackground() throws Exception {
        Matrix gMatrix = new Matrix(inputMatrix, qValue);
        int[] distribution = null;
        try {
            if (button.isSelected()) {
                Vector[] code = generateCode(gMatrix);
                distribution = dmc.calculateDistribution(code);
            } else {
                Matrix controlMatrix = cmf.findControlMatrix(gMatrix, qValue);
                Vector[] dualCode = generateCode(controlMatrix);
                int[] dualWeightDistribution = dmc.calculateDistribution(dualCode);
                distribution = wmc.calculateDistribution(dualWeightDistribution,
                        dualCode[0].getSize(), qValue);
            }
        } catch (ExecutionException out) {
            JOptionPane.showMessageDialog(null, "Per mazai atminties. Sumazinkite matricos dydi arba 'q'",
                    "Klaida", JOptionPane.ERROR_MESSAGE);
            textArea.setText("Skaiciavimas\nnutrauktas");
        }
        return distribution;
    }

    @Override
    protected void done() {
        try {
            if (Thread.interrupted()) {
                throw new Exception();
            }
            textArea.setText(toString(get()));
        } catch (InterruptedException | ExecutionException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Klaida", JOptionPane.ERROR_MESSAGE);
            textArea.setText("Skaiciavimas\nnutrauktas");
        } catch (Exception ex) {
        }
    }

    private String toString(int[] distribution) throws Exception {
        String result = "";
        int i = 0;
        for (int count : distribution) {
            result += "A" + i++ + " = " + count + "\n";
        }
        return result;
    }

    private Vector[] generateCode(Matrix gMatrix) throws Exception {
        cg = new CodeGenerator(gMatrix, qValue);
        cg.execute();
        while (!cg.isDone()) {
            if (Thread.interrupted()) {
                cg.cancel(true);
                throw new Exception();
            }
        }
        return cg.get();
    }
}
