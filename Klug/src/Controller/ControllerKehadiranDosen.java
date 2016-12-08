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
public class ControllerKehadiranDosen extends MouseAdapter implements ActionListener{
    private KehadiranDosen hadir = null;
    private Application app;
    private FileIO file;
    private int userId;
    private int kelasId;
    private int hadirId;
    private String dirup;
    
    public ControllerKehadiranDosen(Application app, FileIO file, int userId, int kelasId, int hadirId){
        hadir = new KehadiranDosen();
        this.app = app;
        this.file = file;
        this.userId = userId;
        this.kelasId = kelasId;
        this.hadirId = hadirId;
        hadir.setResizable(false);
        hadir.getBtnSimpan().addActionListener(this);
        hadir.getBtn_back().addActionListener(this);
        hadir.getMahasiswa().addMouseListener(this);
        hadir.getJudulHalaman().setText("Kehadiran " + app.getDosen(userId).getKelas(kelasId).getNamaMataKuliah());
        DefaultTableModel tabel = (DefaultTableModel) hadir.getMahasiswa().getModel();
        if (hadirId != -1){
            hadir.getJudul().setText(app.getDosen(userId).getKelas(kelasId).getKehadiran(hadirId).getNama());
            hadir.getTanggal().setText(app.getDosen(userId).getKelas(kelasId).getKehadiran(hadirId).getTanggal());
            for (int i=0;i<app.getDosen(userId).getKelas(kelasId).getMahasiswaList().size();i++){
                int result = -1;
                for (int j=0;j<app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getKehadiranList().size();j++){
                    if (app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getKehadiran(j).getKehadiran() == app.getDosen(userId).getKelas(kelasId).getKehadiran(hadirId)){
                        String status;
                        if (app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getKehadiran(j).isHadir()){
                            status = "Hadir";
                        }else{
                            status = "Tidak";
                        }
                        tabel.addRow(new Object[]{app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getNim(), app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getNama(), status});
                        result = j;
                    }
                }
                if (result == -1){
                    tabel.addRow(new Object[]{app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getNim(), app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getNama(), "Tidak"});
                }
            }
        }
        hadir.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(hadir.getBtnSimpan())){
            app.getDosen(userId).getKelas(kelasId).getKehadiran(hadirId).setNama(hadir.getJudul().getText());
            app.getDosen(userId).getKelas(kelasId).getKehadiran(hadirId).setTanggal(hadir.getTanggal().getText());
            ControllerPilihKehadiranDosen pilKelMateri = new ControllerPilihKehadiranDosen(app,file,userId,kelasId);
            hadir.dispose();
        }else if(x.equals(hadir.getBtn_back())){
            ControllerPilihKehadiranDosen pilKelMateri = new ControllerPilihKehadiranDosen(app,file,userId,kelasId);
            hadir.dispose();
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        Object x = e.getSource();
        if (x.equals(hadir.getMahasiswa())){
            if (hadir.getMahasiswa().getSelectedColumn() == 2){
                int res = -1;
                for (int i=0;i<app.getDosen(userId).getKelas(kelasId).getMahasiswa(hadir.getMahasiswa().getSelectedRow()).getKehadiranList().size();i++){
                    if (app.getDosen(userId).getKelas(kelasId).getMahasiswa(hadir.getMahasiswa().getSelectedRow()).getKehadiran(i).getKehadiran() == app.getDosen(userId).getKelas(kelasId).getKehadiran(hadirId)){
                        app.getDosen(userId).getKelas(kelasId).getMahasiswa(hadir.getMahasiswa().getSelectedRow()).getKehadiran(i).toggleHadir();
                        if (app.getDosen(userId).getKelas(kelasId).getMahasiswa(hadir.getMahasiswa().getSelectedRow()).getKehadiran(i).isHadir()){
                            hadir.getMahasiswa().setValueAt("Hadir", hadir.getMahasiswa().getSelectedRow(), 2);
                        }else{
                            hadir.getMahasiswa().setValueAt("Tidak", hadir.getMahasiswa().getSelectedRow(), 2);
                        }
//                        JOptionPane.showMessageDialog(hadir,app.getDosen(userId).getKelas(kelasId).getMahasiswa(hadir.getMahasiswa().getSelectedRow()).getNama() +" berubah jadi "+app.getDosen(userId).getKelas(kelasId).getMahasiswa(hadir.getMahasiswa().getSelectedRow()).getKehadiran(i).isHadir());
                        res = i;
                    }
                }
                if (res == -1){
                    app.getDosen(userId).getKelas(kelasId).getMahasiswa(hadir.getMahasiswa().getSelectedRow()).createKehadiran(new Kehadiran(app.getDosen(userId).getKelas(kelasId).getKehadiran(hadirId),true));
                    hadir.getMahasiswa().setValueAt("Hadir", hadir.getMahasiswa().getSelectedRow(), 2);
//                    JOptionPane.showMessageDialog(hadir,app.getDosen(userId).getKelas(kelasId).getMahasiswa(hadir.getMahasiswa().getSelectedRow()).getNama() +" berubah jadi "+app.getDosen(userId).getKelas(kelasId).getMahasiswa(hadir.getMahasiswa().getSelectedRow()).getKehadiran(app.getDosen(userId).getKelas(kelasId).getMahasiswa(hadir.getMahasiswa().getSelectedRow()).getKehadiranList().size()-1).isHadir());
                }
            }
        }
    }
}
