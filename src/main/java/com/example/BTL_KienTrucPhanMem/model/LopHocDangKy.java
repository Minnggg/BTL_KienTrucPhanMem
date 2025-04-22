package com.example.BTL_KienTrucPhanMem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LopHocDangKy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dangKyHocId")
    private DangKyHoc dangKyHoc;

    @ManyToOne
    @JoinColumn(name = "lopHocId")
    private LopHoc lopHoc;
}

