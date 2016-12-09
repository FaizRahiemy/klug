/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author faiz
 */
public class Jawaban implements Serializable {
    private String jawaban;
    private boolean benar;

    public Jawaban(String jawaban, boolean benar) {
        this.jawaban = jawaban;
        this.benar = benar;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public boolean isBenar() {
        return benar;
    }

    public void setBenar(boolean benar) {
        this.benar = benar;
    }
    
}
