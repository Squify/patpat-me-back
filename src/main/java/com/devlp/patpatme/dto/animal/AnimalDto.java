package com.devlp.patpatme.dto.animal;
import com.devlp.patpatme.entity.AnimalGenderEntity;
import com.devlp.patpatme.entity.AnimalTypeEntity;
import com.devlp.patpatme.entity.BreedEntity;
import com.devlp.patpatme.entity.TemperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AnimalDto {

        private Integer id;
        private String name;
        private String birthday;
        private AnimalTypeEntity type;
        private AnimalGenderEntity gender;
        private BreedEntity breed;
        private List<TemperEntity> tempers;
        private String image_path;

}
