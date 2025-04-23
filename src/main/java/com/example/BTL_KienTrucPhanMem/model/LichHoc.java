package com.example.BTL_KienTrucPhanMem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LichHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ten;
    private String mota;

    @ManyToOne
    @JoinColumn(name = "ngayHocId")
    private NgayHoc ngayHoc;

    @ManyToOne
    @JoinColumn(name = "phongHocId")
    private PhongHoc phongHoc;

    @ManyToOne
    @JoinColumn(name = "dangKyDayId")
    private DangKyDay dangKyDay;

    @ManyToOne
    @JoinColumn(name = "kipHocId")
    private KipHoc kipHoc;
}

