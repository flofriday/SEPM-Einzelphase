package at.ac.tuwien.sepm.assignment.individual.entity;

import java.time.LocalDate;
import java.util.Objects;

public class HorseTree {
    private Long id;
    private String name;
    private String description;
    private LocalDate birthDay;
    private Sex sex;
    private Long favoriteSportId;
    private String favoriteSportName;
    private HorseTree mother;
    private HorseTree father;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Long getFavoriteSportId() {
        return favoriteSportId;
    }

    public void setFavoriteSportId(Long favoriteSportId) {
        this.favoriteSportId = favoriteSportId;
    }

    public String getFavoriteSportName() {
        return favoriteSportName;
    }

    public void setFavoriteSportName(String favoriteSportName) {
        this.favoriteSportName = favoriteSportName;
    }

    public HorseTree getMother() {
        return mother;
    }

    public void setMother(HorseTree mother) {
        this.mother = mother;
    }

    public HorseTree getFather() {
        return father;
    }

    public void setFather(HorseTree father) {
        this.father = father;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HorseTree horseTree = (HorseTree) o;
        return id.equals(horseTree.id) && name.equals(horseTree.name) && Objects.equals(description, horseTree.description) && birthDay.equals(horseTree.birthDay) && sex == horseTree.sex && Objects.equals(favoriteSportId, horseTree.favoriteSportId) && Objects.equals(favoriteSportName, horseTree.favoriteSportName) && Objects.equals(mother, horseTree.mother) && Objects.equals(father, horseTree.father);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, birthDay, sex, favoriteSportId, favoriteSportName, mother, father);
    }

    @Override
    public String toString() {
        return "HorseTree{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", birthDay=" + birthDay +
            ", sex=" + sex +
            ", favoriteSportId=" + favoriteSportId +
            ", favoriteSportName='" + favoriteSportName + '\'' +
            '}';
    }
}
