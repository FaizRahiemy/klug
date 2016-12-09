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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 *
 * @author faiz
 */
public class ControllerTugasMahasiswa extends MouseAdapter implements ActionListener{
    private TugasMahasiswa tugas = null;
    private Application app;
    private IOFile file;
    private int userId;
    private int kelasId;
    
    public ControllerTugasMahasiswa(Application app, IOFile file, int userId, int kelasId){
        tugas = new TugasMahasiswa();
        this.app = app;
        this.file = file;
        this.userId = userId;
        this.kelasId = kelasId;
        tugas.setResizable(false);
        tugas.getBtn_back().addActionListener(this);
        tugas.getBtn_upload().addActionListener(this);
        tugas.getBtn_download().addActionListener(this);
        tugas.getPilihTugas().addMouseListener(this);
        tugas.getJudulHalaman().setText("Tugas " + app.getKelas(kelasId).getNamaMataKuliah());
        DefaultListModel modelList = new DefaultListModel();
        tugas.getPilihTugas().setModel(modelList);
        if (app.getMahasiswa(userId).getKelas(kelasId).getTugasList().size()>0){
            for(int i=0;i<app.getMahasiswa(userId).getKelas(kelasId).getTugasList().size();i++){
                modelList.addElement(app.getMahasiswa(userId).getKelas(kelasId).getTugas(i).getJudulTugas());
                if (i == 0){
                    tugas.getJudul().setText(app.getMahasiswa(userId).getKelas(kelasId).getTugas(0).getJudulTugas());
                    if (app.getMahasiswa(userId).getTugasList().size()>0){
                        for(int j=0;j<app.getMahasiswa(userId).getTugasList().size();j++){
                            if (app.getMahasiswa(userId).getTugas(j).getTugas() == app.getMahasiswa(userId).getKelas(kelasId).getTugas(0)){
                                tugas.getTugas().setText("Status : Sudah upload"+"\n"+app.getMahasiswa(userId).getKelas(kelasId).getTugas(0).getIsiTugas());
                                tugas.getBtn_download().setVisible(true);
                                //tugas.getBtn_upload().setVisible(false);
                                break;
                            }
                        }
                    }else{
                        tugas.getTugas().setText("Status : Belum upload"+"\n"+app.getMahasiswa(userId).getKelas(kelasId).getTugas(0).getIsiTugas());
                        tugas.getBtn_upload().setVisible(true);
                        tugas.getBtn_download().setVisible(false);
                    }
                }
            }
        }else{
            modelList.addElement("Belum ada Tugas");
            tugas.getJudul().setText("Belum ada Tugas");
            tugas.getTugas().setText("Belum ada Tugas");
            tugas.getBtn_upload().setVisible(false);
            tugas.getBtn_download().setVisible(false);
        }
        tugas.getPilihTugas().setSelectedIndex(0);
        tugas.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(tugas.getBtn_back())){
            ControllerPilihKelasTugasMahasiswa pilKelTugas = new ControllerPilihKelasTugasMahasiswa(app,file,userId);
            tugas.dispose();
        }else if(x.equals(tugas.getBtn_upload())){
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(tugas);
            File file1 = chooser.getSelectedFile();
            File dir2 = new File("tugas/"+app.getMahasiswa(userId).getNim()+"_"+app.getMahasiswa(userId).getKelas(kelasId).getNamaMataKuliah()+"_"+app.getMahasiswa(userId).getKelas(kelasId).getTugas(tugas.getPilihTugas().getSelectedIndex()).getJudulTugas()+"_"+file1.getName());
            try {
                Files.copy(file1.toPath(),dir2.toPath(), StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(tugas, "Upload Tugas Sukses");
                int result = -1;
                for(int i=0;i<app.getMahasiswa(userId).getTugasList().size();i++){
                    if (app.getMahasiswa(userId).getTugas(i).getTugas() == app.getMahasiswa(userId).getKelas(kelasId).getTugas(tugas.getPilihTugas().getSelectedIndex())){
                        app.getMahasiswa(userId).getTugas(i).setStatus(true);
                        app.getMahasiswa(userId).getTugas(userId).setLokasi(app.getMahasiswa(userId).getNim()+"_"+app.getMahasiswa(userId).getKelas(kelasId).getNamaMataKuliah()+"_"+app.getMahasiswa(userId).getKelas(kelasId).getTugas(tugas.getPilihTugas().getSelectedIndex()).getJudulTugas()+"_"+file1.getName());
                        try {
                            app.saveFile(app.getOrangList());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        tugas.getTugas().setText("Status : Sudah upload"+"\n"+app.getMahasiswa(userId).getKelas(kelasId).getTugas(tugas.getPilihTugas().getSelectedIndex()).getIsiTugas());
                        tugas.getBtn_download().setVisible(true);
                        result = i;
                    }
                }
                if (result == -1){
                    app.getMahasiswa(userId).createTugas(new TugasMhs(app.getMahasiswa(userId).getKelas(kelasId).getTugas(tugas.getPilihTugas().getSelectedIndex()), app.getMahasiswa(userId).getNim()+"_"+app.getMahasiswa(userId).getKelas(kelasId).getNamaMataKuliah()+"_"+app.getMahasiswa(userId).getKelas(kelasId).getTugas(tugas.getPilihTugas().getSelectedIndex()).getJudulTugas()+"_"+file1.getName()));
                    try {
                        app.saveFile(app.getOrangList());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    tugas.getTugas().setText("Status : Sudah upload"+"\n"+app.getMahasiswa(userId).getKelas(kelasId).getTugas(tugas.getPilihTugas().getSelectedIndex()).getIsiTugas());
                        tugas.getBtn_download().setVisible(true);
                }
            }catch(Exception ae){
                JOptionPane.showMessageDialog(tugas, "ERROR");
            }
        }else if(x.equals(tugas.getBtn_download())){
            JFileChooser fc = new JFileChooser("D:/");
            fc.setAcceptAllFileFilterUsed(false);
            File ff = new File("");
            for(int i=0;i<app.getMahasiswa(userId).getTugasList().size();i++){
                if (app.getMahasiswa(userId).getTugas(i).getTugas() == app.getMahasiswa(userId).getKelas(kelasId).getTugas(tugas.getPilihTugas().getSelectedIndex())){
                    ff = new File("tugas/"+app.getMahasiswa(userId).getTugas(i).getLokasi());
                }
            }
            fc.setSelectedFile(ff);
            
            fc.setCurrentDirectory(new File("D:/"));
            int returnValue = fc.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File newFile = fc.getSelectedFile();
                try {
                    Files.copy(ff.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    JOptionPane.showMessageDialog(tugas, "Download Tugas Sukses");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(tugas,"Coba save di folder lain!");
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object x = e.getSource();
        if (x.equals(tugas.getPilihTugas())){
            if (app.getMahasiswa(userId).getKelas(kelasId).getTugasList().size()>0){
                tugas.getJudul().setText(app.getMahasiswa(userId).getKelas(kelasId).getTugas(tugas.getPilihTugas().getSelectedIndex()).getJudulTugas());
                if (app.getMahasiswa(userId).getTugasList().size()>0){
                    int result = -1;
                    for(int i=0;i<app.getMahasiswa(userId).getTugasList().size();i++){
                        if (app.getMahasiswa(userId).getTugas(i).getTugas() == app.getMahasiswa(userId).getKelas(kelasId).getTugas(tugas.getPilihTugas().getSelectedIndex())){
                            tugas.getTugas().setText("Status : Sudah upload"+"\n"+app.getMahasiswa(userId).getKelas(kelasId).getTugas(tugas.getPilihTugas().getSelectedIndex()).getIsiTugas());
                            result = i;
                            tugas.getBtn_download().setVisible(true);
                            //tugas.getBtn_upload().setVisible(false);
                            break;
                        }
                    }
                    if (result == -1){
                        tugas.getTugas().setText("Status : Belum upload"+"\n"+app.getMahasiswa(userId).getKelas(kelasId).getTugas(tugas.getPilihTugas().getSelectedIndex()).getIsiTugas());
                        tugas.getBtn_upload().setVisible(true);
                        tugas.getBtn_download().setVisible(false);
                    }
                }
            }
        }
    }
}
