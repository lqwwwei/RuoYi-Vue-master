package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.Papers;
import com.ruoyi.system.mapper.PapersMapper;
import com.ruoyi.system.service.IPapersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PapersServicelmpl implements IPapersService {
    @Autowired
    private PapersMapper papersMapper;

    @Override
    public  List<Papers> selectPapersList(Papers papers){
        return papersMapper.selectPapersList(papers);
    }

    @Override
    public  int deletePaperById(Long id){return papersMapper.deletePaperById(id);}
}