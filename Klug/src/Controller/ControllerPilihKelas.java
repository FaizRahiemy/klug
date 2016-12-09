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
/**
 *
 * @author faiz
 */
public class ControllerPilihKelas extends MouseAdapter implements ActionListener{
    private PilihTugasDosen pilihTugas = null;
    private Application app;
    private IOFile file;
    private int userId;
    
    public ControllerPilihKelas(Application app, IOFile file, int userId){
        pilihTugas = new PilihTugasDosen();
        this.app = app;
        this.file = file;
        this.userId = userId;
        pilihTugas.setResizable(false);
        pilihTugas.getBtn_back().addActionListener(this);
        pilihTugas.getBtn_tambah().addActionListener(this);
        pilihTugas.getBtn_hapus().addActionListener(this);
        pilihTugas.getBtn_lihat().addActionListener(this);
        pilihTugas.getPilihTugas().addMouseListener(this);
        pilihTugas.getJudulHalaman().setText("Kelola Kelas");
        pilihTugas.getjLabel1().setText("Daftar Kelas");
        pilihTugas.getBtn_tambah().setText("Tambah Kelas");
        pilihTugas.getBtn_hapus().setText("Hapus Kelas");
        DefaultListModel modelList = new DefaultListModel();
        pilihTugas.getPilihTugas().setModel(modelList);
        if (app.getOrangList().size()>0){
            for(int i=0;i<app.getKelasList().size();i++){
                modelList.addElement(app.getKelas(i).getNamaMataKuliah());
                if (i == 0){
                    pilihTugas.getJudul().setText(app.getKelas(i).getNamaMataKuliah());
                    pilihTugas.getTugas().setText("Dosen : "+app.getKelas(i).getDosen().getNama()+
                                                    "\nRuangan : "+app.getKelas(i).getRuang()+
                                                    "\nJadwal : "+app.getKelas(i).getJadwal());
                }
            }
        }else{
            modelList.addElement("Belum ada Kelas");
            pilihTugas.getJudul().setText("Belum ada Kelas");
            pilihTugas.getTugas().setText("Belum ada Kelas");
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
            ControllerKelas materidetail = new ControllerKelas(app, file, userId, pilihTugas.getPilihTugas().getSelectedIndex());
            pilihTugas.dispose();
        }else if(x.equals(pilihTugas.getBtn_tambah())){
            try {
                app.createKelas("Baru", "Baru", "Baru", null);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            ControllerKelas materidetail = new ControllerKelas(app, file, userId, app.getKelasList().size()-1);
            pilihTugas.dispose();
        }else if(x.equals(pilihTugas.getBtn_hapus())){
            if (app.getKelasList().size() > 0){
                int reply = JOptionPane.showConfirmDialog(pilihTugas, "Yakin akan hapus pengguna "+(app.getKelas(pilihTugas.getPilihTugas().getSelectedIndex()).getNamaMataKuliah())+"?", "Yakin?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    app.getKelasList().remove(pilihTugas.getPilihTugas().getSelectedIndex());
                    try {
                        app.saveFile(app.getKelasList());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    DefaultListModel modelList = new DefaultListModel();
                    pilihTugas.getPilihTugas().setModel(modelList);
                    for(int i=0;i<app.getKelasList().size();i++){
                        modelList.addElement(app.getKelas(i).getNamaMataKuliah());
                    }
                    if (app.getKelasList().size() == 1){
                        pilihTugas.getJudul().setText(app.getKelas(0).getNamaMataKuliah());
                        pilihTugas.getJudul().setText(app.getKelas(0).getNamaMataKuliah());
                        pilihTugas.getTugas().setText("Dosen : "+app.getKelas(0).getDosen().getNama()+
                                                        "\nRuangan : "+app.getKelas(0).getRuang()+
                                                        "\nJadwal : "+app.getKelas(0).getJadwal());
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
            if (app.getKelasList().size()>0){
                pilihTugas.getJudul().setText(app.getKelas(pilihTugas.getPilihTugas().getSelectedIndex()).getNamaMataKuliah());
                pilihTugas.getJudul().setText(app.getKelas(pilihTugas.getPilihTugas().getSelectedIndex()).getNamaMataKuliah());
                pilihTugas.getTugas().setText("Dosen : "+app.getKelas(pilihTugas.getPilihTugas().getSelectedIndex()).getDosen().getNama()+
                                                "\nRuangan : "+app.getKelas(pilihTugas.getPilihTugas().getSelectedIndex()).getRuang()+
                                                "\nJadwal : "+app.getKelas(pilihTugas.getPilihTugas().getSelectedIndex()).getJadwal());
            }
        }
    }
}
