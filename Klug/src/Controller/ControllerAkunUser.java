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
public class ControllerAkunUser implements ActionListener{
    private AkunUser akun = null;
    private Application app;
    private FileIO file;
    private int userId;
    
    public ControllerAkunUser(Application app, FileIO file, int userId){
        akun = new AkunUser();
        this.app = app;
        this.file = file;
        this.userId = userId;
        akun.setResizable(false);
        akun.setUsername(app.getOrang(userId).getNama());
        akun.getBtnSimpan().addActionListener(this);
        akun.getBtnBatal().addActionListener(this);
        akun.getRootPane().setDefaultButton(akun.getBtnSimpan());
        akun.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(akun.getBtnSimpan())){
            String pass = akun.getPassword();
            String pass1 = akun.getPassword1();
            if (pass != ""){
                if (pass.equals(pass1)){
                    app.getOrang(userId).setPassword(pass);
                    JOptionPane.showMessageDialog(akun, "Ubah password berhasil!");
                    akun.dispose();
                    ControllerDashboardMahasiswaDosen dash = new ControllerDashboardMahasiswaDosen(app, file, userId);
                }else{
                    JOptionPane.showMessageDialog(akun, "Password tidak sama!");
                }
            }else{
                JOptionPane.showMessageDialog(akun, "Password harus diisi!");
            }
        }else{
            akun.dispose();
            ControllerDashboardMahasiswaDosen dash = new ControllerDashboardMahasiswaDosen(app, file, userId);
        }
    }
}
