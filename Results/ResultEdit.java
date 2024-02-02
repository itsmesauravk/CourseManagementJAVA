package Swing.Results;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class ResultEdit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField module1;
	private JTextField module2;
	private JTextField module3;
	private JTextField mark1;
	private JTextField mark2;
	private JTextField mark3;
	private JTextField percent;
	private JTextField result;
	private JTextField id;
	private JTextField level;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultEdit frame = new ResultEdit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//adding result
	private void editResult(int std_id,int lvl, String module1,int mark1,  String module2,int mark2, String module3,  int mark3, int percent, String result) {
	    String url = "jdbc:mysql://localhost:3306/CMS";
	    String dbUsername = "root";
	    String dbPassword = "";

	    try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
	         PreparedStatement preparedStatement = connection.prepareStatement(
	        		 "UPDATE results SET std_level = ?, module_1 = ?, mark_1 = ?, module_2 = ?, mark_2 = ?, module_3 = ?, mark_3 = ?, percentage = ?, result = ? WHERE std_id = ?")) {

	        preparedStatement.setInt(1, lvl);
	        preparedStatement.setString(2, module1);
	        preparedStatement.setInt(3, mark1);
	        preparedStatement.setString(4, module2);
	        preparedStatement.setInt(5, mark2);
	        preparedStatement.setString(6, module3);
	        preparedStatement.setInt(7, mark3);
	        preparedStatement.setInt(8, percent);
	        preparedStatement.setString(9, result);
	        preparedStatement.setInt(10, std_id);

	        preparedStatement.executeUpdate();

	        JOptionPane.showMessageDialog(null, "Result Successfully Updated");

	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error occurred while Updating Result.");
	    }
	}
	/**
	 * Create the frame.
	 */
	public ResultEdit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		module1 = new JTextField();
		module1.setBounds(63, 110, 114, 35);
		contentPane.add(module1);
		module1.setColumns(10);
		
		module2 = new JTextField();
		module2.setColumns(10);
		module2.setBounds(209, 110, 114, 35);
		contentPane.add(module2);
		
		module3 = new JTextField();
		module3.setColumns(10);
		module3.setBounds(343, 110, 114, 35);
		contentPane.add(module3);
		
		mark1 = new JTextField();
		mark1.setColumns(10);
		mark1.setBounds(70, 193, 107, 35);
		contentPane.add(mark1);
		
		mark2 = new JTextField();
		mark2.setColumns(10);
		mark2.setBounds(209, 193, 114, 35);
		contentPane.add(mark2);
		
		mark3 = new JTextField();
		mark3.setColumns(10);
		mark3.setBounds(343, 193, 114, 35);
		contentPane.add(mark3);
		
		percent = new JTextField();
		percent.setBounds(137, 268, 100, 35);
		contentPane.add(percent);
		percent.setColumns(10);
		
		result = new JTextField();
		result.setColumns(10);
		result.setBounds(343, 268, 89, 35);
		contentPane.add(result);
		
		JButton btnAdd = new JButton("Edit");
		btnAdd.setBackground(new Color(38, 162, 105));
		btnAdd.setFont(new Font("Dyuthi", Font.BOLD, 20));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String iD = id.getText();
				String lvl = level.getText();
				String module11 = module1.getText();
				String module12 = module2.getText();
				String module13 = module3.getText();
				String mark11 = mark1.getText();
				String mark12 = mark2.getText();
				String mark13 = mark3.getText();
				String per = percent.getText();
				String res = result.getText();
				if(!(iD.isEmpty()& lvl.isEmpty() & module11.isEmpty() & module12.isEmpty() & module13.isEmpty() &
					mark11.isEmpty() & mark12.isEmpty() & mark13.isEmpty())) {
					int stdId = Integer.parseInt(iD);
					int stdLvl = Integer.parseInt(lvl);
				    int mark1Value = Integer.parseInt(mark11);
				    int mark2Value = Integer.parseInt(mark12);
				    int mark3Value = Integer.parseInt(mark13);
				    int perc =Integer.parseInt(per);
				    editResult( stdId,stdLvl,module11,mark1Value,module12,mark2Value,module13, mark3Value,perc,res);
					
				}else {
					JOptionPane.showMessageDialog(null,"Inputs cannot be empty!!");
					
				}
				}
		});
		btnAdd.setBounds(127, 333, 117, 44);
		contentPane.add(btnAdd);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setFont(new Font("Dyuthi", Font.BOLD, 20));
		btnReturn.setBackground(new Color(229, 165, 10));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnReturn.setBounds(280, 333, 117, 44);
		contentPane.add(btnReturn);
		
		id = new JTextField();
		id.setBounds(176, 37, 70, 35);
		contentPane.add(id);
		id.setColumns(10);
		
		level = new JTextField();
		level.setColumns(10);
		level.setBounds(362, 37, 70, 35);
		contentPane.add(level);
		
		JLabel lblPercent_2 = new JLabel("Percent");
		lblPercent_2.setForeground(new Color(97, 53, 131));
		lblPercent_2.setFont(new Font("Dyuthi", Font.BOLD, 20));
		lblPercent_2.setBounds(52, 273, 82, 26);
		contentPane.add(lblPercent_2);
		
		JLabel lblPercent_1_1 = new JLabel("Result");
		lblPercent_1_1.setForeground(new Color(230, 97, 0));
		lblPercent_1_1.setFont(new Font("Dyuthi", Font.BOLD, 20));
		lblPercent_1_1.setBounds(271, 278, 70, 20);
		contentPane.add(lblPercent_1_1);
		
		JLabel lblMark_3 = new JLabel("Mark_1");
		lblMark_3.setForeground(new Color(198, 70, 0));
		lblMark_3.setFont(new Font("Dyuthi", Font.BOLD, 20));
		lblMark_3.setBounds(83, 170, 82, 26);
		contentPane.add(lblMark_3);
		
		JLabel lblMark_5 = new JLabel("Mark_2");
		lblMark_5.setForeground(new Color(26, 95, 180));
		lblMark_5.setFont(new Font("Dyuthi", Font.BOLD, 20));
		lblMark_5.setBounds(226, 170, 82, 26);
		contentPane.add(lblMark_5);
		
		JLabel lblMark_3_1 = new JLabel("Mark_3");
		lblMark_3_1.setFont(new Font("Dyuthi", Font.BOLD, 20));
		lblMark_3_1.setBounds(362, 170, 82, 26);
		contentPane.add(lblMark_3_1);
		
		JLabel lblModule_2 = new JLabel("Module_1");
		lblModule_2.setForeground(new Color(198, 70, 0));
		lblModule_2.setFont(new Font("Dyuthi", Font.BOLD, 20));
		lblModule_2.setBounds(83, 84, 82, 31);
		contentPane.add(lblModule_2);
		
		JLabel lblModule_5 = new JLabel("Module_2");
		lblModule_5.setForeground(new Color(26, 95, 180));
		lblModule_5.setFont(new Font("Dyuthi", Font.BOLD, 20));
		lblModule_5.setBounds(226, 84, 82, 31);
		contentPane.add(lblModule_5);
		
		JLabel lblModule_2_1 = new JLabel("Module_3");
		lblModule_2_1.setFont(new Font("Dyuthi", Font.BOLD, 20));
		lblModule_2_1.setBounds(362, 84, 82, 31);
		contentPane.add(lblModule_2_1);
		
		JLabel lblStdId = new JLabel("Std Id:");
		lblStdId.setForeground(new Color(99, 69, 44));
		lblStdId.setFont(new Font("Dyuthi", Font.BOLD, 20));
		lblStdId.setBounds(94, 40, 64, 31);
		contentPane.add(lblStdId);
		
		JLabel lblLevel = new JLabel("Level :");
		lblLevel.setForeground(new Color(165, 29, 45));
		lblLevel.setFont(new Font("Dyuthi", Font.BOLD, 20));
		lblLevel.setBounds(291, 37, 69, 25);
		contentPane.add(lblLevel);
		
		JLabel lblUpdateMarks = new JLabel("Update Marks");
		lblUpdateMarks.setForeground(new Color(129, 61, 156));
		lblUpdateMarks.setFont(new Font("Dyuthi", Font.BOLD, 24));
		lblUpdateMarks.setBounds(209, 0, 165, 41);
		contentPane.add(lblUpdateMarks);
	}
}
