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
public class Mahasiswa extends Orang{
    private String nim;
    private String prodi;
    private ArrayList<Nilai> nilai;
    private ArrayList<Kehadiran> kehadiran;
    private ArrayList<JawabanMahasiswa> jawaban;

    public Mahasiswa(String username, String password, String nama, String nim, String prodi) {
        super(username,password,nama);
        this.nim = nim;
        this.prodi = prodi;
        nilai = new ArrayList<>();
        kehadiran = new ArrayList<>();
        jawaban = new ArrayList<>();
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public ArrayList<Nilai> getNilaiList() {
        return nilai;
    }

    public ArrayList<Kehadiran> getKehadiranList() {
        return kehadiran;
    }

    public ArrayList<JawabanMahasiswa> getJawabanList() {
        return jawaban;
    }
    
    public Nilai getNilai(int i) {
        return nilai.get(i);
    }

    public Kehadiran getKehadiran(int i) {
        return kehadiran.get(i);
    }

    public JawabanMahasiswa getJawaban(int i) {
        return jawaban.get(i);
    }
    
}
