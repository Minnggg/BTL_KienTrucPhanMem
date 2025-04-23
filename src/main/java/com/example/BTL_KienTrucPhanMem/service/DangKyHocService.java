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
    private HocVienRepository hocVienRepo;

    @Autowired
    private LopHocDangKyRepository lopHocDangKyRepo;

    @Autowired
    private DangKyHocRepository dangKyHocRepository;

    public HocVien getHocVienById(int id) {
        return hocVienRepo.findById(id).orElse(null);
    }

    public List<LopHocDangKy> getLopHocDangKyByHocVienId(int hvId) {
        return lopHocDangKyRepo.findByHocVienId(hvId);
    }

    public void saveLopHocDangKy(LopHocDangKy dangKy) {
        lopHocDangKyRepo.save(dangKy);
    }

    @Transactional
    public void saveDangKyHoc(DangKyHoc dangKyHoc) {
        dangKyHocRepository.save(dangKyHoc);
    }
}

