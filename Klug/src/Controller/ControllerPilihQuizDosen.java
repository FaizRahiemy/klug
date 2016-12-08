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
public class ControllerPilihQuizDosen extends MouseAdapter implements ActionListener{
    private PilihQuizMahasiswa pilKel = null;
    private Application app;
    private FileIO file;
    private int userId;
    private int kelasId;
    
    public ControllerPilihQuizDosen(Application app, FileIO file, int userId, int kelasId){
        pilKel = new PilihQuizMahasiswa();
        this.app = app;
        this.file = file;
        this.userId = userId;
        this.kelasId = kelasId;
        pilKel.setResizable(false);
        pilKel.getBtn_back().addActionListener(this);
        pilKel.getBtn_kerjakan().addActionListener(this);
        pilKel.getBtn_kerjakan().setText("Lihat");
        pilKel.getPilihQuiz().addMouseListener(this);
        pilKel.getBtn_tambah().addActionListener(this);
        pilKel.getBtn_hapus().addActionListener(this);
        pilKel.getJudulHalaman().setText("Quiz " + app.getDosen(userId).getKelas(kelasId).getNamaMataKuliah());
        DefaultListModel modelList = new DefaultListModel();
        pilKel.getPilihQuiz().setModel(modelList);
        if (app.getDosen(userId).getKelas(kelasId).getQuizList().size()>0){
            for(int i=0;i<app.getDosen(userId).getKelas(kelasId).getQuizList().size();i++){
                modelList.addElement(app.getDosen(userId).getKelas(kelasId).getQuiz(i).getJudulQuiz());
                if (i==0){
                    pilKel.getJudul().setText(app.getDosen(userId).getKelas(kelasId).getQuiz(0).getJudulQuiz());
                    pilKel.getStatus().setText("Jumlah Soal : "+app.getDosen(userId).getKelas(kelasId).getQuiz(0).getSoalList().size());
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
            ControllerPilihKelasQuizDosen dashMhs = new ControllerPilihKelasQuizDosen(app,file,userId);
            pilKel.dispose();
        }else if(x.equals(pilKel.getBtn_kerjakan())){
            ControllerQuizDosen quizsMhs = new ControllerQuizDosen(app,file,userId,kelasId,pilKel.getPilihQuiz().getSelectedIndex());
            pilKel.dispose();
        }else if(x.equals(pilKel.getBtn_tambah())){
            Quiz quiz = new Quiz("Baru", app.getDosen(userId).getKelas(kelasId), new Nilai("Quiz Baru", app.getDosen(userId).getKelas(kelasId)));
            quiz.createSoal(new Soal("Soal Baru"));
            quiz.getSoal(0).createJawaban("jawaban baru", true);
            quiz.getSoal(0).createJawaban("jawaban baru", false);
            quiz.getSoal(0).createJawaban("jawaban baru", false);
            quiz.getSoal(0).createJawaban("jawaban baru", false);
            quiz.getSoal(0).createJawaban("jawaban baru", false);
            app.getDosen(userId).getKelas(kelasId).createQuiz(quiz);
            ControllerQuizDosen quizsMhs = new ControllerQuizDosen(app,file,userId,kelasId,app.getDosen(userId).getKelas(kelasId).getQuizList().size()-1);
            pilKel.dispose();
        }else if(x.equals(pilKel.getBtn_hapus())){
            if (app.getDosen(userId).getKelas(kelasId).getQuizList().size() > 0){
                int reply = JOptionPane.showConfirmDialog(pilKel, "Yakin akan hapus quiz "+(app.getDosen(userId).getKelas(kelasId).getQuiz(pilKel.getPilihQuiz().getSelectedIndex()).getJudulQuiz())+"?", "Yakin?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    app.getDosen(userId).getKelas(kelasId).getQuizList().remove(pilKel.getPilihQuiz().getSelectedIndex());
                    DefaultListModel modelList = new DefaultListModel();
                    pilKel.getPilihQuiz().setModel(modelList);
                    for(int i=0;i<app.getDosen(userId).getKelas(kelasId).getQuizList().size();i++){
                        modelList.addElement(app.getDosen(userId).getKelas(kelasId).getQuiz(i).getJudulQuiz());
                    }
                    if (app.getDosen(userId).getKelas(kelasId).getQuizList().size() == 1){
                        pilKel.getStatus().setText("Jumlah Soal : "+app.getDosen(userId).getKelas(kelasId).getQuiz(0).getSoalList().size());
                    }
                    pilKel.getPilihQuiz().setSelectedIndex(0);
                    JOptionPane.showMessageDialog(pilKel, "Berhasil hapus quiz!");
                }
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Object x = e.getSource();
        if (x.equals(pilKel.getPilihQuiz())){
            if (app.getKelas(kelasId).getQuizList().size()>0){
                pilKel.getJudul().setText(app.getDosen(userId).getKelas(kelasId).getQuiz(pilKel.getPilihQuiz().getSelectedIndex()).getJudulQuiz());
                pilKel.getStatus().setText("Jumlah Soal : "+app.getDosen(userId).getKelas(kelasId).getQuiz(pilKel.getPilihQuiz().getSelectedIndex()).getSoalList().size());
            }
        }
    }
}
