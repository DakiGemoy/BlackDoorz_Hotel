package com.Asset.BlackDoorzHotel.Service.Akses;

import com.Asset.BlackDoorzHotel.DAO.aksesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AksesServiceImplement implements aksesService {

    @Autowired
    private aksesRepository aksesRepos;

    @Override
    public Boolean izin(String role, String yangdicari) {
        var data = aksesRepos.findById(role).get();
        switch (yangdicari){
            case "HomeView" :
                return data.getHomeView();
            case "UpdateAccountChangePassword" :
                return data.getUpdateAccountChangePassword();
            case "RoomList" :
                return data.getRoomList();
            case "RoomUpsert" :
                return data.getRoomList();
            case "RoomAdd" :
                return data.getRoomAdd();
            case "RoomUpdate" :
                return data.getRoomUpdate();
            case "RoomDelete" :
                return data.getRoomDelete();
            case "RoomDetail" :
                return data.getRoomDetail();
            case "CustomerList" :
                return data.getCustomerList();
            case "CustomerDelete" :
                return data.getCustomerDelete();
            case "CustomerTransaction" :
                return data.getCustomerTransaction();
            case "CustomerUpdate" :
                return data.getCustomerUpdate();
            case "AdminList" :
                return data.getAdminList();
            case "AdminInsert" :
                return data.getAdminInsert();
            case "AdminAdd" :
                return data.getAdminAdd();
            case "AdminDelete" :
                return data.getAdminDelete();
            case "AdminUpdate" :
                return data.getAdminUpdate();
            case "ReservedCurrentReserved" :
                return data.getReservedCurrentReserved();
            case "ReservedDelete" :
                return data.getReservedDelete();
            case "ReservedReservation" :
                return data.getReservedReservation();
            case "ReservedBill" :
                return data.getReservedBill();
            case "ReservedConfirm" :
                return data.getReservedConfirm();
            case "ReservedReserv" :
                return data.getReservMyReserv();
            default:
                return false;
        }
    }
}
