package com.Asset.BlackDoorzHotel.Service.Admin;

import com.Asset.BlackDoorzHotel.DAO.adminRepository;
import com.Asset.BlackDoorzHotel.DTO.Admin.AdminDto;
import com.Asset.BlackDoorzHotel.DTO.Admin.AdminInsertDto;
import com.Asset.BlackDoorzHotel.DTO.Admin.AdminUpdateDto;
import com.Asset.BlackDoorzHotel.Entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImplement implements adminService{

    @Autowired
    private adminRepository adminRepos;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<AdminDto> getlistadmin() {
        return adminRepos.getlistadmin();
    }

    @Override
    public void postinsertadmin(AdminInsertDto dto) {
        Admin baru = new Admin(dto.getAdminNumber(), passwordEncoder.encode(dto.getPassword()), dto.getJobtitle());
        adminRepos.save(baru);
    }

    @Override
    public void postupdateadmin(AdminUpdateDto dto) {
        Admin baru = new Admin(dto.getAdminNumber(), passwordEncoder.encode(dto.getPassword()), dto.getJobtitle());
        adminRepos.save(baru);
    }

    @Override
    public void deleteadmin(String adminnumber) {
        adminRepos.deleteById(adminnumber);
    }

    @Override
    public AdminUpdateDto getupdate(String adminnumber) {
        var baru = adminRepos.findById(adminnumber).get();
        AdminUpdateDto kirim = new AdminUpdateDto();
        kirim.setAdminNumber(baru.getAdminNumber());
        kirim.setJobtitle(baru.getJobtitle());
        return kirim;
    }
}
