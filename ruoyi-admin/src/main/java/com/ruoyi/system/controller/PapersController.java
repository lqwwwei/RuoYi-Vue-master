package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Papers;
import com.ruoyi.system.domain.Subjects;
import com.ruoyi.system.domain.Tests;
import com.ruoyi.system.service.IPapersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "papers-controller")
@RestController
@RequestMapping("/system/papers")
public class PapersController extends BaseController {

    @Autowired
    private IPapersService papersService;

    @Anonymous
    @ApiOperation("获取试卷列表")
    @GetMapping("/list")
    public AjaxResult getPapers(Papers papers){
          List<Papers> list = papersService.selectPapersList(papers);
       return success(list);
    }

    @Anonymous
    @ApiOperation("删除试卷信息")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return toAjax(papersService.deletePaperById(id));
    }

    @Anonymous
    @ApiOperation("获取试卷详情")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(papersService.selectPaperByid(id));
    }

}
