package com.example.BTL_KienTrucPhanMem.service;

import com.example.BTL_KienTrucPhanMem.model.HocVien;
import com.example.BTL_KienTrucPhanMem.model.ThanhVien;
import com.example.BTL_KienTrucPhanMem.repository.HocVienRepository;
import com.example.BTL_KienTrucPhanMem.repository.ThanhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HocVienService {

    @Autowired
    private HocVienRepository hvRepo;

    @Autowired
    private ThanhVienRepository tvRepo;


    public void saveHocVien(HocVien hv) {
        ThanhVien savedTV = tvRepo.save(hv.getThanhVien());
        hv.setThanhVien(savedTV);
        hvRepo.save(hv);
    }

    public List<HocVien> getAllHocVien() {
        return hvRepo.findAll();
    }


    public List<HocVien> searchByTen(String kw) {
        return hvRepo.searchByHoTen(kw);
    }

}

