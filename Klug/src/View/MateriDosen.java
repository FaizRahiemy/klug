/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 *
 * @author faiz
 */
public class MateriDosen extends javax.swing.JFrame implements View{

    /**
     * Creates new form Login
     */
    public MateriDosen() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundKlug1 = new img.BackgroundKlug();
        judulHalaman = new javax.swing.JLabel();
        close = new javax.swing.JLabel();
        close1 = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pilihMateri = new javax.swing.JList<>();
        judul = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        isiMateri = new javax.swing.JTextArea();
        btn_edit = new javax.swing.JButton();
        btn_tambah = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setUndecorated(true);

        backgroundKlug1.setMaximumSize(new java.awt.Dimension(800, 600));
        backgroundKlug1.setMinimumSize(new java.awt.Dimension(800, 600));
        backgroundKlug1.setPreferredSize(new java.awt.Dimension(800, 600));

        judulHalaman.setFont(new java.awt.Font("Aller", 0, 36)); // NOI18N
        judulHalaman.setForeground(new java.awt.Color(255, 255, 255));
        judulHalaman.setText("Materi");

        close.setBackground(new java.awt.Color(255, 255, 255));
        close.setFont(new java.awt.Font("Aller", 0, 18)); // NOI18N
        close.setForeground(new java.awt.Color(255, 255, 255));
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                closeMouseReleased(evt);
            }
        });

        close1.setBackground(new java.awt.Color(255, 255, 255));
        close1.setFont(new java.awt.Font("Aller", 0, 18)); // NOI18N
        close1.setForeground(new java.awt.Color(255, 255, 255));
        close1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                close1MouseReleased(evt);
            }
        });

        btn_back.setBackground(new java.awt.Color(255, 255, 255));
        btn_back.setFont(new java.awt.Font("Aller", 0, 24)); // NOI18N
        btn_back.setForeground(new java.awt.Color(238, 95, 1));
        btn_back.setText("Kembali");
        btn_back.setBorderPainted(false);

        jLabel1.setFont(new java.awt.Font("Aller", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(238, 95, 1));
        jLabel1.setText("Daftar Materi");

        pilihMateri.setFont(new java.awt.Font("Aller", 0, 18)); // NOI18N
        pilihMateri.setForeground(new java.awt.Color(238, 95, 1));
        pilihMateri.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        pilihMateri.setSelectionBackground(new java.awt.Color(238, 95, 1));
        jScrollPane1.setViewportView(pilihMateri);

        judul.setFont(new java.awt.Font("Aller", 0, 24)); // NOI18N
        judul.setForeground(new java.awt.Color(238, 95, 1));
        judul.setText("Materi");

        isiMateri.setEditable(false);
        isiMateri.setColumns(20);
        isiMateri.setRows(5);
        jScrollPane2.setViewportView(isiMateri);

        btn_edit.setBackground(new java.awt.Color(255, 255, 255));
        btn_edit.setFont(new java.awt.Font("Aller", 0, 24)); // NOI18N
        btn_edit.setForeground(new java.awt.Color(238, 95, 1));
        btn_edit.setText("Edit");
        btn_edit.setBorderPainted(false);

        btn_tambah.setBackground(new java.awt.Color(255, 255, 255));
        btn_tambah.setFont(new java.awt.Font("Aller", 0, 24)); // NOI18N
        btn_tambah.setForeground(new java.awt.Color(238, 95, 1));
        btn_tambah.setText("Tambah Materi");
        btn_tambah.setBorderPainted(false);

        javax.swing.GroupLayout backgroundKlug1Layout = new javax.swing.GroupLayout(backgroundKlug1);
        backgroundKlug1.setLayout(backgroundKlug1Layout);
        backgroundKlug1Layout.setHorizontalGroup(
            backgroundKlug1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundKlug1Layout.createSequentialGroup()
                .addGap(259, 259, 259)
                .addComponent(judulHalaman)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 371, Short.MAX_VALUE)
                .addComponent(close1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(backgroundKlug1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(backgroundKlug1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(backgroundKlug1Layout.createSequentialGroup()
                        .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundKlug1Layout.createSequentialGroup()
                        .addGroup(backgroundKlug1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(backgroundKlug1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(judul)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backgroundKlug1Layout.setVerticalGroup(
            backgroundKlug1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundKlug1Layout.createSequentialGroup()
                .addGroup(backgroundKlug1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundKlug1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(close1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(close, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(backgroundKlug1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(judulHalaman)))
                .addGap(55, 55, 55)
                .addGroup(backgroundKlug1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(judul))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundKlug1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(18, 18, 18)
                .addGroup(backgroundKlug1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_back)
                    .addComponent(btn_edit)
                    .addComponent(btn_tambah))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backgroundKlug1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backgroundKlug1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void close1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close1MouseReleased
        this.setState(MateriDosen.ICONIFIED);
    }//GEN-LAST:event_close1MouseReleased

    private void closeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseReleased
        System.exit(0);
    }//GEN-LAST:event_closeMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MateriDosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MateriDosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MateriDosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MateriDosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MateriDosen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private img.BackgroundKlug backgroundKlug1;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JLabel close;
    private javax.swing.JLabel close1;
    private javax.swing.JTextArea isiMateri;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel judul;
    private javax.swing.JLabel judulHalaman;
    private javax.swing.JList<String> pilihMateri;
    // End of variables declaration//GEN-END:variables

    public JButton getBtn_back() {
        return btn_back;
    }
    
    public JButton getBtnEdit() {
        return btn_edit;
    }
    
    public JButton getBtnTambah() {
        return btn_tambah;
    }
    
    public JList getPilihMateri(){
        return pilihMateri;
    }
    
    public JLabel getJudul(){
        return judul;
    }
    
    public JTextArea getMateri(){
        return isiMateri;
    }
    
    public JLabel getJudulHalaman(){
        return judulHalaman;
    }
    
    @Override
    public void addActionListener(ActionListener e) {
        btn_back.addActionListener(e);
        //pilihMateri.addActionListener(e);
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        pilihMateri.addMouseListener(l);
    }

}
