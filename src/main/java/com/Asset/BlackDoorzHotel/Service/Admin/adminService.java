package com.Asset.BlackDoorzHotel.Service.Admin;

import com.Asset.BlackDoorzHotel.DTO.Admin.AdminDto;
import com.Asset.BlackDoorzHotel.DTO.Admin.AdminInsertDto;
import com.Asset.BlackDoorzHotel.DTO.Admin.AdminUpdateDto;

import java.util.List;

public interface adminService {

    public List<AdminDto> getlistadmin();
    public void postinsertadmin(AdminInsertDto dto);
    public void postupdateadmin(AdminUpdateDto dto);
    public void deleteadmin(String adminnumber);
    public AdminUpdateDto getupdate(String adminnumber);

}
