
package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.LibraryItem;

public abstract class InMemoryLibraryRepository<items>
        implements LibraryRepository {

    private final Map<String, LibraryItem> items =
            new HashMap<>();

    @Override
    public void add(LibraryItem item) {
        if (item == null) {
            throw new IllegalArgumentException(
                    "Tai lieu khong duoc null!");
        }

        String id = item.getId();

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Ma tai lieu khong duoc de trong!");
        }

        id = id.trim();

        if (items.containsKey(id)) {
            throw new IllegalArgumentException(
                    "Ma tai lieu da ton tai: " + id);
        }

        items.put(id, item);
    }

    @Override
    public boolean remove(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        return items.remove(id.trim()) != null;
    }

    @Override
    public LibraryItem findById(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }

        return items.get(id.trim());
    }

    @Override
    public List<LibraryItem> findAll() {
        return new ArrayList<>(items.values());
    }

    @Override
    public List<LibraryItem> search(String keyword) {
        List<LibraryItem> result = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            result.addAll(items.values());
            return result;
        }

        String key = keyword.trim().toLowerCase();

        for (LibraryItem item : items.values()) {
            String id = safe(item.getId());
            String title = safe(item.getTitle());
            String author = safe(item.getAuthor());

            if (id.contains(key)
                    || title.contains(key)
                    || author.contains(key)) {
                result.add(item);
            }
        }

        return result;
    }

    private String safe(String value) {
        return value == null
                ? ""
                : value.toLowerCase();
    }

    // Số lượng tài liệu hiện có
    public int size() {
        return items.size();
    }

    // Kiểm tra thư viện có tài liệu hay không
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
