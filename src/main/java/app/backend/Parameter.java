package app.backend;

public class Parameter {

    public Parameter(int id, Type type, String label, String description, int address, int size, int value) {
        this.id = id;
        this.type = type;
        this.label = label;
        this.description = description;
        this.address = address;
        this.size = size;
        this.value = value;
    }

    enum Type
    {
        BASIC,
        ADVANCED
    };

    private int id;
    private Type type;
    private String label;
    private String description;
    private int address;
    private int size;
    private int value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Parameter() {
        this.id = 0;
        this.type = Type.BASIC;
        this.label = "";
        this.description = "";
        this.address = 0;
        this.size = 0;
        this.value = 0;
    }
}
