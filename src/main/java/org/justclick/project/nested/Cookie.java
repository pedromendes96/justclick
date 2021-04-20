package org.justclick.project.nested;

public class Cookie extends KeyValue{

    public Cookie(String name, String value) {
        super(name, value);
    }

    @Override
    public String toString() {
        return "Cookie " + super.toString();
    }
}
