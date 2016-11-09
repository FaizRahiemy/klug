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
public class Kelas {
    private String namaMataKuliah;
    private String ruang;
    private String jadwal;
    private Dosen dosen;
    private ArrayList<Mahasiswa> mahasiswa;

    public Kelas(String namaMataKuliah, String ruang, String jadwal, Dosen dosen) {
        this.namaMataKuliah = namaMataKuliah;
        this.ruang = ruang;
        this.jadwal = jadwal;
        this.dosen = dosen;
        mahasiswa = new ArrayList<>();
    }

    public String getNamaMataKuliah() {
        return namaMataKuliah;
    }

    public void setNamaMataKuliah(String namaMataKuliah) {
        this.namaMataKuliah = namaMataKuliah;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    public Dosen getDosen() {
        return dosen;
    }

    public void setDosen(Dosen dosen) {
        this.dosen = dosen;
    }

    public ArrayList<Mahasiswa> getMahasiswaList() {
        return mahasiswa;
    }
    
    public Mahasiswa getMahasiswa(int i) {
        return mahasiswa.get(i);
    }
    
    public void addMahasiswa(Mahasiswa mahasiswa){
        this.mahasiswa.add(mahasiswa);
    }
}
