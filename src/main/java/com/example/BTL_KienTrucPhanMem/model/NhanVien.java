package com.example.BTL_KienTrucPhanMem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String vitri;  // "ADMIN" hoặc "GIAOVIEN"
    private String username;
    private String password;
    private String chuyenMon;
    private String trinhDo;

    @OneToOne
    @JoinColumn(name = "thanhVienId")
    private ThanhVien thanhVien;
}

