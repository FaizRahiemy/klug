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
public class ControllerQuizMahasiswa extends MouseAdapter implements ActionListener{
    private QuizMahasiswa pilKel = null;
    private Application app;
    private FileIO file;
    private int userId;
    private int kelasId;
    private int quizId;
    
    private JawabanMahasiswa jawaban;
    
    public ControllerQuizMahasiswa(Application app, FileIO file, int userId, int kelasId, int quizId){
        pilKel = new QuizMahasiswa();
        this.app = app;
        this.file = file;
        this.userId = userId;
        this.kelasId = kelasId;
        this.quizId = quizId;
        pilKel.setResizable(false);
        pilKel.getBtn_back().addActionListener(this);
        pilKel.getBtn_finish().addActionListener(this);
        for (int i=0;i<5;i++){
            pilKel.getJawaban(i+1).addActionListener(this);
        }
        pilKel.getPilihQuiz().addMouseListener(this);
        pilKel.getJudulHalaman().setText("Quiz " + app.getMahasiswa(userId).getKelas(kelasId).getQuiz(quizId).getJudulQuiz());
        pilKel.getJudul().setText("Soal 1");
        DefaultListModel modelList = new DefaultListModel();
        pilKel.getPilihQuiz().setModel(modelList);
        boolean udah=false;
        int result = -1;
        for(int j=0;j<app.getMahasiswa(userId).getJawabanList().size();j++){
            if (app.getMahasiswa(userId).getJawaban(j).getQuiz() == app.getMahasiswa(userId).getKelas(kelasId).getQuiz(quizId)){
                result = j;
                udah = true;
                break;
            }
        }
        if (result == -1){
            jawaban = new JawabanMahasiswa(app.getMahasiswa(userId),app.getMahasiswa(userId).getKelas(kelasId).getQuiz(quizId));
            app.getMahasiswa(userId).createJawaban(jawaban);
            for (int j=0;j<app.getMahasiswa(userId).getKelas(kelasId).getQuiz(quizId).getSoalList().size();j++){
                jawaban.getJawabanList().add(app.getMahasiswa(userId).getKelas(kelasId).getQuiz(quizId).getSoal(j).getJawaban(0));
            }
        }else{
            jawaban = app.getMahasiswa(userId).getJawaban(result);
        }
        if (jawaban.isSudah()){
            pilKel.getBtn_finish().setVisible(false);
            for (int i=0;i<5;i++){
                pilKel.getJawaban(i+1).setEnabled(false);
                if (app.getMahasiswa(userId).getKelas(kelasId).getQuiz(quizId).getSoal(0).getJawaban(i).isBenar()){
                    pilKel.getJawaban(i+1).setBackground(Color.WHITE);
                    pilKel.getJawaban(i+1).setOpaque(true);
                }
            }
        }
        if (app.getMahasiswa(userId).getKelas(kelasId).getQuizList().size()>0){
            for(int i=0;i<app.getMahasiswa(userId).getKelas(kelasId).getQuiz(quizId).getSoalList().size();i++){
                modelList.addElement("Soal "+(i+1));
                if (i==0){
                    pilKel.getSoal().setText(app.getMahasiswa(userId).getKelas(kelasId).getQuiz(quizId).getSoal(0).getSoal());
                    int j;
                    if (udah){
                        if (jawaban.getJawabanList().size() > i){
                            for (j=0;j<app.getMahasiswa(userId).getKelas(kelasId).getQuiz(quizId).getSoal(0).getJawabanList().size();j++){
                                if (app.getMahasiswa(userId).getKelas(kelasId).getQuiz(quizId).getSoal(0).getJawaban(j) == jawaban.getJawaban(0)){
                                    pilKel.getJawaban(j+1).setSelected(true);
                                }
                                pilKel.getJawaban(j+1).setText(app.getMahasiswa(userId).getKelas(kelasId).getQuiz(quizId).getSoal(0).getJawaban(j).getJawaban());
                            }
                        }
                    }else{
                        pilKel.getJawaban(1).setSelected(true);
                    }
                    for (j=0;j<app.getMahasiswa(userId).getKelas(kelasId).getQuiz(quizId).getSoal(0).getJawabanList().size();j++){
                        pilKel.getJawaban(j+1).setText(app.getMahasiswa(userId).getKelas(kelasId).getQuiz(quizId).getSoal(0).getJawaban(j).getJawaban());
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
            ControllerPilihQuizMahasiswa quizMhs = new ControllerPilihQuizMahasiswa(app,file,userId,kelasId);
            pilKel.dispose();
        }else if(x.equals(pilKel.getBtn_finish())){
            jawaban.hitungNilai();
            JOptionPane.showMessageDialog(pilKel, "Pengerjaan Quiz selesai!");
            ControllerPilihQuizMahasiswa quizMhs = new ControllerPilihQuizMahasiswa(app,file,userId,kelasId);
            pilKel.dispose();
        }
        for (int i=0;i<5;i++){
            if(x.equals(pilKel.getJawaban(i+1))){
                jawaban.setJawaban(pilKel.getPilihQuiz().getSelectedIndex(),app.getMahasiswa(userId).getKelas(kelasId).getQuiz(quizId).getSoal(pilKel.getPilihQuiz().getSelectedIndex()).getJawaban(i));
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        Object x = e.getSource();
        if (x.equals(pilKel.getPilihQuiz())){
            if (app.getKelas(kelasId).getQuiz(quizId).getSoalList().size()>0){
                pilKel.getJudul().setText("Soal "+(pilKel.getPilihQuiz().getSelectedIndex()+1));
                pilKel.getSoal().setText(app.getKelas(kelasId).getQuiz(quizId).getSoal(pilKel.getPilihQuiz().getSelectedIndex()).getSoal());
                for(int i=0;i<5;i++){
                    if (jawaban.isSudah()){
                        if (app.getMahasiswa(userId).getKelas(kelasId).getQuiz(quizId).getSoal(pilKel.getPilihQuiz().getSelectedIndex()).getJawaban(i).isBenar()){
                            pilKel.getJawaban(i+1).setBackground(Color.WHITE);
                            pilKel.getJawaban(i+1).setOpaque(true);
                        }else{
                            pilKel.getJawaban(i+1).setOpaque(false);
                        }
                    }
                    if (app.getKelas(kelasId).getQuiz(quizId).getSoal(pilKel.getPilihQuiz().getSelectedIndex()).getJawaban(i) == jawaban.getJawaban(pilKel.getPilihQuiz().getSelectedIndex())){
                        pilKel.getJawaban(i+1).setSelected(true);
                    }
                    pilKel.getJawaban(i+1).setText(app.getKelas(kelasId).getQuiz(quizId).getSoal(pilKel.getPilihQuiz().getSelectedIndex()).getJawaban(i).getJawaban());
                }
                String status;
            }
        }
    }
}
