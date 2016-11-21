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
    private boolean sudah;
    private Quiz quiz;
    private Mahasiswa mahasiswa;
    private ArrayList<Jawaban> jawaban;

    public JawabanMahasiswa(Mahasiswa mahasiswa, Quiz quiz) {
        this.sudah = false;
        this.mahasiswa = mahasiswa;
        this.quiz = quiz;
        jawaban = new ArrayList<>();
    }

    public boolean isSudah() {
        return sudah;
    }

    public void setSudah(boolean sudah) {
        this.sudah = sudah;
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

    public void addJawaban(Jawaban jawaban) {
        this.jawaban.add(jawaban);
    }
    
    public Jawaban getJawaban(int i) {
        return jawaban.get(i);
    }
    
    public void setJawaban(int i, Jawaban jawaban) {
        this.jawaban.set(i,jawaban);
    }
    
    public void hitungNilai(){
        double benar=0;
        double soal = jawaban.size();
        double nilai;
        for(int i=0; i<jawaban.size(); i++){
            if (jawaban.get(i).isBenar()){
                benar++;
            }
        }
        nilai = (benar/soal)*100;
        sudah = true;
        mahasiswa.getNilaiList().add(new Nilai("Quiz "+quiz.getJudulQuiz(), quiz.getKelas(), nilai));
    }
}
