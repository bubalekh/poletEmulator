package app.backend.models;

public class Parameter {

    public Parameter(int id, Type type, String label, String description, String address, String size, String value) {
        this.id = id;
        this.type = type;
        this.label = label;
        this.description = description;
        this.address = address;
        this.size = size;
        this.value = value;
    }

    public enum Type
    {
        BASIC,
        ADVANCED
    }

    private int id;
    private Type type;
    private String label;
    private String description;
    private String address;
    private String size;
    private String value;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public Parameter() {
        this.id = 0;
        this.type = Type.BASIC;
        this.label = "";
        this.description = "";
        this.address = "";
        this.size = "";
        this.value = "";
    }
}
