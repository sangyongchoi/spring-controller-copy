package flow.dto;

public class TestDto1 {
    String name;
    int password;

    public TestDto1() {
    }

    public String getName() {
        return name;
    }

    public int getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "TestDto1{" +
                "name='" + name + '\'' +
                ", password=" + password +
                '}';
    }
}
