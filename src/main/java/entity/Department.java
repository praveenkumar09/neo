package entity;

public class Department {

    public Department() {
    }

    public Department(Long id, String depName) {
        this.id = id;
        this.depName = depName;
    }

    private Long id;

    private String depName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", depName='" + depName + '\'' +
                '}';
    }
}
