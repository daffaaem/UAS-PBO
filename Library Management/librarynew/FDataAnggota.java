/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package librarynew;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class FDataAnggota extends javax.swing.JFrame {

    /**
     * Creates new form FDataAnggota
     */
    public FDataAnggota() {
        initComponents();
        load_data(); // menampilkan data
        IDOtomatis(); // menampilkan id otomatis
        LoadJurusan();    
        
        BEdit.setEnabled(false);
        BDelete.setEnabled(false);
    }
    
//    Tabel Data dari Database
    private void load_data(){
    Connection kon = koneksi.koneksiDb();
    Object header[] = {"ID ANGGOTA", "NPM", "NAMA ANGGOTA", "JK", "JURUSAN", "NO HP", "STATUS"};
    DefaultTableModel data = new DefaultTableModel(null, header);
    TabelAnggota.setModel(data);
    String sql_data = "SELECT * FROM tbl_anggota";
    try {
        Statement st=kon.createStatement();
        ResultSet rs=st.executeQuery(sql_data);
        while(rs.next()){
            String d1 = rs.getString(1);
            String d2 = rs.getString(2);
            String d3 = rs.getString(3);
            String d4 = rs.getString(4);
            String d5 = rs.getString(5);
            String d6 = rs.getString(6);
            String d7 = rs.getString(7);

            String d[] = {d1, d2, d3, d4, d5, d6, d7};
            data.addRow(d);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
    
    // Input Data Anggota
    private void IDOtomatis(){
        try {
            Connection kon = koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql_id="SELECT * FROM tbl_anggota ORDER BY id_anggota desc";
            ResultSet rs=st.executeQuery(sql_id);
            if(rs.next()){
                String id_anggota=rs.getString("id_anggota").substring(1);
                String AN=""+(Integer.parseInt(id_anggota)+1);
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
                ID.setText("A"+Nol+AN);
            }
            else {
                ID.setText("A00001");
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    // Load Jurusan
    public void LoadJurusan(){
        try{
            Connection kon = koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql_jurusan="SELECT id_jurusan FROM tbl_jurusan";
            ResultSet rs=st.executeQuery(sql_jurusan);
            while(rs.next()){
                KJURUSAN.addItem(rs.getString("id_jurusan"));
            }
            rs.close();
        }
        catch(Exception e) {
            
        }
    }
    
    // Load Nama Jurusan
    public void NamaJurusan() {
    try {
        Connection kon = koneksi.koneksiDb();
        Statement st = kon.createStatement();
        String sql_jurusan = "SELECT jurusan FROM tbl_jurusan WHERE id_jurusan='" + KJURUSAN.getSelectedItem().toString() + "'";
        ResultSet rs = st.executeQuery(sql_jurusan);
        while (rs.next()) {
            NJURUSAN.setText(rs.getString("jurusan"));
        }
    } catch (Exception e) {
        // Menangkap exception namun tidak menambahkan kode baru untuk penanganannya
    }
}

    
    
    // Input Data ======================================================
    private void input_data(){
        try {
            Connection kon = koneksi.koneksiDb();
            Statement st=kon.createStatement();
            
            // jenis kelamin
            String jk="";
            if(JKP.isSelected()){
                jk=JKP.getText();
            }
            else {
                jk=JKW.getText();
            }
            
            String sql="INSERT INTO tbl_anggota values('"+ID.getText()
                    +"','"+NPM.getText()
                    +"','"+NAMA.getText()
                    +"','"+jk
                    +"','"+KJURUSAN.getSelectedItem()
                    +"','"+NOHAPE.getText()
                    +"','"+STATUS.getSelectedItem()
                    +"')";
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Anggota Berhasil Diinputkan");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    // Reset Data
    public void clear(){
        NPM.setText("");
        NAMA.setText("");
        JKP.setSelected(rootPaneCheckingEnabled);
        KJURUSAN.setSelectedItem("UPN1");
        NOHAPE.setText("");
        STATUS.setSelectedItem("Aktif");
        
    }
    
    //Edit Data
    public void update(){
        try {
            Connection kon = koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String jk="";
            if(JKP.isSelected()){
                jk=JKP.getText();
            }
            else {
                jk=JKW.getText();
            }
            
            String sql_update="UPDATE tbl_anggota SET npm='"+NPM.getText()
                    +"',nama='"+NAMA.getText()
                    +"',jk='"+jk
                    +"',id_jurusan='"+KJURUSAN.getSelectedItem()
                    +"',no_hp='"+NOHAPE.getText()
                    +"',status='"+STATUS.getSelectedItem()
                    +"'WHERE id_anggota='"+ID.getText()+"'";
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
            String sql_delete="DELETE FROM tbl_anggota WHERE id_anggota='"+ID.getText()+"'";
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
        BKeluar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        NPM = new javax.swing.JTextField();
        NAMA = new javax.swing.JTextField();
        NOHAPE = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        JKP = new javax.swing.JRadioButton();
        JKW = new javax.swing.JRadioButton();
        KJURUSAN = new javax.swing.JComboBox<>();
        STATUS = new javax.swing.JComboBox<>();
        NJURUSAN = new javax.swing.JTextField();
        BInput = new javax.swing.JButton();
        BEdit = new javax.swing.JButton();
        BDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelAnggota = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(760, 408));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setText("DATA ANGGOTA PERPUSTAKAAN");

        BKeluar.setText("Keluar");
        BKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BKeluarActionPerformed(evt);
            }
        });

        jLabel2.setText("ID Anggota");

        jLabel3.setText("NPM");

        jLabel4.setText("Nama Mahasiswa");

        jLabel5.setText("Jurusan");

        jLabel6.setText("No. HP");

        jLabel7.setText("Status");

        ID.setEnabled(false);

        jLabel8.setText("Jenis Kelamin");

        JKP.setSelected(true);
        JKP.setText("PRIA");

        JKW.setText("WANITA");
        JKW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JKWActionPerformed(evt);
            }
        });

        KJURUSAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KJURUSANActionPerformed(evt);
            }
        });

        STATUS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aktif", "Tidak Aktif" }));

        NJURUSAN.setEditable(false);

        BInput.setText("INPUT");
        BInput.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                BInputMouseWheelMoved(evt);
            }
        });
        BInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BInputActionPerformed(evt);
            }
        });

        BEdit.setText("EDIT");
        BEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEditActionPerformed(evt);
            }
        });

        BDelete.setText("DELETE");
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });

        TabelAnggota.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelAnggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelAnggotaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelAnggota);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(NPM, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(31, 31, 31)
                        .addComponent(NAMA, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(63, 63, 63)
                        .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(83, 83, 83)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(JKP)
                                .addGap(18, 18, 18)
                                .addComponent(JKW))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(KJURUSAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(NJURUSAN))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(STATUS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NOHAPE, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(BInput, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BDelete)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(298, 298, 298)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(NPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(NAMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(JKP)
                            .addComponent(JKW))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(KJURUSAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NJURUSAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(NOHAPE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(STATUS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BInput)
                            .addComponent(BKeluar)
                            .addComponent(BEdit)
                            .addComponent(BDelete))
                        .addGap(50, 50, 50))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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

    private void BKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BKeluarActionPerformed
        // TODO add your handling code here:
        int keluar;
        keluar=JOptionPane.showOptionDialog(this,
                "Keluar dari Kelola Data Anggota",
                "Exit", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,null,null);
        if(keluar==JOptionPane.YES_NO_OPTION){
            new FUtamaPustakawan().show();
            this.dispose();
        }
    }//GEN-LAST:event_BKeluarActionPerformed

    private void JKWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JKWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JKWActionPerformed

    private void KJURUSANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KJURUSANActionPerformed
        // TODO add your handling code here:
        NamaJurusan();
    }//GEN-LAST:event_KJURUSANActionPerformed

    private void BInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BInputActionPerformed
        // TODO add your handling code here:
        int simpan=JOptionPane.showOptionDialog(this, "Apakah Data Sudah Benar?", "Simpan Data", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(simpan==JOptionPane.YES_OPTION){
        input_data();
        load_data();
        clear();
        IDOtomatis();
        }
    }//GEN-LAST:event_BInputActionPerformed

    private void TabelAnggotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelAnggotaMouseClicked
        // TODO add your handling code here:
        int bar=TabelAnggota.getSelectedRow();
        String a=TabelAnggota.getValueAt(bar, 0).toString();
        String b=TabelAnggota.getValueAt(bar, 1).toString();
        String c=TabelAnggota.getValueAt(bar, 2).toString();
        String d=TabelAnggota.getValueAt(bar, 3).toString();
        String e=TabelAnggota.getValueAt(bar, 4).toString();
        String f=TabelAnggota.getValueAt(bar, 5).toString();
        String g=TabelAnggota.getValueAt(bar, 6).toString();
        
        ID.setText(a);
        NPM.setText(b);
        NAMA.setText(c);
        //Jenis Kelamin
        if("PRIA".equals(d)){
            JKP.setSelected(true);
        }
        else {
            JKW.setSelected(true);
        }
        KJURUSAN.setSelectedItem(e);
        NOHAPE.setText(f);
        // Status
        if("Aktif".equals(g)){
            STATUS.setSelectedItem(g);
        }
        else {
            STATUS.setSelectedItem(g);
        }
        
        // Set Disable INPUT
        BInput.setEnabled(false);
        BEdit.setEnabled(true);
        BDelete.setEnabled(true);
    }//GEN-LAST:event_TabelAnggotaMouseClicked

    private void BEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditActionPerformed
        // TODO add your handling code here:
        int update=JOptionPane.showOptionDialog(this, "Apakah Data Sudah Benar?", "Update Data", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(update==JOptionPane.YES_OPTION){
            update();
            clear();
            load_data();
            IDOtomatis();

            // Set Disable INPUT
            BInput.setEnabled(true);
            BEdit.setEnabled(false);
            BDelete.setEnabled(false);
        }
    }//GEN-LAST:event_BEditActionPerformed

    private void BDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDeleteActionPerformed
        // TODO add your handling code here:
        int delete=JOptionPane.showOptionDialog(this, "Apakah Yakin Ingin Hapus Data?", "Hapus Data", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(delete==JOptionPane.YES_OPTION){
            delete();
            clear();
            load_data();
            IDOtomatis();

            // Set Disable INPUT
            BInput.setEnabled(true);
            BEdit.setEnabled(false);
            BDelete.setEnabled(false);
        }
    }//GEN-LAST:event_BDeleteActionPerformed

    private void BInputMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_BInputMouseWheelMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_BInputMouseWheelMoved

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
            java.util.logging.Logger.getLogger(FDataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FDataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FDataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FDataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FDataAnggota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BDelete;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BInput;
    private javax.swing.JButton BKeluar;
    private javax.swing.JTextField ID;
    private javax.swing.JRadioButton JKP;
    private javax.swing.JRadioButton JKW;
    private javax.swing.JComboBox<String> KJURUSAN;
    private javax.swing.JTextField NAMA;
    private javax.swing.JTextField NJURUSAN;
    private javax.swing.JTextField NOHAPE;
    private javax.swing.JTextField NPM;
    private javax.swing.JComboBox<String> STATUS;
    private javax.swing.JTable TabelAnggota;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
