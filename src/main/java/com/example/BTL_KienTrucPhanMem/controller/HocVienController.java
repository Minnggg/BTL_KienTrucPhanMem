package com.example.BTL_KienTrucPhanMem.controller;

import com.example.BTL_KienTrucPhanMem.model.HocVien;
import com.example.BTL_KienTrucPhanMem.model.ThanhVien;
import com.example.BTL_KienTrucPhanMem.service.HocVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HocVienController {

    @Autowired
    private HocVienService hocVienService;

    @GetMapping("/hocvien/chon")
    public String chonHocVien(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<HocVien> hocViens;
        if (keyword != null && !keyword.isEmpty()) {
            hocViens = hocVienService.searchByTen(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            hocViens = hocVienService.getAllHocVien();
        }
        model.addAttribute("hocViens", hocViens);
        return "chonhv";
    }


    @GetMapping("/hocvien/themmoi")
    public String hienThiFormThem(Model model) {
        HocVien hocVien = new HocVien();
        hocVien.setThanhVien(new ThanhVien()); // khởi tạo tránh NullPointer
        model.addAttribute("hocVien", hocVien);
        return "themmoihv";
    }


    @PostMapping("/hocvien/them-moi")
    public String addHocVien(@ModelAttribute HocVien hocVien) {
        hocVienService.saveHocVien(hocVien);
        return "redirect:/admin/dang-ky-hoc/chonhv";
    }

    @GetMapping("/admin/dang-ky-hoc/chonhv")
    public String dangKyHoc(Model model) {
        List<HocVien> hocViens = hocVienService.getAllHocVien();
        model.addAttribute("hocViens", hocViens);
        return "chonhv";
    }

}


