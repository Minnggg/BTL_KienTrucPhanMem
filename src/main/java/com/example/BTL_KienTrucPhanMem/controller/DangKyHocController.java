package com.example.BTL_KienTrucPhanMem.controller;

import com.example.BTL_KienTrucPhanMem.model.*;
import com.example.BTL_KienTrucPhanMem.repository.HocVienRepository;
import com.example.BTL_KienTrucPhanMem.service.DangKyHocService;
import com.example.BTL_KienTrucPhanMem.service.HocVienService;
import com.example.BTL_KienTrucPhanMem.service.LopHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.List;

@Controller
public class DangKyHocController {
    @Autowired
    HocVienRepository hocVienRepository;

    @Autowired
    private HocVienService hocVienService;

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

    @GetMapping("/chonlop")
    public String chonLop(Model model, @RequestParam("selectedHV") Integer hocVienId) {
        HocVien hocVien = hocVienRepository.findById(hocVienId).orElse(null);

        List<LopHoc> cacLopChuaDangKy = lopHocService.getLopChuaDangKyCuaHocVien(hocVienId);

        model.addAttribute("listLop", cacLopChuaDangKy);
        model.addAttribute("hv", hocVien);
        return "chonlop"; // Giao diện chonlop.html
    }
}

