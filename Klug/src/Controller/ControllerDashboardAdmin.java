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
import javax.swing.JOptionPane;
/**
 *
 * @author faiz
 */
public class ControllerDashboardAdmin implements ActionListener{
    private DashboardAdmin dashMhs = null;
    private Application app;
    private FileIO file;
    private int userId;
    
    public ControllerDashboardAdmin(Application app, FileIO file, int userId){
        dashMhs = new DashboardAdmin();
        this.app = app;
        this.file = file;
        this.userId = userId;
        dashMhs.setResizable(false);
        if (app.getOrang(userId) instanceof Dosen){
            dashMhs.setJudul("Dashboard Dosen");
        }
        dashMhs.setWelkom("Selamat Datang, " + app.getOrang(userId).getNama());
        dashMhs.getBtn_logout().addActionListener(this);
        dashMhs.getBtnPengguna().addActionListener(this);
        dashMhs.getBtnKelas().addActionListener(this);
        dashMhs.getBtnAkun().addActionListener(this);
        dashMhs.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(dashMhs.getBtnPengguna())){
            ControllerPilihPengguna pengguna = new ControllerPilihPengguna(app,file,userId);
        }else if(x.equals(dashMhs.getBtnKelas())){
            ControllerPilihKelas akun = new ControllerPilihKelas(app,file,userId);
        }else if(x.equals(dashMhs.getBtnAkun())){
            ControllerAkunUser acc = new ControllerAkunUser(app,file,userId);
        }else if(x.equals(dashMhs.getBtn_logout())){
            ControllerLogin login = new ControllerLogin(app,file);
        }
        dashMhs.dispose();
    }
}
