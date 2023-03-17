package com.Asset.BlackDoorzHotel.DAO;

import com.Asset.BlackDoorzHotel.DTO.Admin.AdminDto;
import com.Asset.BlackDoorzHotel.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface adminRepository extends JpaRepository<Admin, String> {

    @Query(""" 
            SELECT COUNT(*)
            FROM Admin as adm
            WHERE adm.AdminNumber = :username
            """)
    public Long cekadminterdaftar(@Param("username") String username);


    @Query("""
            SELECT new com.Asset.BlackDoorzHotel.DTO.Admin.AdminDto(adm.AdminNumber, adm.Password, adm.jobtitle)
            FROM Admin as adm
            """)
    public List<AdminDto> getlistadmin();

}
