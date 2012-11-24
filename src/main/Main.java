// Pagridine klase - paleidus programa, pirmiausiai vykdomas sios klases funkcijos 'main' kodas

package main;

import java.awt.EventQueue;
import java.util.logging.Logger;

public class Main {

    // Funkcija, kuri vykdoma pati pirma, paleidus programa
    public static void main(String[] args) {

        // Sis kodo gabalas nustato GUI (grafinio vartotojo interfeiso) stiliu
        // Cia neisdestomi GUI elementai, o tik nurodoma mygtuku, ivedimo lauku ir kitu elemetu isvaizda
        // Tam naudojama Java Nimbus biblioteka
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Sukuriamas pagrindinis GUI langas
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mf = new MainFrame();
            }
        });
    }
}
