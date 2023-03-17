package com.Asset.BlackDoorzHotel.DAO;

import com.Asset.BlackDoorzHotel.Entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface superadminRepository extends JpaRepository<SuperAdmin, String> {

    @Query("""
           SELECT COUNT(*)
           FROM SuperAdmin as supad
           WHERE supad.SuperAdminNumb = :username
            """)
    public Long ceksupadminterdaftar(@Param("username") String username);
}
