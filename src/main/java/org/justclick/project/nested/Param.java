package org.justclick.project.nested;

public class Param extends KeyValue{

    public Param(String name, String value) {
        super(name, value);
    }
    
    @Override
    public String toString() {
        return "Header " + super.toString();
    }
}
