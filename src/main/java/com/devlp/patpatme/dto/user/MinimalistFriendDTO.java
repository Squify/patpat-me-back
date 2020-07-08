package com.devlp.patpatme.dto.user;

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
public class MinimalistFriendDTO {

    private Integer id;
    private String pseudo;
    private String profile_pic_path;
    private String firstname;
    private String lastname;
    private boolean display_real_name;
    private List<Integer> friends = new ArrayList<>();
}
