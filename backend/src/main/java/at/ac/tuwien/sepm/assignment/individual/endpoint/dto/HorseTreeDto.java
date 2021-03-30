package at.ac.tuwien.sepm.assignment.individual.endpoint.dto;

import java.util.Objects;

public class HorseTreeDto {
    private Long id;
    private String name;
    private String description;
    private String birthDay;
    private String sex;
    private Long favoriteSportId;
    private String favoriteSportName;
    private HorseTreeDto mother;
    private HorseTreeDto father;

    public HorseTreeDto() {
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

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
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

    public HorseTreeDto getMother() {
        return mother;
    }

    public void setMother(HorseTreeDto mother) {
        this.mother = mother;
    }

    public HorseTreeDto getFather() {
        return father;
    }

    public void setFather(HorseTreeDto father) {
        this.father = father;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HorseTreeDto that = (HorseTreeDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(birthDay, that.birthDay) && Objects.equals(sex, that.sex) && Objects.equals(favoriteSportId, that.favoriteSportId) && Objects.equals(favoriteSportName, that.favoriteSportName) && Objects.equals(mother, that.mother) && Objects.equals(father, that.father);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, birthDay, sex, favoriteSportId, favoriteSportName, mother, father);
    }

    @Override
    public String toString() {
        return "HorseTreeDto{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", birthDay='" + birthDay + '\'' +
            ", sex='" + sex + '\'' +
            ", favoriteSportId=" + favoriteSportId +
            ", favoriteSportName='" + favoriteSportName + '\'' +
            '}';
    }
}
