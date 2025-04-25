package com.example.BTL_KienTrucPhanMem.controller;

import com.example.BTL_KienTrucPhanMem.model.DangKyDay;
import com.example.BTL_KienTrucPhanMem.model.LopHoc;
import com.example.BTL_KienTrucPhanMem.model.NhanVien;
import com.example.BTL_KienTrucPhanMem.model.ThanhVien;
import com.example.BTL_KienTrucPhanMem.repository.DangKyDayRepository;
import com.example.BTL_KienTrucPhanMem.repository.LopHocRepository;
import com.example.BTL_KienTrucPhanMem.service.DangKyDayService;
import com.example.BTL_KienTrucPhanMem.service.LopHocService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
public class DangKyDayController {
    @Autowired
    private DangKyDayService dkdSv;

    @GetMapping("/teacher/dang-ky-day")
    public String dangKyDay(HttpSession session, Model model) {
        NhanVien gv = (NhanVien) session.getAttribute("gv");
        if (gv == null) return "redirect:/login";
        ThanhVien thanhVien = gv.getThanhVien(); // assuming NhanVien has a ThanhVien property

        List<LopHoc> lopChuaDangKy = dkdSv.getLopHocChuaDangKy(thanhVien.getId());
        List<DangKyDay> lopDaDangKy = dkdSv.getLopHocDaDangKy(thanhVien.getId());

        model.addAttribute("tbLop", lopChuaDangKy);
        model.addAttribute("tbDK", lopDaDangKy);
        model.addAttribute("gv", gv);

        return "suadkday";
    }


    @PostMapping("/luudkday")
    public String saveDangKyDay(@RequestParam List<Integer> lopHocIds, HttpSession session) {
        NhanVien gv = (NhanVien) session.getAttribute("gv");
        if (gv == null) return "redirect:/login";

        ThanhVien tv = gv.getThanhVien();

        for (Integer lhId : lopHocIds) {
            DangKyDay dkd = new DangKyDay();
            LopHoc lh = new LopHoc();
            lh.setId(lhId);

            dkd.setThanhVien(tv);
            dkd.setLopHoc(lh);

            dkdSv.saveDKD(dkd);
        }

        return "redirect:/teacher/dang-ky-day";
    }

    @GetMapping("/xoadkday")
    public String xoaDangKyDay(@RequestParam("dkdId") Integer dkdId) {
        dkdSv.deleteDKD(dkdId);
        return "redirect:/teacher/dang-ky-day"; // quay lại trang chính
    }

}
