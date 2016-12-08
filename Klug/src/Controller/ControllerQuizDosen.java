/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Model.*;
import View.*;
import FileIO.*;
import java.awt.Color;
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
public class ControllerQuizDosen extends MouseAdapter implements ActionListener{
    private QuizMahasiswa pilKel = null;
    private Application app;
    private FileIO file;
    private int userId;
    private int kelasId;
    private int quizId;
    
    public ControllerQuizDosen(Application app, FileIO file, int userId, int kelasId, int quizId){
        pilKel = new QuizMahasiswa();
        this.app = app;
        this.file = file;
        this.userId = userId;
        this.kelasId = kelasId;
        this.quizId = quizId;
        pilKel.setResizable(false);
        pilKel.getBtn_back().addActionListener(this);
        pilKel.getBtn_finish().addActionListener(this);
        pilKel.getBtn_finish().setText("Simpan");
        pilKel.getSoal().setEditable(true);
        pilKel.getBtn_tambah().addActionListener(this);
        pilKel.getBtn_hapus().addActionListener(this);
        for (int i=0;i<5;i++){
            pilKel.getJawaban(i+1).addActionListener(this);
        }
        pilKel.getPilihQuiz().addMouseListener(this);
        pilKel.getJudulHalaman().setText("Quiz");
        if (quizId == -1){
            pilKel.getJudulQuiz().setText("Baru");
        }else{
            pilKel.getJudulQuiz().setText(app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getJudulQuiz());
        }
        pilKel.getJudul().setText("Soal 1");
        DefaultListModel modelList = new DefaultListModel();
        pilKel.getPilihQuiz().setModel(modelList);
        if (app.getDosen(userId).getKelas(kelasId).getQuizList().size()>0){
            if (quizId != -1){
                for(int i=0;i<app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoalList().size();i++){
                    modelList.addElement("Soal "+(i+1));
                    if (i==0){
                        pilKel.getSoal().setText(app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoal(0).getSoal());
                        int j;
                        for (j=0;j<5;j++){
                            if (app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoal(i).getJawaban(j).isBenar()){
                                pilKel.getJawaban(j+1).setSelected(true);
                            }else{
                            }
                            pilKel.getJaw(j+1).setText(app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoal(i).getJawaban(j).getJawaban());
                        }
                    }
                }
            }
        }else{
            modelList.addElement("Belum ada Soal");
            pilKel.getJudul().setText("Belum ada Soal");
            for (int i=1;i<=5;i++){
                pilKel.getJawaban(i).setText("Belum ada Soal");
            }
        }
        pilKel.getPilihQuiz().setSelectedIndex(0);
        pilKel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object x = e.getSource();
        if(x.equals(pilKel.getBtn_back())){
            app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).setJudulQuiz(pilKel.getJudulQuiz().getText());
            app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getNilai().setJudulNilai("Quiz "+pilKel.getJudulQuiz().getText());
            ControllerPilihQuizDosen quizMhs = new ControllerPilihQuizDosen(app,file,userId,kelasId);
            pilKel.dispose();
        }else if(x.equals(pilKel.getBtn_finish())){
            app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoal(pilKel.getPilihQuiz().getSelectedIndex()).setSoal(pilKel.getSoal().getText());
            for (int i=0;i<5;i++){
                app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoal(pilKel.getPilihQuiz().getSelectedIndex()).getJawaban(i).setJawaban(pilKel.getJaw(i+1).getText());
            }
            JOptionPane.showMessageDialog(pilKel, "Soal "+(pilKel.getPilihQuiz().getSelectedIndex()+1)+" berhasil disimpan!");
        }else if(x.equals(pilKel.getBtn_tambah())){
            Soal soal = new Soal("Soal Baru");
            soal.createJawaban("Jawaban baru", true);
            soal.createJawaban("Jawaban baru", false);
            soal.createJawaban("Jawaban baru", false);
            soal.createJawaban("Jawaban baru", false);
            soal.createJawaban("Jawaban baru", false);
            app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).createSoal(soal);
            DefaultListModel modelList = new DefaultListModel();
            pilKel.getPilihQuiz().setModel(modelList);
            for(int i=0;i<app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoalList().size();i++){
                modelList.addElement("Soal "+(i+1));
            }
            if (app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoalList().size() == 1){
                pilKel.getSoal().setText(app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoal(0).getSoal());
                for (int j=0;j<5;j++){
                    if (app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoal(0).getJawaban(j).isBenar()){
                        pilKel.getJawaban(j+1).setSelected(true);
                    }else{
                    }
                    pilKel.getJaw(j+1).setText(app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoal(0).getJawaban(j).getJawaban());
                }
            }
            pilKel.getPilihQuiz().setSelectedIndex(0);
            JOptionPane.showMessageDialog(pilKel, "Berhasil tambah soal!");
        }else if(x.equals(pilKel.getBtn_hapus())){
            if (app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoalList().size() > 0){
                int reply = JOptionPane.showConfirmDialog(pilKel, "Yakin akan hapus soal "+(pilKel.getPilihQuiz().getSelectedIndex()+1)+"?", "Yakin?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoalList().remove(pilKel.getPilihQuiz().getSelectedIndex());
                    DefaultListModel modelList = new DefaultListModel();
                    pilKel.getPilihQuiz().setModel(modelList);
                    for(int i=0;i<app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoalList().size();i++){
                        modelList.addElement("Soal "+(i+1));
                    }
                    if (app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoalList().size() == 1){
                        pilKel.getSoal().setText(app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoal(0).getSoal());
                        for (int j=0;j<5;j++){
                            if (app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoal(0).getJawaban(j).isBenar()){
                                pilKel.getJawaban(j+1).setSelected(true);
                            }else{
                            }
                            pilKel.getJaw(j+1).setText(app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoal(0).getJawaban(j).getJawaban());
                        }
                    }
                    pilKel.getPilihQuiz().setSelectedIndex(0);
                    JOptionPane.showMessageDialog(pilKel, "Berhasil hapus soal!");
                }
            }
        }
        for (int i=0;i<5;i++){
            if(x.equals(pilKel.getJawaban(i+1))){
                app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoal(pilKel.getPilihQuiz().getSelectedIndex()).getJawaban(i).setBenar(true);
                for (int j=0;j<5;j++){
                    if (j!=i) app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoal(pilKel.getPilihQuiz().getSelectedIndex()).getJawaban(j).setBenar(false);
                }
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Object x = e.getSource();
        if (x.equals(pilKel.getPilihQuiz())){
            if (app.getKelas(kelasId).getQuiz(quizId).getSoalList().size()>0){
                pilKel.getJudul().setText("Soal "+(pilKel.getPilihQuiz().getSelectedIndex()+1));
                pilKel.getSoal().setText(app.getKelas(kelasId).getQuiz(quizId).getSoal(pilKel.getPilihQuiz().getSelectedIndex()).getSoal());
                for(int i=0;i<5;i++){
                    if (app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoal(pilKel.getPilihQuiz().getSelectedIndex()).getJawaban(i).isBenar()){
                        pilKel.getJawaban(i+1).setSelected(true);
                    }else{
                    }
                    pilKel.getJaw(i+1).setText(app.getDosen(userId).getKelas(kelasId).getQuiz(quizId).getSoal(pilKel.getPilihQuiz().getSelectedIndex()).getJawaban(i).getJawaban());
                }
            }
        }
    }
}
