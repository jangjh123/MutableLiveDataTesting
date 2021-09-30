package jiho.pub.mutablelivedatatesting;

public class Pack {
    private int id;
    private String name;

    public Pack(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String setMyName(String name) {
        this.name = name;

        return name;
    }
}
