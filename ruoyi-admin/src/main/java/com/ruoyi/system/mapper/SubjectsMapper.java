package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Subjects;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

/**
 * 试题管理Mapper接口
 *
 * @author ruoyi
 * @date 2024-10-28
 */
@Mapper
public interface SubjectsMapper {
    /**
     * 批量新增试题
     *
     * @param subjects 试题集合
     * @return 影响的行数
     */
   public int batchInsertSubjects(@Param("subjects") List<Subjects> subjects);
   public List<Subjects> getSbujectsBypaperId(@Param("paperId") Long paperId);

    public  Subjects findBySubjectId(@Param("id") Long id);

    public List<Subjects> getSubjectsByPaperIdAndType(@Param("paperId") Long paperId,@Param("subjectType") Integer subjectType);
}