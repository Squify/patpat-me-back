package com.devlp.patpatme.dto.animal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UpdateAnimalDto {

    private String name;
    private String birthday;
    private String fk_id_type;
    private String fk_id_gender;
    private String fk_id_breed;
    private List<String> tempers;
}
