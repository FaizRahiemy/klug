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
/**
 *
 * @author faiz
 */
public class ControllerMateriDosen extends MouseAdapter implements ActionListener{
    private MateriDosen materi = null;
    private Application app;
    private IOFile file;
    private int userId;
    private int kelasId;
    
    public ControllerMateriDosen(Application app, IOFile file, int userId, int kelasId){
        materi = new MateriDosen();
        this.app = app;
        this.file = file;
        this.userId = userId;
        this.kelasId = kelasId;
        materi.setResizable(false);
        materi.getBtn_back().addActionListener(this);
        materi.getBtnEdit().addActionListener(this);
        materi.getBtnTambah().addActionListener(this);
        materi.getBtnHapus().addActionListener(this);
        materi.getPilihMateri().addMouseListener(this);
        materi.getJudulHalaman().setText("Materi " + app.getDosen(userId).getKelas(kelasId).getNamaMataKuliah());
        DefaultListModel modelList = new DefaultListModel();
        materi.getPilihMateri().setModel(modelList);
        if (app.getDosen(userId).getKelas(kelasId).getMateriList().size()>0){
            for(int i=0;i<app.getDosen(userId).getKelas(kelasId).getMateriList().size();i++){
                modelList.addElement(app.getDosen(userId).getKelas(kelasId).getMateri(i).getJudulMateri());
                if (i == 0){
                    materi.getJudul().setText(app.getDosen(userId).getKelas(kelasId).getMateri(0).getJudulMateri());
                    materi.getMateri().setText(app.getDosen(userId).getKelas(kelasId).getMateri(0).getIsiMateri());
                }
            }
        }else{
            modelList.addElement("Belum ada Materi");
            materi.getJudul().setText("Belum ada Materi");
            materi.getMateri().setText("Belum ada Materi");
            materi.getBtnEdit().setVisible(false);
        }
        materi.getPilihMateri().setSelectedIndex(0);
        materi.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(materi.getBtn_back())){
            ControllerPilihKelasMateriDosen pilKelMateri = new ControllerPilihKelasMateriDosen(app,file,userId);
            materi.dispose();
        }else if(x.equals(materi.getBtnEdit())){
            ControllerMateriDetailDosen materidetail = new ControllerMateriDetailDosen(app, file, userId, kelasId, materi.getPilihMateri().getSelectedIndex());
            materi.dispose();
        }else if(x.equals(materi.getBtnTambah())){
            ControllerMateriDetailDosen materidetail = new ControllerMateriDetailDosen(app, file, userId, kelasId, -1);
            materi.dispose();
        }else if(x.equals(materi.getBtnHapus())){
            if (app.getDosen(userId).getKelas(kelasId).getMateriList().size() > 0){
                int reply = JOptionPane.showConfirmDialog(materi, "Yakin akan hapus materi "+(app.getDosen(userId).getKelas(kelasId).getMateri(materi.getPilihMateri().getSelectedIndex()).getJudulMateri())+"?", "Yakin?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    app.getDosen(userId).getKelas(kelasId).getMateriList().remove(materi.getPilihMateri().getSelectedIndex());
                    try {
                        app.saveFile(app.getKelasList());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    DefaultListModel modelList = new DefaultListModel();
                    materi.getPilihMateri().setModel(modelList);
                    for(int i=0;i<app.getDosen(userId).getKelas(kelasId).getMateriList().size();i++){
                        modelList.addElement(app.getDosen(userId).getKelas(kelasId).getMateri(i).getJudulMateri());
                    }
                    if (app.getDosen(userId).getKelas(kelasId).getMateriList().size() == 1){
                        materi.getJudul().setText(app.getDosen(userId).getKelas(kelasId).getMateri(0).getJudulMateri());
                        materi.getMateri().setText(app.getDosen(userId).getKelas(kelasId).getMateri(0).getIsiMateri());
                    }
                    materi.getPilihMateri().setSelectedIndex(0);
                    JOptionPane.showMessageDialog(materi, "Berhasil hapus materi!");
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object x = e.getSource();
        if (x.equals(materi.getPilihMateri())){
            if (app.getDosen(userId).getKelas(kelasId).getMateriList().size()>0){
                materi.getJudul().setText(app.getDosen(userId).getKelas(kelasId).getMateri(materi.getPilihMateri().getSelectedIndex()).getJudulMateri());
                materi.getMateri().setText(app.getDosen(userId).getKelas(kelasId).getMateri(materi.getPilihMateri().getSelectedIndex()).getIsiMateri());
            }
        }
    }
}
