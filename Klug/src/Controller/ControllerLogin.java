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
public class ControllerLogin implements ActionListener, KeyListener{
    private Login login = null;
    private Application app;
    private FileIO file;
    
    public ControllerLogin(Application app, FileIO file){
        login = new Login();
        this.app = app;
        this.file = file;
        login.setResizable(false);
        login.getBtnLogin().addActionListener(this);
        login.getRootPane().setDefaultButton(login.getBtnLogin());
        login.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(login.getBtnLogin())){
            String idUser = login.getUsername();
            int result = -1;
            for(int i=0; i<app.getOrangList().size(); i++){
                if (idUser .equals(app.getOrangList().get(i).getUsername())){
                    result = i;
                    break;
                }
            }
            if(result == -1){
                JOptionPane.showMessageDialog(login, "Username Tidak Terdaftar");
            }else{
                String pass = login.getPassword();
                if (pass.equals(app.getOrangList().get(result).getPassword())){
                    if ((app.getOrangList().get(result) instanceof Mahasiswa) || (app.getOrangList().get(result) instanceof Dosen)) {
                        ControllerDashboardMahasiswaDosen dashMhs = new ControllerDashboardMahasiswaDosen(app,file,result);
                    }else{
                        JOptionPane.showMessageDialog(login, "Halo "+app.getOrangList().get(result).getNama()+", Login Berhasil");
                    }
                    login.dispose();
                }else{
                    JOptionPane.showMessageDialog(login, "Password Salah");
                }
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_ENTER) {
            String idUser = login.getUsername();
            int result = -1;
            for(int i=0; i<app.getOrangList().size(); i++){
                if (idUser .equals(app.getOrangList().get(i).getUsername())){
                    result = i;
                    break;
                }
            }
            if(result == -1){
                JOptionPane.showMessageDialog(login, "Username Tidak Terdaftar");
            }else{
                String pass = login.getPassword();
                if (pass.equals(app.getOrangList().get(result).getPassword())){
                    if (app.getOrangList().get(result) instanceof Mahasiswa){
                        ControllerDashboardMahasiswaDosen dashMhs = new ControllerDashboardMahasiswaDosen(app,file,result);
                    }else{
                        JOptionPane.showMessageDialog(login, "Halo "+app.getOrangList().get(result).getNama()+", Login Berhasil");
                    }
                    login.dispose();
                }else{
                    JOptionPane.showMessageDialog(login, "Password Salah");
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
