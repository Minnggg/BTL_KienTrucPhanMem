package com.example.BTL_KienTrucPhanMem.repository;

import com.example.BTL_KienTrucPhanMem.model.HocVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HocVienRepository extends JpaRepository<HocVien, Integer> {
    @Query("SELECT hv FROM HocVien hv WHERE LOWER(hv.thanhVien.hoten) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<HocVien> searchByHoTen(@Param("keyword") String keyword);
}


