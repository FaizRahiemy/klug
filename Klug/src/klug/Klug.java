/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klug;

import Model.Application;
import Controller.ControllerLogin;
import java.io.IOException;
import FileIO.IOFile;
import Model.*;

/**
 *
 * @author faiz
 */
public class Klug {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        IOFile file = new IOFile();
        Application app = new Application(file);
        ControllerLogin c = new ControllerLogin(app, file);
    }
}
