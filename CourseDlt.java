package Swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class CourseDlt extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseDlt frame = new CourseDlt();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	String url = "jdbc:mysql://localhost:3306/CMS";
	String dbUsername = "root";
	String dbPassword = "";
	
	public void deleteCourseData(int courseId) {
	    try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
	            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM courses WHERE course_id = ?")) {

	        preparedStatement.setInt(1, courseId);
	        int rowsAffected = preparedStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Course deleted successfully!");
//	            JOptionPane.showMessageDialog(null, "Course Deleted Sucessfully");
	        } else {
	            System.out.println("Course not found with ID: " + courseId);
//	            JOptionPane.showMessageDialog(null, "Course not deleted");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * Create the frame.
	 */
	public CourseDlt() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeleteCourse = new JLabel("Delete Course");
		lblDeleteCourse.setFont(new Font("Dyuthi", Font.BOLD, 22));
		lblDeleteCourse.setBounds(133, 0, 149, 48);
		contentPane.add(lblDeleteCourse);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String courseIdString = textField.getText();

			        if (!courseIdString.isEmpty()) {
			            try {
			                int courseId = Integer.parseInt(courseIdString);
			                
			                // Check if courseId is greater than zero
			                if (courseId > 0) {
			                    deleteCourseData(courseId);
			                    JOptionPane.showMessageDialog(null, "Course Deleted Successfully.");
			                } else {
			                    JOptionPane.showMessageDialog(null, "Course Id should be greater than zero.");
			                }
			            } catch (NumberFormatException ex) {
			                JOptionPane.showMessageDialog(null, "Invalid Course Id. Please enter a valid integer.");
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "Course Id cannot be empty!!");
			        }
			}
		});
		btnNewButton.setForeground(new Color(192, 191, 188));
		btnNewButton.setBackground(new Color(165, 29, 45));
		btnNewButton.setFont(new Font("Dyuthi", Font.BOLD, 18));
		btnNewButton.setBounds(133, 175, 117, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblCourseId = new JLabel("Course ID :");
		lblCourseId.setFont(new Font("Dyuthi", Font.BOLD, 22));
		lblCourseId.setBounds(75, 92, 117, 33);
		contentPane.add(lblCourseId);
		
		textField = new JTextField();
		textField.setBounds(199, 87, 77, 43);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnReturn.setForeground(new Color(192, 191, 188));
		btnReturn.setFont(new Font("Dyuthi", Font.BOLD, 18));
		btnReturn.setBackground(new Color(38, 162, 105));
		btnReturn.setBounds(133, 233, 117, 25);
		contentPane.add(btnReturn);
	}
}
