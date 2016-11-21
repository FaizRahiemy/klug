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
        app.getKelas(0).createQuiz("Refactoring", app.getKelas(0));
        app.getKelas(0).getQuiz(0).createSoal("Mana yang benar:");
        app.getKelas(0).getQuiz(0).getSoal(0).createJawaban(1,"Bukan benar", false);
        app.getKelas(0).getQuiz(0).getSoal(0).createJawaban(2,"Benar", true);
        app.getKelas(0).getQuiz(0).getSoal(0).createJawaban(3,"Salah", false);
        app.getKelas(0).getQuiz(0).getSoal(0).createJawaban(4,"Tidak benar", false);
        app.getKelas(0).getQuiz(0).getSoal(0).createJawaban(5,"Bukan tidak salah", false);
        app.getKelas(0).getQuiz(0).createSoal("Mana yang salah:");
        app.getKelas(0).getQuiz(0).getSoal(1).createJawaban(1,"Bukan salah", false);
        app.getKelas(0).getQuiz(0).getSoal(1).createJawaban(2,"Benar", false);
        app.getKelas(0).getQuiz(0).getSoal(1).createJawaban(3,"Salah", true);
        app.getKelas(0).getQuiz(0).getSoal(1).createJawaban(4,"Tidak salah", false);
        app.getKelas(0).getQuiz(0).getSoal(1).createJawaban(5,"Bukan tidak benar", false);
        app.getKelas(0).createQuiz("Strategy Pattern", app.getKelas(0));
        app.getKelas(0).getQuiz(1).createSoal("Mana yang benar:");
        app.getKelas(0).getQuiz(1).getSoal(0).createJawaban(1,"Bukan benar", false);
        app.getKelas(0).getQuiz(1).getSoal(0).createJawaban(2,"Benar", true);
        app.getKelas(0).getQuiz(1).getSoal(0).createJawaban(3,"Salah", false);
        app.getKelas(0).getQuiz(1).getSoal(0).createJawaban(4,"Tidak benar", false);
        app.getKelas(0).getQuiz(1).getSoal(0).createJawaban(5,"Bukan tidak salah", false);
        app.getMahasiswa(0).createKehadiran(app.getKelas(0), true);
        app.getMahasiswa(0).createKehadiran(app.getKelas(0), true);
        app.getMahasiswa(0).createKehadiran(app.getKelas(0), false);
        app.getMahasiswa(0).createKehadiran(app.getKelas(0), false);
        app.getMahasiswa(0).createKehadiran(app.getKelas(1), true);
        ControllerLogin c = new ControllerLogin(app, file);
    }
    
}
