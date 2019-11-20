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

    public void setId(String id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return id + " = " + value.toString();
    }
}
