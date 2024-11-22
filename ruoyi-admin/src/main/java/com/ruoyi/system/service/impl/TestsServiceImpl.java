package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TestsMapper;
import com.ruoyi.system.domain.Tests;
import com.ruoyi.system.service.ITestsService;

/**
 * 考试管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-28
 */
@Service
public class TestsServiceImpl implements ITestsService 
{
    @Autowired
    private TestsMapper testsMapper;

    /**
     * 查询考试管理
     * 
     * @param id 考试管理主键
     * @return 考试管理
     */
    @Override
    public Tests selectTestsById(Long id)
    {
        return testsMapper.selectTestsById(id);
    }

    /**
     * 查询考试管理列表
     * 
     * @param tests 考试管理
     * @return 考试管理
     */
    @Override
    public List<Tests> selectTestsList(Tests tests)
    {
        return testsMapper.selectTestsList(tests);
    }

    /**
     * 新增考试管理
     * 
     * @param tests 考试管理
     * @return 结果
     */
    @Override
    public int insertTests(Tests tests)
    {
        return testsMapper.insertTests(tests);
    }

    /**
     * 修改考试管理
     * 
     * @param tests 考试管理
     * @return 结果
     */
    @Override
    public int updateTests(Tests tests)
    {
        return testsMapper.updateTests(tests);
    }

    /**
     * 批量删除考试管理
     * 
     * @param ids 需要删除的考试管理主键
     * @return 结果
     */
    @Override
    public int deleteTestsByIds(Long[] ids)
    {
        return testsMapper.deleteTestsByIds(ids);
    }

    /**
     * 删除考试管理信息
     * 
     * @param id 考试管理主键
     * @return 结果
     */
    @Override
    public int deleteTestsById(Long id)
    {
        return testsMapper.deleteTestsById(id);
    }
}
