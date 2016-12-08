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
import javax.swing.JOptionPane;
/**
 *
 * @author faiz
 */
public class ControllerPengguna implements ActionListener{
    private Pengguna akun = null;
    private Application app;
    private FileIO file;
    private int userId;
    private int pengguna;
    private int jenis;
    
    public ControllerPengguna(Application app, FileIO file, int userId, int pengguna){
        akun = new Pengguna();
        this.app = app;
        this.file = file;
        this.userId = userId;
        this.pengguna = pengguna;
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
                        akun.dispose();
                        ControllerPilihPengguna dash = new ControllerPilihPengguna(app, file, userId);
                    }else{
                        JOptionPane.showMessageDialog(akun, "Password tidak sama!");
                    }
                }else{
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
                    akun.dispose();
                    ControllerPilihPengguna dash = new ControllerPilihPengguna(app, file, userId);
                }
            }else{
                if (jen == 0){
                    prodi = akun.getProdi().getText();
                    app.createMahasiswa(username, pass, nama, nim, prodi);
                }else if (jen == 1){
                    prodi = akun.getProdi().getText();
                    app.createDosen(username, pass, nama, nim, prodi);
                }else{
                    app.createAdmin(username, pass, nama, nim);
                }
                akun.dispose();
                ControllerPilihPengguna dash = new ControllerPilihPengguna(app, file, userId);
            }
        }else{
            akun.dispose();
            ControllerPilihPengguna dash = new ControllerPilihPengguna(app, file, userId);
        }
    }
}
