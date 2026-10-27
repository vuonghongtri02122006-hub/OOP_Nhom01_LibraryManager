package model;

public class Book extends LibraryItem {
    private String isbn;
    private int pages;

    public Book(String id, String title, String author, int year, boolean available, String isbn, int pages) {
        super(id, title, author, year, available);
        setIsbn(isbn);
        setPages(pages);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if (isbn == null || !isbn.matches("\\d{10}|\\d{13}")) {
            throw new IllegalArgumentException("Mã ISBN phải đúng 10 hoặc 13 chữ số!");
        }
        this.isbn = isbn;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Số trang phải lớn hơn 0!");
        }
        this.pages = pages;
    }

    @Override
    public int getLoanDays() {
        return 14; // Sách mượn tối đa 14 ngày
    }

    @Override
    public String getTypeName() {
        return "Sách";
    }

    @Override
    public double calculateFine(int overdueDays) {
        return overdueDays * 5000.0;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | ISBN: %s | Số trang: %d", isbn, pages);
    }
}