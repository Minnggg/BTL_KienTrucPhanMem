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
    private HocVienRepository hocVienRepository;

    @Autowired
    private ThanhVienRepository thanhVienRepository;


    public void saveHocVien(HocVien hocVien) {
        // Lưu ThanhVien trước
        ThanhVien savedTV = thanhVienRepository.save(hocVien.getThanhVien());
        hocVien.setThanhVien(savedTV);

        // Lưu HocVien
        hocVienRepository.save(hocVien);
    }

    public List<HocVien> getAllHocVien() {
        return hocVienRepository.findAll();
    }


    public List<HocVien> searchByTen(String keyword) {
        return hocVienRepository.searchByHoTen(keyword);
    }

}

