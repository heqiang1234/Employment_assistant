package com.human.service.impl;

import com.human.dao.eChartsDataDao;
import com.human.model.Mapp;
import com.human.model.Photo;
import com.human.model.eChartsData;
import com.human.service.eChartsDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class eChartsDataServiceImpl implements eChartsDataService {

    @Resource
    private eChartsDataDao eChartsDataDao;

    @Override
    public List<eChartsData> ShowPlaceTotal() {
        return  eChartsDataDao.ShowPlaceTotal();
    }

    @Override
    public List<eChartsData> ShowEmploymentTotal() {
        return eChartsDataDao.ShowEmploymentTotal();
    }

    @Override
    public List<eChartsData> ShowMeetDayTotal() {
        return eChartsDataDao.ShowMeetDayTotal();
    }

    @Override
    public List<Mapp> ShowMap() {
        return eChartsDataDao.ShowMap();
    }

    @Override
    public List<Photo> ShowPhoto() {
        return eChartsDataDao.ShowPhoto();
    }


}
