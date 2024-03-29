/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jFrame;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


/**
 *
 * @author admin
 */
public class ReturnBook extends javax.swing.JFrame {

    /**
     * Creates new form IssueBook
     */
    Color dengerIn = new Color(204,0,0);
    Color original = new Color(102,102,255);
    public ReturnBook() {
        initComponents();
    }
    
  //to fetch issue book deatails from the database and display it to book detail Panel
    public void getIssueBookDetails(){
        
        int bookId = Integer.parseInt(txt_bookId.getText());
        int memberId = Integer.parseInt(txt_memberId.getText());
        
        try {
            Connection con = DBConnection.getConnection();
            String sql = "select * from issue_book_details where book_id = ? and member_id=? and status=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId); 
            pst.setInt(2, memberId);
            pst.setString(3, "pending");
            
             ResultSet rs = pst.executeQuery();
            if(rs.next()){
               
                lbl_issueId.setText(rs.getString("id"));
                lbl_bookName.setText(rs.getString("book_name"));
                lbl_memberName.setText(rs.getString("member_name"));
                lbl_issueDate.setText(rs.getString("issue_date"));
                lbl_dueDate.setText(rs.getString("due_date"));
                lbl_bookError.setText("");
            }else{
                lbl_issueId.setText("");
                lbl_bookName.setText("");
                lbl_memberName.setText("");
                lbl_issueDate.setText("");
                lbl_dueDate.setText("");
               lbl_bookError.setText("No Record Found"); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    
    //return the book
    public boolean returnBook(){
        boolean isReturned = false;
        int bookId = Integer.parseInt(txt_bookId.getText());
        int memberId = Integer.parseInt(txt_memberId.getText());
        
        try {
            Connection con = DBConnection.getConnection();
            String sql = "update issue_book_details set status=? where book_id = ? and member_id=? and status=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "returned");
            pst.setInt(2, bookId); 
            pst.setInt(3, memberId);
            pst.setString(4, "pending");
            int rowCount = pst.executeUpdate();
            if(rowCount > 0){
                 isReturned=true;
            }else{
                isReturned=false;
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isReturned;
    }
    
    //updating book count
    public void updateBookCount(){
         int bookId = Integer.parseInt(txt_bookId.getText());
         try{
         Connection con = DBConnection.getConnection();
         String sql = "update book_details set quantity = quantity + 1 where book_id=?";
         PreparedStatement pst = con.prepareStatement(sql);
         pst.setInt(1, bookId);
         int rowCount = pst.executeUpdate();
            if(rowCount > 0){
                 JOptionPane.showMessageDialog(this, "book count Updated");
                 
            }else{
                JOptionPane.showMessageDialog(this, "can't Update book count");
            }
         }catch(Exception e){
             e.printStackTrace();
         }
    }
    
    //Validation
    public boolean validateField() {
        String bookId = txt_bookId.getText();
        String memberId = txt_memberId.getText();
        
         if (bookId.equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Book Id");
            return false;
        } else if (memberId.equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Member Id");
            return false;
        }
        
         return true;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_main = new javax.swing.JPanel();
        lbl_bookError = new javax.swing.JLabel();
        lbl_memberError = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        lbl_memberName = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_issueId = new javax.swing.JLabel();
        lbl_bookName = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbl_issueDate = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lbl_dueDate = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txt_bookId = new app.bolivia.swing.JCTextField();
        jLabel31 = new javax.swing.JLabel();
        txt_memberId = new app.bolivia.swing.JCTextField();
        jLabel30 = new javax.swing.JLabel();
        rSMaterialButtonCircle4 = new rojerusan.RSMaterialButtonCircle();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        rSMaterialButtonCircle5 = new rojerusan.RSMaterialButtonCircle();
        jLabel21 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_main.setBackground(new java.awt.Color(255, 255, 255));
        panel_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_bookError.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 15)); // NOI18N
        lbl_bookError.setForeground(new java.awt.Color(222, 3, 3));
        panel_main.add(lbl_bookError, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 360, 160, 30));

        lbl_memberError.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 15)); // NOI18N
        lbl_memberError.setForeground(new java.awt.Color(222, 3, 3));
        panel_main.add(lbl_memberError, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 280, 130, 40));

        jPanel3.setBackground(new java.awt.Color(255, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 23)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Literature_100px.png"))); // NOI18N
        jLabel12.setText(" Issued  Book Details");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 70, 300, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 6, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 340, 6));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Member Name  :");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 120, -1));

        lbl_memberName.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        lbl_memberName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_memberName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 170, 20));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText(" Book Name      :");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 120, -1));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText(" Issue Id             :");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 120, -1));

        lbl_issueId.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        lbl_issueId.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_issueId, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 170, 20));

        lbl_bookName.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        lbl_bookName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 170, 20));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Issue Date          :");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 120, -1));

        lbl_issueDate.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        lbl_issueDate.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_issueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 170, 20));

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Due Date           :");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 120, -1));

        lbl_dueDate.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        lbl_dueDate.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_dueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, 170, 20));

        panel_main.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 410, 670));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 23)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel1.setText("   Return Book");
        panel_main.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 100, 210, -1));

        jPanel5.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panel_main.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 170, 290, 6));

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText("Enter Book Id      :");
        panel_main.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 240, 140, 30));

        txt_bookId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        txt_bookId.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_bookId.setPlaceholder("Enter Book Id");
        txt_bookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIdFocusLost(evt);
            }
        });
        txt_bookId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookIdActionPerformed(evt);
            }
        });
        panel_main.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 230, 180, -1));

        jLabel31.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 51, 51));
        jLabel31.setText("Enter Member Id  :");
        panel_main.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 300, 150, 30));

        txt_memberId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        txt_memberId.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_memberId.setPlaceholder("Enter Member Id...");
        txt_memberId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_memberIdFocusLost(evt);
            }
        });
        txt_memberId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_memberIdActionPerformed(evt);
            }
        });
        panel_main.add(txt_memberId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 290, 180, -1));

        jLabel30.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 51, 51));
        jLabel30.setText("Enter Member Id");
        panel_main.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 300, 140, 30));

        rSMaterialButtonCircle4.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonCircle4.setText("Return Book");
        rSMaterialButtonCircle4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle4ActionPerformed(evt);
            }
        });
        panel_main.add(rSMaterialButtonCircle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 510, 330, 60));

        jPanel6.setBackground(new java.awt.Color(102, 102, 255));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel11.setText("Back");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel11MouseExited(evt);
            }
        });
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, 20));

        panel_main.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 40));

        rSMaterialButtonCircle5.setBackground(new java.awt.Color(102, 102, 255));
        rSMaterialButtonCircle5.setText("Find ");
        rSMaterialButtonCircle5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle5ActionPerformed(evt);
            }
        });
        panel_main.add(rSMaterialButtonCircle5, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 420, 330, 60));

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/library-2.png"))); // NOI18N
        panel_main.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 490, 310));

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/close_2.png"))); // NOI18N
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel20MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel20MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panel_main.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 0, 60, 40));

        getContentPane().add(panel_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 670));

        setSize(new java.awt.Dimension(1350, 670));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        HomePage home = new HomePage();
       home.setVisible(true);
       dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void txt_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusLost
     
    }//GEN-LAST:event_txt_bookIdFocusLost

    private void txt_bookIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookIdActionPerformed

    private void txt_memberIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_memberIdFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_memberIdFocusLost

    private void txt_memberIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_memberIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_memberIdActionPerformed

    private void rSMaterialButtonCircle4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle4ActionPerformed
     if(validateField() == true){
        if(returnBook()==true){
         JOptionPane.showMessageDialog(this, "Book Returned Successfully");
         updateBookCount();
     }else{
        JOptionPane.showMessageDialog(this, "Book Returned Failed"); 
     }
     }
    }//GEN-LAST:event_rSMaterialButtonCircle4ActionPerformed

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
       HomePage home = new HomePage();
       home.setVisible(true);
       dispose();
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jLabel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseEntered
      jPanel6.setBackground(dengerIn);
    }//GEN-LAST:event_jLabel11MouseEntered

    private void rSMaterialButtonCircle5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle5ActionPerformed
      if(validateField() == true){
        getIssueBookDetails();
    }
    }//GEN-LAST:event_rSMaterialButtonCircle5ActionPerformed

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseEntered
        jPanel2.setBackground(dengerIn);
    }//GEN-LAST:event_jLabel20MouseEntered

    private void jLabel20MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseExited
        jPanel2.setBackground(original);
    }//GEN-LAST:event_jLabel20MouseExited

    private void jLabel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseExited
        jPanel6.setBackground(original);
    }//GEN-LAST:event_jLabel11MouseExited

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
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReturnBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lbl_bookError;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_dueDate;
    private javax.swing.JLabel lbl_issueDate;
    private javax.swing.JLabel lbl_issueId;
    private javax.swing.JLabel lbl_memberError;
    private javax.swing.JLabel lbl_memberName;
    private javax.swing.JPanel panel_main;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle4;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle5;
    private app.bolivia.swing.JCTextField txt_bookId;
    private app.bolivia.swing.JCTextField txt_memberId;
    // End of variables declaration//GEN-END:variables
}
