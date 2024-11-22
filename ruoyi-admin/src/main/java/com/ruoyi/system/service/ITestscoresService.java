package com.ruoyi.system.service;


import com.ruoyi.system.domain.Subjects;
import com.ruoyi.system.domain.Testscores;

import java.util.List;

public interface ITestscoresService {
    public void updateTests(Testscores testscores);

    public  Double calculateAndSaveScore(Testscores testscores);

    public List<Testscores> getTestscoresById(Long testRecodeId);
}
