package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.Papers;
import com.ruoyi.system.domain.Subjects;
/**
 * 考试管理Service接口
 *
 * @author ruoyi
 * @date 2024-10-28
 */
public interface ISubjectsService {
    /**
     * 导入试题
     *
     * @param subjects 试题集合
     * @return 结果
     */
    public boolean importSubjects(List<Subjects> subjects,Long paperId);


    public List<Subjects> getSbujectsBypaperId(Long paperId);

    public List<Subjects> getSubjectsByPaperIdAndType(Long paperId,Integer subjectType);
}