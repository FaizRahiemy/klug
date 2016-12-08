/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Model.*;
import View.*;
import FileIO.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author faiz
 */
public class ControllerNilaiMahasiswa implements ActionListener{
    private NilaiMahasiswa nilai = null;
    private Application app;
    private FileIO file;
    private int userId;
    private int kelasId;
    
    public ControllerNilaiMahasiswa(Application app, FileIO file, int userId, int kelasId){
        nilai = new NilaiMahasiswa();
        this.app = app;
        this.file = file;
        this.userId = userId;
        this.kelasId = kelasId;
        nilai.setResizable(false);
        nilai.getBtn_back().addActionListener(this);
        DefaultTableModel tabel = (DefaultTableModel) nilai.getNilai().getModel();
        for (int i=0;i<app.getMahasiswa(userId).getNilaiList().size();i++){
            if (app.getMahasiswa(userId).getNilai(i).getNil().getKelas() == app.getMahasiswa(userId).getKelas(kelasId)){
                tabel.addRow(new Object[]{app.getMahasiswa(userId).getNilai(i).getNil().getJudulNilai(),app.getMahasiswa(userId).getNilai(i).getNilai()});
            }
        }
        nilai.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(nilai.getBtn_back())){
            ControllerPilihKelasNilaiMahasiswa kehadiran = new ControllerPilihKelasNilaiMahasiswa(app,file,userId);
        }
        nilai.dispose();
    }
}
