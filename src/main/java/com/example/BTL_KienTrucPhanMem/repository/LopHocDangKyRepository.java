package com.example.BTL_KienTrucPhanMem.repository;

import com.example.BTL_KienTrucPhanMem.model.LopHocDangKy;
import com.example.BTL_KienTrucPhanMem.model.ThanhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LopHocDangKyRepository extends JpaRepository<LopHocDangKy, Integer> {
    @Query("SELECT l FROM LopHocDangKy l WHERE l.dangKyHoc.thanhVien.id = :hvId")
    List<LopHocDangKy> findByHocVienId(@Param("hvId") Integer hvId);
}
