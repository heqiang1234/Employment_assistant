package com.human.service;

import com.human.model.Mapp;
import com.human.model.Photo;
import com.human.model.eChartsData;

import java.util.List;

public interface eChartsDataService {

    public List<eChartsData> ShowPlaceTotal();

    public List<eChartsData> ShowEmploymentTotal();

    public List<eChartsData> ShowMeetDayTotal();

    public List<Mapp> ShowMap();

    public List<Photo> ShowPhoto();


}
