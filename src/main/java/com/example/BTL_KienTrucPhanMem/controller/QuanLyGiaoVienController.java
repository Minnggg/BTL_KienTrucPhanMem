package com.example.BTL_KienTrucPhanMem.controller;

import com.example.BTL_KienTrucPhanMem.model.NhanVien;
import com.example.BTL_KienTrucPhanMem.model.ThanhVien;
import com.example.BTL_KienTrucPhanMem.repository.NhanVienRepository;
import com.example.BTL_KienTrucPhanMem.repository.ThanhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin/giaovien")
@PreAuthorize("hasRole('ADMIN')")
public class QuanLyGiaoVienController {

    private final NhanVienRepository repository;
    private final ThanhVienRepository thanhVienRepo;

    @Autowired
    public QuanLyGiaoVienController(NhanVienRepository repository, ThanhVienRepository thanhVienRepo) {
        this.repository = repository;
        this.thanhVienRepo = thanhVienRepo;
    }

    @GetMapping
    public String danhSachGiaoVien(Model model) {
        List<NhanVien> listGV = repository.findByVitri("GIAOVIEN");
        model.addAttribute("listGV", listGV);
        return "admin-quan-ly-giao-vien";
    }

    @GetMapping("form")
    public String formThemGiaoVien() {
        return "giaovien-form";
    }

    @PostMapping("/add")
    public String themGiaoVien(@RequestParam Map<String, String> params) {
        ThanhVien tv = new ThanhVien();
        tv.setHoten(params.get("hoten"));
        tv.setNgaysinh(LocalDate.parse(params.get("ngaysinh")));
        tv.setEmail(params.get("email"));
        tv.setSdt(params.get("sdt"));
        thanhVienRepo.save(tv);

        NhanVien nv = new NhanVien();
        nv.setThanhVien(tv);
        nv.setUsername(params.get("username"));
        nv.setPassword(new BCryptPasswordEncoder().encode(params.get("password")));
        nv.setVitri(params.get("vitri"));
        nv.setChuyenMon(params.get("chuyenMon"));
        nv.setTrinhDo(params.get("trinhDo"));
        repository.save(nv);

        return "redirect:/admin/giaovien";
    }

    @GetMapping("/delete/{id}")
    public String deleteGiaoVien(@PathVariable("id") Integer id) {
        Optional<NhanVien> optionalNV = repository.findById(id);
        if (optionalNV.isPresent()) {
            NhanVien nhanVien = optionalNV.get();
            Integer thanhVienId = nhanVien.getThanhVien().getId();

            // Xóa nhân viên
            repository.deleteById(id);

            // Xóa thành viên tương ứng
            thanhVienRepo.deleteById(thanhVienId);
        }

        return "redirect:/admin/giaovien"; // trang danh sách nhân viên
    }

    @PostMapping("/update")
    public String updateGiaoVien(@RequestParam Map<String, String> params) {
        Integer id = Integer.parseInt(params.get("id"));
        String hoten = params.get("hoten");
        LocalDate ngaysinh = LocalDate.parse(params.get("ngaysinh"));
        String email = params.get("email");
        String sdt = params.get("sdt");
        String username = params.get("username");
        String chuyenMon = params.get("chuyenMon");
        String trinhDo = params.get("trinhDo");

        Optional<NhanVien> optionalNV = repository.findById(id);
        if (optionalNV.isPresent()) {
            NhanVien nv = optionalNV.get();
            ThanhVien thanhVien = nv.getThanhVien();

            thanhVien.setHoten(hoten);
            thanhVien.setNgaysinh(ngaysinh);
            thanhVien.setEmail(email);
            thanhVien.setSdt(sdt);

            nv.setUsername(username);
            nv.setChuyenMon(chuyenMon);
            nv.setTrinhDo(trinhDo);

            thanhVienRepo.save(thanhVien);
            repository.save(nv);

            return "redirect:/admin/giaovien";
        } else {
            return "redirect:/admin/giaovien";
        }
    }

}
