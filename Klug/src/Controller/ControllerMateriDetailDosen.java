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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
/**
 *
 * @author faiz
 */
public class ControllerMateriDetailDosen extends MouseAdapter implements ActionListener{
    private MateriDetailDosen materi = null;
    private Application app;
    private IOFile file;
    private int userId;
    private int kelasId;
    private int materiId;
    private String dirup;
    
    public ControllerMateriDetailDosen(Application app, IOFile file, int userId, int kelasId, int materiId){
        materi = new MateriDetailDosen();
        this.app = app;
        this.file = file;
        this.userId = userId;
        this.kelasId = kelasId;
        this.materiId = materiId;
        materi.setResizable(false);
        materi.getBtn_back().addActionListener(this);
        materi.getBtnSimpan().addActionListener(this);
        materi.getBtn_upload().addActionListener(this);
        materi.getBtn_download().addActionListener(this);
        materi.getJudulHalaman().setText("Materi " + app.getDosen(userId).getKelas(kelasId).getNamaMataKuliah());
        if (materiId == -1){
            materi.getBtn_download().setVisible(false);
        }else{
            materi.getJudul().setText(app.getDosen(userId).getKelas(kelasId).getMateri(materiId).getJudulMateri());
            materi.getMateri().setText(app.getDosen(userId).getKelas(kelasId).getMateri(materiId).getIsiMateri());
            materi.getDir().setText("materi/"+app.getDosen(userId).getUsername()+"/"+app.getDosen(userId).getKelas(kelasId).getMateri(materiId).getDir());
            dirup = app.getDosen(userId).getKelas(kelasId).getMateri(materiId).getDir();
        }
        materi.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(materi.getBtn_back())){
            ControllerMateriDosen pilKelMateri = new ControllerMateriDosen(app,file,userId,kelasId);
            materi.dispose();
        }else if(x.equals(materi.getBtn_upload())){
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(materi);
            File file1 = chooser.getSelectedFile();
            File dir2 = new File("materi/"+app.getDosen(userId).getUsername()+"/"+file1.getName());
            materi.getDir().setText("materi/"+app.getDosen(userId).getUsername()+"/"+file1.getName());
            dirup = file1.getName();
            try {
                Files.copy(file1.toPath(),dir2.toPath(), StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(materi, "Upload Materi Sukses");
            }catch(Exception ae){
                JOptionPane.showMessageDialog(materi, "ERROR");
            }
        }else if(x.equals(materi.getBtn_download())){
            JFileChooser fc = new JFileChooser("D:/");
            fc.setAcceptAllFileFilterUsed(false);
            File ff = new File("materi/"+app.getDosen(userId).getUsername()+"/"+app.getDosen(userId).getKelas(kelasId).getMateri(materiId).getDir());
            fc.setSelectedFile(ff);
            
            fc.setCurrentDirectory(new File("D:/"));
            int returnValue = fc.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File newFile = fc.getSelectedFile();
                try {
                    Files.copy(ff.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    JOptionPane.showMessageDialog(materi, "Download Materi Sukses");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(materi,"Coba save di folder lain!");
                }
            }
        }else if(x.equals(materi.getBtnSimpan())){
            if (materiId == -1){
                if (materi.getJudul().getText().isEmpty() || materi.getMateri().getText().isEmpty() || materi.getDir().getText().isEmpty()){
                    JOptionPane.showMessageDialog(materi,"Semua data harus diisi!");
                }else{
                    app.getDosen(userId).getKelas(kelasId).createMateri(materi.getJudul().getText(), materi.getMateri().getText(), dirup);
                    try {
                        app.saveFile(app.getKelasList());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    ControllerMateriDosen pilKelMateri = new ControllerMateriDosen(app,file,userId,kelasId);
                    materi.dispose();
                }
            }else{
                if (materi.getJudul().getText().isEmpty() || materi.getMateri().getText().isEmpty() || materi.getDir().getText().isEmpty()){
                    JOptionPane.showMessageDialog(materi,"Semua data harus diisi!");
                }else{
                    app.getDosen(userId).getKelas(kelasId).getMateri(materiId).setJudulMateri(materi.getJudul().getText());
                    app.getDosen(userId).getKelas(kelasId).getMateri(materiId).setIsiMateri(materi.getMateri().getText());
                    app.getDosen(userId).getKelas(kelasId).getMateri(materiId).setDir(dirup);
                    try {
                        app.saveFile(app.getKelasList());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    ControllerMateriDosen pilKelMateri = new ControllerMateriDosen(app,file,userId,kelasId);
                    materi.dispose();
                }
            }
        }
    }
}
