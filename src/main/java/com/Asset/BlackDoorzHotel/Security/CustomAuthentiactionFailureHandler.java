package com.Asset.BlackDoorzHotel.Security;

import com.Asset.BlackDoorzHotel.DAO.adminRepository;
import com.Asset.BlackDoorzHotel.DAO.custRepository;
import com.Asset.BlackDoorzHotel.DAO.superadminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthentiactionFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private adminRepository adminRepos;

    @Autowired
    private custRepository custRepos;

    @Autowired
    private superadminRepository superadminRepos;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        HttpServletRequest isidaridepan = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        var link = "/login/formlogin";
        var msg = "";

        if(isidaridepan.getParameter("role").equals("SuperAdmin")){
            if(superadminRepos.ceksupadminterdaftar(isidaridepan.getParameter("username"))==1){
                msg = "Password tidak sesuai";
            }else {
                msg = "Username tidak terdaftar sebagai SuperAdmin";
            }
        } else if(isidaridepan.getParameter("role").equals("Administrator")) {
            if(adminRepos.cekadminterdaftar(isidaridepan.getParameter("username"))==1){
                msg = "Password tidak sesuai";
            } else {
                msg = "Username tidak terdaftar sebagai Administrator";
            }
        } else {
            if(custRepos.cekcustomerterdaftar(isidaridepan.getParameter("username"))==1){
                msg = "Password tidak sesuai";
            } else {
                msg = "Username tidak terdaftar sebagai Customer";
            }
        }

        response.sendRedirect(request.getContextPath()+link+"?error="+msg);
    }
}
