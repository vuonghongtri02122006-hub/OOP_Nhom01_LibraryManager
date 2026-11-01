package model;

import java.time.LocalDate;

public class LoanRecord {
    private LibraryItem item;
    private String borrowerName;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public LoanRecord(LibraryItem item, String borrowerName, LocalDate borrowDate) {
        if (item == null) {
            throw new IllegalArgumentException("Tài liệu không được null!");
        }
        if (borrowerName == null || borrowerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên người mượn không được để trống!");
        }
        this.item = item;
        this.borrowerName = borrowerName.trim();
        this.borrowDate = (borrowDate != null) ? borrowDate : LocalDate.now();
        this.dueDate = this.borrowDate.plusDays(item.getLoanDays());
        this.returnDate = null;
    }

    public LibraryItem getItem() {
        return item;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isOverdue(LocalDate currentDate) {
        if (returnDate != null) {
            return returnDate.isAfter(dueDate);
        }
        return currentDate.isAfter(dueDate);
    }

    public int getOverdueDays(LocalDate currentDate) {
        LocalDate checkDate = (returnDate != null) ? returnDate : currentDate;
        if (checkDate.isAfter(dueDate)) {
            return (int) java.time.temporal.ChronoUnit.DAYS.between(dueDate, checkDate);
        }
        return 0;
    }

    public double calculateFine(LocalDate currentDate) {
        int overdueDays = getOverdueDays(currentDate);
        return item.calculateFine(overdueDays);
    }
}