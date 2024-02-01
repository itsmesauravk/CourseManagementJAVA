package Swing;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteCourse extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	
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
	            JOptionPane.showMessageDialog(null, "Course Deleted Sucessfully");
	        } else {
	            System.out.println("Course not found with ID: " + courseId);
	            JOptionPane.showMessageDialog(null, "Course not deleted");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	
	public DeleteCourse() {
		setLayout(null);
		
		JLabel lblDeleteCourse = new JLabel("Delete Course");
		lblDeleteCourse.setFont(new Font("Dyuthi", Font.BOLD, 24));
		lblDeleteCourse.setBounds(146, 12, 183, 29);
		add(lblDeleteCourse);
		
		textField = new JTextField();
		textField.setBounds(131, 66, 85, 39);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblCourseId = new JLabel("Course ID :");
		lblCourseId.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblCourseId.setBounds(28, 72, 85, 29);
		add(lblCourseId);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID = Integer.parseInt(textField.getText());
				deleteCourseData(ID);
				JOptionPane.showMessageDialog(null, "Course Deleted Sucessfully");
				
			}
		});
		btnDelete.setForeground(new Color(246, 245, 244));
		btnDelete.setBackground(new Color(165, 29, 45));
		btnDelete.setFont(new Font("Dyuthi", Font.BOLD, 18));
		btnDelete.setBounds(236, 66, 85, 39);
		add(btnDelete);
		

	}
}
