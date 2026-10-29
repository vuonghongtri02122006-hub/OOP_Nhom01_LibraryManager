//Author: Nguyen Thi Thu Loan - Student ID: N24DVCN028
package view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AboutDialog extends JDialog {

    public AboutDialog(java.awt.Frame parent) {
        super(parent, "Giới thiệu phần mềm", true);

        setSize(420, 280);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel(
                "QUẢN LÝ THƯ VIỆN",
                SwingConstants.CENTER
        );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel infoLabel = new JLabel(
                "<html><div style='text-align:center;'>"
                + "Phần mềm quản lý sách, tạp chí và luận văn.<br><br>"
                + "Chức năng: Quản lý tài liệu, mượn trả và thống kê.<br><br>"
                + "Môn học: Lập trình hướng đối tượng - INT1332<br>"
                + "Trường: PTIT<br>"
                + "Nhóm thực hiện: Nhóm 01"
                + "</div></html>",
                SwingConstants.CENTER
        );

        JButton closeButton = new JButton("Đóng");
        closeButton.addActionListener(e -> dispose());

        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(
                20, 15, 10, 15
        ));

        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(infoLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);

        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        getRootPane().setDefaultButton(closeButton);
    }
}
