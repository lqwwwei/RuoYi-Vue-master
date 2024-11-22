package com.ruoyi.system.controller;


import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Testscores;
import com.ruoyi.system.service.ITestscoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/system/Testscores")
public class TestscoresController extends BaseController {
    @Autowired
    private ITestscoresService testscoresService;

    @Anonymous
    @PostMapping("/getScore")
    public AjaxResult updateScore(@RequestBody Testscores testScores) {
        return success(testscoresService.calculateAndSaveScore(testScores));
    }

    @Anonymous
    @GetMapping(value = "/{testRecodeId}")
    public AjaxResult getRecode(@PathVariable("testRecodeId") Long testRecodeId){
            return  success(testscoresService.getTestscoresById(testRecodeId));
    }
}
