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
public class Tugas {
    private String judulTugas;
    private ArrayList<Soal> soal;

    public Tugas(String judulTugas) {
        this.judulTugas = judulTugas;
        soal = new ArrayList<>();
    }

    public String getJudulTugas() {
        return judulTugas;
    }

    public void setJudulTugas(String judulTugas) {
        this.judulTugas = judulTugas;
    }

    public ArrayList<Soal> getSoalList() {
        return soal;
    }
    
    public Soal getSoal(int i) {
        return soal.get(i);
    }
    
    public void createSoal(String soal){
        this.soal.add(new Soal(soal));
    }
    
    public void uploadJawaban( ){
        
    }
    
    public void downloadJawaban( ){
        
    }
}
