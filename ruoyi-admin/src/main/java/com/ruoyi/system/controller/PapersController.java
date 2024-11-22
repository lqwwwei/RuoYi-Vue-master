package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Papers;
import com.ruoyi.system.domain.Subjects;
import com.ruoyi.system.domain.Tests;
import com.ruoyi.system.service.IPapersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/papers")
public class PapersController extends BaseController {

    @Autowired
    private IPapersService papersService;

    @Anonymous
    @GetMapping("/list")
    public AjaxResult getPapers(Papers papers){
          List<Papers> list = papersService.selectPapersList(papers);
       return success(list);
    }

    @Anonymous
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return toAjax(papersService.deletePaperById(id));
    }
}
