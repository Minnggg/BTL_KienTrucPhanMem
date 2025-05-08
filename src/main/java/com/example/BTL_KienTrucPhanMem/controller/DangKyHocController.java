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
    private HocVienService hvSv;

    @Autowired
    private LopHocService lhSv;

    @Autowired
    private DangKyHocService dkhSv;

    @PostMapping("/xacnhandkhoc")
    public String showXacNhanPage(Model model,
                                  @RequestParam("hocVienId") int hvID,
                                  @RequestParam(value = "lopIds", required = false) List<Integer> lopID) {

        HocVien hocVien = dkhSv.getHocVienById(hvID);
        List<LopHoc> cacLopChuaDangKy = lhSv.getLopChuaDangKyCuaHocVien(hvID);

        List<LopHoc> listLop = cacLopChuaDangKy.stream()
                .filter(lop -> lopID != null && lopID.contains(lop.getId()))
                .toList();

        double tongTien = listLop.stream().mapToDouble(LopHoc::getHocphi).sum();

        model.addAttribute("hv", hocVien);
        model.addAttribute("listLop", listLop);
        model.addAttribute("tongTien", tongTien);
        model.addAttribute("lopIds", lopID);
        return "xacnhandkhoc";
    }

    @PostMapping("dang-ky-hoc/xacnhan")
    public String xacNhanDangKy(Model model,
                                @RequestParam("hvId") int hocVienId,
                                @RequestParam("lopIds") List<Integer> lopIds) {

        HocVien hocVien = dkhSv.getHocVienById(hocVienId);
        List<LopHoc> listLop = lhSv.getLopHocByIds(lopIds);
        double tongTien = listLop.stream().mapToDouble(LopHoc::getHocphi).sum();

        DangKyHoc dangKyHoc = new DangKyHoc();
        dangKyHoc.setThanhVien(hocVien.getThanhVien());
        dangKyHoc.setTongtien(tongTien);
        dangKyHoc.setNgayDK(LocalDate.now());

        dkhSv.saveDangKyHoc(dangKyHoc);

        for (LopHoc lop : listLop) {
            LopHocDangKy dk = new LopHocDangKy();
            dk.setDangKyHoc(dangKyHoc);
            dk.setLopHoc(lop);
            dkhSv.saveLopHocDangKy(dk);
        }

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
            hocViens = hvSv.searchByTen(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            hocViens = hvSv.getAllHocVien();
        }
        model.addAttribute("hocViens", hocViens);
        return "chonhv";
    }


    @GetMapping("/hocvien/themmoi")
    public String hienThiFormThem(Model model) {
        HocVien hocVien = new HocVien();
        hocVien.setThanhVien(new ThanhVien());
        model.addAttribute("hocVien", hocVien);
        return "themmoihv";
    }


    @PostMapping("/hocvien/them-moi")
    public String addHocVien(@ModelAttribute HocVien hv) {
        hvSv.saveHocVien(hv);
        return "redirect:/admin/dang-ky-hoc/chonhv";
    }

    @GetMapping("/admin/dang-ky-hoc/chonhv")
    public String dangKyHoc(Model model) {
        List<HocVien> hocViens = hvSv.getAllHocVien();
        model.addAttribute("hocViens", hocViens);
        return "chonhv";
    }

    @GetMapping("/chonlop")
    public String chonLop(Model model, @RequestParam("selectedHV") Integer hvID) {
        HocVien hocVien = hocVienRepository.findById(hvID).orElse(null);

        List<LopHoc> cacLopChuaDangKy = lhSv.getLopChuaDangKyCuaHocVien(hvID);

        model.addAttribute("listLop", cacLopChuaDangKy);
        model.addAttribute("hv", hocVien);
        return "chonlop";
    }
}

