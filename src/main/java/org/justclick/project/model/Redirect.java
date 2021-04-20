package org.justclick.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Redirect{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable=false, unique=true)
    public String key;
    
    @Column(nullable=false)
    public String redirectTo;

    @Column(nullable=false)
    public Integer counter;

    @Override
    public String toString() {
        return "Redirect [id=" + id + ", path=" + key + ", to=" + redirectTo + "]";
    }
}