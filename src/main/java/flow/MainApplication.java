package flow;

public class MainApplication {
    public void init(){
        String path = this.getClass().getResource(".").getPath();
        System.out.println(path);
    }
}
