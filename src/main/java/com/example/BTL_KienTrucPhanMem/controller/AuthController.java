package com.example.BTL_KienTrucPhanMem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/")
    public String gologinForm() {
        return "redirect:/login"; // login.html trong templates
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login"; // login.html trong templates
    }

    @GetMapping("/admin/dashboard")
    public String adminPage() {
        return "admin-dashboard"; // trang dashboard cho admin
    }

    @GetMapping("/giaovien/dashboard")
    public String gvPage() {
        return "gv-dashboard"; // trang dashboard cho giáo viên
    }
}
