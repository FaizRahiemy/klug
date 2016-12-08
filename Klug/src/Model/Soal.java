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
public class Soal {
    private String soal;
    private ArrayList<Jawaban> jawaban;

    public Soal(String soal) {
        this.soal = soal;
        jawaban = new ArrayList<>();
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public ArrayList<Jawaban> getJawabanList() {
        return jawaban;
    }
    
    public Jawaban getJawaban(int i) {
        return jawaban.get(i);
    }
    
    public void createJawaban(String jawaban, boolean benar){
        this.jawaban.add(new Jawaban(jawaban, benar));
    }
}
