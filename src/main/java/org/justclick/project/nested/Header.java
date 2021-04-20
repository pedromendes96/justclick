package org.justclick.project.nested;

public class Header extends KeyValue{

    public Header(String name, String value) {
        super(name, value);
    }
    
    @Override
    public String toString() {
        return "Header " + super.toString();
    }
}
