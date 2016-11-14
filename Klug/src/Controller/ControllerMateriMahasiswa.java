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
public class ControllerMateriMahasiswa extends MouseAdapter implements ActionListener{
    private MateriMahasiswa materi = null;
    private Application app;
    private FileIO file;
    private int userId;
    private int kelasId;
    
    public ControllerMateriMahasiswa(Application app, FileIO file, int userId, int kelasId){
        materi = new MateriMahasiswa();
        this.app = app;
        this.file = file;
        this.userId = userId;
        this.kelasId = kelasId;
        materi.setResizable(false);
        materi.getBtn_back().addActionListener(this);
        materi.getPilihMateri().addMouseListener(this);
        materi.getJudulHalaman().setText("Materi " + app.getMahasiswa(userId).getKelas(kelasId).getNamaMataKuliah());
        DefaultListModel modelList = new DefaultListModel();
        materi.getPilihMateri().setModel(modelList);
        if (app.getMahasiswa(userId).getKelas(kelasId).getMateriList().size()>0){
            for(int i=0;i<app.getMahasiswa(userId).getKelas(kelasId).getMateriList().size();i++){
                modelList.addElement(app.getMahasiswa(userId).getKelas(kelasId).getMateri(i).getJudulMateri());
                if (i == 0){
                    materi.getJudul().setText(app.getMahasiswa(userId).getKelas(kelasId).getMateri(0).getJudulMateri());
                    materi.getMateri().setText(app.getMahasiswa(userId).getKelas(kelasId).getMateri(0).getIsiMateri());
                }
            }
        }else{
            modelList.addElement("Belum ada Materi");
            materi.getJudul().setText("Belum ada Materi");
            materi.getMateri().setText("Belum ada Materi");
        }
        materi.getPilihMateri().setSelectedIndex(0);
        materi.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(materi.getBtn_back())){
            ControllerPilihKelasMateriMahasiswa pilKelMateri = new ControllerPilihKelasMateriMahasiswa(app,file,userId);
        }
        materi.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object x = e.getSource();
        if (x.equals(materi.getPilihMateri())){
            if (app.getKelas(kelasId).getMateriList().size()>0){
                materi.getJudul().setText(app.getMahasiswa(userId).getKelas(kelasId).getMateri(materi.getPilihMateri().getSelectedIndex()).getJudulMateri());
                materi.getMateri().setText(app.getMahasiswa(userId).getKelas(kelasId).getMateri(materi.getPilihMateri().getSelectedIndex()).getIsiMateri());
            }
        }
    }
}
