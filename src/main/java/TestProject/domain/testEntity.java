package TestProject.domain;

import javax.persistence.*;

/**
 * Created by Alexandr on 15.05.2016.
 */
@Entity
@Table(name = "`entity !`")
public class testEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NUMBER")
    private int number;

    @Column (name = "YEAR")
    private int year;

    @Column (name = "DESCRIPTION")
    private String description;

    public testEntity(String name, int number, int year) {
        this.name = name;
        this.number = number;
        this.year = year;
        this.description = name + " " + number + " " + year;
    }

    public testEntity(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getNumber(){
        return number;
    }

    public int getYear(){
        return year;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "testEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", year=" + year +
                '}';
    }
}
