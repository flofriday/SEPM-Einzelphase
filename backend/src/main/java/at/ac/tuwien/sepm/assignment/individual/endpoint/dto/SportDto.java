package at.ac.tuwien.sepm.assignment.individual.endpoint.dto;

import java.util.Objects;

public class SportDto {
    private Long id;
    private String name;

    public SportDto() {
    }

    public SportDto(String name) {
        this.name = name;
    }

    public SportDto(Long id, String name) {
        this(name);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportDto sportDto = (SportDto) o;
        return Objects.equals(id, sportDto.id) &&
            Objects.equals(name, sportDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    private String fieldsString() {
        return "id = " + id + ", name='" + name + '\'';
    }

    @Override
    public String toString() {
        return "SportDto{ " + fieldsString() + " }";
    }
}
