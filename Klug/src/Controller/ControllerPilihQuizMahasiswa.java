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
public class ControllerPilihQuizMahasiswa extends MouseAdapter implements ActionListener{
    private PilihQuizMahasiswa pilKel = null;
    private Application app;
    private FileIO file;
    private int userId;
    private int kelasId;
    
    public ControllerPilihQuizMahasiswa(Application app, FileIO file, int userId, int kelasId){
        pilKel = new PilihQuizMahasiswa();
        this.app = app;
        this.file = file;
        this.userId = userId;
        this.kelasId = kelasId;
        pilKel.setResizable(false);
        pilKel.getBtn_back().addActionListener(this);
        pilKel.getBtn_kerjakan().addActionListener(this);
        pilKel.getPilihQuiz().addMouseListener(this);
        pilKel.getJudulHalaman().setText("Quiz " + app.getMahasiswa(userId).getKelas(kelasId).getNamaMataKuliah());
        DefaultListModel modelList = new DefaultListModel();
        pilKel.getPilihQuiz().setModel(modelList);
        if (app.getMahasiswa(userId).getKelas(kelasId).getQuizList().size()>0){
            for(int i=0;i<app.getMahasiswa(userId).getKelas(kelasId).getQuizList().size();i++){
                modelList.addElement(app.getKelas(kelasId).getQuiz(i).getJudulQuiz());
                if (i==0){
                    pilKel.getJudul().setText(app.getMahasiswa(userId).getKelas(kelasId).getQuiz(0).getJudulQuiz());
                    boolean udah=false;
                int result = -1;
                for(int j=0;j<app.getMahasiswa(userId).getJawabanList().size();j++){
                    if (app.getMahasiswa(userId).getJawaban(j).getQuiz() == app.getMahasiswa(userId).getKelas(kelasId).getQuiz(0)){
                        result = j;
                        udah = true;
                        break;
                    }
                }
                String status;
                if (udah){
                    if (app.getMahasiswa(userId).getJawaban(result).isSudah()){
                        status = "Sudah dikerjakan";
                        pilKel.getBtn_kerjakan().setText("Lihat");
                    }else{
                        status = "Belum selesai";
                        pilKel.getBtn_kerjakan().setText("Lanjutkan");
                    }
                }else{
                    status = "Belum dikerjakan";
                }
                    pilKel.getStatus().setText("Status : " + status + " \nJumlah Soal : "+app.getMahasiswa(userId).getKelas(kelasId).getQuiz(0).getSoalList().size());
                }
            }
        }else{
            modelList.addElement("Belum ada Quiz");
            pilKel.getJudul().setText("Belum ada Quiz");
            pilKel.getStatus().setText("Belum ada Quiz");
            pilKel.getBtn_kerjakan().setVisible(false);
        }
        pilKel.getPilihQuiz().setSelectedIndex(0);
        pilKel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(pilKel.getBtn_back())){
            ControllerPilihKelasQuizMahasiswa dashMhs = new ControllerPilihKelasQuizMahasiswa(app,file,userId);
        }else if(x.equals(pilKel.getBtn_kerjakan())){
            ControllerQuizMahasiswa quizsMhs = new ControllerQuizMahasiswa(app,file,userId,kelasId,pilKel.getPilihQuiz().getSelectedIndex());
        }
        pilKel.dispose();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        Object x = e.getSource();
        if (x.equals(pilKel.getPilihQuiz())){
            if (app.getKelas(kelasId).getQuizList().size()>0){
                pilKel.getJudul().setText(app.getMahasiswa(userId).getKelas(kelasId).getQuiz(pilKel.getPilihQuiz().getSelectedIndex()).getJudulQuiz());
                boolean udah=false;
                int result = -1;
                for(int i=0;i<app.getMahasiswa(userId).getJawabanList().size();i++){
                    if (app.getMahasiswa(userId).getJawaban(i).getQuiz() == app.getMahasiswa(userId).getKelas(kelasId).getQuiz(pilKel.getPilihQuiz().getSelectedIndex())){
                        result = i;
                        udah = true;
                        break;
                    }
                }
                String status;
                String nilai = "";
                if (udah){
                    if (app.getMahasiswa(userId).getJawaban(result).isSudah()){
                        status = "Sudah dikerjakan";
                        pilKel.getBtn_kerjakan().setText("Lihat");
                        for (int i=0;i<app.getMahasiswa(userId).getNilaiList().size();i++){
                            if (app.getMahasiswa(userId).getNilai(i).getJudulNilai() == "Quiz "+app.getMahasiswa(userId).getJawaban(result).getQuiz().getJudulQuiz()){
                                nilai = "Nilai : "+app.getMahasiswa(userId).getNilai(i);
                                break;
                            };
                        }
                    }else{
                        status = "Belum selesai";
                        pilKel.getBtn_kerjakan().setText("Lanjutkan");
                    }
                }else{
                    status = "Belum dikerjakan";
                    pilKel.getBtn_kerjakan().setText("Kerjakan");
                }
                pilKel.getStatus().setText("Status : " + status + " \nJumlah Soal : "+app.getMahasiswa(userId).getKelas(kelasId).getQuiz(pilKel.getPilihQuiz().getSelectedIndex()).getSoalList().size()+"\n"+nilai);
            }
        }
    }
}
