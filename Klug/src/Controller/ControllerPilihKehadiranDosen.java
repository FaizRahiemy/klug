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
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
/**
 *
 * @author faiz
 */
public class ControllerPilihKehadiranDosen extends MouseAdapter implements ActionListener{
    private PilihQuizMahasiswa pilKel = null;
    private Application app;
    private IOFile file;
    private int userId;
    private int kelasId;
    
    public ControllerPilihKehadiranDosen(Application app, IOFile file, int userId, int kelasId){
        pilKel = new PilihQuizMahasiswa();
        this.app = app;
        this.file = file;
        this.userId = userId;
        this.kelasId = kelasId;
        pilKel.setResizable(false);
        pilKel.getBtn_back().addActionListener(this);
        pilKel.getBtn_kerjakan().addActionListener(this);
        pilKel.getBtn_kerjakan().setText("Lihat");
        pilKel.getPilihQuiz().addMouseListener(this);
        pilKel.getBtn_tambah().addActionListener(this);
        pilKel.getBtn_tambah().setText("Tambah Kehadiran");
        pilKel.getBtn_hapus().addActionListener(this);
        pilKel.getBtn_hapus().setText("Hapus Kehadiran");
        pilKel.getJudulHalaman().setText("Kehadiran " + app.getDosen(userId).getKelas(kelasId).getNamaMataKuliah());
        pilKel.getjLabel1().setText("Daftar Kehadiran");
        DefaultListModel modelList = new DefaultListModel();
        pilKel.getPilihQuiz().setModel(modelList);
        if (app.getDosen(userId).getKelas(kelasId).getKehadiran().size()>0){
            for(int i=0;i<app.getDosen(userId).getKelas(kelasId).getKehadiran().size();i++){
                modelList.addElement(app.getDosen(userId).getKelas(kelasId).getKehadiran(i).getNama());
                if (i==0){
                    pilKel.getJudul().setText(app.getDosen(userId).getKelas(kelasId).getKehadiran(i).getNama());
                    pilKel.getStatus().setText(app.getDosen(userId).getKelas(kelasId).getKehadiran(i).getTanggal());
                }
            }
        }else{
            modelList.addElement("Belum ada Kehadiran");
            pilKel.getJudul().setText("Belum ada Kehadiran");
            pilKel.getStatus().setText("Belum ada Kehadiran");
            pilKel.getBtn_kerjakan().setVisible(false);
        }
        pilKel.getPilihQuiz().setSelectedIndex(0);
        pilKel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(pilKel.getBtn_back())){
            ControllerPilihKelasKehadiranDosen dashMhs = new ControllerPilihKelasKehadiranDosen(app,file,userId);
            pilKel.dispose();
        }else if(x.equals(pilKel.getBtn_kerjakan())){
            ControllerKehadiranDosen quizsMhs = new ControllerKehadiranDosen(app,file,userId,kelasId,pilKel.getPilihQuiz().getSelectedIndex());
            pilKel.dispose();
        }else if(x.equals(pilKel.getBtn_tambah())){
            Kehadiran kehadiran = new Kehadiran("Baru", "Baru", app.getDosen(userId).getKelas(kelasId), true);
            app.getDosen(userId).getKelas(kelasId).createKehadiran(kehadiran);
            try {
                app.saveFile(app.getKelasList());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            ControllerKehadiranDosen quizsMhs = new ControllerKehadiranDosen(app,file,userId,kelasId,app.getDosen(userId).getKelas(kelasId).getKehadiran().size()-1);
            pilKel.dispose();
        }else if(x.equals(pilKel.getBtn_hapus())){
            if (app.getDosen(userId).getKelas(kelasId).getKehadiran().size() > 0){
                int reply = JOptionPane.showConfirmDialog(pilKel, "Yakin akan hapus kehadiran "+(app.getDosen(userId).getKelas(kelasId).getKehadiran(pilKel.getPilihQuiz().getSelectedIndex()).getNama())+"?", "Yakin?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    app.getDosen(userId).getKelas(kelasId).getKehadiran().remove(pilKel.getPilihQuiz().getSelectedIndex());
                    DefaultListModel modelList = new DefaultListModel();
                    pilKel.getPilihQuiz().setModel(modelList);
                    for(int i=0;i<app.getDosen(userId).getKelas(kelasId).getKehadiran().size();i++){
                        modelList.addElement(app.getDosen(userId).getKelas(kelasId).getKehadiran(i).getNama());
                    }
                    if (app.getDosen(userId).getKelas(kelasId).getKehadiran().size() == 1){
                        pilKel.getStatus().setText(app.getDosen(userId).getKelas(kelasId).getKehadiran(0).getTanggal());
                    }
                    try {
                        app.saveFile(app.getKelasList());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    pilKel.getPilihQuiz().setSelectedIndex(0);
                    JOptionPane.showMessageDialog(pilKel, "Berhasil hapus kehadiran!");
                }
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Object x = e.getSource();
        if (x.equals(pilKel.getPilihQuiz())){
            if (app.getDosen(userId).getKelas(kelasId).getKehadiran().size()>0){
                pilKel.getJudul().setText(app.getDosen(userId).getKelas(kelasId).getKehadiran(pilKel.getPilihQuiz().getSelectedIndex()).getNama());
                pilKel.getStatus().setText(app.getDosen(userId).getKelas(kelasId).getKehadiran(pilKel.getPilihQuiz().getSelectedIndex()).getTanggal());
            }
        }
    }
}
