package com.example.demo.pojo;

import lombok.Data;

@Data
public class StudyArticle {

    private Integer id;
    private String title;
    private String content;
    private String author;
    private String type;
    private String outer_url;
    private String video_url;
}
