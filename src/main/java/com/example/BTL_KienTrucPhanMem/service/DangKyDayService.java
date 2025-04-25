package com.example.BTL_KienTrucPhanMem.service;

import com.example.BTL_KienTrucPhanMem.model.DangKyDay;
import com.example.BTL_KienTrucPhanMem.model.LopHoc;
import com.example.BTL_KienTrucPhanMem.repository.DangKyDayRepository;
import com.example.BTL_KienTrucPhanMem.repository.LopHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DangKyDayService {

    @Autowired
    private DangKyDayRepository dkdRepo;

    @Autowired
    private LopHocRepository lopHocRepository;

    public List<DangKyDay> getLopHocDaDangKy(Integer thanhVienId) {
        return dkdRepo.findByThanhVienId(thanhVienId);
    }

    public List<LopHoc> getLopHocChuaDangKy(Integer thanhVienId) {
        return lopHocRepository.findLopHocChuaDangKyByThanhVienId(thanhVienId);
    }

    public DangKyDay saveDKD(DangKyDay dk) {
        return dkdRepo.save(dk);
    }

    public void deleteDKD(Integer dkdId) {
        dkdRepo.deleteById(dkdId);
    }
}
