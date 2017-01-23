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
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author faiz
 */
public class ControllerTambahMahasiswa extends MouseAdapter implements ActionListener{
    private TambahMahasiswa hadir = null;
    private Application app;
    private IOFile file;
    private int userId;
    private int kelasId;
    
    public ControllerTambahMahasiswa(Application app, IOFile file, int userId, int kelasId){
        hadir = new TambahMahasiswa();
        this.app = app;
        this.file = file;
        this.userId = userId;
        this.kelasId = kelasId;
        hadir.setResizable(false);
        hadir.getBtnSimpan().addActionListener(this);
        hadir.getBtnBatal().addActionListener(this);
        DefaultTableModel tabel = (DefaultTableModel) hadir.getMahasiswa().getModel();
        for (int i=0;i<app.getOrangList().size();i++){
            if (app.getOrang(i) instanceof Mahasiswa){
                tabel.addRow(new Object[]{app.getMahasiswa(i).getNim(), app.getMahasiswa(i).getNama(), app.getMahasiswa(i).getProdi()});
            }
        }
        hadir.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(hadir.getBtnSimpan())){
            int res=-1;
            for (int i=0;i<app.getOrangList().size();i++){
                if (app.getOrang(i) instanceof Mahasiswa){
                    if (app.getMahasiswa(i).getNim() == hadir.getMahasiswa().getValueAt(hadir.getMahasiswa().getSelectedRow(), 0)){
                        res = i;
                    }
                }
            }
            int res2 = -1;
            for (int i=0;i<app.getKelas(kelasId).getMahasiswaList().size();i++){
                if (app.getMahasiswa(res).getNim().equals(app.getKelas(kelasId).getMahasiswa(i).getNim())){
                    res2 = i;
                }
            }
            if (res2 == -1){
                app.getKelas(kelasId).addMahasiswa(app.getMahasiswa(res));
                try {
                    app.saveFile(app.getKelasList());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                ControllerKelas pilKelMateri = new ControllerKelas(app,file,userId,kelasId);
                hadir.dispose();
            }else{
                JOptionPane.showMessageDialog(null,
                "Mahasiswa Sudah Terdaftar di Kelas Ini!", "Mahasiswa Terdaftar",
                JOptionPane.ERROR_MESSAGE);
            }
        }else if(x.equals(hadir.getBtnBatal())){
            ControllerKelas pilKelMateri = new ControllerKelas(app,file,userId,kelasId);
            hadir.dispose();
        }
    }
    
    public void changedUpdate(DocumentEvent e) {
        Object x = e.getDocument();
        if(x.equals(hadir.getNama()))
        warn();
    }
    
    public void removeUpdate(DocumentEvent e) {
        Object x = e.getDocument();
        if(x.equals(hadir.getNama()))
        warn();
    }
    
    public void insertUpdate(DocumentEvent e) {
        Object x = e.getDocument();
        if(x.equals(hadir.getNama()))
        warn();
    }
      
    public void warn() {
        JOptionPane.showMessageDialog(null,
        "Error: Please enter number bigger than 0", "Error Massage",
        JOptionPane.ERROR_MESSAGE);
    }
}
