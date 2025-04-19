package lib;

public class Child {
    private String name;
    private String idNumber;

    public Child(String name, String idNumber) {
        this.name = name;
        this.idNumber = idNumber;
    }

    // Getters might be needed for other purposes
    public String getName() {
        return name;
    }

    public String getIdNumber() {
        return idNumber;
    }
}