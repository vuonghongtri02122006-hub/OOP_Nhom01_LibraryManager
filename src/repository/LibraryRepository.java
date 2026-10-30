
package repository;

import java.util.List;
import model.LibraryItem;

public interface LibraryRepository {

    // Thêm tài liệu
    void add(LibraryItem item);

    // Xóa tài liệu theo mã
    boolean remove(String id);

    // Tìm tài liệu theo mã
    LibraryItem findById(String id);

    // Lấy danh sách tất cả tài liệu
    List<LibraryItem> findAll();

    // Tìm kiếm theo từ khóa
    List<LibraryItem> search(String keyword);
}
