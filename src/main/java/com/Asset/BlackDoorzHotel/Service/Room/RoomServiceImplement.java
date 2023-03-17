package com.Asset.BlackDoorzHotel.Service.Room;

import com.Asset.BlackDoorzHotel.DAO.roomRepository;
import com.Asset.BlackDoorzHotel.DAO.transactionRepository;
import com.Asset.BlackDoorzHotel.DTO.Room.RoomDto;
import com.Asset.BlackDoorzHotel.DTO.Room.RoomInsertDto;
import com.Asset.BlackDoorzHotel.DTO.Room.RoomUpdateDto;
import com.Asset.BlackDoorzHotel.DTO.Transaction.TransactionDto;
import com.Asset.BlackDoorzHotel.Entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImplement implements roomService{

    @Autowired
    private roomRepository roomRepos;

    @Autowired
    private transactionRepository transactionRepos;


    @Override
    public List<RoomDto> getlistroom(String roomnumber, Integer halaman) {
        Integer dataperhalaman = 4;
        Pageable paging = PageRequest.of(halaman-1, dataperhalaman, Sort.by("RoomNumber"));
        return roomRepos.getlistroom(roomnumber, paging);
    }

    @Override
    public RoomDto getupdate(String roomnumber) {
        return roomRepos.getroombyroomnumb(roomnumber);
    }

    @Override
    public void insertroom(RoomInsertDto dto) {
        dto.setReserved(false);
        dto.setConfirmed(false);
        Room baru = new Room(dto.getRoomNumber(), dto.getType(), dto.getPrice(), dto.getReserved(), dto.getConfirmed());
        roomRepos.save(baru);
    }

    @Override
    public void updateroom(RoomUpdateDto dto) {
        Room baru = new Room(dto.getRoomNumber(), dto.getType(), dto.getPrice(), dto.getReserved(), dto.getConfirmed());
        roomRepos.save(baru);
    }

    @Override
    public Boolean cekpaksaupdateroom(String roomnumber) {
        var cek = roomRepos.getroombyroomnumb(roomnumber);
        if(cek.getStatus().equals("Occupied") || cek.getStatus().equals("Reserved")){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Long totaldatahalaman(String roomnumber) {
        Long dataperhalaman = (long) 4;
        Double totaldata = (double) roomRepos.totaldata(roomnumber);
        Long hasil = (long) (Math.ceil(totaldata/dataperhalaman));
        return hasil;
    }

    @Override
    public Boolean cekterhubung(String roomnumber) {
        if(transactionRepos.cekroomterhubung(roomnumber)>0){
            return false;
        } else {
            roomRepos.deleteById(roomnumber);
            return true;
        }
    }

    @Override
    public TransactionDto gettransactionbyroomnumber(String roomnumb) {
        var data = transactionRepos.gettransbyroomnumb(roomnumb);
        if ( data.size() == 1 ){
            return data.get(0);
        }
        else {
            TransactionDto baru = new TransactionDto();

            for(TransactionDto a : data){
                if(baru.getCekin() == null){
                    baru = a;
                } else {
                    if(baru.getCekin().isBefore(a.getCekin())){
                        baru=a;
                    }
                }
            }

            return baru;
        }
    }
}
