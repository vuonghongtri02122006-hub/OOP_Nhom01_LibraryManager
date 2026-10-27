package model;

public class Magazine extends LibraryItem {
    private int issueNumber;

    public Magazine(String id, String title, String author, int year, boolean available, int issueNumber) {
        super(id, title, author, year, available);
        setIssueNumber(issueNumber);
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        if (issueNumber <= 0) {
            throw new IllegalArgumentException("Số phát hành phải lớn hơn 0!");
        }
        this.issueNumber = issueNumber;
    }

    @Override
    public int getLoanDays() {
        return 7; // Tạp chí mượn tối đa 7 ngày
    }

    @Override
    public String getTypeName() {
        return "Tạp chí";
    }

    @Override
    public double calculateFine(int overdueDays) {
        return overdueDays * 10000.0;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Số PH: %d", issueNumber);
    }
}