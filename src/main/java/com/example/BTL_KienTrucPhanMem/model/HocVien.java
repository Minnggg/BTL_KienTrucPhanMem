package com.example.BTL_KienTrucPhanMem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class HocVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer thanhVienId;

    @OneToOne
    @JoinColumn(name = "thanhVienId")
    private ThanhVien thanhVien;
}

