/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author faiz
 */
public class Kehadiran {
    private String nama;
    private String tanggal;
    private Kelas kelas;
    private boolean hadir;

    public Kehadiran(String nama, Kelas kelas, boolean hadir) {
        this.nama = nama;
        this.kelas = kelas;
        this.hadir = hadir;
    }
    
    public Kehadiran(Kelas kelas, boolean hadir) {
        this.kelas = kelas;
        this.hadir = hadir;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public boolean isHadir() {
        return hadir;
    }

    public void setHadir(boolean hadir) {
        this.hadir = hadir;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Kelas getKelas() {
        return kelas;
    }

    public void setKelas(Kelas kelas) {
        this.kelas = kelas;
    }
    
}
