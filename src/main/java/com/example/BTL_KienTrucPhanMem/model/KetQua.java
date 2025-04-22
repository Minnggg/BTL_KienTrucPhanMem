package com.example.BTL_KienTrucPhanMem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class KetQua {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ten;
    private Double diem;

    @ManyToOne
    @JoinColumn(name = "lopHocDangKyId")
    private LopHocDangKy lopHocDangKy;
}

