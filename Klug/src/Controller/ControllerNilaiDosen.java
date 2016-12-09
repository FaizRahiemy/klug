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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author faiz
 */
public class ControllerNilaiDosen extends MouseAdapter implements ActionListener{
    private NilaiDosen nilDos = null;
    private Application app;
    private IOFile file;
    private int userId;
    private int kelasId;
    private int nilaiId;
    private String dirup;
    
    public ControllerNilaiDosen(Application app, IOFile file, int userId, int kelasId, int nilaiId){
        nilDos = new NilaiDosen();
        this.app = app;
        this.file = file;
        this.userId = userId;
        this.kelasId = kelasId;
        this.nilaiId = nilaiId;
        nilDos.setResizable(false);
        nilDos.getBtnSimpan().addActionListener(this);
        nilDos.getBtn_back().addActionListener(this);
        nilDos.getMahasiswa().addMouseListener(this);
        nilDos.getJudulHalaman().setText("Nilai " + app.getDosen(userId).getKelas(kelasId).getNamaMataKuliah());
        DefaultTableModel tabel = (DefaultTableModel) nilDos.getMahasiswa().getModel();
        if (nilaiId != -1){
            nilDos.getJudul().setText(app.getDosen(userId).getKelas(kelasId).getNilai(nilaiId).getJudulNilai());
            for (int i=0;i<app.getDosen(userId).getKelas(kelasId).getMahasiswaList().size();i++){
                int result = -1;
                for (int j=0;j<app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getNilaiList().size();j++){
                    if (app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getNilai(j).getNil() == app.getDosen(userId).getKelas(kelasId).getNilai(nilaiId)){
                        tabel.addRow(new Object[]{app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getNim(), app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getNama(), app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getNilai(j).getNilai()});
                        result = j;
                    }
                }
                if (result == -1){
                    tabel.addRow(new Object[]{app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getNim(), app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getNama(), "0"});
                }
            }
        }
        nilDos.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(nilDos.getBtnSimpan())){
            app.getDosen(userId).getKelas(kelasId).getNilai(nilaiId).setJudulNilai(nilDos.getJudul().getText());
            for (int i=0;i<app.getDosen(userId).getKelas(kelasId).getMahasiswaList().size();i++){
                int result = -1;
                for (int j=0;j<app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getNilaiList().size();j++){
                    if (app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getNilai(j).getNil() == app.getDosen(userId).getKelas(kelasId).getNilai(nilaiId)){
                        Double nilbaru = 0.0;
                        if (nilDos.getMahasiswa().getValueAt(i, 2) instanceof String){
                            nilbaru = Double.parseDouble((String) nilDos.getMahasiswa().getValueAt(i, 2));
                        }else if(nilDos.getMahasiswa().getValueAt(i, 2) instanceof Double){
                            nilbaru = (Double) nilDos.getMahasiswa().getValueAt(i, 2);
                        }
                        result = j;
                        app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getNilai(j).setNilai(nilbaru);
                    }
                }
                if (result == -1){
                    Double nilbaru = 0.0;
                    if (nilDos.getMahasiswa().getValueAt(i, 2) instanceof String){
                        nilbaru = Double.parseDouble((String) nilDos.getMahasiswa().getValueAt(i, 2));
                    }else if(nilDos.getMahasiswa().getValueAt(i, 2) instanceof Double){
                        nilbaru = (Double) nilDos.getMahasiswa().getValueAt(i, 2);
                    }
                    app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).createNilai(new Nilai(app.getDosen(userId).getKelas(kelasId).getNilai(nilaiId),nilbaru));
                }
            }
            try {
                app.saveFile(app.getKelasList());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                app.saveFile(app.getOrangList());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            ControllerPilihNilaiDosen pilKelMateri = new ControllerPilihNilaiDosen(app,file,userId,kelasId);
            nilDos.dispose();
        }else if(x.equals(nilDos.getBtn_back())){
            ControllerPilihNilaiDosen pilKelMateri = new ControllerPilihNilaiDosen(app,file,userId,kelasId);
            nilDos.dispose();
        }
    }
}
