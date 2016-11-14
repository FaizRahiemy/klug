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
public class ControllerDashboardMahasiswa implements ActionListener{
    private DashboardMahasiswa dashMhs = null;
    private Application app;
    private FileIO file;
    private int userId;
    
    public ControllerDashboardMahasiswa(Application app, FileIO file, int userId){
        dashMhs = new DashboardMahasiswa();
        this.app = app;
        this.file = file;
        this.userId = userId;
        dashMhs.setResizable(false);
        dashMhs.getBtn_akun().addActionListener(this);
        dashMhs.getBtn_logout().addActionListener(this);
        dashMhs.getBtn_materi().addActionListener(this);
        dashMhs.getBtn_pelaporan().addActionListener(this);
        dashMhs.getBtn_quiz().addActionListener(this);
        dashMhs.getBtn_tugas().addActionListener(this);
        dashMhs.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(dashMhs.getBtn_akun())){
            
        }else if(x.equals(dashMhs.getBtn_logout())){
            ControllerLogin login = new ControllerLogin(app,file);
        }else if(x.equals(dashMhs.getBtn_materi())){
            ControllerPilihKelasMateriMahasiswa pilKelMateri = new ControllerPilihKelasMateriMahasiswa(app,file,userId);
        }else if(x.equals(dashMhs.getBtn_tugas())){
            ControllerPilihKelasTugasMahasiswa pilKelTugas = new ControllerPilihKelasTugasMahasiswa(app,file,userId);
        }
        dashMhs.dispose();
    }
}
