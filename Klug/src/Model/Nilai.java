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
public class Nilai {
    private String judulNilai;
    private Kelas kelas;
    private double nilai;

    public Nilai(String judulNilai, Kelas kelas, double nilai) {
        this.judulNilai = judulNilai;
        this.kelas = kelas;
        this.nilai = nilai;
    }

    public String getJudulNilai() {
        return judulNilai;
    }

    public void setJudulNilai(String judulNilai) {
        this.judulNilai = judulNilai;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    public Kelas getKelas() {
        return kelas;
    }
    
}
