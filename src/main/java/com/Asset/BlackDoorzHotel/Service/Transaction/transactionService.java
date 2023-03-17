package com.Asset.BlackDoorzHotel.Service.Transaction;

import com.Asset.BlackDoorzHotel.DTO.Room.RoomDto;
import com.Asset.BlackDoorzHotel.DTO.Transaction.TransactionDto;
import com.Asset.BlackDoorzHotel.DTO.Transaction.TransactionInsertDto;

import java.util.List;

public interface transactionService {

    public List<TransactionDto> getalltransaction(String custname, String roomnumb);
    public List<RoomDto> getallfromroom(String typeroom);
    public Long totalhalaman(String custname, String roomnumb);
    public boolean deletedtransaction(Integer idreserv);
    public RoomDto getroomforreserve(String roomnumb);
    public void postreserved(TransactionInsertDto dto);
    public TransactionDto getconfirm(String user);
    public void postconfirm(Integer idreserved);
    public List<TransactionDto> getalltransbyuser(String username);

}
