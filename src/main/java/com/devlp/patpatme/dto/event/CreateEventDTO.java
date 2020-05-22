package com.devlp.patpatme.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CreateEventDTO {

    private String name;
    private String description;
    private String localisation;
    private String date;
    private String type;
}
