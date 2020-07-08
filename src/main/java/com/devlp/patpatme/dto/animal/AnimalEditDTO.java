package com.devlp.patpatme.dto.animal;

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
public class AnimalEditDTO {

    private Integer id;
    private Integer owner;
    private String birthday;
    private String type;
    private String gender;
    private String breed;
    private List<String> tempers = new ArrayList<>();
    private String image_path;
}
