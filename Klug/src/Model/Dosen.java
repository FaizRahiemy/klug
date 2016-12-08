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
public class Dosen extends Orang{
    private String nip;
    private String kodeDosen;
    private ArrayList<Kelas> kelas;

    public Dosen(String username, String password, String nama, String nip, String kodeDosen) {
        super(username, password, nama);
        this.nip = nip;
        this.kodeDosen = kodeDosen;
        kelas = new ArrayList<>();
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getKodeDosen() {
        return kodeDosen;
    }

    public void setKodeDosen(String kodeDosen) {
        this.kodeDosen = kodeDosen;
    }
    
    public ArrayList<Kelas> getKelasList() {
        return kelas;
    }
    
    public Kelas getKelas(int i) {
        return kelas.get(i);
    }
    
    public void addKelas(Kelas kelas){
        this.kelas.add(kelas);
    }
}
