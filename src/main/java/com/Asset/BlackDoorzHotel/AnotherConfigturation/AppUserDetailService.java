package com.Asset.BlackDoorzHotel.AnotherConfigturation;

import com.Asset.BlackDoorzHotel.DAO.adminRepository;
import com.Asset.BlackDoorzHotel.DAO.custRepository;
import com.Asset.BlackDoorzHotel.DAO.superadminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    private adminRepository adminRepos;

    @Autowired
    private custRepository custRepos;

    @Autowired
    private superadminRepository superadminRepos;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        var userrole = request.getParameter("role");
        UserLoged user = new UserLoged();

        if(userrole.equals("SuperAdmin")){
            if(superadminRepos.ceksupadminterdaftar(username)==1){
                var mentah = superadminRepos.findById(username).get();
                user.setUsername(mentah.getSuperAdminNumb());
                user.setPassword(mentah.getPassword());
                user.setRole("SuperAdmin");
            } else {
                return new AppUserDetails(user);
            }
        }

        if(userrole.equals("Administrator")){
            if(adminRepos.cekadminterdaftar(username)==1){
                var mentah = adminRepos.findById(username).get();
                user.setUsername(mentah.getAdminNumber());
                user.setPassword(mentah.getPassword());
                user.setRole("Administrator");
            } else {
                return new AppUserDetails(user);
            }
        }

        if(userrole.equals("Customer")){
            if(custRepos.cekcustomerterdaftar(username)==1){
                var mentah = custRepos.findById(username).get();
                user.setUsername(mentah.getUsername());
                user.setPassword(mentah.getPassword());
                user.setRole("Customer");
            } else {
                return new AppUserDetails(user);
            }
        }

        return new AppUserDetails(user);
    }
}
