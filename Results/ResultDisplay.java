package Swing.Results;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.*;
//
public class ResultDisplay extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;

    public ResultDisplay() {
//        setLayout(null);
    	setLayout(new BorderLayout());

        // Getting the data
        DefaultTableModel model = new DefaultTableModel();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS", "root", "");
            Statement stmt = con.createStatement();
            String query = "SELECT r.std_id, u.firstname, u.surname, u.email,u.std_course,r.std_level, r.module_1, r.mark_1,r.module_2, r.mark_2,r.module_3, r.mark_3, r.percentage, r.result FROM users u JOIN results r ON u.id=r.std_id";
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            int columnCount = rsmd.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(rsmd.getColumnName(i));
            }

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Instantiate the JTable after fetching data
        table = new JTable(model);

        // Create JScrollPane with the initialized JTable
//        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        // Set the bounds for the JScrollPane explicitly
        scrollPane.setBounds(0, 0, 511, 310);

        // Add the JScrollPane to the panel
        add(scrollPane, BorderLayout.CENTER);
        
//        TableColumnModel columnModel = table.getColumnModel();
//
//        columnModel.getColumn(0).setPreferredWidth(120);
//        columnModel.getColumn(1).setPreferredWidth(120);
//        columnModel.getColumn(2).setPreferredWidth(120); 
//        columnModel.getColumn(3).setPreferredWidth(120);
//        columnModel.getColumn(4).setPreferredWidth(120);
//        columnModel.getColumn(6).setPreferredWidth(120);
//        columnModel.getColumn(8).setPreferredWidth(120);
//        columnModel.getColumn(10).setPreferredWidth(120);
//        columnModel.getColumn(11).setPreferredWidth(120);
//        columnModel.getColumn(12).setPreferredWidth(120);


        table.setShowGrid(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);

        table.setRowHeight(30);
 
        JTableHeader header = table.getTableHeader();
        header.setVisible(true);
        table.setIntercellSpacing(new java.awt.Dimension(7, 7));

        customizeTableAppearance();
    }

    private void customizeTableAppearance() {
        table.setGridColor(Color.BLACK);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(25);
        table.setRowMargin(5);
        table.setIntercellSpacing(new Dimension(10, 10));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setShowGrid(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Result Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.getContentPane().add(new ResultDisplay());
        frame.setVisible(true);
    }
}




//////




