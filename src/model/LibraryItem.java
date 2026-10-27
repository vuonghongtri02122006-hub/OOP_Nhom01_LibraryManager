package model;

import java.time.Year;

public abstract class LibraryItem {
    private String id;
    private String title;
    private String author;
    private int year;
    private boolean available;

    public LibraryItem(String id, String title, String author, int year, boolean available) {
        setId(id);
        setTitle(title);
        setAuthor(author);
        setYear(year);
        this.available = available;
    }

    // Hai phương thức trừu tượng theo yêu cầu đề tài
    public abstract int getLoanDays();
    public abstract String getTypeName();

    // Phương thức tính tiền phạt (phục vụ phần BONUS)
    public abstract double calculateFine(int overdueDays);

    // Getters & Setters đóng gói kiểm tra hợp lệ
    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã tài liệu không được để trống!");
        }
        this.id = id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Tiêu đề không được để trống!");
        }
        this.title = title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Tác giả không được để trống!");
        }
        this.author = author.trim();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        int currentYear = Year.now().getValue();
        if (year < 1900 || year > currentYear) {
            throw new IllegalArgumentException("Năm xuất bản phải từ 1900 đến " + currentYear);
        }
        this.year = year;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s (%d) [%s]", id, title, author, year, isAvailable() ? "Có sẵn" : "Đang mượn");
    }
}