package com.example.BTL_KienTrucPhanMem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LopHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ten;
    private String mota;
    private Double hocphi;

    @ManyToOne
    @JoinColumn(name = "mucDoId")
    private MucDo mucDo;

    // Getters and Setters
}

