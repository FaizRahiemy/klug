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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author faiz
 */
public class ControllerPengguna extends MouseAdapter implements ActionListener{
    private Pengguna akun = null;
    private Application app;
    private IOFile file;
    private int userId;
    private int pengguna;
    private int jenis;
    
    public ControllerPengguna(Application app, IOFile file, int userId, int pengguna){
        akun = new Pengguna();
        this.app = app;
        this.file = file;
        this.userId = userId;
        this.pengguna = pengguna;
        akun.getJenis().addMouseListener(this);
        akun.setResizable(false);
        if (pengguna != -1){
            akun.getJenis().setEnabled(false);
            if (app.getOrang(pengguna) instanceof Mahasiswa){
                jenis = 0;
                akun.getNim().setText(app.getMahasiswa(pengguna).getNim());
                akun.getProdi().setText(app.getMahasiswa(pengguna).getProdi());
            }else if (app.getOrang(pengguna) instanceof Dosen){
                jenis = 1;
                akun.getLabelNim().setText("NIP");
                akun.getNim().setText(app.getDosen(pengguna).getNip());
                akun.getLabelProdi().setText("Kode Dosen");
                akun.getProdi().setText(app.getDosen(pengguna).getKodeDosen());
            }else{
                jenis = 2;
                akun.getLabelNim().setText("NIP");
                akun.getNim().setText(app.getAdmin(pengguna).getNip());
                akun.getLabelProdi().setVisible(false);
                akun.getProdi().setVisible(false);
            }
            akun.setUsername(app.getOrang(pengguna).getUsername());
            akun.getPassword().setText(app.getOrang(pengguna).getPassword());
            akun.getPassword1().setText(app.getOrang(pengguna).getPassword());
            akun.getNama().setText(app.getOrang(pengguna).getNama());
            akun.getJenis().setSelectedIndex(jenis);
        }
        akun.getBtnSimpan().addActionListener(this);
        akun.getBtnBatal().addActionListener(this);
        akun.getRootPane().setDefaultButton(akun.getBtnSimpan());
        akun.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(akun.getBtnSimpan())){
            String pass = akun.getPassword().getText();
            String pass1 = akun.getPassword1().getText();
            String username = akun.getUsername();
            String nim = akun.getNim().getText();
            String nama = akun.getNama().getText();
            String prodi = "";
            int jen = akun.getJenis().getSelectedIndex();
            if (pengguna != -1){
                if (app.getOrang(pengguna) instanceof Mahasiswa || app.getOrang(pengguna) instanceof Dosen){
                    prodi = akun.getProdi().getText();
                }
                if (pass.isEmpty() == false){
                    if (username.isEmpty() == false && nama.isEmpty() == false && nim.isEmpty() == false){
                        if (pass.equals(pass1)){
                            app.getOrang(pengguna).setPassword(pass);
                            app.getOrang(pengguna).setUsername(username);
                            app.getOrang(pengguna).setNama(nama);
                            if (app.getOrang(pengguna) instanceof Mahasiswa){
                                app.getMahasiswa(pengguna).setNim(nim);
                                app.getMahasiswa(pengguna).setProdi(prodi);
                            }else if (app.getOrang(pengguna) instanceof Dosen){
                                app.getDosen(pengguna).setNip(nim);
                                app.getDosen(pengguna).setKodeDosen(prodi);
                            }else{
                                app.getAdmin(pengguna).setNip(nim);
                            }
                            JOptionPane.showMessageDialog(akun, "Ubah data berhasil!");
                            try {
                                app.saveFile(app.getOrangList());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            akun.dispose();
                            ControllerPilihPengguna dash = new ControllerPilihPengguna(app, file, userId);
                        }else{
                            JOptionPane.showMessageDialog(akun, "Password tidak sama!");
                        }
                    }else{
                        JOptionPane.showMessageDialog(akun, "Semua kolom harus diisi!");
                    }
                }else{
                    if (username.isEmpty() == false && nama.isEmpty() == false && nim.isEmpty() == false){
                        app.getOrang(pengguna).setUsername(username);
                        app.getOrang(pengguna).setNama(nama);
                        if (app.getOrang(pengguna) instanceof Mahasiswa){
                            app.getMahasiswa(pengguna).setNim(nim);
                            app.getMahasiswa(pengguna).setProdi(prodi);
                        }else if (app.getOrang(pengguna) instanceof Dosen){
                            app.getDosen(pengguna).setNip(nim);
                            app.getDosen(pengguna).setKodeDosen(prodi);
                        }else{
                            app.getAdmin(pengguna).setNip(nim);
                        }
                        try {
                            app.saveFile(app.getOrangList());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        akun.dispose();
                        ControllerPilihPengguna dash = new ControllerPilihPengguna(app, file, userId);
                    }
                }
            }else{
                int res = -1;
                for (int i = 0; i < app.getOrangList().size(); i++) {
                    if (app.getOrang(i).getUsername().equals(username)){
                        JOptionPane.showMessageDialog(akun, "Username sudah ada!");
                        res = i;
                        break;
                    }
                }
                if (res == -1){
                System.out.println("masuk");
                    if (username.isEmpty() == false && nama.isEmpty() == false && nim.isEmpty() == false && pass.isEmpty() == false){
                        if (pass.equals(pass1)){
                            if (jen == 0){
                                prodi = akun.getProdi().getText();
                                try {
                                    app.createMahasiswa(username, pass, nama, nim, prodi);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }else if (jen == 1){
                                prodi = akun.getProdi().getText();
                                try {
                                    app.createDosen(username, pass, nama, nim, prodi);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }else{
                                try {
                                    app.createAdmin(username, pass, nama, nim);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            try {
                                app.saveFile(app.getOrangList());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            akun.dispose();
                            ControllerPilihPengguna dash = new ControllerPilihPengguna(app, file, userId);
                        }else{
                            JOptionPane.showMessageDialog(akun, "Password tidak sama!");
                        }
                    }else{
                        JOptionPane.showMessageDialog(akun, "Semua kolom harus diisi!");
                    }
                }
            }
        }else{
            akun.dispose();
            ControllerPilihPengguna dash = new ControllerPilihPengguna(app, file, userId);
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        Object x = e.getSource();
        if (x.equals(akun.getJenis())){
            if (akun.getJenis().getSelectedIndex() == 0){
//                System.out.println("masuk");
                akun.getLabelNim().setText("NIM");
                akun.getLabelProdi().setText("Program Studi");
                akun.getLabelProdi().setVisible(true);
                akun.getProdi().setVisible(true);
            }else if (akun.getJenis().getSelectedIndex() == 1){
                akun.getLabelNim().setText("NIP");
                akun.getLabelProdi().setText("Kode Dosen");
                akun.getLabelProdi().setVisible(true);
                akun.getProdi().setVisible(true);
            }else{
                akun.getLabelNim().setText("NIP");
                akun.getLabelProdi().setVisible(false);
                akun.getProdi().setVisible(false);
            }
        }
    }
}
