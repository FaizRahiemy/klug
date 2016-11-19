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
public class ControllerPilihKelasTugasMahasiswa implements ActionListener{
    private PilihKelas pilKelTugas = null;
    private Application app;
    private FileIO file;
    private int userId;
    
    public ControllerPilihKelasTugasMahasiswa(Application app, FileIO file, int userId){
        pilKelTugas = new PilihKelas();
        this.app = app;
        this.file = file;
        this.userId = userId;
        pilKelTugas.setResizable(false);
        pilKelTugas.getBtn_back().addActionListener(this);
        pilKelTugas.getBtn_pilih().addActionListener(this);
        DefaultListModel modelList = new DefaultListModel();
        pilKelTugas.getPilihKelas().setModel(modelList);
        for(int i=0;i<app.getMahasiswa(userId).getKelasList().size();i++){
            modelList.addElement(app.getMahasiswa(userId).getKelas(i).getNamaMataKuliah());
        }
        pilKelTugas.getPilihKelas().setSelectedIndex(0);
        pilKelTugas.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(pilKelTugas.getBtn_back())){
            ControllerDashboardMahasiswa dashMhs = new ControllerDashboardMahasiswa(app,file,userId);
        }else if(x.equals(pilKelTugas.getBtn_pilih())){
            ControllerTugasMahasiswa tugasMhs = new ControllerTugasMahasiswa(app,file,userId,pilKelTugas.getPilihKelas().getSelectedIndex());
        }
        pilKelTugas.dispose();
    }
}
