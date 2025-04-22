package com.example.BTL_KienTrucPhanMem;

import com.example.BTL_KienTrucPhanMem.model.NhanVien;
import com.example.BTL_KienTrucPhanMem.model.ThanhVien;
import com.example.BTL_KienTrucPhanMem.repository.NhanVienRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BtlKienTrucPhanMemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtlKienTrucPhanMemApplication.class, args);
	}


	@Bean
	CommandLineRunner init(NhanVienRepository repo) {
		return args -> {
			if (repo.findByUsername("admin").isEmpty()) {
				NhanVien admin = new NhanVien();
				admin.setUsername("admin");
				admin.setPassword(new BCryptPasswordEncoder().encode("admin123"));
				admin.setVitri("ADMIN");
				repo.save(admin);
			}
		};
	}

}
