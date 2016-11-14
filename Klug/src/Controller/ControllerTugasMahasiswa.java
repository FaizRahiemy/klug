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
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
/**
 *
 * @author faiz
 */
public class ControllerTugasMahasiswa extends MouseAdapter implements ActionListener{
    private TugasMahasiswa tugas = null;
    private Application app;
    private FileIO file;
    private int userId;
    private int kelasId;
    
    public ControllerTugasMahasiswa(Application app, FileIO file, int userId, int kelasId){
        tugas = new TugasMahasiswa();
        this.app = app;
        this.file = file;
        this.userId = userId;
        this.kelasId = kelasId;
        tugas.setResizable(false);
        tugas.getBtn_back().addActionListener(this);
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
        }
        tugas.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object x = e.getSource();
        if (x.equals(tugas.getPilihTugas())){
            if (app.getKelas(kelasId).getTugasList().size()>0){
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
