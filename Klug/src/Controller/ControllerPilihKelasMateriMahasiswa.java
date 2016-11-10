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
    private PilihKelasMateriMahasiswa pilKelMateri = null;
    private Application app;
    private FileIO file;
    
    public ControllerPilihKelasMateriMahasiswa(Application app, FileIO file){
        pilKelMateri = new PilihKelasMateriMahasiswa();
        this.app = app;
        this.file = file;
        pilKelMateri.setResizable(false);
        pilKelMateri.getBtn_back().addActionListener(this);
        pilKelMateri.getBtn_pilih().addActionListener(this);
        DefaultListModel modelList = new DefaultListModel();
        pilKelMateri.getPilihKelas().setModel(modelList);
        modelList.addElement("Sisop IF-38-02");
        modelList.addElement("AI IF-38-02");
        modelList.addElement("DAA IF-38-02");
        pilKelMateri.getPilihKelas().setSelectedIndex(0);
        pilKelMateri.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(pilKelMateri.getBtn_back())){
            ControllerDashboardMahasiswa dashMhs = new ControllerDashboardMahasiswa(app,file);
            pilKelMateri.dispose();
        }else if(x.equals(pilKelMateri.getBtn_pilih())){
            JOptionPane.showMessageDialog(pilKelMateri, "Pilih Kelas "+pilKelMateri.getPilihKelas().getSelectedValue());
            //ControllerDashboardMahasiswa dashMhs = new ControllerDashboardMahasiswa(app,file);
            //pilKelMateri.dispose();
        }
    }
}
