/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klug;

import Model.Application;
import Controller.ControllerLogin;
import java.io.IOException;
import FileIO.FileIO;
import Model.*;

/**
 *
 * @author faiz
 */
public class Klug {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application app = new Application();
        FileIO file = new FileIO();
        app.createMahasiswa("tes", "tes", "tes", "tes", "tes");
        app.createDosen("a", "a", "a", "a", "a");
        app.createKelas("IMPAL IF-38-02", "A307B", "Jumat, 15.30-18.30", (Dosen) app.getOrang(1));
        app.getMahasiswa(0).addKelas(app.getKelas(0));
        app.createKelas("Sisop IF-38-02", "A307B", "Kamis, 15.30-18.30", (Dosen) app.getOrang(1));
        app.getMahasiswa(0).addKelas(app.getKelas(1));
        app.getKelas(0).createMateri("MVC", "Model View Controller");
        app.getKelas(0).createMateri("Pattern", "Strategy Pattern");
        app.getKelas(0).createTugas("Rangkum MVC", "Rangkum Model View Controller");
        app.getKelas(0).createTugas("Rangkum Pattern", "Rangkum Strategy Pattern");
        app.getMahasiswa(0).createTugas(new TugasMhs(app.getKelas(0).getTugas(0),"D:"));
        app.getKelas(0).createQuiz("Refactoring");
        app.getKelas(0).getQuiz(0).createSoal("Mana yang benar:");
        app.getKelas(0).getQuiz(0).getSoal(0).createJawaban("Bukan benar", false);
        app.getKelas(0).getQuiz(0).getSoal(0).createJawaban("Benar", true);
        app.getKelas(0).getQuiz(0).getSoal(0).createJawaban("Salah", false);
        app.getKelas(0).getQuiz(0).getSoal(0).createJawaban("Tidak benar", false);
        app.getKelas(0).getQuiz(0).getSoal(0).createJawaban("Bukan tidak salah", false);
        app.getKelas(0).getQuiz(0).createSoal("Mana yang salah:");
        app.getKelas(0).getQuiz(0).getSoal(1).createJawaban("Bukan salah", false);
        app.getKelas(0).getQuiz(0).getSoal(1).createJawaban("Benar", false);
        app.getKelas(0).getQuiz(0).getSoal(1).createJawaban("Salah", true);
        app.getKelas(0).getQuiz(0).getSoal(1).createJawaban("Tidak salah", false);
        app.getKelas(0).getQuiz(0).getSoal(1).createJawaban("Bukan tidak benar", false);
        app.getKelas(0).createQuiz("Strategy Pattern");
        app.getKelas(0).getQuiz(1).createSoal("Mana yang benar:");
        app.getKelas(0).getQuiz(1).getSoal(0).createJawaban("Bukan benar", false);
        app.getKelas(0).getQuiz(1).getSoal(0).createJawaban("Benar", true);
        app.getKelas(0).getQuiz(1).getSoal(0).createJawaban("Salah", false);
        app.getKelas(0).getQuiz(1).getSoal(0).createJawaban("Tidak benar", false);
        app.getKelas(0).getQuiz(1).getSoal(0).createJawaban("Bukan tidak salah", false);
        app.getMahasiswa(0).createJawaban(new JawabanMahasiswa(app.getMahasiswa(0),app.getKelas(0).getQuiz(1)));
        app.getMahasiswa(0).getJawaban(0).setSudah();
        ControllerLogin c = new ControllerLogin(app, file);
    }
    
}
