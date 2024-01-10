/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jFrame;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class onebook extends javax.swing.JFrame {

    /**
     * Creates new form IssueBook
     */
    Color dengerIn = new Color(204, 0, 0);
    Color original = new Color(102, 102, 255);

    public onebook() {
        initComponents();
    }

    //to fetch book details from the detabase and display it to book details panel 
    public void getBookDetails() {
        int bookId = Integer.parseInt(txt_bookId.getText());

        try {
            Connection con = DBConnection.getConnection();
            String sql = "select * from book_details where book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                lbl_bookId.setText(rs.getString("book_id"));
                lbl_bookName.setText(rs.getString("book_name"));
                lbl_author.setText(rs.getString("author"));
                lbl_publication.setText(rs.getString("publication"));
                lbl_language.setText(rs.getString("language"));
                lbl_quantity.setText(rs.getString("quantity"));
                lbl_pages.setText(rs.getString("pages"));
                lbl_shelfNumber.setText(rs.getString("shelf_number"));
            } else {
                lbl_bookId.setText("");
                lbl_bookName.setText("");
                lbl_author.setText("");
                lbl_publication.setText("");
                lbl_language.setText("");
                lbl_quantity.setText("");
                lbl_pages.setText("");
                lbl_shelfNumber.setText("");
                lbl_bookError.setText("Invalid Book Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //to fetch Member details from the detabase and display it to Member details panel 
    public void getMemberDetails() {
        int memberId = Integer.parseInt(txt_memberId.getText());

        try {
            Connection con = DBConnection.getConnection();
            String sql = "select * from member_details where member_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, memberId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                lbl_memberId.setText(rs.getString("member_id"));
                lbl_memberName.setText(rs.getString("mem_name"));
                lbl_memContactNo.setText(rs.getString("contact_no"));
                lbl_memEmail.setText(rs.getString("email"));
                lbl_memGender.setText(rs.getString("gender"));
                lbl_joinDate.setText(rs.getString("join_date"));
                lbl_expDate.setText(rs.getString("exp_date"));
                lbl_memType.setText(rs.getString("mem_type"));
            } else {
                lbl_memberId.setText("");
                lbl_memberName.setText("");
                lbl_memContactNo.setText("");
                lbl_memEmail.setText("");
                lbl_memGender.setText("");
                lbl_joinDate.setText("");
                lbl_expDate.setText("");
                lbl_memType.setText("");
                lbl_memberError.setText("Invalid Member Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //insert issue book details to database
    public boolean issueBook() {
        boolean isIssued = false;

        int bookId = Integer.parseInt(txt_bookId.getText());
        int memberId = Integer.parseInt(txt_memberId.getText());
        String bookName = lbl_bookName.getText();
        String memberName = lbl_memberName.getText();

        Date uIssueDate = date_issueDate.getDate();
        Date uDueDate = date_dueDate.getDate();

        long l1 = uIssueDate.getTime();
        long l2 = uDueDate.getTime();

        java.sql.Date sIssueDate = new java.sql.Date(l1);
        java.sql.Date sDueDate = new java.sql.Date(l2);

        // SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        //String sIssueDate = dateformat.format(date_issueDate.getDate());
        //String sDueDate = dateformat.format(date_dueDate.getDate());
        try {
            Connection con = DBConnection.getConnection();
            String sql = "insert into issue_book_details(book_id,book_name,member_id,member_name,issue_date,due_date,status) values(?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setString(2, bookName);
            pst.setInt(3, memberId);
            pst.setString(4, memberName);
            pst.setDate(5, sIssueDate);
            pst.setDate(6, sDueDate);
            pst.setString(7, "pending");

            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                isIssued = true;
            } else {
                isIssued = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isIssued;
    }

    //updating book count
    public void updateBookCount() {
        int bookId = Integer.parseInt(txt_bookId.getText());
        try {
            Connection con = DBConnection.getConnection();
            String sql = "update book_details set quantity = quantity - 1 where book_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                JOptionPane.showMessageDialog(this, "book count Updated");
                int initialCount = Integer.parseInt(lbl_quantity.getText());
                lbl_quantity.setText(Integer.toString(initialCount - 1));
            } else {
                JOptionPane.showMessageDialog(this, "can't Update book count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Checking whether book already allocated Entered Member or not
    public boolean isAlreadyIssued() {
        boolean isAlreadyIssued = false;
        int bookId = Integer.parseInt(txt_bookId.getText());
        int memberId = Integer.parseInt(txt_memberId.getText());
        try {
            Connection con = DBConnection.getConnection();
            String sql = "select * from issue_book_details where book_id=? and member_id=? and status=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setInt(2, memberId);
            pst.setString(3, "pending");

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                isAlreadyIssued = true;

            } else {
                isAlreadyIssued = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isAlreadyIssued;
    }

    //Checking whether book already allocated or not
    public boolean isAlreadyIssuedTwoBook() {
        boolean isAlreadyIssued = false;

        int memberId = Integer.parseInt(txt_memberId.getText());
        int count = 0;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "select count(*) from issue_book_details where member_id=? and status=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, memberId);
            pst.setString(2, "pending");

            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                
               // count = count+1;
                  count = rs.getRow();    
            }
           // int count = rs.getRow();
            
            
            JOptionPane.showMessageDialog(this,count);
            /*if (rs.next()) {
                isAlreadyIssued = true;

            } else {
                isAlreadyIssued = false;
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isAlreadyIssued;
    }

    //Validation
    public boolean validateDates() {
        
        String bookId = txt_bookId.getText();
        String memberId = txt_memberId.getText();
        

        if (bookId.equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Book Id");
            return false;
        }else if (memberId.equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Book Name");
            return false;
        }else if (date_issueDate.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please Enter Issue Date");
            return false;
        } else if (date_dueDate.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please Enter Due Date");
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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lbl_memEmail = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lbl_memberId = new javax.swing.JLabel();
        lbl_memberName = new javax.swing.JLabel();
        lbl_memContactNo = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lbl_memGender = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lbl_joinDate = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lbl_expDate = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lbl_memType = new javax.swing.JLabel();
        lbl_bookError = new javax.swing.JLabel();
        lbl_memberError = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        lbl_publication = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_bookId = new javax.swing.JLabel();
        lbl_bookName = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbl_language = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lbl_pages = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lbl_shelfNumber = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txt_bookId = new app.bolivia.swing.JCTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_memberId = new app.bolivia.swing.JCTextField();
        date_issueDate = new com.toedter.calendar.JDateChooser();
        jLabel31 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        date_dueDate = new com.toedter.calendar.JDateChooser();
        rSMaterialButtonCircle4 = new rojerusan.RSMaterialButtonCircle();
        jPanel8 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_main.setBackground(new java.awt.Color(255, 255, 255));
        panel_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 6, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 360, 6));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 28)); // NOI18N
        jLabel3.setText("   Member Details");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel22.setText("Email                     :");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 140, -1));

        lbl_memEmail.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jPanel1.add(lbl_memEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, 180, 20));

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel23.setText("Member Name     :");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 140, -1));

        jLabel24.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel24.setText("Contact No.           :");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 140, -1));

        jLabel25.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel25.setText("Member Id            :");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 140, -1));

        lbl_memberId.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jPanel1.add(lbl_memberId, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 180, 20));

        lbl_memberName.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jPanel1.add(lbl_memberName, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 180, 20));

        lbl_memContactNo.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jPanel1.add(lbl_memContactNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 180, 20));

        jLabel26.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel26.setText("Gender                  :");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 140, -1));

        lbl_memGender.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jPanel1.add(lbl_memGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 180, 20));

        jLabel27.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel27.setText("Join Date               :");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, 140, -1));

        lbl_joinDate.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jPanel1.add(lbl_joinDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 470, 180, 20));

        jLabel28.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel28.setText("Expiry Date            :");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 140, -1));

        lbl_expDate.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jPanel1.add(lbl_expDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 530, 180, 20));

        jLabel29.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel29.setText("Membership Type  :");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 590, 140, -1));

        lbl_memType.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jPanel1.add(lbl_memType, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 590, 180, 20));

        panel_main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 448, 670));

        lbl_bookError.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 15)); // NOI18N
        lbl_bookError.setForeground(new java.awt.Color(222, 3, 3));
        panel_main.add(lbl_bookError, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 220, 130, 30));

        lbl_memberError.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 15)); // NOI18N
        lbl_memberError.setForeground(new java.awt.Color(222, 3, 3));
        panel_main.add(lbl_memberError, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 270, 130, 40));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 28)); // NOI18N
        jLabel12.setText("   Book Details");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 200, 60));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 350, 5));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel13.setText("Publication       :");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 110, -1));

        lbl_publication.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jPanel3.add(lbl_publication, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 180, 20));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel15.setText("Book Name     :");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 110, -1));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel16.setText("Author              :");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 110, -1));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel17.setText("Book Id            :");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 110, -1));

        lbl_bookId.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jPanel3.add(lbl_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 180, 20));

        lbl_bookName.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jPanel3.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 180, 20));

        lbl_author.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jPanel3.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 180, 20));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel18.setText("Language         :");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 110, -1));

        lbl_language.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jPanel3.add(lbl_language, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 180, 20));

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel19.setText("Quantity           :");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, 110, -1));

        lbl_quantity.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jPanel3.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, 180, 20));

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel20.setText("Pages                :");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 510, 110, -1));

        lbl_pages.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jPanel3.add(lbl_pages, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 510, 180, 20));

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jLabel21.setText("Shelf Number   :");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 570, 110, -1));

        lbl_shelfNumber.setFont(new java.awt.Font("Yu Gothic UI", 0, 15)); // NOI18N
        jPanel3.add(lbl_shelfNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 570, 180, 20));

        panel_main.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 448, 670));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 23)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel1.setText("   Issue Book");
        panel_main.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 90, 210, -1));

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

        panel_main.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 160, 290, 6));

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText("Enter Book Id      :");
        panel_main.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 230, 140, 30));

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
        panel_main.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 220, 220, -1));

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 51, 51));
        jLabel14.setText("Enter Issue Date  :");
        panel_main.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 350, 140, 30));

        txt_memberId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        txt_memberId.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_memberId.setPlaceholder("Enter Member Id");
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
        panel_main.add(txt_memberId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 280, 220, -1));

        date_issueDate.setBackground(new java.awt.Color(255, 51, 51));
        date_issueDate.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 51, 51)));
        date_issueDate.setForeground(new java.awt.Color(255, 51, 51));
        date_issueDate.setDateFormatString("yyyy-MM-dd");
        panel_main.add(date_issueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 350, 230, 30));

        jLabel31.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 51, 51));
        jLabel31.setText("Enter Member Id  :");
        panel_main.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 290, 150, 30));

        jLabel30.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 51, 51));
        jLabel30.setText("Enter Member Id");
        panel_main.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 290, 140, 30));

        jLabel32.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 51, 51));
        jLabel32.setText("Enter Due Date    :");
        panel_main.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 420, 140, 30));

        date_dueDate.setBackground(new java.awt.Color(255, 51, 51));
        date_dueDate.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 51, 51)));
        date_dueDate.setForeground(new java.awt.Color(255, 51, 51));
        date_dueDate.setDateFormatString("yyyy-MM-dd");
        panel_main.add(date_dueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 420, 230, 30));

        rSMaterialButtonCircle4.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonCircle4.setText("Issue Book");
        rSMaterialButtonCircle4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle4ActionPerformed(evt);
            }
        });
        panel_main.add(rSMaterialButtonCircle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 520, 330, 60));

        jPanel8.setBackground(new java.awt.Color(102, 102, 255));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/close_2.png"))); // NOI18N
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel33MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel33MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel33MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panel_main.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 0, 60, 40));

        getContentPane().add(panel_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 670));

        setSize(new java.awt.Dimension(1350, 670));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusLost
        if (!txt_bookId.getText().equals("")) {
            getBookDetails();

            if (!lbl_bookId.getText().equals("")) {
                lbl_bookError.setText("");
            }

        }
    }//GEN-LAST:event_txt_bookIdFocusLost

    private void txt_bookIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookIdActionPerformed

    private void txt_memberIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_memberIdFocusLost
        if (!txt_memberId.getText().equals("")) {
            getMemberDetails();

            if (!lbl_memberId.getText().equals("")) {
                lbl_memberError.setText("");
            }

        }
    }//GEN-LAST:event_txt_memberIdFocusLost

    private void txt_memberIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_memberIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_memberIdActionPerformed

    private void rSMaterialButtonCircle4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle4ActionPerformed

        if (validateDates() == true) {
            if (lbl_bookError.getText().equals("")) {
                if (lbl_quantity.getText().equals("0")) {
                    JOptionPane.showMessageDialog(this, "Book is not available");
                } else {
                    if (isAlreadyIssued() == false) {
                        if (isAlreadyIssuedTwoBook() == false) {
                            if (issueBook() == true) {
                                JOptionPane.showMessageDialog(this, "Book Issued Successfully");
                                updateBookCount();
                            } else {
                                JOptionPane.showMessageDialog(this, "Can't issue the book");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "This member already 2 books pending");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "This member already get this book");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please Enter Valid Data ");
            }
        }
    }//GEN-LAST:event_rSMaterialButtonCircle4ActionPerformed

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel33MouseClicked

    private void jLabel33MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseEntered
        jPanel8.setBackground(dengerIn);
    }//GEN-LAST:event_jLabel33MouseEntered

    private void jLabel33MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseExited
        jPanel8.setBackground(original);
    }//GEN-LAST:event_jLabel33MouseExited

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
            java.util.logging.Logger.getLogger(onebook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(onebook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(onebook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(onebook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new onebook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser date_dueDate;
    private com.toedter.calendar.JDateChooser date_issueDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookError;
    private javax.swing.JLabel lbl_bookId;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_expDate;
    private javax.swing.JLabel lbl_joinDate;
    private javax.swing.JLabel lbl_language;
    private javax.swing.JLabel lbl_memContactNo;
    private javax.swing.JLabel lbl_memEmail;
    private javax.swing.JLabel lbl_memGender;
    private javax.swing.JLabel lbl_memType;
    private javax.swing.JLabel lbl_memberError;
    private javax.swing.JLabel lbl_memberId;
    private javax.swing.JLabel lbl_memberName;
    private javax.swing.JLabel lbl_pages;
    private javax.swing.JLabel lbl_publication;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_shelfNumber;
    private javax.swing.JPanel panel_main;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle4;
    private app.bolivia.swing.JCTextField txt_bookId;
    private app.bolivia.swing.JCTextField txt_memberId;
    // End of variables declaration//GEN-END:variables
}
