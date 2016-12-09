/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import FileIO.IOFile;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author faiz
 */
public class Application {
    private ArrayList<Kelas> kelas;
    private ArrayList<Orang> orang;
    private IOFile file;

    public Application(IOFile file) throws IOException {
        this.file = file;
        Object f = file.loadFile("orang.dat");
        if (f == null){
            orang = new ArrayList<>();
            orang.add(new Mahasiswa("ulfa", "ulfa", "Ulfa Wardani", "1301144082", "S1 Teknik Informatika"));
            orang.add(new Dosen("frahiemy", "1234", "Faiz Rahiemy", "1301144422", "FRA"));
            orang.add(new Mahasiswa("ridho", "ridho", "Ridho Kurniawan", "1301144185", "S1 Teknik Informatika"));
            orang.add(new Dosen("manda", "manda", "Dainty Amanda F", "1301140062", "MAN"));
            orang.add(new Admin("usro", "usro", "Hafidz Abdurrahman", "1301140015"));
        }else{
            orang = (ArrayList<Orang>) f;
        }
        Object x = file.loadFile("kelas.dat");
        if (x == null){
            kelas = new ArrayList<>();
            kelas.add(new Kelas("IMPAL IF-38-02", "A307B", "Jumat, 15.30-18.30", (Dosen) orang.get(1)));
            kelas.add(new Kelas("Sisop IF-38-02", "A307B", "Kamis, 15.30-18.30", (Dosen) orang.get(3)));
        }else{
            kelas = (ArrayList<Kelas>) x;
        }
        if (x == null && f == null){
            kelas.get(0).addMahasiswa((Mahasiswa) orang.get(0));
            kelas.get(0).addMahasiswa((Mahasiswa) orang.get(2));
            Mahasiswa m = (Mahasiswa) orang.get(0);
            m.addKelas(kelas.get(0));
            m.addKelas(kelas.get(1));
            m = (Mahasiswa) orang.get(2);
            m.addKelas(kelas.get(0));
            this.getDosen(1).addKelas(kelas.get(0));
            kelas.get(0).createMateri("MVC", "Model View Controller","mvc.docx");
            kelas.get(0).createMateri("Pattern", "Strategy Pattern","mvc.docx");
            kelas.get(0).createTugas("Rangkum MVC", "Rangkum Model View Controller");
            kelas.get(0).createTugas("Rangkum Pattern", "Rangkum Strategy Pattern");
            this.getMahasiswa(0).createTugas(new TugasMhs(kelas.get(0).getTugas(0),"tes_IMPAL IF-38-02_Rangkum MVC_move 30 juni.txt"));
            kelas.get(0).createNilai(new Nilai("Quiz Refactoring", kelas.get(0)));
            kelas.get(0).createQuiz(new Quiz("Refactoring", kelas.get(0), kelas.get(0).getNilai(0)));
            kelas.get(0).getQuiz(0).createSoal(new Soal("Mana yang benar:"));
            kelas.get(0).getQuiz(0).getSoal(0).createJawaban("Bukan benar", false);
            kelas.get(0).getQuiz(0).getSoal(0).createJawaban("Benar", true);
            kelas.get(0).getQuiz(0).getSoal(0).createJawaban("Salah", false);
            kelas.get(0).getQuiz(0).getSoal(0).createJawaban("Tidak benar", false);
            kelas.get(0).getQuiz(0).getSoal(0).createJawaban("Bukan tidak salah", false);
            kelas.get(0).getQuiz(0).createSoal(new Soal("Mana yang salah:"));
            kelas.get(0).getQuiz(0).getSoal(1).createJawaban("Bukan salah", false);
            kelas.get(0).getQuiz(0).getSoal(1).createJawaban("Benar", false);
            kelas.get(0).getQuiz(0).getSoal(1).createJawaban("Salah", true);
            kelas.get(0).getQuiz(0).getSoal(1).createJawaban("Tidak salah", false);
            kelas.get(0).getQuiz(0).getSoal(1).createJawaban("Bukan tidak benar", false);
            kelas.get(0).createNilai(new Nilai("Quiz Strategy Pattern", kelas.get(0)));
            kelas.get(0).createQuiz(new Quiz("Strategy Pattern", kelas.get(0), kelas.get(0).getNilai(1)));
            kelas.get(0).getQuiz(1).createSoal(new Soal("Mana yang benar:"));
            kelas.get(0).getQuiz(1).getSoal(0).createJawaban("Bukan benar", false);
            kelas.get(0).getQuiz(1).getSoal(0).createJawaban("Benar", true);
            kelas.get(0).getQuiz(1).getSoal(0).createJawaban("Salah", false);
            kelas.get(0).getQuiz(1).getSoal(0).createJawaban("Tidak benar", false);
            kelas.get(0).getQuiz(1).getSoal(0).createJawaban("Bukan tidak salah", false);
            this.getDosen(1).getKelas(0).createKehadiran(new Kehadiran("Pembukaan", "1 November 2016", kelas.get(0), true));
            this.getDosen(1).getKelas(0).createKehadiran(new Kehadiran("Pertemuan 1", "2 November 2016", kelas.get(0), true));
            this.getDosen(1).getKelas(0).createKehadiran(new Kehadiran("Pertemuan 2", "3 November 2016", kelas.get(0), true));
            this.getDosen(1).getKelas(0).createKehadiran(new Kehadiran("Pertemuan 3", "4 November 2016", kelas.get(0), true));
            this.getMahasiswa(0).createKehadiran(new Kehadiran(this.getDosen(1).getKelas(0).getKehadiran(0), true));
            this.getMahasiswa(2).createKehadiran(new Kehadiran(this.getDosen(1).getKelas(0).getKehadiran(0), true));
            this.getMahasiswa(0).createKehadiran(new Kehadiran(this.getDosen(1).getKelas(0).getKehadiran(1), true));
            this.getMahasiswa(0).createKehadiran(new Kehadiran(this.getDosen(1).getKelas(0).getKehadiran(2), false));
            this.getMahasiswa(0).createKehadiran(new Kehadiran(this.getDosen(1).getKelas(0).getKehadiran(3), false));
            kelas.get(0).createNilai(new Nilai("UTS", kelas.get(0)));
            this.getMahasiswa(0).createNilai(new Nilai(kelas.get(0).getNilai(2), 89));
        }
    }

    public ArrayList<Kelas> getKelasList() {
        return kelas;
    }

    public ArrayList<Orang> getOrangList() {
        return orang;
    }
    
    public Kelas getKelas(int i) {
        return kelas.get(i);
    }

    public Orang getOrang(int i) {
        return orang.get(i);
    }
    
    public Mahasiswa getMahasiswa(int i){
        Mahasiswa mhs = (Mahasiswa) orang.get(i);
        return mhs;
    }
    
    public Dosen getDosen(int i){
        Dosen dosen = (Dosen) orang.get(i);
        return dosen;
    }
    
    public Admin getAdmin(int i){
        Admin admin = (Admin) orang.get(i);
        return admin;
    }
       
    public void createKelas(String namaMataKuliah, String ruang, String jadwal, Dosen dosen) throws IOException{
        kelas.add(new Kelas(namaMataKuliah, ruang, jadwal, dosen));
        file.saveFile(kelas, "kelas.dat");
    }
    
    public void createAdmin(String username, String password, String nama, String nip) throws IOException{
        orang.add(new Admin(username, password, nama, nip));
        file.saveFile(orang, "orang.dat");
    }
    
    public void createDosen(String username, String password, String nama, String nip, String kodeDosen) throws IOException{
        orang.add(new Dosen(username, password, nama, nip, kodeDosen));
        file.saveFile(orang, "orang.dat");
    }
    
    public void createMahasiswa(String username, String password, String nama, String nim, String prodi) throws IOException{
        orang.add(new Mahasiswa(username, password, nama, nim, prodi));
        file.saveFile(orang, "orang.dat");
    }
    
    public void saveFile(Object object) throws IOException{
        if (object == kelas){
            file.saveFile(object, "kelas.dat");
        }else if (object == orang){
            file.saveFile(object, "orang.dat");
        }
    }
}
