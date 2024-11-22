package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Tests;

/**
 * 考试管理Service接口
 * 
 * @author ruoyi
 * @date 2024-10-28
 */
public interface ITestsService 
{
    /**
     * 查询考试管理
     * 
     * @param id 考试管理主键
     * @return 考试管理
     */
    public Tests selectTestsById(Long id);

    /**
     * 查询考试管理列表
     * 
     * @param tests 考试管理
     * @return 考试管理集合
     */
    public List<Tests> selectTestsList(Tests tests);

    /**
     * 新增考试管理
     * 
     * @param tests 考试管理
     * @return 结果
     */
    public int insertTests(Tests tests);

    /**
     * 修改考试管理
     * 
     * @param tests 考试管理
     * @return 结果
     */
    public int updateTests(Tests tests);

    /**
     * 批量删除考试管理
     * 
     * @param ids 需要删除的考试管理主键集合
     * @return 结果
     */
    public int deleteTestsByIds(Long[] ids);

    /**
     * 删除考试管理信息
     * 
     * @param id 考试管理主键
     * @return 结果
     */
    public int deleteTestsById(Long id);
}
