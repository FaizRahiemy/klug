/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author faiz
 */
public class Kehadiran implements Serializable {
    private Kehadiran kehadiran;
    private String nama;
    private String tanggal;
    private Kelas kelas;
    private boolean hadir;

    public Kehadiran(Kehadiran kehadiran, boolean hadir) {
        this.kehadiran = kehadiran;
        this.hadir = hadir;
    }
    
    public Kehadiran(String nama, String tanggal, Kelas kelas, boolean hadir) {
        this.nama = nama;
        this.tanggal = tanggal;
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

    public Kehadiran getKehadiran() {
        return kehadiran;
    }

    public void setKehadiran(Kehadiran kehadiran) {
        this.kehadiran = kehadiran;
    }
    
    public void toggleHadir() {
        if (hadir){
            hadir = false;
        }else{
            hadir = true;
        }
    }
    
}
