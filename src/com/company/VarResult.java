// Peter Fröberg, pefr7147@student.su.se
// Douglas Hammarstam, doha6991@student.su.se
package com.company;


public class VarResult {
    private String id;
    private Double value;

    public VarResult(String id, Double value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return id + " = " + value.toString();
    }
}
