/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klug;

import Model.Application;
import Controller.ControllerLogin;
import java.io.IOException;
import FileIO.FileIO;
import Model.Dosen;
import Model.TugasMhs;

/**
 *
 * @author faiz
 */
public class Klug {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application app = new Application();
        FileIO file = new FileIO();
        app.createMahasiswa("tes", "tes", "tes", "tes", "tes");
        app.createDosen("a", "a", "a", "a", "a");
        app.createKelas("IMPAL IF-38-02", "A307B", "Jumat, 15.30-18.30", (Dosen) app.getOrang(1));
        app.getMahasiswa(0).addKelas(app.getKelas(0));
        app.createKelas("Sisop IF-38-02", "A307B", "Kamis, 15.30-18.30", (Dosen) app.getOrang(1));
        app.getMahasiswa(0).addKelas(app.getKelas(1));
        app.getKelas(0).createMateri("MVC", "Model View Controller");
        app.getKelas(0).createMateri("Pattern", "Strategy Pattern");
        app.getKelas(0).createTugas("Rangkum MVC", "Rangkum Model View Controller");
        app.getKelas(0).createTugas("Rangkum Pattern", "Rangkum Strategy Pattern");
        app.getMahasiswa(0).createTugas(new TugasMhs(app.getKelas(0).getTugas(0),"D:"));
        ControllerLogin c = new ControllerLogin(app, file);
    }
    
}
