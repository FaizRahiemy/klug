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
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
/**
 *
 * @author faiz
 */
public class ControllerPilihKelasMateriMahasiswa implements ActionListener{
    private PilihKelas pilKelMateri = null;
    private Application app;
    private IOFile file;
    private int userId;
    
    public ControllerPilihKelasMateriMahasiswa(Application app, IOFile file, int userId){
        pilKelMateri = new PilihKelas();
        this.app = app;
        this.file = file;
        this.userId = userId;
        pilKelMateri.setResizable(false);
        pilKelMateri.getBtn_back().addActionListener(this);
        pilKelMateri.getBtn_pilih().addActionListener(this);
        pilKelMateri.getHalaman().setText("Materi");
        DefaultListModel modelList = new DefaultListModel();
        pilKelMateri.getPilihKelas().setModel(modelList);
        for(int i=0;i<app.getMahasiswa(userId).getKelasList().size();i++){
            modelList.addElement(app.getMahasiswa(userId).getKelas(i).getNamaMataKuliah());
        }
        pilKelMateri.getPilihKelas().setSelectedIndex(0);
        pilKelMateri.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(pilKelMateri.getBtn_back())){
            ControllerDashboardMahasiswaDosen dashMhs = new ControllerDashboardMahasiswaDosen(app,file,userId);
        }else if(x.equals(pilKelMateri.getBtn_pilih())){
            ControllerMateriMahasiswa materiMhs = new ControllerMateriMahasiswa(app,file,userId,pilKelMateri.getPilihKelas().getSelectedIndex());
        }
        pilKelMateri.dispose();
    }
}
