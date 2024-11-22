package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.service.ISysUserService;
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
    @PreAuthorize("@ss.hasPermi('system:tests:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(testsService.selectTestsById(id));
    }

    /**
     * 新增考试管理
     */
    @PreAuthorize("@ss.hasPermi('system:tests:add')")
    @Log(title = "考试管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Tests tests) {
        // 插入考试信息
        int result = testsService.insertTests(tests);

        if (result > 0) {
            // 获取所有用户
            List<SysUser> userList = userServicel.selectUserList(new SysUser());

            // 为每个用户创建考试记录
            for (SysUser user : userList) {
                Tests testsRecord = new Tests();
                testsRecord.setId(tests.getId()); // 假设插入考试后能立即获取到ID
                testsRecord.setStartTime(tests.getStartTime());
                testsRecord.setEndTime(tests.getEndTime());
                testsRecord.setTestName(tests.getTestName());
                testsRecord.setPaperId(tests.getPaperId());
                testsRecord.setUserId(user.getUserId());
                testsRecord.setStatus(0); // 或其他初始状态
                testsRecord.setCreatedAt(tests.getCreatedAt());
                testsRecord.setUpdatedAt(tests.getUpdatedAt());
                testsService.insertTests(testsRecord);
            }

            return AjaxResult.success("考试添加成功");
        } else {
            return AjaxResult.error("考试添加失败");
        }
    }


    /**
     * 修改考试管理
     */
    @PreAuthorize("@ss.hasPermi('system:tests:edit')")
    @Log(title = "考试管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Tests tests)
    {
        return toAjax(testsService.updateTests(tests));
    }

    /**
     * 删除考试管理
     */
    @PreAuthorize("@ss.hasPermi('system:tests:remove')")
    @Log(title = "考试管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(testsService.deleteTestsByIds(ids));
    }
}
