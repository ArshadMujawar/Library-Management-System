/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jFrame;

import static jFrame.DBConnection.con;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.table.TableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class ManageMembers extends javax.swing.JFrame {

    /**
     * Creates new form ManageBooks
     */
    Color dengerIn = new Color(204, 0, 0);
    Color original = new Color(102, 102, 255);
    Color original2 = new Color(255, 51, 51);
    String memberName, contactNo, email, memberAddress, gender, memberType, joinDate, expDate;
    int memberId;
    double fee;
    // Date joinDate,expDate;
    DefaultTableModel model;
    private DefaultTableModel TableModel;

    public ManageMembers() {
        initComponents();
        setMemberDetailsToTable();
    }

    //to set the book detaile into the table
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

    public boolean addMember() {
        boolean isAdded = false;

        memberId = Integer.parseInt(txt_memberId.getText());
        memberName = txt_memberName.getText();
        contactNo = txt_memberContactNo.getText();
        email = txt_memberEmail.getText();
        memberAddress = txt_member_addess.getText();

        if (RadioButton_male.isSelected()) {
            gender = RadioButton_male.getText();
        } else if (RadioButton_female.isSelected()) {
            gender = RadioButton_female.getText();
        } else {
            JOptionPane.showMessageDialog(this, "Please Select Gender");
        }

        Date uJoinDate = dateChooser_joinDate.getDate();
        Date uExpiryDate = dateChooser_ExpDate.getDate();

        long l1 = uJoinDate.getTime();
        long l2 = uExpiryDate.getTime();

        java.sql.Date sJoinDate = new java.sql.Date(l1);
        java.sql.Date sExpiryDate = new java.sql.Date(l2);

        memberType = Combo_membershipType.getSelectedItem().toString();
        fee = Double.parseDouble(txt_Fee.getText());

        try {

            Connection con = DBConnection.getConnection();
            String sql = "insert into member_details values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pat = con.prepareStatement(sql);
            pat.setInt(1, memberId);
            pat.setString(2, memberName);
            pat.setString(3, contactNo);
            pat.setString(4, email);
            pat.setString(5, memberAddress);
            pat.setString(6, gender);
            pat.setDate(7, sJoinDate);
            pat.setString(8, memberType);
            pat.setDate(9, sExpiryDate);
            pat.setDouble(10, fee);

            int rowCount = pat.executeUpdate();
            if (rowCount > 0) {
                isAdded = true;
            } else {
                isAdded = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAdded;
    }

    //
    public boolean updateMember() {
        boolean isUpdated = false;

        memberId = Integer.parseInt(txt_memberId.getText());
        memberName = txt_memberName.getText();
        contactNo = txt_memberContactNo.getText();
        email = txt_memberEmail.getText();
        memberAddress = txt_member_addess.getText();

        if (RadioButton_male.isSelected()) {
            gender = RadioButton_male.getText();
        } else if (RadioButton_female.isSelected()) {
            gender = RadioButton_female.getText();
        } else {
            JOptionPane.showMessageDialog(this, "Please Select Gender");
        }

        Date uJoinDate = dateChooser_joinDate.getDate();
        Date uExpiryDate = dateChooser_ExpDate.getDate();

        long l1 = uJoinDate.getTime();
        long l2 = uExpiryDate.getTime();

        java.sql.Date sJoinDate = new java.sql.Date(l1);
        java.sql.Date sExpiryDate = new java.sql.Date(l2);
        memberType = Combo_membershipType.getSelectedItem().toString();
        fee = Double.parseDouble(txt_Fee.getText());

        try {
            Connection con = DBConnection.getConnection();
            String sql = "update member_details set mem_name=?, contact_no=?, email=?, address=?, gender=?, join_date=?, mem_type=?, exp_date=?, fee=? where member_id=?";
            PreparedStatement pat = con.prepareStatement(sql);

            pat.setString(1, memberName);
            pat.setString(2, contactNo);
            pat.setString(3, email);
            pat.setString(4, memberAddress);
            pat.setString(5, gender);
            pat.setDate(6, sJoinDate);
            pat.setString(7, memberType);
            pat.setDate(8, sExpiryDate);
            pat.setDouble(9, fee);
            pat.setInt(10, memberId);

            int rowCount = pat.executeUpdate();
            if (rowCount > 0) {
                isUpdated = true;
            } else {
                isUpdated = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    //method to delete book detail
    public boolean deleteMember() {
        boolean isDeleted = false;
        memberId = Integer.parseInt(txt_memberId.getText());

        try {
            Connection con = DBConnection.getConnection();
            String sql = "delete from member_details where member_id = ?";
            PreparedStatement pat = con.prepareStatement(sql);
            pat.setInt(1, memberId);

            int rowCount = pat.executeUpdate();
            if (rowCount > 0) {
                isDeleted = true;
            } else {
                isDeleted = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    //Validation
    public boolean validateMemberField() {
        String memberId = txt_memberId.getText();
        memberName = txt_memberName.getText();
        contactNo = txt_memberContactNo.getText();
        email = txt_memberEmail.getText();
        memberAddress = txt_member_addess.getText();
        String fee = txt_Fee.getText();

        if (memberId.equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Member Id");
            return false;
        } else if (memberName.equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Member Name");
            return false;
        } else if (contactNo.equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Contact No");
            return false;
        } else if (email.equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Email");
            return false;
        } else if (memberAddress.equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Address");
            return false;
        } else if (!RadioButton_male.isSelected() && !RadioButton_female.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please Select Gender");
            return false;
        } else if (dateChooser_joinDate.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please Enter Join Date");
            return false;
        } else if (dateChooser_ExpDate.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please Enter Expiry Date");
            return false;
        } else if (fee.equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Fee");
            return false;
        }

        return true;
    }

    public void clearField() {

        txt_memberId.setText("");
        txt_memberName.setText("");
        txt_memberContactNo.setText("");
        txt_memberEmail.setText("");
        txt_member_addess.setText("");
        dateChooser_joinDate.setDate(null);
        dateChooser_ExpDate.setDate(null);
        txt_Fee.setText("");
        buttonGroup_gender.clearSelection();

    }

    //method to clear table
    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) tbl_memberDetails.getModel();
        model.setRowCount(0);
    }
    
    public void calculate(){
         Date uJoinDate = dateChooser_joinDate.getDate();
       //  long l1 = uJoinDate.getTime();
        // java.sql.Date sJoinDate = new java.sql.Date(l1);
        
        
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup_gender = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_memberContactNo = new app.bolivia.swing.JCTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_memberId = new app.bolivia.swing.JCTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_memberName = new app.bolivia.swing.JCTextField();
        jLabel11 = new javax.swing.JLabel();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle4 = new rojerusan.RSMaterialButtonCircle();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_memberEmail = new app.bolivia.swing.JCTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_member_addess = new javax.swing.JTextArea();
        RadioButton_male = new javax.swing.JRadioButton();
        RadioButton_female = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txt_Fee = new javax.swing.JTextField();
        dateChooser_ExpDate = new com.toedter.calendar.JDateChooser();
        dateChooser_joinDate = new com.toedter.calendar.JDateChooser();
        Combo_membershipType = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_memberDetails = new rojeru_san.complementos.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Enter Address");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 140, 40));

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Contact Number");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 150, 30));

        txt_memberContactNo.setBackground(new java.awt.Color(102, 102, 255));
        txt_memberContactNo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_memberContactNo.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_memberContactNo.setPlaceholder("Enter Contact Number");
        txt_memberContactNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_memberContactNoFocusLost(evt);
            }
        });
        txt_memberContactNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_memberContactNoActionPerformed(evt);
            }
        });
        jPanel1.add(txt_memberContactNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 220, 35));

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/home-address.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 30, 40));

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Enter Member Id");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 160, 30));

        txt_memberId.setBackground(new java.awt.Color(102, 102, 255));
        txt_memberId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
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
        jPanel1.add(txt_memberId, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 98, 220, -1));

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Contact_26px.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 30, 30));

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Enter Member Name");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 190, 30));

        txt_memberName.setBackground(new java.awt.Color(102, 102, 255));
        txt_memberName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_memberName.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_memberName.setPlaceholder("Enter Member Name...");
        txt_memberName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_memberNameFocusLost(evt);
            }
        });
        txt_memberName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_memberNameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_memberName, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 210, 35));

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Moleskine_26px.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 30, 50));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonCircle2.setText("UPDATE");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 590, 160, 60));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonCircle3.setLabel("DELETE");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 590, 160, 60));

        rSMaterialButtonCircle4.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonCircle4.setText("ADD");
        rSMaterialButtonCircle4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle4ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 590, 160, 60));

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

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 40));

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/email.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, 30, 40));

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Enter Email");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, 140, 30));

        txt_memberEmail.setBackground(new java.awt.Color(102, 102, 255));
        txt_memberEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_memberEmail.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_memberEmail.setPlaceholder("Enter Email ");
        txt_memberEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_memberEmailFocusLost(evt);
            }
        });
        txt_memberEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_memberEmailActionPerformed(evt);
            }
        });
        jPanel1.add(txt_memberEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 210, 40));

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Choose Gender");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, 130, 30));

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/gender.png"))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, 30, 40));

        jLabel16.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Fee :");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 520, 50, 40));

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/Join_date.png"))); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 450, 30, 40));

        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        txt_member_addess.setColumns(20);
        txt_member_addess.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txt_member_addess.setLineWrap(true);
        txt_member_addess.setRows(5);
        jScrollPane1.setViewportView(txt_member_addess);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 290, 230, 90));

        buttonGroup_gender.add(RadioButton_male);
        RadioButton_male.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        RadioButton_male.setForeground(new java.awt.Color(255, 255, 255));
        RadioButton_male.setText("Male");
        RadioButton_male.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton_maleActionPerformed(evt);
            }
        });
        jPanel1.add(RadioButton_male, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, 70, 20));

        buttonGroup_gender.add(RadioButton_female);
        RadioButton_female.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        RadioButton_female.setForeground(new java.awt.Color(255, 255, 255));
        RadioButton_female.setText("Female");
        RadioButton_female.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton_femaleActionPerformed(evt);
            }
        });
        jPanel1.add(RadioButton_female, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 350, 90, 20));

        jLabel21.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/contact_no.png"))); // NOI18N
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 30, 50));

        jLabel20.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("join date");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, -1, 30));

        jLabel22.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/Join_date.png"))); // NOI18N
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 30, 40));

        jLabel23.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Membershipe Type");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 410, 150, 30));

        jLabel24.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/membershipe type.png"))); // NOI18N
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 450, 30, 40));

        jLabel25.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Expiry Date");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 410, 110, 30));

        txt_Fee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_FeeActionPerformed(evt);
            }
        });
        jPanel1.add(txt_Fee, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 520, 150, 40));

        dateChooser_ExpDate.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(dateChooser_ExpDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 450, 140, 30));

        dateChooser_joinDate.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(dateChooser_joinDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, 140, 30));

        Combo_membershipType.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        Combo_membershipType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 Month", "3 Month", "6 Month", "1 Year ", "Life Time" }));
        Combo_membershipType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Combo_membershipTypeActionPerformed(evt);
            }
        });
        jPanel1.add(Combo_membershipType, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 450, 150, 32));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 675, 670));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/close_2.png"))); // NOI18N
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel18MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 60, 40));

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
        tbl_memberDetails.setColorBackgoundHead(new java.awt.Color(102, 102, 255));
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
        tbl_memberDetails.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tbl_memberDetails.getColumnModel().getColumnCount() > 0) {
            tbl_memberDetails.getColumnModel().getColumn(0).setPreferredWidth(95);
            tbl_memberDetails.getColumnModel().getColumn(1).setPreferredWidth(230);
            tbl_memberDetails.getColumnModel().getColumn(2).setPreferredWidth(190);
            tbl_memberDetails.getColumnModel().getColumn(3).setPreferredWidth(200);
            tbl_memberDetails.getColumnModel().getColumn(4).setPreferredWidth(300);
            tbl_memberDetails.getColumnModel().getColumn(5).setPreferredWidth(80);
            tbl_memberDetails.getColumnModel().getColumn(6).setPreferredWidth(100);
            tbl_memberDetails.getColumnModel().getColumn(7).setPreferredWidth(100);
            tbl_memberDetails.getColumnModel().getColumn(8).setPreferredWidth(100);
            tbl_memberDetails.getColumnModel().getColumn(9).setPreferredWidth(100);
        }

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 640, 450));

        jPanel4.setBackground(new java.awt.Color(255, 51, 51));
        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 365, 6));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Student_Male_100px_1.png"))); // NOI18N
        jLabel2.setText("  Manage Members");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 320, 50));

        jButton1.setBackground(new java.awt.Color(153, 0, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 610, 100, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 0, 680, 670));

        setSize(new java.awt.Dimension(1350, 670));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_memberContactNoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_memberContactNoFocusLost

    }//GEN-LAST:event_txt_memberContactNoFocusLost

    private void txt_memberContactNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_memberContactNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_memberContactNoActionPerformed

    private void txt_memberIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_memberIdFocusLost

    }//GEN-LAST:event_txt_memberIdFocusLost

    private void txt_memberIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_memberIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_memberIdActionPerformed

    private void txt_memberNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_memberNameFocusLost

    }//GEN-LAST:event_txt_memberNameFocusLost

    private void txt_memberNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_memberNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_memberNameActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed

        if (validateMemberField() == true) {
            if (updateMember() == true) {
                JOptionPane.showMessageDialog(this, "Member Updated");
                clearTable();
                setMemberDetailsToTable();
            } else {
                JOptionPane.showMessageDialog(this, "Member Updation Failed");
            }
        }
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        if (deleteMember() == true) {
            JOptionPane.showMessageDialog(this, "Member Deleted");
            clearTable();
            clearField();
            setMemberDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Member Deletion Failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void rSMaterialButtonCircle4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle4ActionPerformed
        if (validateMemberField() == true) {
            if (addMember() == true) {
                JOptionPane.showMessageDialog(this, "Member Added");
                clearTable();
                setMemberDetailsToTable();
            } else {
                JOptionPane.showMessageDialog(this, "Member Addition Failed");
            }
        }
    }//GEN-LAST:event_rSMaterialButtonCircle4ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void txt_memberEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_memberEmailFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_memberEmailFocusLost

    private void txt_memberEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_memberEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_memberEmailActionPerformed

    private void tbl_memberDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_memberDetailsMouseClicked
        int rowNo = tbl_memberDetails.getSelectedRow();
        TableModel model = tbl_memberDetails.getModel();

        txt_memberId.setText(model.getValueAt(rowNo, 0).toString());
        txt_memberName.setText(model.getValueAt(rowNo, 1).toString());
        txt_memberContactNo.setText(model.getValueAt(rowNo, 2).toString());
        txt_memberEmail.setText(model.getValueAt(rowNo, 3).toString());
        txt_member_addess.setText(model.getValueAt(rowNo, 4).toString());
        String gender = model.getValueAt(rowNo, 5).toString();

        if (gender.equalsIgnoreCase("Male")) {

            RadioButton_male.setSelected(true);

        } else if (gender.equalsIgnoreCase("Female")) {
            RadioButton_female.setSelected(true);
        }

        String dte = (model.getValueAt(rowNo, 6)).toString();
        Date date1;
        try {
            date1 = new SimpleDateFormat("yyyy-mm-dd").parse(dte);
            dateChooser_joinDate.setDate(date1);
        } catch (ParseException ex) {
            Logger.getLogger(ManageMembers.class.getName()).log(Level.SEVERE, null, ex);
        }

        String dte2 = (model.getValueAt(rowNo, 8)).toString();
        Date date12;
        try {
            date12 = new SimpleDateFormat("yyyy-mm-dd").parse(dte2);
            dateChooser_ExpDate.setDate(date12);
        } catch (ParseException ex) {
            Logger.getLogger(ManageMembers.class.getName()).log(Level.SEVERE, null, ex);
        }

        Combo_membershipType.setSelectedItem(model.getValueAt(rowNo, 7));
        txt_Fee.setText(model.getValueAt(rowNo, 9).toString());
    }//GEN-LAST:event_tbl_memberDetailsMouseClicked

    private void RadioButton_femaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton_femaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RadioButton_femaleActionPerformed

    private void RadioButton_maleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton_maleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RadioButton_maleActionPerformed

    private void txt_FeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_FeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_FeeActionPerformed

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseEntered
        jPanel2.setBackground(dengerIn);
    }//GEN-LAST:event_jLabel18MouseEntered

    private void jLabel18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseExited
        jPanel2.setBackground(original);
    }//GEN-LAST:event_jLabel18MouseExited

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        jPanel5.setBackground(dengerIn);
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        jPanel5.setBackground(original2);
    }//GEN-LAST:event_jLabel3MouseExited

    private void Combo_membershipTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Combo_membershipTypeActionPerformed
       
    }//GEN-LAST:event_Combo_membershipTypeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MemberReport report = new MemberReport();
        report.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ManageMembers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageMembers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageMembers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageMembers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageMembers().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Combo_membershipType;
    private javax.swing.JRadioButton RadioButton_female;
    private javax.swing.JRadioButton RadioButton_male;
    private javax.swing.ButtonGroup buttonGroup_gender;
    private com.toedter.calendar.JDateChooser dateChooser_ExpDate;
    private com.toedter.calendar.JDateChooser dateChooser_joinDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle4;
    private rojeru_san.complementos.RSTableMetro tbl_memberDetails;
    private javax.swing.JTextField txt_Fee;
    private app.bolivia.swing.JCTextField txt_memberContactNo;
    private app.bolivia.swing.JCTextField txt_memberEmail;
    private app.bolivia.swing.JCTextField txt_memberId;
    private app.bolivia.swing.JCTextField txt_memberName;
    private javax.swing.JTextArea txt_member_addess;
    // End of variables declaration//GEN-END:variables
}
