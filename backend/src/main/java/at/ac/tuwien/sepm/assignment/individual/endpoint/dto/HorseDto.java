package at.ac.tuwien.sepm.assignment.individual.endpoint.dto;

import java.util.Objects;

public class HorseDto {
    private Long id;
    private String name;
    private String description;
    private String birthDay;
    private String sex;
    private Long favoriteSportId;
    private String favoriteSportName;
    private Long motherId;
    private Long fatherId;
    private String motherName;
    private String fatherName;

    public HorseDto() {
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

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HorseDto horseDto = (HorseDto) o;
        return Objects.equals(id, horseDto.id) &&
            Objects.equals(name, horseDto.name) &&
            Objects.equals(description, horseDto.description) &&
            Objects.equals(birthDay, horseDto.birthDay) &&
            Objects.equals(sex, horseDto.sex) &&
            Objects.equals(favoriteSportId, horseDto.favoriteSportId) &&
            Objects.equals(motherId, horseDto.motherId) &&
            Objects.equals(fatherId, horseDto.fatherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, birthDay, sex, favoriteSportId, motherId, fatherId);
    }

    protected String fieldsString() {
        return String.format("id=%d, name='%s', description='%s', birthDay=%s, sex='%s', favoriteSportId=%d, motherId=%d, fatherId=%d",
            id, name, description, birthDay, sex, favoriteSportId, motherId, fatherId);
    }

    @Override
    public String toString() {
        return "SportDto{ " + fieldsString() + " }";
    }
}
