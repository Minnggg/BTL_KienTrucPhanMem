package com.example.BTL_KienTrucPhanMem.repository;

import com.example.BTL_KienTrucPhanMem.model.LopHoc;
import com.example.BTL_KienTrucPhanMem.model.ThanhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LopHocRepository extends JpaRepository<LopHoc, Integer> {
    @Query("SELECT l FROM LopHoc l WHERE l.id NOT IN (" +
            "SELECT ldk.lopHoc.id FROM LopHocDangKy ldk WHERE ldk.dangKyHoc.thanhVien.id = :hocVienId)")
    List<LopHoc> findLopChuaDangKy(@Param("hocVienId") Integer hocVienId);

}
