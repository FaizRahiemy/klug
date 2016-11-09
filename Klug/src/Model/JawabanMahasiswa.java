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
public class JawabanMahasiswa {
    private Quiz quiz;
    private Mahasiswa mahasiswa;
    private ArrayList<Jawaban> jawaban;

    public JawabanMahasiswa(Mahasiswa mahasiswa, Quiz quiz) {
        this.mahasiswa = mahasiswa;
        this.quiz = quiz;
        jawaban = new ArrayList<>();
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public ArrayList<Jawaban> getJawabanList() {
        return jawaban;
    }
    
    public Jawaban getJawaban(int i) {
        return jawaban.get(i);
    }
    
    public void hitungNilai(){
        int benar=0;
        int nilai;
        for(int i=0; i<jawaban.size(); i++){
            if (jawaban.get(i).isBenar()){
                benar++;
            }
        }
        nilai = benar/jawaban.size();
        mahasiswa.getNilaiList().add(new Nilai("Quiz "+quiz.getJudulQuiz(), quiz.getKelas(), nilai));
    }
}
