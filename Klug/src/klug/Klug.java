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
        app.createMahasiswa("ulfa", "ulfa", "Ulfa Wardani", "1301144082", "S1 Teknik Informatika");
        app.createDosen("frahiemy", "1234", "Faiz Rahiemy", "1301144422", "FRA");
        app.createMahasiswa("ridho", "ridho", "Ridho Kurniawan", "1301144185", "S1 Teknik Informatika");
        app.createDosen("manda", "manda", "Dainty Amanda F", "1301140062", "MAN");
        app.createAdmin("usro", "usro", "Hafidz Abdurrahman", "1301140015");
        app.createKelas("IMPAL IF-38-02", "A307B", "Jumat, 15.30-18.30", (Dosen) app.getOrang(1));
        app.getKelas(0).addMahasiswa(app.getMahasiswa(0));
        app.getKelas(0).addMahasiswa(app.getMahasiswa(2));
        app.getMahasiswa(0).addKelas(app.getKelas(0));
        app.getMahasiswa(2).addKelas(app.getKelas(0));
        app.getDosen(1).addKelas(app.getKelas(0));
        app.createKelas("Sisop IF-38-02", "A307B", "Kamis, 15.30-18.30", (Dosen) app.getOrang(3));
        app.getMahasiswa(0).addKelas(app.getKelas(1));
        app.getKelas(0).createMateri("MVC", "Model View Controller","mvc.docx");
        app.getKelas(0).createMateri("Pattern", "Strategy Pattern","mvc.docx");
        app.getKelas(0).createTugas("Rangkum MVC", "Rangkum Model View Controller");
        app.getKelas(0).createTugas("Rangkum Pattern", "Rangkum Strategy Pattern");
        app.getMahasiswa(0).createTugas(new TugasMhs(app.getKelas(0).getTugas(0),"tes_IMPAL IF-38-02_Rangkum MVC_move 30 juni.txt"));
        app.getKelas(0).createNilai(new Nilai("Quiz Refactoring", app.getKelas(0)));
        app.getKelas(0).createQuiz(new Quiz("Refactoring", app.getKelas(0), app.getKelas(0).getNilai(0)));
        app.getKelas(0).getQuiz(0).createSoal(new Soal("Mana yang benar:"));
        app.getKelas(0).getQuiz(0).getSoal(0).createJawaban("Bukan benar", false);
        app.getKelas(0).getQuiz(0).getSoal(0).createJawaban("Benar", true);
        app.getKelas(0).getQuiz(0).getSoal(0).createJawaban("Salah", false);
        app.getKelas(0).getQuiz(0).getSoal(0).createJawaban("Tidak benar", false);
        app.getKelas(0).getQuiz(0).getSoal(0).createJawaban("Bukan tidak salah", false);
        app.getKelas(0).getQuiz(0).createSoal(new Soal("Mana yang salah:"));
        app.getKelas(0).getQuiz(0).getSoal(1).createJawaban("Bukan salah", false);
        app.getKelas(0).getQuiz(0).getSoal(1).createJawaban("Benar", false);
        app.getKelas(0).getQuiz(0).getSoal(1).createJawaban("Salah", true);
        app.getKelas(0).getQuiz(0).getSoal(1).createJawaban("Tidak salah", false);
        app.getKelas(0).getQuiz(0).getSoal(1).createJawaban("Bukan tidak benar", false);
        app.getKelas(0).createNilai(new Nilai("Quiz Strategy Pattern", app.getKelas(0)));
        app.getKelas(0).createQuiz(new Quiz("Strategy Pattern", app.getKelas(0), app.getKelas(0).getNilai(1)));
        app.getKelas(0).getQuiz(1).createSoal(new Soal("Mana yang benar:"));
        app.getKelas(0).getQuiz(1).getSoal(0).createJawaban("Bukan benar", false);
        app.getKelas(0).getQuiz(1).getSoal(0).createJawaban("Benar", true);
        app.getKelas(0).getQuiz(1).getSoal(0).createJawaban("Salah", false);
        app.getKelas(0).getQuiz(1).getSoal(0).createJawaban("Tidak benar", false);
        app.getKelas(0).getQuiz(1).getSoal(0).createJawaban("Bukan tidak salah", false);
        app.getDosen(1).getKelas(0).createKehadiran(new Kehadiran("Pembukaan", "1 November 2016", app.getKelas(0), true));
        app.getDosen(1).getKelas(0).createKehadiran(new Kehadiran("Pertemuan 1", "2 November 2016", app.getKelas(0), true));
        app.getDosen(1).getKelas(0).createKehadiran(new Kehadiran("Pertemuan 2", "3 November 2016", app.getKelas(0), true));
        app.getDosen(1).getKelas(0).createKehadiran(new Kehadiran("Pertemuan 3", "4 November 2016", app.getKelas(0), true));
        app.getMahasiswa(0).createKehadiran(new Kehadiran(app.getDosen(1).getKelas(0).getKehadiran(0), true));
        app.getMahasiswa(2).createKehadiran(new Kehadiran(app.getDosen(1).getKelas(0).getKehadiran(0), true));
        app.getMahasiswa(0).createKehadiran(new Kehadiran(app.getDosen(1).getKelas(0).getKehadiran(1), true));
        app.getMahasiswa(0).createKehadiran(new Kehadiran(app.getDosen(1).getKelas(0).getKehadiran(2), false));
        app.getMahasiswa(0).createKehadiran(new Kehadiran(app.getDosen(1).getKelas(0).getKehadiran(3), false));
        app.getKelas(0).createNilai(new Nilai("UTS", app.getKelas(0)));
        app.getMahasiswa(0).createNilai(new Nilai(app.getKelas(0).getNilai(2), 89));
        ControllerLogin c = new ControllerLogin(app, file);
    }
}
