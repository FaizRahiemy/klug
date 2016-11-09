/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author faiz
 */
public class Dosen extends Orang{
    private String nip;
    private String kodeDosen;

    public Dosen(String nip, String kodeDosen, String username, String password, String nama) {
        super(username, password, nama);
        this.nip = nip;
        this.kodeDosen = kodeDosen;
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
    
    
}
