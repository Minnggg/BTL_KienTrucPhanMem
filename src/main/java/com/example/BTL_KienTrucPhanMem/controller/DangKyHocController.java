package com.example.BTL_KienTrucPhanMem.controller;

import com.example.BTL_KienTrucPhanMem.model.DangKyHoc;
import com.example.BTL_KienTrucPhanMem.model.HocVien;
import com.example.BTL_KienTrucPhanMem.model.LopHoc;
import com.example.BTL_KienTrucPhanMem.model.LopHocDangKy;
import com.example.BTL_KienTrucPhanMem.service.DangKyHocService;
import com.example.BTL_KienTrucPhanMem.service.LopHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class DangKyHocController {

    @Autowired
    private LopHocService lopHocService;

    @Autowired
    private DangKyHocService dkhService;

    @PostMapping("/xacnhandkhoc")
    public String showXacNhanPage(Model model,
                                  @RequestParam("hocVienId") int hocVienId,
                                  @RequestParam(value = "lopIds", required = false) List<Integer> lopIds) {

        HocVien hocVien = dkhService.getHocVienById(hocVienId);
        List<LopHoc> cacLopChuaDangKy = lopHocService.getLopChuaDangKyCuaHocVien(hocVienId);

        List<LopHoc> listLop = cacLopChuaDangKy.stream()
                .filter(lop -> lopIds != null && lopIds.contains(lop.getId()))
                .toList();

        double tongTien = listLop.stream().mapToDouble(LopHoc::getHocphi).sum();

        model.addAttribute("hv", hocVien);
        model.addAttribute("listLop", listLop);
        model.addAttribute("tongTien", tongTien);
        model.addAttribute("lopIds", lopIds);
        return "xacnhandkhoc";
    }

    @PostMapping("dang-ky-hoc/xacnhan")
    public String xacNhanDangKy(Model model,
                                @RequestParam("hvId") int hocVienId,
                                @RequestParam("lopIds") List<Integer> lopIds) {

        // Lấy học viên và lớp học đã chọn
        HocVien hocVien = dkhService.getHocVienById(hocVienId);
        List<LopHoc> listLop = lopHocService.getLopHocByIds(lopIds);
        double tongTien = listLop.stream().mapToDouble(LopHoc::getHocphi).sum();

        // 1. Tạo và lưu bản ghi DangKyHoc
        DangKyHoc dangKyHoc = new DangKyHoc();
        dangKyHoc.setThanhVien(hocVien.getThanhVien());
        dangKyHoc.setTongtien(tongTien);
        dangKyHoc.setNgayDK(LocalDate.now());

        dkhService.saveDangKyHoc(dangKyHoc); // CẦN đảm bảo method này tồn tại trong service

        // 2. Tạo và lưu từng bản ghi LopHocDangKy
        for (LopHoc lop : listLop) {
            LopHocDangKy dk = new LopHocDangKy();
            dk.setDangKyHoc(dangKyHoc); // gắn vào bản ghi đã có trong DB
            dk.setLopHoc(lop);
            dkhService.saveLopHocDangKy(dk);
        }

        // Truyền sang view
        model.addAttribute("hv", hocVien);
        model.addAttribute("listLop", listLop);
        model.addAttribute("tongTien", tongTien);

        return "dkhocok";
    }


    @GetMapping("/dkhocok")
    public String dangKyThanhCong() {
        return "dkhocok";
    }
}

