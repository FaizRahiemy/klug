/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author faiz
 */
public class Application {
    private ArrayList<Kelas> kelas;
    private ArrayList<Orang> orang;

    public Application() {
        kelas = new ArrayList<>();
        orang = new ArrayList<>();
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
       
    public void createKelas(String namaMataKuliah, String ruang, String jadwal, Dosen dosen){
        kelas.add(new Kelas(namaMataKuliah, ruang, jadwal, dosen));
    }
    
    public void createAdmin(String username, String password, String nama, String nip){
        orang.add(new Admin(username, password, nama, nip));
    }
    
    public void createDosen(String username, String password, String nama, String nip, String kodeDosen){
        orang.add(new Dosen(username, password, nama, nip, kodeDosen));
    }
    
    public void createMahasiswa(String username, String password, String nama, String nim, String prodi){
        orang.add(new Mahasiswa(username, password, nama, nim, prodi));
    }
}
