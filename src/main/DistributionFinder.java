// Si klase vykdo svoriu skirstinio paieska
// Visa tai vyksta "background'e", todel pagrindinis langas lieka reaguojantis ir skaiciavimui uztrukus, galima ji atsaukti

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

    // Konstruktorius
    // Paduodama matricos simboliu eilutes (String) reprezentacija, 'q' reiksme, skaiciavimo budo pasirinkimo mygtukas, rezultatu laukas
    protected DistributionFinder(String inputMatrix, int qValue, JRadioButton button, JTextArea textArea) {
        this.inputMatrix = inputMatrix;
        this.qValue = qValue;
        this.button = button;
        this.textArea = textArea;
    }

    // Pagrindine funkcija, kuri vykdo svoriu skirstinio paieska
    // Paduodamu parametru nera, grazinamas svoriu skirstinio masyvas
    @Override
    protected int[] doInBackground() throws Exception {
        // Pagal matricos simboliu eilutes reprezentacija, sukuriamas matricos objektas
        Matrix gMatrix = new Matrix(inputMatrix, qValue);

        int[] distribution = null;
        try {
            // Tikrinama koks skaiciavimo budas pasirinktas
            if (button.isSelected()) {
                // Skaiciuojama tiesioginio perrinkimo budu
                // Pagal generuojancia matrica generuojamas kodas
                Vector[] code = generateCode(gMatrix);
                // Skaiciuojamas sugeneruoto kodo zodziu svoriu skirstinys tiesioginio perrinkimo budu
                distribution = dmc.calculateDistribution(code);
            } else {
                // Skaiciuojama pritaikant MacWilliams tapatybe
                // Randama ivestos matricos kontroline matrica
                Matrix controlMatrix = cmf.findControlMatrix(gMatrix, qValue);
                // Pagal kontroline matrica generuojamas kodas
                Vector[] dualCode = generateCode(controlMatrix);
                // Apskaiciuojamas dualaus kodo zodziu svoriu skirstinys
                int[] dualWeightDistribution = dmc.calculateDistribution(dualCode);
                // Apskaiciuojamas ivestos generuojancios matricos kodo zodziu svoriu skirstinys
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

    // Funkcija, kuri vykdoma iskart po to, kai darba baigia auksciau esanti 'doInBackground()' funkcija
    // Ji neima ir negrazina jokiu parametru ir yra skirta isvesti apskaiciuota kodo zodziu svoriu skirstini i rezultatu lauka
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

    // Is kodo zodziu svoriu skirstinio, kuris laikomas sveiku skaiciu masyve, suformuoja simboliu eilute, skirta isvedimui i rezultatu lauka
    // Paduodamas svriu skirstinys, grazinama skirstinio simboliu eilute
    private String toString(int[] distribution) throws Exception {
        String result = "";
        int i = 0;
        for (int count : distribution) {
            result += "A" + i++ + " = " + count + "\n";
        }
        return result;
    }

    // Generuoja koda pagal generuojancia matrica
    // Paduodama generuojanti matrica, grazinami kodo zodziai
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
