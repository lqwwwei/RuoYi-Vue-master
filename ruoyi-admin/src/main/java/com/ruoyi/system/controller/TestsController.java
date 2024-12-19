package com.ruoyi.system.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Tests;
import com.ruoyi.system.service.ITestsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考试管理Controller
 * 
 * @author ruoyi
 * @date 2024-10-28
 */
@Api(tags = "tests-controller")
@RestController
@RequestMapping("/system/tests")
public class TestsController extends BaseController
{
    @Autowired
    private ITestsService testsService;

    @Autowired
    private ISysUserService userServicel;

    /**
     * 查询考试管理列表
     */
    @ApiOperation("获取考试列表")
    @PreAuthorize("@ss.hasPermi('system:tests:list')")
    @GetMapping("/list")
    public TableDataInfo list(Tests tests)
    {
        startPage();
        List<Tests> list = testsService.selectTestsList(tests);
        return getDataTable(list);
    }

    /**
     * 导出考试管理列表
     */
    @ApiOperation("考试导出")
    @PreAuthorize("@ss.hasPermi('system:tests:export')")
    @Log(title = "考试管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Tests tests)
    {
        List<Tests> list = testsService.selectTestsList(tests);
        ExcelUtil<Tests> util = new ExcelUtil<Tests>(Tests.class);
        util.exportExcel(response, list, "考试管理数据");
    }

    /**
     * 获取考试管理详细信息
     */
    @ApiOperation("获取考试详情")
    @PreAuthorize("@ss.hasPermi('system:tests:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(testsService.selectTestsById(id));
    }

    /**
     * 新增考试管理
     */
    @ApiOperation("新增考试")
    @PreAuthorize("@ss.hasPermi('system:tests:add')")
    @Log(title = "考试管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Tests tests) {
        if (testsService.isExist(tests.getTestName())) {
            return AjaxResult.error("考试名称不能重复，添加失败");
        }
        tests.setStatus(0);
        tests.setCreatedAt(new Date());
            // 获取所有用户
            List<SysUser> userList = userServicel.selectUserList(new SysUser());
            // 为每个用户创建考试记录
            for (SysUser user : userList) {
                Tests testsRecord = new Tests();
                testsRecord.setId(tests.getId());
                testsRecord.setStartTime(tests.getStartTime());
                testsRecord.setEndTime(tests.getEndTime());
                testsRecord.setTestName(tests.getTestName());
                testsRecord.setPaperId(tests.getPaperId());
                testsRecord.setUserId(user.getUserId());
                testsRecord.setStatus(0);
                testsRecord.setCreatedAt(tests.getCreatedAt());
                testsRecord.setUpdatedAt(tests.getUpdatedAt());
                testsService.insertTests(testsRecord);
            }
            return AjaxResult.success("考试添加成功");

    }


    /**
     * 修改考试管理
     */
    @ApiOperation("修改考试信息")
    @PreAuthorize("@ss.hasPermi('system:tests:edit')")
    @Log(title = "考试管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Tests tests)
    {
        Date now = new Date();

            if (tests.getEndTime().before(now)) {
                tests.setStatus(2);
            } else
                tests.setStatus(1);    //更新时对时间做验证
        Long testsId=tests.getId();
        Tests test=testsService.selectTestsById(testsId);
        testsService.updateTestsByName(tests, test.getTestName());   //更新与考试名称相同的记录

        return toAjax(testsService.updateTests(tests));  //根据id更新该条记录
    }

    @ApiOperation("保存考试记录")
    @PreAuthorize("@ss.hasPermi('system:tests:edit')")
    @Log(title = "考试管理", businessType = BusinessType.UPDATE)
    @PutMapping("/save")
    public AjaxResult save(@RequestBody Tests tests)
    {
        return toAjax(testsService.updateTests(tests));
    }

    /**
     * 删除考试管理
     */
    @ApiOperation("删除考试")
    @PreAuthorize("@ss.hasPermi('system:tests:remove')")
    @Log(title = "考试管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(testsService.deleteTestsByIds(ids));
    }
}
