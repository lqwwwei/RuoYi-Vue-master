package com.ruoyi.system.controller;


import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Testscores;
import com.ruoyi.system.service.ITestscoresService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "testscores-controller")
@RestController
@RequestMapping("/system/Testscores")
public class TestscoresController extends BaseController {
    @Autowired
    private ITestscoresService testscoresService;

    @ApiOperation("单题作答")
    @Anonymous
    @PostMapping("/getScore")
    public AjaxResult updateScore(@RequestBody Testscores testScores) {
        testscoresService.calculateAndSaveScore(testScores);
        return success();
    }

    @ApiOperation("获取作答情况")
    @Anonymous
    @GetMapping(value = "/{testRecodeId}")
    public AjaxResult getRecode(@PathVariable("testRecodeId") Long testRecodeId){
            return  success(testscoresService.getTestscoresById(testRecodeId));
    }
}
