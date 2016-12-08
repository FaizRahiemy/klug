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
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 *
 * @author faiz
 */
public class ControllerPilihPengguna extends MouseAdapter implements ActionListener{
    private PilihTugasDosen pilihTugas = null;
    private Application app;
    private FileIO file;
    private int userId;
    
    public ControllerPilihPengguna(Application app, FileIO file, int userId){
        pilihTugas = new PilihTugasDosen();
        this.app = app;
        this.file = file;
        this.userId = userId;
        pilihTugas.setResizable(false);
        pilihTugas.getBtn_back().addActionListener(this);
        pilihTugas.getBtn_tambah().addActionListener(this);
        pilihTugas.getBtn_hapus().addActionListener(this);
        pilihTugas.getBtn_hapus().setVisible(false);
        pilihTugas.getBtn_lihat().addActionListener(this);
        pilihTugas.getPilihTugas().addMouseListener(this);
        pilihTugas.getJudulHalaman().setText("Kelola Pengguna");
        pilihTugas.getjLabel1().setText("Daftar Pengguna");
        pilihTugas.getBtn_tambah().setText("Tambah Pengguna");
        DefaultListModel modelList = new DefaultListModel();
        pilihTugas.getPilihTugas().setModel(modelList);
        if (app.getOrangList().size()>0){
            for(int i=0;i<app.getOrangList().size();i++){
                modelList.addElement(app.getOrang(i).getNama());
                if (i == 0){
                    pilihTugas.getJudul().setText(app.getOrang(i).getNama());
                    String status;
                    if (app.getOrang(i) instanceof Mahasiswa){
                        status = ("Nama : "+app.getOrang(i).getNama()+
                                "\nNIM : "+app.getMahasiswa(i).getNim()+
                                "\nProdi : "+app.getMahasiswa(i).getProdi());
                    }else if(app.getOrang(i) instanceof Dosen){
                        status = ("Kode Dosen : "+app.getDosen(i).getKodeDosen()+
                                "\nNama : "+app.getOrang(i).getNama()+
                                "\nNIP : "+app.getDosen(i).getNip());      
                    }else{
                        status = ("Nama : "+app.getOrang(i).getNama()+
                                "\nNIP : "+app.getAdmin(i).getNip());
                    }
                    pilihTugas.getTugas().setText(status);
                }
            }
        }else{
            modelList.addElement("Belum ada Pengguna");
            pilihTugas.getJudul().setText("Belum ada Pengguna");
            pilihTugas.getTugas().setText("Belum ada Pengguna");
            pilihTugas.getBtn_lihat().setVisible(false);
        }
        pilihTugas.getPilihTugas().setSelectedIndex(0);
        pilihTugas.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(pilihTugas.getBtn_back())){
            ControllerDashboardAdmin pilKelMateri = new ControllerDashboardAdmin(app,file,userId);
            pilihTugas.dispose();
        }else if(x.equals(pilihTugas.getBtn_lihat())){
            ControllerPengguna materidetail = new ControllerPengguna(app, file, userId, pilihTugas.getPilihTugas().getSelectedIndex());
            pilihTugas.dispose();
        }else if(x.equals(pilihTugas.getBtn_tambah())){
            ControllerPengguna materidetail = new ControllerPengguna(app, file, userId, -1);
            pilihTugas.dispose();
        }else if(x.equals(pilihTugas.getBtn_hapus())){
            if (app.getOrangList().size() > 0){
                int reply = JOptionPane.showConfirmDialog(pilihTugas, "Yakin akan hapus pengguna "+(app.getOrang(pilihTugas.getPilihTugas().getSelectedIndex()).getNama())+"?", "Yakin?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    app.getOrangList().remove(pilihTugas.getPilihTugas().getSelectedIndex());
                    DefaultListModel modelList = new DefaultListModel();
                    pilihTugas.getPilihTugas().setModel(modelList);
                    for(int i=0;i<app.getOrangList().size();i++){
                        modelList.addElement(app.getOrang(i).getNama());
                    }
                    if (app.getOrangList().size() == 1){
                        pilihTugas.getJudul().setText(app.getOrang(0).getNama());
                        String status;
                        if (app.getOrang(0) instanceof Mahasiswa){
                            status = ("Nama : "+app.getOrang(0).getNama()+
                                    "\nNIM : "+app.getMahasiswa(0).getNim()+
                                    "\nProdi : "+app.getMahasiswa(0).getProdi());
                        }else if(app.getOrang(0) instanceof Dosen){
                            status = ("Kode Dosen : "+app.getDosen(0).getKodeDosen()+
                                    "\nNama : "+app.getOrang(0).getNama()+
                                    "\nNIP : "+app.getDosen(0).getNip());      
                        }else{
                            status = ("Nama : "+app.getOrang(0).getNama()+
                                    "\nNIP : "+app.getAdmin(0).getNip());
                        }
                        pilihTugas.getTugas().setText(status);
                    }
                    pilihTugas.getPilihTugas().setSelectedIndex(0);
                    JOptionPane.showMessageDialog(pilihTugas, "Berhasil hapus pengguna!");
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object x = e.getSource();
        if (x.equals(pilihTugas.getPilihTugas())){
            if (app.getOrangList().size()>0){
                pilihTugas.getJudul().setText(app.getOrang(pilihTugas.getPilihTugas().getSelectedIndex()).getNama());
                String status;
                int i = pilihTugas.getPilihTugas().getSelectedIndex();
                if (app.getOrang(i) instanceof Mahasiswa){
                    status = ("Nama : "+app.getOrang(i).getNama()+
                            "\nNIM : "+app.getMahasiswa(i).getNim()+
                            "\nProdi : "+app.getMahasiswa(i).getProdi());
                }else if(app.getOrang(i) instanceof Dosen){
                    status = ("Kode Dosen : "+app.getDosen(i).getKodeDosen()+
                            "\nNama : "+app.getOrang(i).getNama()+
                            "\nNIP : "+app.getDosen(i).getNip());      
                }else{
                    status = ("Nama : "+app.getOrang(i).getNama()+
                            "\nNIP : "+app.getAdmin(i).getNip());
                }
                pilihTugas.getTugas().setText(status);
            }
        }
    }
}
