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
public class Controller implements ActionListener{
    private View view = null;
    private Application app;
    private FileIO file;
    
    public Controller(Application app, FileIO file){
        this.app = app;
        this.file = file;
        gotoLogin();
    }
    
    public void gotoLogin(){
        Login login = new Login();
        login.setVisible(true);
        login.addActionListener(this);
        view = login;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
