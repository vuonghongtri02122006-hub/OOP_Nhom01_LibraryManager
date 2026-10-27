package model;

public class Thesis extends LibraryItem {
    private String supervisor;
    private String university;

    public Thesis(String id, String title, String author, int year, boolean available, String supervisor, String university) {
        super(id, title, author, year, available);
        setSupervisor(supervisor);
        setUniversity(university);
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        if (supervisor == null || supervisor.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên người hướng dẫn không được để trống!");
        }
        this.supervisor = supervisor.trim();
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        if (university == null || university.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên trường không được để trống!");
        }
        this.university = university.trim();
    }

    @Override
    public int getLoanDays() {
        return 3; // Luận văn mượn tối đa 3 ngày
    }

    @Override
    public String getTypeName() {
        return "Luận văn";
    }

    @Override
    public double calculateFine(int overdueDays) {
        return overdueDays * 20000.0;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | GVHD: %s | Trường: %s", supervisor, university);
    }
}