/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package librarynew;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author ASUS
 */
public class FDataPeminjam extends javax.swing.JFrame {

    /**
     * Creates new form FDataPeminjam
     */
    public FDataPeminjam() {
        initComponents();
        load_data_peminjam();
        IDPeminjamOtomatis();
        LoadAnggota();
        LoadBuku();
        
        BEdit.setEnabled(false);
        BDelete.setEnabled(false);
    }
    
    
    private void load_data_peminjam(){
    Connection kon = koneksi.koneksiDb();
    Object header[] = {"ID PEMINJAM", "ID ANGGOTA", "ID BUKU", "TANGGAL PINJAM"};
    DefaultTableModel data = new DefaultTableModel(null, header);
    TabelPeminjam.setModel(data);
    String sql_data = "SELECT * FROM tbl_peminjam";
    try {
        Statement st=kon.createStatement();
        ResultSet rs=st.executeQuery(sql_data);
        while(rs.next()){
            String d1 = rs.getString(1);
            String d2 = rs.getString(2);
            String d3 = rs.getString(3);
            String d4 = rs.getString(4);

            String d[] = {d1, d2, d3, d4};
            data.addRow(d);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
    
    private void IDPeminjamOtomatis(){
        try {
            Connection kon = koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql_id="SELECT * FROM tbl_peminjam ORDER BY id_peminjam desc";
            ResultSet rs=st.executeQuery(sql_id);
            if(rs.next()){
                String id_peminjam=rs.getString("id_peminjam").substring(1);
                String AN=""+(Integer.parseInt(id_peminjam)+1);
                String Nol="";
                if(AN.length()==1){
                    Nol="0000";
                } 
                else if(AN.length()==2){
                    Nol="000";
                }
                else if(AN.length()==3){
                    Nol="00";
                }
                IDPeminjam.setText("P"+Nol+AN);
            }
            else {
                IDPeminjam.setText("P00001");
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
//    
    private void input_data() {
    try {
        Connection kon = koneksi.koneksiDb();
        Statement st = kon.createStatement();

        String sql = "INSERT INTO tbl_peminjam (id_peminjam, id_anggota, id_buku, tanggal_pinjam) VALUES ('" 
                + IDPeminjam.getText() + "','" 
                + KIDAnggota.getSelectedItem() + "','" 
                + KIDBuku.getSelectedItem() + "','" 
                + TanggalPinjam.getText() + "')";
        st.execute(sql);
        JOptionPane.showMessageDialog(null, "Data Peminjam Berhasil Diinputkan");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}

//    
    // Load ID Anggota
    public void LoadAnggota(){
        try{
            Connection kon = koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql_peminjam="SELECT id_anggota FROM tbl_anggota";
            ResultSet rs=st.executeQuery(sql_peminjam);
            while(rs.next()){
                KIDAnggota.addItem(rs.getString("id_anggota"));
            }
            rs.close();
        }
        catch(Exception e) {
            
        }
    }
    
    // Load Nama Anggota
    public void NamaAnggota() {
    try {
        Connection kon = koneksi.koneksiDb();
        Statement st = kon.createStatement();
        String sql_peminjam = "SELECT nama FROM tbl_anggota WHERE id_anggota='" + KIDAnggota.getSelectedItem().toString() + "'";
        ResultSet rs = st.executeQuery(sql_peminjam);
        while (rs.next()) {
            NNamaPeminjam.setText(rs.getString("nama"));
        }
    } catch (Exception e) {
        
    }
}
    
//    Load ID Buku
    public void LoadBuku(){
        try{
            Connection kon = koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql_buku="SELECT id_buku FROM tbl_buku";
            ResultSet rs=st.executeQuery(sql_buku);
            while(rs.next()){
                KIDBuku.addItem(rs.getString("id_buku"));
            }
            rs.close();
        }
        catch(Exception e) {
            
        }
    }
    
    // Load Judul Buku
    public void JudulBuku() {
    try {
        Connection kon = koneksi.koneksiDb();
        Statement st = kon.createStatement();
        String sql_buku = "SELECT judul FROM tbl_buku WHERE id_buku='" + KIDBuku.getSelectedItem().toString() + "'";
        ResultSet rs = st.executeQuery(sql_buku);
        while (rs.next()) {
            NJudul.setText(rs.getString("judul"));
        }
    } catch (Exception e) {
        
    }
}
    
    public void clear(){
        IDPeminjam.setText("");
        KIDAnggota.setSelectedItem("A00001");
        NNamaPeminjam.setText("");
        KIDBuku.setSelectedItem("B00001");
        NJudul.setText("");
        TanggalPinjam.setText("");
        
    }
    
//    Edit Data
    public void update(){
        try {
            Connection kon = koneksi.koneksiDb();
            Statement st=kon.createStatement();
            
            String sql_update="UPDATE tbl_peminjam SET id_anggota='"+KIDAnggota.getSelectedItem()
                    +"',id_buku='"+KIDBuku.getSelectedItem()
                    +"',tanggal_pinjam='"+TanggalPinjam.getText()
                    +"'WHERE id_peminjam='"+IDPeminjam.getText()+"'";
            st.execute(sql_update);
            JOptionPane.showMessageDialog(null, "Data Berhasil di Update");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    // Delete Data
    private void delete(){
        try{
            Connection kon = koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql_delete="DELETE FROM tbl_peminjam WHERE id_peminjam='"+IDPeminjam.getText()+"'";
            st.executeUpdate(sql_delete);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        IDPeminjam = new javax.swing.JTextField();
        NNamaPeminjam = new javax.swing.JTextField();
        KIDAnggota = new javax.swing.JComboBox<>();
        KIDBuku = new javax.swing.JComboBox<>();
        NJudul = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelPeminjam = new javax.swing.JTable();
        BKeluar = new javax.swing.JButton();
        BInput = new javax.swing.JButton();
        BEdit = new javax.swing.JButton();
        BDelete = new javax.swing.JButton();
        TanggalPinjam = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setText("DATA PEMINJAMAN");

        jLabel2.setText("ID Peminjam");

        jLabel3.setText("ID Anggota");

        jLabel4.setText("Nama Peminjam");

        jLabel5.setText("ID Buku");

        jLabel6.setText("Judul Buku");

        jLabel7.setText("Tanggal Pinjam");

        IDPeminjam.setEnabled(false);
        IDPeminjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDPeminjamActionPerformed(evt);
            }
        });

        NNamaPeminjam.setEnabled(false);
        NNamaPeminjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NNamaPeminjamActionPerformed(evt);
            }
        });

        KIDAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KIDAnggotaActionPerformed(evt);
            }
        });

        KIDBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KIDBukuActionPerformed(evt);
            }
        });

        NJudul.setEnabled(false);

        TabelPeminjam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TabelPeminjam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelPeminjamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelPeminjam);

        BKeluar.setText("Keluar");
        BKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BKeluarActionPerformed(evt);
            }
        });

        BInput.setText("Input");
        BInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BInputActionPerformed(evt);
            }
        });

        BEdit.setText("Edit");
        BEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEditActionPerformed(evt);
            }
        });

        BDelete.setText("Delete");
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(305, 305, 305))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BInput, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(87, 87, 87)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(KIDAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(KIDBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NNamaPeminjam)
                            .addComponent(NJudul, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(IDPeminjam)
                            .addComponent(TanggalPinjam))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(IDPeminjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(KIDAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(NNamaPeminjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(KIDBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(NJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(TanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BKeluar)
                            .addComponent(BInput)
                            .addComponent(BEdit)
                            .addComponent(BDelete))
                        .addGap(66, 66, 66))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NNamaPeminjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NNamaPeminjamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NNamaPeminjamActionPerformed

    private void BKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BKeluarActionPerformed
        // TODO add your handling code here:
        int keluar;
        keluar=JOptionPane.showOptionDialog(this,
                "Keluar dari Kelola Data Peminjam",
                "Exit", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,null,null);
        if(keluar==JOptionPane.YES_NO_OPTION){
            new FUtamaPustakawan().show();
            this.dispose();
        }
    }//GEN-LAST:event_BKeluarActionPerformed

    private void IDPeminjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDPeminjamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPeminjamActionPerformed

    private void KIDAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KIDAnggotaActionPerformed
        // TODO add your handling code here:
        NamaAnggota();
    }//GEN-LAST:event_KIDAnggotaActionPerformed

    private void KIDBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KIDBukuActionPerformed
        // TODO add your handling code here:
        JudulBuku();
    }//GEN-LAST:event_KIDBukuActionPerformed

    private void BInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BInputActionPerformed
        // TODO add your handling code here:
        int simpan=JOptionPane.showOptionDialog(this, "Apakah Data Peminjam Sudah Benar?", "Simpan Data", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(simpan==JOptionPane.YES_OPTION){
        input_data();
        load_data_peminjam();
        clear();
        IDPeminjamOtomatis();
        }
    }//GEN-LAST:event_BInputActionPerformed

    private void BEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditActionPerformed
        // TODO add your handling code here:
        int update=JOptionPane.showOptionDialog(this, "Apakah Data Peminjam Sudah Benar?", "Update Data", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(update==JOptionPane.YES_OPTION){
            update();
            clear();
            load_data_peminjam();
            IDPeminjamOtomatis();

            // Set Disable INPUT
            BInput.setEnabled(true);
            BEdit.setEnabled(false);
            BDelete.setEnabled(false);
        }
    }//GEN-LAST:event_BEditActionPerformed

    private void BDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDeleteActionPerformed
        // TODO add your handling code here:
        int delete=JOptionPane.showOptionDialog(this, "Apakah Yakin Ingin Hapus Data Peminjam?", "Hapus Data", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(delete==JOptionPane.YES_OPTION){
            delete();
            clear();
            load_data_peminjam();
            IDPeminjamOtomatis();

            // Set Disable INPUT
            BInput.setEnabled(true);
            BEdit.setEnabled(false);
            BDelete.setEnabled(false);
        }
    }//GEN-LAST:event_BDeleteActionPerformed

    private void TabelPeminjamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelPeminjamMouseClicked
        // TODO add your handling code here:
        int bar=TabelPeminjam.getSelectedRow();
        String a=TabelPeminjam.getValueAt(bar, 0).toString();
        String b=TabelPeminjam.getValueAt(bar, 1).toString();
        String c=TabelPeminjam.getValueAt(bar, 2).toString();
        String d=TabelPeminjam.getValueAt(bar, 3).toString();
        
        IDPeminjam.setText(a);
        KIDAnggota.setSelectedItem(b);
        KIDBuku.setSelectedItem(c);
        TanggalPinjam.setText(d);
        
        // Set Disable INPUT
        BInput.setEnabled(false);
        BEdit.setEnabled(true);
        BDelete.setEnabled(true);
    }//GEN-LAST:event_TabelPeminjamMouseClicked

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
            java.util.logging.Logger.getLogger(FDataPeminjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FDataPeminjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FDataPeminjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FDataPeminjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FDataPeminjam().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BDelete;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BInput;
    private javax.swing.JButton BKeluar;
    private javax.swing.JTextField IDPeminjam;
    private javax.swing.JComboBox<String> KIDAnggota;
    private javax.swing.JComboBox<String> KIDBuku;
    private javax.swing.JTextField NJudul;
    private javax.swing.JTextField NNamaPeminjam;
    private javax.swing.JTable TabelPeminjam;
    private javax.swing.JTextField TanggalPinjam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
