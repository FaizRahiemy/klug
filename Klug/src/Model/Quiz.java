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
public class Quiz {
    private String judulQuiz;
    private Nilai nilai;
    private Kelas kelas;
    private ArrayList<Soal> soal;

    public Quiz(String judulQuiz, Kelas kelas, Nilai nilai) {
        this.judulQuiz = judulQuiz;
        this.kelas = kelas;
        this.nilai = nilai;
        soal = new ArrayList<>();
    }

    public String getJudulQuiz() {
        return judulQuiz;
    }

    public void setJudulQuiz(String judulQuiz) {
        this.judulQuiz = judulQuiz;
    }

    public ArrayList<Soal> getSoalList() {
        return soal;
    }
    
    public Soal getSoal(int i) {
        return soal.get(i);
    }
    
    public void createSoal(Soal soal){
        this.soal.add(soal);
    }
    
    public Kelas getKelas() {
        return kelas;
    }

    public Nilai getNilai() {
        return nilai;
    }

    public void setNilai(Nilai nilai) {
        this.nilai = nilai;
    }
    
}
