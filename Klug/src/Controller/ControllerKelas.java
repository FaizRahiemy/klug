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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author faiz
 */
public class ControllerKelas extends MouseAdapter implements ActionListener{
    private MenuKelas hadir = null;
    private Application app;
    private IOFile file;
    private int userId;
    private int kelasId;
    
    public ControllerKelas(Application app, IOFile file, int userId, int kelasId){
        hadir = new MenuKelas();
        this.app = app;
        this.file = file;
        this.userId = userId;
        this.kelasId = kelasId;
        hadir.setResizable(false);
        hadir.getBtnSimpan().addActionListener(this);
        hadir.getBtnBatal().addActionListener(this);
        hadir.getBtnTambah().addActionListener(this);
        hadir.getBtnDosen().addActionListener(this);
        hadir.getMahasiswa().addMouseListener(this);
        DefaultTableModel tabel = (DefaultTableModel) hadir.getMahasiswa().getModel();
        if (kelasId != -1){
            hadir.getNama().setText(app.getKelas(kelasId).getNamaMataKuliah());
            hadir.getJadwal().setText(app.getKelas(kelasId).getJadwal());
            hadir.getRuang().setText(app.getKelas(kelasId).getRuang());
            int idDos = 0;
            for (int i=0;i<app.getOrangList().size();i++){
                if (app.getOrang(i) instanceof Dosen){
                    if (app.getDosen(i) == app.getKelas(kelasId).getDosen()){
                        idDos = i;
                    }
                }
            }
            hadir.getDosen().setText(app.getDosen(idDos).getNama());
            for (int i=0;i<app.getKelas(kelasId).getMahasiswaList().size();i++){
                tabel.addRow(new Object[]{app.getKelas(kelasId).getMahasiswa(i).getNim(), app.getKelas(kelasId).getMahasiswa(i).getNama(), "Hapus"});
            }
        }
        hadir.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(hadir.getBtnSimpan())){
            app.getKelas(kelasId).setNamaMataKuliah(hadir.getNama().getText());
            app.getKelas(kelasId).setJadwal(hadir.getJadwal().getText());
            app.getKelas(kelasId).setRuang(hadir.getRuang().getText());
            try {
                app.saveFile(app.getKelasList());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            ControllerPilihKelas pilKelMateri = new ControllerPilihKelas(app,file,userId);
            hadir.dispose();
        }else if(x.equals(hadir.getBtnBatal())){
            ControllerPilihKelas pilKelMateri = new ControllerPilihKelas(app,file,userId);
            hadir.dispose();
        }else if(x.equals(hadir.getBtnTambah())){
            ControllerTambahMahasiswa pilKelMateri = new ControllerTambahMahasiswa(app,file,userId,kelasId);
            hadir.dispose();
        }else if(x.equals(hadir.getBtnDosen())){
            ControllerTambahDosen pilKelMateri = new ControllerTambahDosen(app,file,userId,kelasId);
            hadir.dispose();
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        Object x = e.getSource();
        if (x.equals(hadir.getMahasiswa())){
            if (hadir.getMahasiswa().getSelectedColumn() == 2){
                app.getKelas(kelasId).getMahasiswaList().remove(hadir.getMahasiswa().getSelectedRow());
                DefaultTableModel tabel = (DefaultTableModel) hadir.getMahasiswa().getModel();
                for (int i = tabel.getRowCount()- 1; i >= 0; i--) {
                    tabel.removeRow(i);
                }
                for (int i=0;i<app.getKelas(kelasId).getMahasiswaList().size();i++){
                    tabel.addRow(new Object[]{app.getKelas(kelasId).getMahasiswa(i).getNim(), app.getKelas(kelasId).getMahasiswa(i).getNama(), "Hapus"});
                }
                try {
                    app.saveFile(app.getKelasList());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
