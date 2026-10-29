//Author: Nguyen Thi Thu Loan - Student ID: N24DVCN028
package view;

import model.LibraryItem;
import model.Book;
import model.Magazine;
import model.Thesis;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.time.Year;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ItemFormDialog extends JDialog {

    private JTextField idField;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField yearField;

    private JTextField isbnField;
    private JTextField pagesField;
    private JTextField issueNumberField;
    private JTextField supervisorField;
    private JTextField universityField;

    private JComboBox<String> typeCombo;
    private JPanel specificPanel;
    private CardLayout cardLayout;

    private LibraryItem item;
    private boolean saved = false;

    public ItemFormDialog(Frame parent, LibraryItem item) {
        super(parent, item == null ? "Thêm tài liệu" : "Sửa tài liệu", true);

        this.item = item;

        setSize(480, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        createForm();
        createButtons();

        if (item != null) {
            loadItemData();
        }
    }

    private void createForm() {
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));

        idField = new JTextField();
        titleField = new JTextField();
        authorField = new JTextField();
        yearField = new JTextField();

        typeCombo = new JComboBox<>(
                new String[]{"Sách", "Tạp chí", "Luận văn"}
        );

        formPanel.add(new JLabel("Mã tài liệu:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Loại tài liệu:"));
        formPanel.add(typeCombo);

        formPanel.add(new JLabel("Tên tài liệu:"));
        formPanel.add(titleField);

        formPanel.add(new JLabel("Tác giả:"));
        formPanel.add(authorField);

        formPanel.add(new JLabel("Năm xuất bản:"));
        formPanel.add(yearField);

        cardLayout = new CardLayout();
        specificPanel = new JPanel(cardLayout);

        specificPanel.add(createBookPanel(), "Sách");
        specificPanel.add(createMagazinePanel(), "Tạp chí");
        specificPanel.add(createThesisPanel(), "Luận văn");

        typeCombo.addActionListener(e -> {
            cardLayout.show(specificPanel, (String) typeCombo.getSelectedItem());
        });

        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.add(formPanel, BorderLayout.NORTH);
        centerPanel.add(specificPanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        cardLayout.show(specificPanel, "Sách");
    }

    private JPanel createBookPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Thông tin sách"));

        isbnField = new JTextField();
        pagesField = new JTextField();

        panel.add(new JLabel("ISBN:"));
        panel.add(isbnField);

        panel.add(new JLabel("Số trang:"));
        panel.add(pagesField);

        return panel;
    }

    private JPanel createMagazinePanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Thông tin tạp chí"));

        issueNumberField = new JTextField();

        panel.add(new JLabel("Số phát hành:"));
        panel.add(issueNumberField);

        return panel;
    }

    private JPanel createThesisPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Thông tin luận văn"));

        supervisorField = new JTextField();
        universityField = new JTextField();

        panel.add(new JLabel("Người hướng dẫn:"));
        panel.add(supervisorField);

        panel.add(new JLabel("Trường đại học:"));
        panel.add(universityField);

        return panel;
    }

    private void createButtons() {
        JPanel buttonPanel = new JPanel();

        JButton saveButton = new JButton("Lưu");
        JButton cancelButton = new JButton("Hủy");

        saveButton.addActionListener(e -> saveItem());
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);
        getRootPane().setDefaultButton(saveButton);
    }

    private void saveItem() {
        try {
            String id = idField.getText().trim();
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            int year = Integer.parseInt(yearField.getText().trim());

            if (id.isEmpty() || title.isEmpty() || author.isEmpty()) {
                throw new IllegalArgumentException(
                        "Mã, tên tài liệu và tác giả không được để trống!"
                );
            }

            if (year < 1900 || year > Year.now().getValue()) {
                throw new IllegalArgumentException(
                        "Năm xuất bản phải từ 1900 đến "
                        + Year.now().getValue() + "!"
                );
            }

            String type = (String) typeCombo.getSelectedItem();

            if ("Sách".equals(type)) {
                String isbn = isbnField.getText().trim();
                int pages = Integer.parseInt(pagesField.getText().trim());

                if (!isbn.matches("(\\d{10}|\\d{13})")) {
                    throw new IllegalArgumentException(
                            "ISBN phải gồm đúng 10 hoặc 13 chữ số!"
                    );
                }

                item = new Book(
                        id, title, author, year, true, isbn, pages
                );

            } else if ("Tạp chí".equals(type)) {
                int issueNumber = Integer.parseInt(
                        issueNumberField.getText().trim()
                );

                item = new Magazine(
                        id, title, author, year, true, issueNumber
                );

            } else {
                String supervisor = supervisorField.getText().trim();
                String university = universityField.getText().trim();

                item = new Thesis(
                        id, title, author, year, true,
                        supervisor, university
                );
            }

            saved = true;
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Năm xuất bản, số trang và số phát hành phải là số nguyên!",
                    "Dữ liệu không hợp lệ",
                    JOptionPane.WARNING_MESSAGE
            );
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Dữ liệu không hợp lệ",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void loadItemData() {
        idField.setText(item.getId());
        titleField.setText(item.getTitle());
        authorField.setText(item.getAuthor());
        yearField.setText(String.valueOf(item.getYear()));

        if (item instanceof Book) {
            Book book = (Book) item;
            typeCombo.setSelectedItem("Sách");
            isbnField.setText(book.getIsbn());
            pagesField.setText(String.valueOf(book.getPages()));

        } else if (item instanceof Magazine) {
            Magazine magazine = (Magazine) item;
            typeCombo.setSelectedItem("Tạp chí");
            issueNumberField.setText(
                    String.valueOf(magazine.getIssueNumber())
            );

        } else if (item instanceof Thesis) {
            Thesis thesis = (Thesis) item;
            typeCombo.setSelectedItem("Luận văn");
            supervisorField.setText(thesis.getSupervisor());
            universityField.setText(thesis.getUniversity());
        }
    }

    public LibraryItem getItem() {
        return item;
    }

    public boolean isSaved() {
        return saved;
    }
}
