package com.example.BTL_KienTrucPhanMem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class DangKyHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate ngayDK;
    private Double tongtien;

    @ManyToOne
    @JoinColumn(name = "thanhVienId")
    private ThanhVien thanhVien;
}

