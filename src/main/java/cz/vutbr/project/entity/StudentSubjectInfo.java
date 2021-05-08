package cz.vutbr.project.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "student_subject_info")
public class StudentSubjectInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long user_id;
    @Column
    private Long subject_id;
    @Column
    private Float mark;


}
