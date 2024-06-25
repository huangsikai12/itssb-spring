package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainLog {

    private Integer id;
    private String file_name;
    private String content;
    private String user_name;
    private Date date;
}
