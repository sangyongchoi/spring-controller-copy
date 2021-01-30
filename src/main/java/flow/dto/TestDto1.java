package flow.dto;

public class TestDto1 {
    String name;
    String password;

    public TestDto1() {
    }

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
