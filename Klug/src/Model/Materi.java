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
public class Materi {
    private String judulMateri;
    private String isiMateri;

    public Materi(String judulMateri, String isiMateri) {
        this.judulMateri = judulMateri;
        this.isiMateri = isiMateri;
    }

    public String getJudulMateri() {
        return judulMateri;
    }

    public void setJudulMateri(String judulMateri) {
        this.judulMateri = judulMateri;
    }

    public String getIsiMateri() {
        return isiMateri;
    }

    public void setIsiMateri(String isiMateri) {
        this.isiMateri = isiMateri;
    }
    
}
