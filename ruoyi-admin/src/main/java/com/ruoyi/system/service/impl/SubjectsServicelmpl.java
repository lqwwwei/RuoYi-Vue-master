package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.Papers;
import com.ruoyi.system.domain.Subjects;
import com.ruoyi.system.mapper.SubjectsMapper;
import com.ruoyi.system.service.ISubjectsService;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SubjectsServicelmpl implements ISubjectsService {
    @Autowired
    private SubjectsMapper subjectsMapper;

    @Override
    public boolean importSubjects(List<Subjects> subjects, Long paperId) {
        // 设置每个Subjects对象的paperId、type、createdAt和updatedAt
        for (Subjects subject : subjects) {
            subject.setPaperId(paperId);
            subject.setCreatedAt(new Date());
            subject.setUpdatedAt(new Date());
        }
        // 调用Mapper的方法进行批量插入
        int result = subjectsMapper.batchInsertSubjects(subjects);
        return result > 0; // 如果至少有一条记录被插入，则返回true
    }
    @Override
    public List<Subjects> getSbujectsBypaperId(Long paperId) {
        return subjectsMapper.getSbujectsBypaperId(paperId);
    }

    @Override
    public List<Subjects> getSubjectsByPaperIdAndType(Long paperId,Integer subjectType){return subjectsMapper.getSubjectsByPaperIdAndType(paperId,subjectType);}
}
