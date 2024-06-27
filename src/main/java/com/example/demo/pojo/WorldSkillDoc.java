package com.example.demo.pojo;

import lombok.Data;

import java.util.List;

@Data
public class WorldSkillDoc {

    private Integer id;
    private String file_name;
    private String full_name;
    private Integer is_parent;
    private List<WorldSkillDoc> children;

}
