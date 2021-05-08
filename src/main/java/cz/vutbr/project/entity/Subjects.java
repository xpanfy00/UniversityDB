package cz.vutbr.project.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Subjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column(name = "teacher_id")
    private Long teacherId;


    public Subjects() {

    }
}
