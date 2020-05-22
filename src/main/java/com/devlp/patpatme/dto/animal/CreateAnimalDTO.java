package com.devlp.patpatme.dto.animal;

import com.devlp.patpatme.entity.AnimalGenderEntity;
import com.devlp.patpatme.entity.AnimalTypeEntity;
import com.devlp.patpatme.entity.BreedEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CreateAnimalDTO {

    private String name;
    private String birthday;
    private String type;
    private String gender;
    private String breed;
    private List<String> tempers = new ArrayList<>();

}