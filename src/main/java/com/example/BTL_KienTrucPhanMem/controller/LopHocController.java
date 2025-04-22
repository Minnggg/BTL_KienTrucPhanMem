package com.example.BTL_KienTrucPhanMem.controller;

import com.example.BTL_KienTrucPhanMem.model.HocVien;
import com.example.BTL_KienTrucPhanMem.model.LopHoc;
import com.example.BTL_KienTrucPhanMem.repository.HVRepository;
import com.example.BTL_KienTrucPhanMem.service.LopHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LopHocController {
    @Autowired
    HVRepository hocVienRepository;

    @Autowired
    LopHocService lopHocService;

    @GetMapping("/chonlop")
    public String chonLop(Model model, @RequestParam("selectedHV") Integer hocVienId) {
        HocVien hocVien = hocVienRepository.findById(hocVienId).orElse(null);

        List<LopHoc> cacLopChuaDangKy = lopHocService.getLopChuaDangKyCuaHocVien(hocVienId);

        model.addAttribute("listLop", cacLopChuaDangKy);
        model.addAttribute("hv", hocVien);
        return "chonlop"; // Giao diện chonlop.html
    }

}
