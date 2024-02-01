
package Swing;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Swing.Admin.AdminAdd;
import Swing.Admin.AdminDelete;
import Swing.Admin.AdminDisplay;
import Swing.Admin.AdminEdit;
import Swing.Course.CourseAdd;
import Swing.Course.CourseDlt;
import Swing.Course.CourseUpdate;
import Swing.Course.CoursesDisplay;
import Swing.Students.StudentAdd;
import Swing.Students.StudentDelete;
import Swing.Students.StudentDisplay;
import Swing.Students.StudentEdit;
import Swing.Teachers.TeacherAdd;
import Swing.Teachers.TeacherDelete;
import Swing.Teachers.TeacherEdit;
import Swing.Teachers.TeachersDisplay;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class Dashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private CoursesDisplay coursesDis;
	private StudentDisplay studentDis;
	private TeachersDisplay teacherDis;
	private AdminDisplay adminDis;
	
	//for refreshing
	private JLabel label_1;
	private JLabel label_1_1;
	private JLabel label_1_2;
	private JLabel label_1_3;
	

	/**
	 * Launch the application.
	 */
	//getter setter(ENcapsulation
	
	private static String userEmail;
	
	//getting
	public String getUserEmail() {
		return userEmail;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard(userEmail);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JTextField txtCources;
	

	
	/**
	 * Create the frame.
	 */
	String url = "jdbc:mysql://localhost:3306/CMS";
    String dbUsername = "root";
    String dbPassword = "";
	
	public Map<String, String> getUserData(String userEmail) {
        Map<String, String> userData = new HashMap<>();

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?")) {

            preparedStatement.setString(1, userEmail);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Getting data from the result set
                    userData.put("firstname", resultSet.getString("firstname"));
                    userData.put("surname", resultSet.getString("surname"));
                    userData.put("email", resultSet.getString("email"));
                    userData.put("role", resultSet.getString("role"));
                   
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userData;
    }
	//total number of admin,teacher,student
	public int getTotalCount(String role) {
	    String url = "jdbc:mysql://localhost:3306/CMS";
	    String dbUsername = "root";
	    String dbPassword = "";

	    String query = "SELECT COUNT(*) FROM users WHERE role = ?";

	    try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setString(1, role);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            return resultSet.getInt(1);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle the exception as needed
	    }

	    return 0; // Return 0 if something went wrong
	}
	
	//to get total courses
	public class DatabaseOperations {

	    // Your existing database connection parameters
	    private String url = "jdbc:mysql://localhost:3306/CMS";
	    private String dbUsername = "root";
	    private String dbPassword = "";

	    // Method to get the total number of courses
	    public int getTotalCourseCount() {
	        String query = "SELECT COUNT(*) FROM courses";

	        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
	             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                return resultSet.getInt(1);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            
	        }

	        return 0; // Return 0 if something went wrong
	    }
	}

	


	public Dashboard(String userEmail) {
		
		this.userEmail = userEmail;
		Map<String, String> userData = getUserData(userEmail);

		String firstname = userData.get("firstname");
		String surname = userData.get("surname");
		String email = userData.get("email");
		String role = userData.get("role");
		
		
		DatabaseOperations databaseOperations = new DatabaseOperations();
        int totalCourses = databaseOperations.getTotalCourseCount();
        String totalCourse = String.valueOf(totalCourses);


        int totalStds = getTotalCount("Student");
        int totalA = getTotalCount("Admin");
        int totalT = getTotalCount("Teacher");

        String totalAdmins = String.valueOf(totalA);
        String totalTeachers = String.valueOf(totalT);
        String totalStudents = String.valueOf(totalStds);
        
		

		setBackground(new Color(94, 92, 100));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 886, 624);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(94, 92, 100));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(173, 0, 516, 582);
		contentPane.add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Home", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblTab_2 = new JLabel("DashBoard");
		lblTab_2.setFont(new Font("Dyuthi", Font.BOLD, 30));
		lblTab_2.setBounds(12, 0, 163, 53);
		panel_2.add(lblTab_2);
		
		JLabel lblNewLabel = new JLabel(firstname);
		lblNewLabel.setForeground(new Color(46, 194, 126));
		lblNewLabel.setFont(new Font("Dyuthi", Font.BOLD, 24));
		lblNewLabel.setBounds(369, 11, 130, 33);
		panel_2.add(lblNewLabel);
		
		JLabel lblWelcome = new JLabel("Welcome,");
		lblWelcome.setBounds(297, 20, 70, 15);
		panel_2.add(lblWelcome);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(98, 160, 234));
		panel_7.setBounds(23, 79, 130, 113);
		panel_2.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setBounds(29, 12, 75, 29);
		lblAdmin.setFont(new Font("Dyuthi", Font.BOLD, 22));
		panel_7.add(lblAdmin);
		
		JLabel label_1 = new JLabel(totalAdmins);
		label_1.setFont(new Font("Dialog", Font.BOLD, 44));
		label_1.setBounds(29, 41, 75, 60);
		panel_7.add(label_1);
		
		JPanel panel_7_1 = new JPanel();
		panel_7_1.setBackground(new Color(255, 163, 72));
		panel_7_1.setBounds(182, 79, 123, 113);
		panel_2.add(panel_7_1);
		panel_7_1.setLayout(null);
		
		JLabel lblTeacher = new JLabel("Teacher");
		lblTeacher.setBounds(24, 12, 87, 23);
		lblTeacher.setFont(new Font("Dyuthi", Font.BOLD, 22));
		panel_7_1.add(lblTeacher);
		
		JLabel label_1_1 = new JLabel(totalTeachers);
		label_1_1.setFont(new Font("Dialog", Font.BOLD, 44));
		label_1_1.setBounds(12, 41, 75, 60);
		panel_7_1.add(label_1_1);
		
		JPanel panel_7_2 = new JPanel();
		panel_7_2.setBackground(new Color(192, 97, 203));
		panel_7_2.setBounds(333, 79, 123, 113);
		panel_2.add(panel_7_2);
		panel_7_2.setLayout(null);
		
		JLabel lblStudent = new JLabel("Student");
		lblStudent.setBounds(23, 12, 76, 23);
		lblStudent.setFont(new Font("Dyuthi", Font.BOLD, 22));
		panel_7_2.add(lblStudent);
		
		JLabel label_1_2 = new JLabel(totalStudents);
		label_1_2.setFont(new Font("Dialog", Font.BOLD, 44));
		label_1_2.setBounds(12, 41, 99, 60);
		panel_7_2.add(label_1_2);
		
		JPanel panel_7_3 = new JPanel();
		panel_7_3.setLayout(null);
		panel_7_3.setBackground(new Color(143, 240, 164));
		panel_7_3.setBounds(23, 227, 130, 113);
		panel_2.add(panel_7_3);
		
		JLabel lblAdmin_1 = new JLabel("Course");
		lblAdmin_1.setFont(new Font("Dyuthi", Font.BOLD, 22));
		lblAdmin_1.setBounds(29, 12, 75, 29);
		panel_7_3.add(lblAdmin_1);
		
		JLabel label_1_3 = new JLabel(totalCourse);
		label_1_3.setFont(new Font("Dialog", Font.BOLD, 44));
		label_1_3.setBounds(29, 41, 75, 60);
		panel_7_3.add(label_1_3);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Teacher", null, panel_3, null);
		panel_3.setLayout(null);
		
		
		JLabel lblTab_2_1 = new JLabel("Teacher");
		lblTab_2_1.setBounds(12, 12, 141, 40);
		lblTab_2_1.setFont(new Font("Dyuthi", Font.BOLD, 30));
		panel_3.add(lblTab_2_1);
		
		JButton btnRefresh_1_1_2 = new JButton("Refresh");
		btnRefresh_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teacherDis = new TeachersDisplay();
				teacherDis.setBounds(12, 176, 468, 331);
				teacherDis.setVisible(true);
				panel_3.add(teacherDis);
				teacherDis.setLayout(null);
			}
		});
		btnRefresh_1_1_2.setBackground(new Color(51, 209, 122));
		btnRefresh_1_1_2.setBounds(371, 21, 117, 25);
		panel_3.add(btnRefresh_1_1_2);
		
		JLabel lblSearch_1_1 = new JLabel("Search : ");
		lblSearch_1_1.setFont(new Font("Dyuthi", Font.BOLD, 20));
		lblSearch_1_1.setBounds(57, 64, 73, 20);
		panel_3.add(lblSearch_1_1);
		
		JButton btnAddTeacher = new JButton("Add Teacher");
		btnAddTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherAdd addTeacher = new TeacherAdd();
				addTeacher.setVisible(true);
			}
		});
		btnAddTeacher.setBackground(new Color(53, 132, 228));
		btnAddTeacher.setFont(new Font("Dyuthi", Font.BOLD, 12));
		btnAddTeacher.setBounds(33, 114, 117, 33);
		panel_3.add(btnAddTeacher);
		
		JButton btnEditTeacher = new JButton("Edit Teacher");
		btnEditTeacher.setBackground(new Color(229, 165, 10));
		btnEditTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherEdit editTeacher = new TeacherEdit();
				editTeacher.setVisible(true);
			}
		});
		btnEditTeacher.setFont(new Font("Dyuthi", Font.BOLD, 12));
		btnEditTeacher.setBounds(181, 114, 117, 33);
		panel_3.add(btnEditTeacher);
		
		JButton btnDelTeacher = new JButton("Del Teacher");
		btnDelTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherDelete deleteTeacher = new TeacherDelete();
				deleteTeacher.setVisible(true);
			}
		});
		btnDelTeacher.setBackground(new Color(165, 29, 45));
		btnDelTeacher.setFont(new Font("Dyuthi", Font.BOLD, 12));
		btnDelTeacher.setBounds(319, 114, 109, 33);
		panel_3.add(btnDelTeacher);
		
		teacherDis = new TeachersDisplay();
		teacherDis.setBounds(12, 176, 468, 331);
		teacherDis.setVisible(true);
		panel_3.add(teacherDis);
		teacherDis.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Student", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblTab_2_2 = new JLabel("Student");
		lblTab_2_2.setBounds(12, 12, 137, 40);
		lblTab_2_2.setFont(new Font("Dyuthi", Font.BOLD, 30));
		panel_4.add(lblTab_2_2);
		
		JButton btnRefresh_1_1 = new JButton("Refresh");
		btnRefresh_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentDis = new StudentDisplay();
				studentDis.setBounds(12, 176, 468, 331);
				studentDis.setVisible(true);
				panel_4.add(studentDis);
				studentDis.setLayout(null);
			}
		});
		btnRefresh_1_1.setBounds(356, 21, 117, 25);
		btnRefresh_1_1.setBackground(new Color(51, 209, 122));
		panel_4.add(btnRefresh_1_1);
		
		JLabel lblSearch_1 = new JLabel("Search : ");
		lblSearch_1.setFont(new Font("Dyuthi", Font.BOLD, 20));
		lblSearch_1.setBounds(51, 88, 73, 20);
		panel_4.add(lblSearch_1);
		
//		textField = new JTextField();
//		textField.setFont(new Font("Dyuthi", Font.PLAIN, 12));
//		textField.setColumns(10);
//		textField.setBounds(131, 77, 316, 30);
//		panel_4.add(textField);
		
	

	
		JButton btnAddStudent = new JButton("Add Std");
		btnAddStudent.setBackground(new Color(53, 132, 228));
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentAdd addStd = new StudentAdd();
				addStd.setVisible(true);	
			}
		});
		btnAddStudent.setFont(new Font("Dyuthi", Font.BOLD, 12));
		btnAddStudent.setBounds(55, 120, 94, 32);
		panel_4.add(btnAddStudent);
		
		JButton btnEditStd = new JButton("Edit Std");
		btnEditStd.setBackground(new Color(229, 165, 10));
		btnEditStd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentEdit editStd = new StudentEdit();
				editStd.setVisible(true);
			}
		});
		btnEditStd.setFont(new Font("Dyuthi", Font.BOLD, 12));
		btnEditStd.setBounds(178, 120, 109, 32);
		panel_4.add(btnEditStd);
		
		JButton btnDltStd = new JButton("Dlt Std");
		btnDltStd.setBackground(new Color(165, 29, 45));
		btnDltStd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentDelete dltStudent = new StudentDelete();
				dltStudent.setVisible(true);
			}
		});
		btnDltStd.setFont(new Font("Dyuthi", Font.BOLD, 12));
		btnDltStd.setBounds(319, 120, 99, 32);
		panel_4.add(btnDltStd);
		
		
		
		studentDis = new StudentDisplay();
		studentDis.setBounds(12, 176, 468, 331);
		studentDis.setVisible(true);
		panel_4.add(studentDis);
		studentDis.setLayout(null);
		
		
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Admin", null, panel_5, null);
		panel_5.setLayout(null);
		
		JLabel lblTab_2_2_1 = new JLabel("Admin");
		lblTab_2_2_1.setBounds(24, 12, 102, 30);
		lblTab_2_2_1.setFont(new Font("Dyuthi", Font.BOLD, 30));
		panel_5.add(lblTab_2_2_1);
		
		JButton btnRefresh_1_1_1 = new JButton("Refresh");
		btnRefresh_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminDis = new AdminDisplay();
				adminDis.setBounds(12, 176, 468, 331);
				adminDis.setVisible(true);
				panel_5.add(adminDis);
				adminDis.setLayout(null);
			}
		});
		btnRefresh_1_1_1.setBackground(new Color(51, 209, 122));
		btnRefresh_1_1_1.setBounds(353, 16, 117, 25);
		panel_5.add(btnRefresh_1_1_1);
		
		JButton btnAddStudent_1 = new JButton("Add Admin");
		btnAddStudent_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminAdd addAdmin = new AdminAdd();
				addAdmin.setVisible(true);
			}
		});
		btnAddStudent_1.setFont(new Font("Dyuthi", Font.BOLD, 12));
		btnAddStudent_1.setBackground(new Color(53, 132, 228));
		btnAddStudent_1.setBounds(48, 122, 94, 32);
		panel_5.add(btnAddStudent_1);
		
		JButton btnEditStd_1 = new JButton("Edit Admin");
		btnEditStd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminEdit editAdmin = new AdminEdit();
				editAdmin.setVisible(true);
			}
		});
		btnEditStd_1.setFont(new Font("Dyuthi", Font.BOLD, 12));
		btnEditStd_1.setBackground(new Color(229, 165, 10));
		btnEditStd_1.setBounds(165, 122, 109, 32);
		panel_5.add(btnEditStd_1);
		
		JButton btnDltStd_1 = new JButton("Dlt Admin");
		btnDltStd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminDelete deleteAdmin = new AdminDelete();
				deleteAdmin.setVisible(true);
			}
		});
		btnDltStd_1.setFont(new Font("Dyuthi", Font.BOLD, 12));
		btnDltStd_1.setBackground(new Color(165, 29, 45));
		btnDltStd_1.setBounds(302, 122, 99, 32);
		panel_5.add(btnDltStd_1);
		
		adminDis = new AdminDisplay();
		adminDis.setBounds(12, 176, 468, 331);
		adminDis.setVisible(true);
		panel_5.add(adminDis);
		adminDis.setLayout(null);
		
		
		
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Course", null, panel_6, null);
		panel_6.setLayout(null);
		
		
		JLabel lblTab_2_2_1_1 = new JLabel("Courses");
		lblTab_2_2_1_1.setBounds(173, 12, 102, 30);
		lblTab_2_2_1_1.setFont(new Font("Dyuthi", Font.BOLD, 30));
		panel_6.add(lblTab_2_2_1_1);
		
		JLabel label = new JLabel("");
		label.setBounds(122, 20, 0, 0);
		panel_6.add(label);																							
		
		JLabel lblSearch = new JLabel("Search : ");
		lblSearch.setFont(new Font("Dyuthi", Font.BOLD, 20));
		lblSearch.setBounds(39, 71, 73, 20);
		panel_6.add(lblSearch);
		
		txtCources = new JTextField();
		txtCources.setFont(new Font("Dyuthi", Font.PLAIN, 12));
		txtCources.setBounds(120, 68, 316, 30);
		panel_6.add(txtCources);
		txtCources.setColumns(10);
		
		JButton btnNewButton = new JButton("Add Course");
		btnNewButton.setBackground(new Color(53, 132, 228));
		btnNewButton.setFont(new Font("Dyuthi", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseAdd addCourse = new CourseAdd();
				addCourse.setVisible(true);
			}
		});
		btnNewButton.setBounds(49, 110, 109, 41);
		panel_6.add(btnNewButton);
		
		JButton btnEditCourse = new JButton("Edit Course");
		btnEditCourse.setBackground(new Color(229, 165, 10));
		btnEditCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseUpdate updateC = new CourseUpdate();
				updateC.setVisible(true);
			}
		});
		btnEditCourse.setFont(new Font("Dyuthi", Font.BOLD, 14));
		btnEditCourse.setBounds(197, 110, 102, 41);
		panel_6.add(btnEditCourse);
		
		JButton btnDltCourse = new JButton("Dlt Course");
		btnDltCourse.setBackground(new Color(165, 29, 45));
		btnDltCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseDlt deleteC = new CourseDlt();
				deleteC.setVisible(true);			
				
			}
		});
		
		
		
		btnDltCourse.setFont(new Font("Dyuthi", Font.BOLD, 14));
		btnDltCourse.setBounds(348, 110, 88, 41);
		panel_6.add(btnDltCourse);
		
		coursesDis = new CoursesDisplay();
		coursesDis.setBounds(12, 176, 468, 331);
		coursesDis.setVisible(true);
		panel_6.add(coursesDis);
		coursesDis.setLayout(null);
		
		JButton btnRefresh_1 = new JButton("Refresh");
		btnRefresh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coursesDis = new CoursesDisplay();
				coursesDis.setBounds(12, 176, 468, 331);
				coursesDis.setVisible(true);
				panel_6.add(coursesDis);
				coursesDis.setLayout(null);
			}
		});
		btnRefresh_1.setBackground(new Color(51, 209, 122));
		btnRefresh_1.setBounds(382, 16, 117, 25);
		panel_6.add(btnRefresh_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(119, 118, 123));
		panel.setBounds(0, 0, 161, 582);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnTeacher = new JButton("Teacher");
		btnTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnTeacher.setBounds(35, 149, 91, 25);
		panel.add(btnTeacher);
		
		JButton btnStudent = new JButton("Student");
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnStudent.setBounds(35, 181, 91, 25);
		panel.add(btnStudent);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnAdmin.setBounds(35, 218, 91, 25);
		panel.add(btnAdmin);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				
			}
		});
		btnHome.setBounds(35, 112, 91, 25);
		panel.add(btnHome);
		
		JLabel lblCms = new JLabel("CMS");
		lblCms.setBackground(new Color(249, 240, 107));
		lblCms.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCms.setFont(new Font("Dyuthi", Font.BOLD, 30));
		lblCms.setBounds(35, 12, 71, 35);
		panel.add(lblCms);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLogin lgn = new UserLogin();
				lgn.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setBackground(new Color(165, 29, 45));
		btnLogOut.setForeground(new Color(255, 255, 255));
		btnLogOut.setBounds(14, 505, 117, 25);
		panel.add(btnLogOut);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int totalStds = getTotalCount("Student");
		        int totalA = getTotalCount("Admin");
		        int totalT = getTotalCount("Teacher");
				String totalAdmins = String.valueOf(totalA);
		        String totalTeachers = String.valueOf(totalT);
		        String totalStudents = String.valueOf(totalStds);
		        
		     // Update the JLabels with the new counts
		        label_1.setText(totalAdmins);
		        label_1_1.setText(totalTeachers);
		        label_1_2.setText(totalStudents);
		        label_1_3.setText(totalCourse);

		        // Repaint the frame
		        revalidate();
		        repaint();
		        
			}
		});
		btnRefresh.setBackground(new Color(245, 194, 17));
		btnRefresh.setBounds(14, 457, 117, 25);
		panel.add(btnRefresh);
		
		JButton btnCourse = new JButton("Course");
		btnCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
			}
		});
		btnCourse.setBounds(35, 255, 96, 25);
		panel.add(btnCourse);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setFont(new Font("Dyuthi", Font.BOLD, 18));
		lblUser.setBounds(14, 61, 46, 25);
		panel.add(lblUser);
		
		JLabel lblNewLabel_1 = new JLabel(role);
		lblNewLabel_1.setForeground(new Color(51, 209, 122));
		lblNewLabel_1.setFont(new Font("FreeMono", Font.BOLD, 20));
		lblNewLabel_1.setBounds(61, 59, 88, 25);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(119, 118, 123));
		panel_1.setBounds(701, 0, 163, 582);
		contentPane.add(panel_1);
		
		
		
		if ("Admin".equals(role)) {
		    // students
		    btnAddStudent.setVisible(true);
		    btnEditStd.setVisible(true);
		    btnDltStd.setVisible(true);
		    //teacher
		    btnAddTeacher.setVisible(true);
		    btnEditTeacher.setVisible(true);
		    btnDelTeacher.setVisible(true);
		    //admin
		    btnAddStudent_1.setVisible(true);
		    btnEditStd_1.setVisible(true);
		    btnDltStd_1.setVisible(true);
		    //course
		    btnNewButton.setVisible(true);
		    btnEditCourse.setVisible(true);
		    btnDltCourse.setVisible(true);
		} else {
		    
		    btnAddStudent.setVisible(false);
		    btnEditStd.setVisible(false);
		    btnDltStd.setVisible(false);
		    
		    btnAddTeacher.setVisible(false);
		    btnEditTeacher.setVisible(false);
		    btnDelTeacher.setVisible(false);
		    
		    btnAddStudent_1.setVisible(false);
		    btnEditStd_1.setVisible(false);
		    btnDltStd_1.setVisible(false);
		    
		    btnNewButton.setVisible(false);
		    btnEditCourse.setVisible(false);
		    btnDltCourse.setVisible(false);
		}
		
		
		
	}
}
