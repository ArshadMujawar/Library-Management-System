/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jFrame;

import static jFrame.DBConnection.con;
import java.awt.Color;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class MemberReport extends javax.swing.JFrame {

    /**
     * Creates new form MemberReport
     */
    Color dengerIn = new Color(204, 0, 0);
    Color original2 = new Color(255, 51, 51);
    DefaultTableModel model;
    public MemberReport() {
        initComponents();
        setMemberDetailsToTable();
    }
    
    public void setMemberDetailsToTable() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from member_details");

            while (rs.next()) {
                String memberId = rs.getString("member_id");
                String memberName = rs.getString("mem_name");
                String contactNo = rs.getString("contact_no");
                String email = rs.getString("email");
                String memberAddress = rs.getString("address");
                String gender = rs.getString("gender");
                String joinDate = rs.getString("join_date");
                String memberType = rs.getString("mem_type");
                String expDate = rs.getString("exp_date");
                String Fee = rs.getString("fee");

                Object[] obj = {memberId, memberName, contactNo, email, memberAddress, gender, joinDate, memberType, expDate, Fee};
                model = (DefaultTableModel) tbl_memberDetails.getModel();
                model.addRow(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
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

        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_memberDetails = new rojeru_san.complementos.RSTableMetro();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_memberDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "contact No", "Email", "Address", "Gender", " Join date", "Member Type", "Expiry date", "Fee"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_memberDetails.setAutoResizeMode(0);
        tbl_memberDetails.setColorBackgoundHead(new java.awt.Color(153, 153, 153));
        tbl_memberDetails.setColorBordeFilas(new java.awt.Color(102, 102, 255));
        tbl_memberDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_memberDetails.setColorSelBackgound(new java.awt.Color(255, 51, 51));
        tbl_memberDetails.setColumnSelectionAllowed(true);
        tbl_memberDetails.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        tbl_memberDetails.setFuenteHead(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tbl_memberDetails.setMinimumSize(new java.awt.Dimension(120, 300));
        tbl_memberDetails.setRowHeight(30);
        tbl_memberDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_memberDetailsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_memberDetails);
        tbl_memberDetails.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (tbl_memberDetails.getColumnModel().getColumnCount() > 0) {
            tbl_memberDetails.getColumnModel().getColumn(0).setPreferredWidth(65);
            tbl_memberDetails.getColumnModel().getColumn(1).setPreferredWidth(200);
            tbl_memberDetails.getColumnModel().getColumn(2).setPreferredWidth(170);
            tbl_memberDetails.getColumnModel().getColumn(3).setPreferredWidth(190);
            tbl_memberDetails.getColumnModel().getColumn(4).setPreferredWidth(280);
            tbl_memberDetails.getColumnModel().getColumn(5).setPreferredWidth(70);
            tbl_memberDetails.getColumnModel().getColumn(6).setPreferredWidth(90);
            tbl_memberDetails.getColumnModel().getColumn(7).setPreferredWidth(90);
            tbl_memberDetails.getColumnModel().getColumn(8).setPreferredWidth(90);
            tbl_memberDetails.getColumnModel().getColumn(9).setPreferredWidth(75);
        }

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 1340, 440));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel2.setText("  Manage Members");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, 320, 50));

        jPanel5.setBackground(new java.awt.Color(255, 51, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel3.setText("Back");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
        });
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, 20));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 40));

        setSize(new java.awt.Dimension(1373, 643));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_memberDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_memberDetailsMouseClicked
       
    }//GEN-LAST:event_tbl_memberDetailsMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        ManageMembers back = new ManageMembers();
        back.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        jPanel5.setBackground(dengerIn);
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        jPanel5.setBackground(original2);
    }//GEN-LAST:event_jLabel3MouseExited

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
            java.util.logging.Logger.getLogger(MemberReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MemberReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MemberReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MemberReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MemberReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private rojeru_san.complementos.RSTableMetro tbl_memberDetails;
    // End of variables declaration//GEN-END:variables
}
