package com.example.BTL_KienTrucPhanMem.repository;

import com.example.BTL_KienTrucPhanMem.model.DangKyDay;
import com.example.BTL_KienTrucPhanMem.model.DangKyHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DangKyDayRepository extends JpaRepository<DangKyDay, Integer> {
    List<DangKyDay> findByThanhVienId(Integer thanhVienId);
}