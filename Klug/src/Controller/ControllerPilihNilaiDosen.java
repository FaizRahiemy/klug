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
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
/**
 *
 * @author faiz
 */
public class ControllerPilihNilaiDosen extends MouseAdapter implements ActionListener{
    private PilihQuizMahasiswa pilKel = null;
    private Application app;
    private FileIO file;
    private int userId;
    private int kelasId;
    
    public ControllerPilihNilaiDosen(Application app, FileIO file, int userId, int kelasId){
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
        pilKel.getBtn_tambah().setText("Tambah Nilai");
        pilKel.getBtn_hapus().addActionListener(this);
        pilKel.getBtn_hapus().setText("Hapus Nilai");
        pilKel.getjLabel1().setText("Daftar Nilai");
        pilKel.getJudulHalaman().setText("Nilai " + app.getDosen(userId).getKelas(kelasId).getNamaMataKuliah());
        DefaultListModel modelList = new DefaultListModel();
        pilKel.getPilihQuiz().setModel(modelList);
        if (app.getDosen(userId).getKelas(kelasId).getNilai().size()>0){
            for(int i=0;i<app.getDosen(userId).getKelas(kelasId).getNilai().size();i++){
                modelList.addElement(app.getDosen(userId).getKelas(kelasId).getNilai(i).getJudulNilai());
                if (i==0){
                    pilKel.getJudul().setText(app.getDosen(userId).getKelas(kelasId).getNilai(i).getJudulNilai());
                    pilKel.getStatus().setText(app.getDosen(userId).getKelas(kelasId).getNilai(i).getJudulNilai());
                }
            }
        }else{
            modelList.addElement("Belum ada Nilai");
            pilKel.getJudul().setText("Belum ada Nilai");
            pilKel.getStatus().setText("Belum ada Nilai");
            pilKel.getBtn_kerjakan().setVisible(false);
        }
        pilKel.getPilihQuiz().setSelectedIndex(0);
        pilKel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(pilKel.getBtn_back())){
            ControllerPilihKelasNilaiDosen dashMhs = new ControllerPilihKelasNilaiDosen(app,file,userId);
            pilKel.dispose();
        }else if(x.equals(pilKel.getBtn_kerjakan())){
            ControllerNilaiDosen quizsMhs = new ControllerNilaiDosen(app,file,userId,kelasId,pilKel.getPilihQuiz().getSelectedIndex());
            pilKel.dispose();
        }else if(x.equals(pilKel.getBtn_tambah())){
            Nilai nilai = new Nilai("Baru", app.getDosen(userId).getKelas(kelasId));
            app.getDosen(userId).getKelas(kelasId).createNilai(nilai);
            ControllerNilaiDosen quizsMhs = new ControllerNilaiDosen(app,file,userId,kelasId,app.getDosen(userId).getKelas(kelasId).getKehadiran().size()-1);
            pilKel.dispose();
        }else if(x.equals(pilKel.getBtn_hapus())){
            if (app.getDosen(userId).getKelas(kelasId).getNilai().size() > 0){
                int reply = JOptionPane.showConfirmDialog(pilKel, "Yakin akan hapus nilai "+(app.getDosen(userId).getKelas(kelasId).getNilai(pilKel.getPilihQuiz().getSelectedIndex()).getJudulNilai())+"?", "Yakin?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    app.getDosen(userId).getKelas(kelasId).getNilai().remove(pilKel.getPilihQuiz().getSelectedIndex());
                    DefaultListModel modelList = new DefaultListModel();
                    pilKel.getPilihQuiz().setModel(modelList);
                    for(int i=0;i<app.getDosen(userId).getKelas(kelasId).getNilai().size();i++){
                        modelList.addElement(app.getDosen(userId).getKelas(kelasId).getNilai(i).getJudulNilai());
                    }
                    if (app.getDosen(userId).getKelas(kelasId).getNilai().size() == 1){
                        pilKel.getJudul().setText(app.getDosen(userId).getKelas(kelasId).getNilai(0).getJudulNilai());
                        pilKel.getStatus().setText(app.getDosen(userId).getKelas(kelasId).getNilai(0).getJudulNilai());
                    }
                    pilKel.getPilihQuiz().setSelectedIndex(0);
                    JOptionPane.showMessageDialog(pilKel, "Berhasil hapus nilai!");
                }
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Object x = e.getSource();
        if (x.equals(pilKel.getPilihQuiz())){
            if (app.getKelas(kelasId).getNilai().size()>0){
                pilKel.getJudul().setText(app.getDosen(userId).getKelas(kelasId).getNilai(pilKel.getPilihQuiz().getSelectedIndex()).getJudulNilai());
                pilKel.getStatus().setText(app.getDosen(userId).getKelas(kelasId).getNilai(pilKel.getPilihQuiz().getSelectedIndex()).getJudulNilai());
            }
        }
    }
}
