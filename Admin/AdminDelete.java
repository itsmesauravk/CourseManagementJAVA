package Swing.Admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AdminDelete extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField teacherId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDelete frame = new AdminDelete();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//delete student
	String url = "jdbc:mysql://localhost:3306/CMS";
	String dbUsername = "root";
	String dbPassword = "";
	
	public void deleteAdminData(int studentId) {
	    try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
	            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {

	        preparedStatement.setInt(1, studentId);
	        int rowsAffected = preparedStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Admin deleted successfully!");
//	            JOptionPane.showMessageDialog(null, "Course Deleted Sucessfully");
	        } else {
	            System.out.println("Admin not found with ID: ");
//	            JOptionPane.showMessageDialog(null, "Course not deleted");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	/**
	 * Create the frame.
	 */
	public AdminDelete() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeleteStudent = new JLabel("Delete Admin");
		lblDeleteStudent.setBounds(125, 12, 156, 23);
		lblDeleteStudent.setFont(new Font("Dyuthi", Font.BOLD, 22));
		contentPane.add(lblDeleteStudent);
		
		JLabel lblStudentId = new JLabel("Admin ID :");
		lblStudentId.setFont(new Font("Dyuthi", Font.BOLD, 22));
		lblStudentId.setBounds(55, 86, 117, 33);
		contentPane.add(lblStudentId);
		
		teacherId = new JTextField();
		teacherId.setColumns(10);
		teacherId.setBounds(191, 81, 77, 43);
		contentPane.add(teacherId);
		
		JButton btnDeleteStd = new JButton("Delete Admin");
		btnDeleteStd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String teacherIdString = teacherId.getText();

		        if (!teacherIdString.isEmpty()) {
		            try {
		                int teacherId = Integer.parseInt(teacherIdString);
		                
		                // Check if courseId is greater than zero
		                if (teacherId > 0) {
		                    deleteAdminData(teacherId);
		                    JOptionPane.showMessageDialog(null, "Admin Deleted Successfully.");
		                } else {
		                    JOptionPane.showMessageDialog(null, "Admin Id should be greater than zero.");
		                }
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Invalid Admin Id. Please enter a valid integer.");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Admin Id cannot be empty!!");
		        }
			}
			
		});
		btnDeleteStd.setForeground(Color.BLACK);
		btnDeleteStd.setFont(new Font("Dyuthi", Font.BOLD, 18));
		btnDeleteStd.setBackground(new Color(165, 29, 45));
		btnDeleteStd.setBounds(55, 187, 162, 33);
		contentPane.add(btnDeleteStd);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnReturn.setForeground(Color.BLACK);
		btnReturn.setFont(new Font("Dyuthi", Font.BOLD, 18));
		btnReturn.setBackground(new Color(38, 162, 105));
		btnReturn.setBounds(249, 186, 150, 35);
		contentPane.add(btnReturn);
		
		JLabel lblenterIdOf = new JLabel("(Enter ID of Admin to delete)");
		lblenterIdOf.setForeground(new Color(246, 97, 81));
		lblenterIdOf.setFont(new Font("FreeMono", Font.BOLD | Font.ITALIC, 10));
		lblenterIdOf.setBounds(159, 131, 206, 23);
		contentPane.add(lblenterIdOf);
	}

}
