package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Papers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface PapersMapper {
    public int insertPaper(Papers paper);

    public List<Papers> selectPapersList(Papers papers);

    public int deletePaperById(Long id);

    public int selectPaperByid(Long id);

    public int isExist(String name);
}
