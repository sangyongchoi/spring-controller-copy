package flow.dto;

public class TestDto2 {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "TestDto1{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
