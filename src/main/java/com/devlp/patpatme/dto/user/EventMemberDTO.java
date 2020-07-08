package com.devlp.patpatme.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EventMemberDTO {

    private Integer id;
    private String pseudo;
    private String profile_pic_path;
}
