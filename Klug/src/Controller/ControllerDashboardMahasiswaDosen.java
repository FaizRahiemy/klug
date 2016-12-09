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
public class ControllerDashboardMahasiswaDosen implements ActionListener{
    private DashboardMahasiswaDosen dashMhs = null;
    private Application app;
    private IOFile file;
    private int userId;
    
    public ControllerDashboardMahasiswaDosen(Application app, IOFile file, int userId){
        dashMhs = new DashboardMahasiswaDosen();
        this.app = app;
        this.file = file;
        this.userId = userId;
        dashMhs.setResizable(false);
        if (app.getOrang(userId) instanceof Dosen){
            dashMhs.setJudul("Dashboard Dosen");
        }
        dashMhs.setWelkom("Selamat Datang, " + app.getOrang(userId).getNama());
        dashMhs.getBtn_akun().addActionListener(this);
        dashMhs.getBtn_logout().addActionListener(this);
        dashMhs.getBtn_materi().addActionListener(this);
        dashMhs.getBtn_kehadiran().addActionListener(this);
        dashMhs.getBtn_nilai().addActionListener(this);
        dashMhs.getBtn_quiz().addActionListener(this);
        dashMhs.getBtn_tugas().addActionListener(this);
        dashMhs.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if (app.getOrang(userId) instanceof Mahasiswa){
            if(x.equals(dashMhs.getBtn_materi())){
                ControllerPilihKelasMateriMahasiswa pilKelMateri = new ControllerPilihKelasMateriMahasiswa(app,file,userId);
            }else if(x.equals(dashMhs.getBtn_tugas())){
                ControllerPilihKelasTugasMahasiswa pilKelTugas = new ControllerPilihKelasTugasMahasiswa(app,file,userId);
            }else if(x.equals(dashMhs.getBtn_quiz())){
                ControllerPilihKelasQuizMahasiswa pilKelQuiz = new ControllerPilihKelasQuizMahasiswa(app,file,userId);
            }else if(x.equals(dashMhs.getBtn_kehadiran())){
                ControllerKehadiranMahasiswa kehadiran = new ControllerKehadiranMahasiswa(app,file,userId);
            }else if(x.equals(dashMhs.getBtn_nilai())){
                ControllerPilihKelasNilaiMahasiswa kehadiran = new ControllerPilihKelasNilaiMahasiswa(app,file,userId);
            }else if(x.equals(dashMhs.getBtn_akun())){
                ControllerAkunUser akun = new ControllerAkunUser(app,file,userId);
            }else if(x.equals(dashMhs.getBtn_logout())){
                ControllerLogin login = new ControllerLogin(app,file);
            }
        }else{
            if(x.equals(dashMhs.getBtn_materi())){
                ControllerPilihKelasMateriDosen pilKelMateri = new ControllerPilihKelasMateriDosen(app,file,userId);
            }else if(x.equals(dashMhs.getBtn_tugas())){
                ControllerPilihKelasTugasDosen pilKelTugas = new ControllerPilihKelasTugasDosen(app,file,userId);
            }else if(x.equals(dashMhs.getBtn_quiz())){
                ControllerPilihKelasQuizDosen pilKelQuiz = new ControllerPilihKelasQuizDosen(app,file,userId);
            }else if(x.equals(dashMhs.getBtn_kehadiran())){
                ControllerPilihKelasKehadiranDosen kehadiran = new ControllerPilihKelasKehadiranDosen(app,file,userId);
            }else if(x.equals(dashMhs.getBtn_nilai())){
                ControllerPilihKelasNilaiDosen kehadiran = new ControllerPilihKelasNilaiDosen(app,file,userId);
            }else if(x.equals(dashMhs.getBtn_akun())){
                ControllerAkunUser akun = new ControllerAkunUser(app,file,userId);
            }else if(x.equals(dashMhs.getBtn_logout())){
                ControllerLogin login = new ControllerLogin(app,file);
            }
        }
        dashMhs.dispose();
    }
}
