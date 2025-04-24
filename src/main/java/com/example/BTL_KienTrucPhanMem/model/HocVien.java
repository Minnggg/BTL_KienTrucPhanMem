package com.example.BTL_KienTrucPhanMem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class HocVien {
    @Id
    private Integer thanhVienId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "thanhVienId")
    @MapsId
    private ThanhVien thanhVien;
}

