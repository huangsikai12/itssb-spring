package com.example.demo.mapper;

import com.example.demo.pojo.StudyArticle;
import com.example.demo.pojo.WorldSkillDoc;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface StudyArticleMapper {

    List<StudyArticle> getArticleByType(String type);
}
