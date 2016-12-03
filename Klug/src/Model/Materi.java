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
    private String dir;

    public Materi(String judulMateri, String isiMateri, String dir) {
        this.judulMateri = judulMateri;
        this.isiMateri = isiMateri;
        this.dir = dir;
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

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
    
}
