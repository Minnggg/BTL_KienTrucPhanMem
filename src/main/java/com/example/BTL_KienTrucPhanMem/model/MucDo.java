package com.example.BTL_KienTrucPhanMem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MucDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ten;
    private String mota;

    @ManyToOne
    @JoinColumn(name = "chuongTrinhHocId")
    private ChuongTrinhHoc chuongTrinhHoc;
}

