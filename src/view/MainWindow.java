
//Author: Nguyen Thi Thu Loan - Student ID: N24DVCN028
package view;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class MainWindow extends BaseWindow {

    private JTable table;
    private DefaultTableModel tableModel;

    public MainWindow() {
        super("Library Manager", 1000, 600);
    }

    @Override
    protected void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("QUẢN LÝ THƯ VIỆN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        String[] columns = {
            "Mã tài liệu",
            "Tên tài liệu",
            "Tác giả",
            "Năm xuất bản",
            "Loại tài liệu",
            "Tình trạng"
        };

        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        setStatus("Sẵn sàng quản lý tài liệu");
    }

    @Override
    protected void showAbout() {
        new AboutDialog(this).setVisible(true);
    }
}
