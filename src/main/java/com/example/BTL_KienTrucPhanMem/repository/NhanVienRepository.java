package com.example.BTL_KienTrucPhanMem.repository;

import com.example.BTL_KienTrucPhanMem.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    Optional<NhanVien> findByUsername(String username);
    List<NhanVien> findByVitri(String vitri);
}
