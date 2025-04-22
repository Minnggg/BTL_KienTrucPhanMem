package com.example.BTL_KienTrucPhanMem.repository;

import com.example.BTL_KienTrucPhanMem.model.NhanVien;
import com.example.BTL_KienTrucPhanMem.model.ThanhVien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThanhVienRepository extends JpaRepository<ThanhVien, Integer> {
}
