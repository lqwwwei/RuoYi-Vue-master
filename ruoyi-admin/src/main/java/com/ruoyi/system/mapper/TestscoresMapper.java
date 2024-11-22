package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.Testscores;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TestscoresMapper {
    public Integer updateTests(Testscores testscores);

    public Integer insert(Testscores testScores);
    public  Testscores findRecode(@Param("testrecordsId") Long testrecordsId, @Param("subjectId") Long subjectId);

    public List<Testscores> getTestscoresById(@Param("testRecodeId") Long testRecodeId);
}
