package entity;

public class Subject {

    public Subject() {
    }

    public Subject(Long id, String subName) {
        this.id = id;
        this.subName = subName;
    }

    private Long id;

    private String subName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", subName='" + subName + '\'' +
                '}';
    }
}
