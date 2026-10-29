
//Author: Nguyen Thi Thu Loan - Student ID: N24DVCN028
package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.LibraryItem;
import repository.LibraryRepository;

public class ItemTableModel extends AbstractTableModel {

    private final String[] columnNames = {
        "Mã tài liệu",
        "Tên tài liệu",
        "Tác giả",
        "Năm xuất bản",
        "Loại tài liệu",
        "Tình trạng"
    };

    private final LibraryRepository repo;

    public ItemTableModel(LibraryRepository repo) {
        this.repo = repo;
    }

    @Override
    public int getRowCount() {
        return repo.findAll().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<LibraryItem> items = repo.findAll();
        LibraryItem item = items.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return item.getId();
            case 1:
                return item.getTitle();
            case 2:
                return item.getAuthor();
            case 3:
                return item.getYear();
            case 4:
                return item.getTypeName();
            case 5:
                return item.isAvailable() ? "Có sẵn" : "Đang được mượn";
            default:
                return "";
        }
    }

    public void refreshData() {
        fireTableDataChanged();
    }
}
