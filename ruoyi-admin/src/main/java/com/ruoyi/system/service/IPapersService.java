package com.ruoyi.system.service;
import com.ruoyi.system.domain.Papers;

import java.util.List;

public interface IPapersService {

    public List<Papers> selectPapersList(Papers papers);

    public int deletePaperById(Long id);

    public int selectPaperByid(Long id);

    public boolean isExist(String name);
}
