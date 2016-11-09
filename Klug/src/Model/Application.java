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
    private ArrayList<Mahasiswa> mahasiswa;
    private ArrayList<Dosen> dosen;
    private ArrayList<Admin> admin;

    public Application() {
        kelas = new ArrayList<>();
    }

    public ArrayList<Kelas> getKelasList() {
        return kelas;
    }

    public ArrayList<Mahasiswa> getMahasiswaList() {
        return mahasiswa;
    }

    public ArrayList<Dosen> getDosenList() {
        return dosen;
    }

    public ArrayList<Admin> getAdminList() {
        return admin;
    }
    
    public Kelas getKelas(int i) {
        return kelas.get(i);
    }

    public Mahasiswa getMahasiswa(int i) {
        return mahasiswa.get(i);
    }

    public Dosen getDosen(int i) {
        return dosen.get(i);
    }

    public Admin getAdmin(int i){
        return admin.get(i);
    }
       
    public void createKelas(String namaMataKuliah, String ruang, String jadwal, Dosen dosen){
        kelas.add(new Kelas(namaMataKuliah, ruang, jadwal, dosen));
    }
    
    public void createAdmin(String username, String password, String nama, String nip){
        admin.add(new Admin(username, password, nama, nip));
    }
    
    public void createDosen(String username, String password, String nama, String nip, String kodeDosen){
        dosen.add(new Dosen(username, password, nama, nip, kodeDosen));
    }
    
    public void createMahasiswa(String username, String password, String nama, String nim, String prodi){
        mahasiswa.add(new Mahasiswa(username, password, nama, nim, prodi));
    }
}
