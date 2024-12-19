package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Tests;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 考试管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-28
 */
@Mapper
public interface TestsMapper 
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
     * 删除考试管理
     * 
     * @param id 考试管理主键
     * @return 结果
     */
    public int deleteTestsById(Long id);

    /**
     * 批量删除考试管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTestsByIds(Long[] ids);

    public int isExist(@Param("name")String name);

    public int updateTestsByName(@Param("tests") Tests tests,@Param("name") String name);
}
