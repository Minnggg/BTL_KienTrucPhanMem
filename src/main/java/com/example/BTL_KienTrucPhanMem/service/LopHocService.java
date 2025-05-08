package com.example.BTL_KienTrucPhanMem.service;

import com.example.BTL_KienTrucPhanMem.model.LopHoc;
import com.example.BTL_KienTrucPhanMem.repository.LopHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LopHocService {
    @Autowired
    LopHocRepository lhRepo;

    public List<LopHoc> getLopChuaDangKyCuaHocVien(Integer hvID) {
        return lhRepo.findLopChuaDangKy(hvID);
    }

    public List<LopHoc> getLopHocByIds(List<Integer> ids) {
        return lhRepo.findAllById(ids);
    }
}
