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
public class ControllerKehadiranMahasiswa implements ActionListener{
    private KehadiranMahasiswa kehadiran = null;
    private Application app;
    private IOFile file;
    private int userId;
    
    public ControllerKehadiranMahasiswa(Application app, IOFile file, int userId){
        kehadiran = new KehadiranMahasiswa();
        this.app = app;
        this.file = file;
        this.userId = userId;
//        System.out.println(userId);
        kehadiran.setResizable(false);
        kehadiran.getBtn_back().addActionListener(this);
        DefaultTableModel tabel = (DefaultTableModel) kehadiran.getKehadiran().getModel();
        for (int i=0;i<app.getMahasiswa(userId).getKelasList().size();i++){
            tabel.addRow(new Object[]{app.getMahasiswa(userId).getKelas(i).getNamaMataKuliah(),app.getMahasiswa(userId).getKelas(i).getDosen().getNama(),app.getMahasiswa(userId).getTotalPertemuan(app.getMahasiswa(userId).getKelas(i)),app.getMahasiswa(userId).getTotalKehadiran(app.getMahasiswa(userId).getKelas(i)),(app.getMahasiswa(userId).getPersentaseKehadiran(app.getMahasiswa(userId).getKelas(i))+" %")});
        }
        kehadiran.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(kehadiran.getBtn_back())){
            ControllerDashboardMahasiswaDosen dashboardMhs = new ControllerDashboardMahasiswaDosen(app,file,userId);
        }
        kehadiran.dispose();
    }
}
