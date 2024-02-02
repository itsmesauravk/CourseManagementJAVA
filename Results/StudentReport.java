package Swing.Results;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentReport extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StudentReport frame = new StudentReport(123); // Replace with the actual student ID
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private Map<String, String> getStudentData(int id) {
        Map<String, String> studentData = new HashMap<>();

        String url = "jdbc:mysql://localhost:3306/CMS";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT u.firstname, u.surname, u.email,u.std_course,r.std_level, r.module_1, r.mark_1,r.module_2, r.mark_2,r.module_3, r.mark_3, r.percentage, r.result FROM users u JOIN results r ON u.id=r.std_id WHERE u.id = ?")) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Getting data from the result set and putting it into the HashMap
                    studentData.put("firstname", resultSet.getString("firstname"));
                    studentData.put("surname", resultSet.getString("surname"));
                    studentData.put("email", resultSet.getString("email"));
                    studentData.put("course", resultSet.getString("std_course"));
                    studentData.put("level", resultSet.getString("std_level"));
                    studentData.put("module_1", resultSet.getString("module_1"));
                    studentData.put("mark_1", resultSet.getString("mark_1"));
                    studentData.put("module_2", resultSet.getString("module_2"));
                    studentData.put("mark_2", resultSet.getString("mark_2"));
                    studentData.put("module_3", resultSet.getString("module_3"));
                    studentData.put("mark_3", resultSet.getString("mark_3"));
                    studentData.put("percentage", resultSet.getString("percentage"));
                    studentData.put("result", resultSet.getString("result"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentData;
    }

    public StudentReport(int reportId) {
        Map<String, String> studentData = getStudentData(reportId);

        String fname = studentData.get("firstname");
        String lname = studentData.get("surname");
        String email = studentData.get("email");
        String course = studentData.get("course");
        String module1 = studentData.get("module_1");
        String mark1 = studentData.get("mark_1");
        String module2 = studentData.get("module_2");
        String mark2 = studentData.get("mark_2");
        String module3 = studentData.get("module_3");
        String mark3 = studentData.get("mark_3");
        String percent = studentData.get("percentage");
        String result = studentData.get("result");
        String lvl = studentData.get("level");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 739, 445);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblStudentReport = new JLabel("Student Report");
        lblStudentReport.setBounds(284, 0, 141, 31);
        lblStudentReport.setFont(new Font("Dyuthi", Font.BOLD, 18));
        contentPane.add(lblStudentReport);

        JLabel lblName = new JLabel("First");
        lblName.setBounds(27, 61, 80, 24);
        lblName.setFont(new Font("Dyuthi", Font.BOLD, 18));
        contentPane.add(lblName);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(27, 97, 80, 24);
        lblEmail.setFont(new Font("Dyuthi", Font.BOLD, 18));
        contentPane.add(lblEmail);

        JLabel lblCourse = new JLabel("Course");
        lblCourse.setBounds(27, 133, 80, 24);
        lblCourse.setFont(new Font("Dyuthi", Font.BOLD, 18));
        contentPane.add(lblCourse);

        JLabel lblLast = new JLabel("Last");
        lblLast.setBounds(345, 61, 60, 24);
        lblLast.setFont(new Font("Dyuthi", Font.BOLD, 18));
        contentPane.add(lblLast);

        JLabel lblNewLabel = new JLabel(fname);
        lblNewLabel.setBounds(103, 61, 149, 24);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel(lname);
        lblNewLabel_1.setBounds(408, 61, 149, 24);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel(email);
        lblNewLabel_2.setBounds(103, 97, 220, 24);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel(course);
        lblNewLabel_3.setBounds(103, 133, 231, 24);
        contentPane.add(lblNewLabel_3);
        
        JLabel lblModule = new JLabel("Modules");
        lblModule.setBounds(27, 186, 93, 24);
        lblModule.setForeground(new Color(97, 53, 131));
        lblModule.setFont(new Font("Dyuthi", Font.BOLD, 22));
        contentPane.add(lblModule);
        
        JLabel lblMarks = new JLabel("Marks");
        lblMarks.setBounds(343, 186, 95, 24);
        lblMarks.setForeground(new Color(198, 70, 0));
        lblMarks.setFont(new Font("Dyuthi", Font.BOLD, 22));
        contentPane.add(lblMarks);
        
        JLabel lblPercentage = new JLabel("Percentage :");
        lblPercentage.setBounds(27, 329, 141, 31);
        lblPercentage.setForeground(new Color(26, 95, 180));
        lblPercentage.setFont(new Font("Dyuthi", Font.BOLD, 22));
        contentPane.add(lblPercentage);
        
        JLabel lblResult = new JLabel("Result :");
        lblResult.setBounds(27, 372, 93, 31);
        lblResult.setForeground(new Color(46, 194, 126));
        lblResult.setFont(new Font("Dyuthi", Font.BOLD, 22));
        contentPane.add(lblResult);
        
        JLabel lblNewLabel_4 = new JLabel(module1);
        lblNewLabel_4.setBounds(27, 216, 296, 24);
        lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 16));
        contentPane.add(lblNewLabel_4);
        
        JLabel lblNewLabel_4_1 = new JLabel(module2);
        lblNewLabel_4_1.setBounds(27, 247, 296, 24);
        lblNewLabel_4_1.setFont(new Font("Dialog", Font.BOLD, 16));
        contentPane.add(lblNewLabel_4_1);
        
        JLabel lblNewLabel_4_2 = new JLabel(module3);
        lblNewLabel_4_2.setBounds(27, 283, 296, 24);
        lblNewLabel_4_2.setFont(new Font("Dialog", Font.BOLD, 16));
        contentPane.add(lblNewLabel_4_2);
        
        JLabel lblMark = new JLabel(mark1);
        lblMark.setBounds(343, 216, 85, 24);
        lblMark.setFont(new Font("Dialog", Font.BOLD, 16));
        contentPane.add(lblMark);
        
        JLabel lblMark_1 = new JLabel(mark2);
        lblMark_1.setBounds(340, 247, 85, 24);
        lblMark_1.setFont(new Font("Dialog", Font.BOLD, 16));
        contentPane.add(lblMark_1);
        
        JLabel lblMark_2 = new JLabel(mark3);
        lblMark_2.setBounds(340, 283, 85, 24);
        lblMark_2.setFont(new Font("Dialog", Font.BOLD, 16));
        contentPane.add(lblMark_2);
        
        JLabel lblMark_2_1 = new JLabel(percent);
        lblMark_2_1.setBounds(160, 329, 85, 24);
        lblMark_2_1.setFont(new Font("Dialog", Font.BOLD, 16));
        contentPane.add(lblMark_2_1);
        
        JLabel lblMark_2_2 = new JLabel(result);
        lblMark_2_2.setBounds(160, 372, 85, 24);
        lblMark_2_2.setFont(new Font("Dialog", Font.BOLD, 16));
        contentPane.add(lblMark_2_2);
        
        JButton btnReturn = new JButton("Return");
        btnReturn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnReturn.setBounds(593, 371, 119, 25);
        contentPane.add(btnReturn);
        
        JLabel lblLevel = new JLabel("Level");
        lblLevel.setFont(new Font("Dyuthi", Font.BOLD, 18));
        lblLevel.setBounds(345, 101, 66, 24);
        contentPane.add(lblLevel);
        
        JLabel lblNewLabel_1_1 = new JLabel(lvl);
        lblNewLabel_1_1.setBounds(408, 101, 60, 24);
        contentPane.add(lblNewLabel_1_1);
    }
}
