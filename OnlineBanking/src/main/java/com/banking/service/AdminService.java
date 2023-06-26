package com.banking.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.banking.model.AdminEntity;
import com.banking.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

	public Optional<AdminEntity> viewAdmin(int adminId) {
		try {
			return adminRepository.findById(adminId);
		} catch (Exception e) {
			logger.error("Failed to view admin", e);
			return Optional.empty();
		}
	}

	public String deleteAdmin(int adminId) {
		try {
			adminRepository.deleteById(adminId);
			return "Admin Deleted";
		} catch (Exception e) {
			logger.error("Failed to delete admin", e);
			throw new RuntimeException("Failed to delete admin", e);
		}
	}

	public AdminEntity updateAdmin(AdminEntity admin) {
		try {
			AdminEntity existingAdmin = adminRepository.findById(admin.getAdminId())
					.orElseThrow(() -> new IllegalArgumentException("Admin not found"));

			existingAdmin.setAdminAddress(admin.getAdminAddress());
			existingAdmin.setAdminName(admin.getAdminName());
			existingAdmin.setAdminNumber(admin.getAdminNumber());

			return adminRepository.save(existingAdmin);
		} catch (Exception e) {
			logger.error("Failed to update admin", e);
			throw new RuntimeException("Failed to update admin", e);
		}
	}

}
