package com.example.BTL_KienTrucPhanMem.service;

import com.example.BTL_KienTrucPhanMem.model.DangKyHoc;
import com.example.BTL_KienTrucPhanMem.model.HocVien;
import com.example.BTL_KienTrucPhanMem.model.LopHocDangKy;
import com.example.BTL_KienTrucPhanMem.repository.DangKyHocRepository;
import com.example.BTL_KienTrucPhanMem.repository.HocVienRepository;
import com.example.BTL_KienTrucPhanMem.repository.LopHocDangKyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DangKyHocService {

    @Autowired
    private HocVienRepository hvRepo;

    @Autowired
    private LopHocDangKyRepository lhdkRepo;

    @Autowired
    private DangKyHocRepository dkhRepo;

    public HocVien getHocVienById(int id) {
        return hvRepo.findById(id).orElse(null);
    }

    public void saveLopHocDangKy(LopHocDangKy dk) {
        lhdkRepo.save(dk);
    }

    @Transactional
    public void saveDangKyHoc(DangKyHoc dk) {
        dkhRepo.save(dk);
    }
}

