package com.example.demo.mapper;

import com.example.demo.pojo.TrainLog;
import com.example.demo.pojo.WorldSkillDoc;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorldSkillDocMapper {

    List<WorldSkillDoc> getAllDoc();
    void setDoc(WorldSkillDoc worldSkillDoc);
}
