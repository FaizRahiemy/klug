/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author faiz
 */
public class Tugas implements Serializable {
    private String judulTugas;
    private String isiTugas;

    public Tugas(String judulTugas, String isiTugas) {
        this.judulTugas = judulTugas;
        this.isiTugas = isiTugas;
    }

    public String getJudulTugas() {
        return judulTugas;
    }

    public void setJudulTugas(String judulTugas) {
        this.judulTugas = judulTugas;
    }

    public String getIsiTugas() {
        return isiTugas;
    }

    public void setIsiTugas(String isiTugas) {
        this.isiTugas = isiTugas;
    }
    
    public void uploadJawaban( ){
        
    }
    
    public void downloadJawaban( ){
        
    }
}
