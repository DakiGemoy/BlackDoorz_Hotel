package com.Asset.BlackDoorzHotel.Service.Room;

import com.Asset.BlackDoorzHotel.DTO.Room.RoomDto;
import com.Asset.BlackDoorzHotel.DTO.Room.RoomInsertDto;
import com.Asset.BlackDoorzHotel.DTO.Room.RoomUpdateDto;
import com.Asset.BlackDoorzHotel.DTO.Transaction.TransactionDto;

import java.util.List;

public interface roomService {

    public List<RoomDto> getlistroom(String roomnumber, Integer halaman);
    public RoomDto getupdate(String roomnumber);
    public void insertroom(RoomInsertDto dto);
    public void updateroom(RoomUpdateDto dto);
    public Boolean cekpaksaupdateroom(String roomnumber);
    public Long totaldatahalaman(String roomnumber);
    public Boolean cekterhubung(String roomnumber);
    public TransactionDto gettransactionbyroomnumber(String roomnumb);
}
