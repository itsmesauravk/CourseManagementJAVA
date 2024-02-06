// ResultDisplay.java

package Swing.Results;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import java.awt.BorderLayout;
import java.sql.*;

public class ResultDisplay extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;

    public ResultDisplay() {
//        setLayout(new BorderLayout());
        setLayout(null);

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

        JScrollPane scrollPane = new JScrollPane();
        // Set the bounds for the JScrollPane explicitly
        scrollPane.setBounds(0, 0, 465, 311);
        add(scrollPane);

        table = new JTable(model);
        scrollPane.setViewportView(table);
        table.setRowSelectionAllowed(false);

        TableColumnModel columnModel = table.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(120);
        columnModel.getColumn(2).setPreferredWidth(70); 
        columnModel.getColumn(3).setPreferredWidth(70);
        columnModel.getColumn(4).setPreferredWidth(50);
        columnModel.getColumn(6).setPreferredWidth(50);
        columnModel.getColumn(8).setPreferredWidth(50);
        columnModel.getColumn(10).setPreferredWidth(50);
        columnModel.getColumn(11).setPreferredWidth(50);
        columnModel.getColumn(12).setPreferredWidth(70);
        
        
        

        table.setShowGrid(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);

        table.setRowHeight(30);

        JTableHeader header = table.getTableHeader();
        header.setVisible(true);
        table.setIntercellSpacing(new java.awt.Dimension(7, 7));
    }
}