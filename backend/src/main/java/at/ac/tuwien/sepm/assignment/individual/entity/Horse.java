package at.ac.tuwien.sepm.assignment.individual.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Horse {
    private Long id;
    private String name;
    private String description;
    private LocalDate birthDay;
    private Sex sex;
    private Long favoriteSportId;
    private Long motherId;
    private Long fatherId;

    public Horse() {
    }

    public Horse(String name) {
        this.name = name;
    }

    public Horse(Long id, String name) {
        this(name);
        this.id = id;
    }

    public Horse(Long id, String name, String description) {
        this(id, name);
        this.description = description;
    }

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

    public Long getMotherId() {
        return motherId;
    }

    public void setMotherId(Long motherId) {
        this.motherId = motherId;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long fatherId) {
        this.fatherId = fatherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horse horse = (Horse) o;
        return Objects.equals(id, horse.id) &&
            Objects.equals(name, horse.name) &&
            Objects.equals(description, horse.description) &&
            Objects.equals(birthDay, horse.birthDay) &&
            Objects.equals(sex, horse.sex) &&
            Objects.equals(favoriteSportId, horse.favoriteSportId) &&
            Objects.equals(motherId, horse.motherId) &&
            Objects.equals(fatherId, horse.fatherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, birthDay, sex, favoriteSportId, motherId, fatherId);
    }

    protected String fieldsString() {
        return String.format("id=%d, name='%s', description='%s', birthDay=%s sex=%s, favoriteSportId=%d, motherId=%d, fatherId=%d",
            id, name, description, birthDay.toString(), sex.toString(), favoriteSportId, motherId, fatherId);
    }

    @Override
    public String toString() {
        return "Horse{ " + fieldsString() +" }";
    }
}
