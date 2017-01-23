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
public class ControllerTugasDosen extends MouseAdapter implements ActionListener{
    private TugasDosen tugasDos = null;
    private Application app;
    private IOFile file;
    private int userId;
    private int kelasId;
    private int tugasId;
    private String dirup;
    
    public ControllerTugasDosen(Application app, IOFile file, int userId, int kelasId, int tugasId){
        tugasDos = new TugasDosen();
        this.app = app;
        this.file = file;
        this.userId = userId;
        this.kelasId = kelasId;
        this.tugasId = tugasId;
        tugasDos.setResizable(false);
        tugasDos.getBtnSimpan().addActionListener(this);
        tugasDos.getBtn_back().addActionListener(this);
        tugasDos.getMahasiswa().addMouseListener(this);
        tugasDos.getJudulHalaman().setText("Tugas " + app.getDosen(userId).getKelas(kelasId).getNamaMataKuliah());
        DefaultTableModel tabel = (DefaultTableModel) tugasDos.getMahasiswa().getModel();
        if (tugasId != -1){
            tugasDos.getJudul().setText(app.getDosen(userId).getKelas(kelasId).getTugas(tugasId).getJudulTugas());
            tugasDos.getTugas().setText(app.getDosen(userId).getKelas(kelasId).getTugas(tugasId).getIsiTugas());
            for (int i=0;i<app.getDosen(userId).getKelas(kelasId).getMahasiswaList().size();i++){
                int result = -1;
                for (int j=0;j<app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getTugasList().size();j++){
                    if (app.getDosen(userId).getKelas(kelasId).getTugas(tugasId) == app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getTugas(j).getTugas()){
                        tabel.addRow(new Object[]{app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getNim(), app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getNama(), "Sudah", "DOWNLOAD"});
                        result = j;
                    }
                }
                if (result == -1){
                    tabel.addRow(new Object[]{app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getNim(), app.getDosen(userId).getKelas(kelasId).getMahasiswa(i).getNama(), "Belum", "-"});
                }
            }
        }
        tugasDos.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(tugasDos.getBtnSimpan())){
            if (tugasId == -1){
                app.getDosen(userId).getKelas(kelasId).createTugas(tugasDos.getJudul().getText(), tugasDos.getTugas().getText());
            }else{
                app.getDosen(userId).getKelas(kelasId).getTugas(tugasId).setJudulTugas(tugasDos.getJudul().getText());
                app.getDosen(userId).getKelas(kelasId).getTugas(tugasId).setIsiTugas(tugasDos.getTugas().getText());
            }
            try {
                app.saveFile(app.getKelasList());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            ControllerPilihTugasDosen pilKelMateri = new ControllerPilihTugasDosen(app,file,userId,kelasId);
            tugasDos.dispose();
        }else if(x.equals(tugasDos.getBtn_back())){
            ControllerPilihTugasDosen pilKelMateri = new ControllerPilihTugasDosen(app,file,userId,kelasId);
            tugasDos.dispose();
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        Object x = e.getSource();
        if (x.equals(tugasDos.getMahasiswa())){
            if (tugasDos.getMahasiswa().getSelectedColumn() == 3){
                JFileChooser fc = new JFileChooser("D:/");
                fc.setAcceptAllFileFilterUsed(false);
                int result = -1;
                int res2 = -1;
                for (int i=0;i<app.getOrangList().size();i++){
                    if (app.getOrang(i) instanceof Mahasiswa){
                        if (app.getMahasiswa(i).getNim() == (tugasDos.getMahasiswa().getValueAt(tugasDos.getMahasiswa().getSelectedRow(), 0))){
                            result = i;
                            for (int j=0;j<app.getMahasiswa(i).getTugasList().size();j++){
                                if (app.getMahasiswa(i).getTugas(j).getTugas() == app.getDosen(userId).getKelas(kelasId).getTugas(tugasId)){
                                    res2 = j;
                                }
                            }
                        }
                    }
                }
                if (res2 != -1){
                    File ff = new File("tugas/"+app.getMahasiswa(result).getTugas(res2).getLokasi());
                    fc.setSelectedFile(ff);

                    fc.setCurrentDirectory(new File("D:/"));
                    int returnValue = fc.showSaveDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File newFile = fc.getSelectedFile();
                        try {
                            Files.copy(ff.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            JOptionPane.showMessageDialog(tugasDos, "Download Tugas Sukses");
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(tugasDos,"Coba save di folder lain!");
                        }
                    }
                }
            }
        }
    }
}
