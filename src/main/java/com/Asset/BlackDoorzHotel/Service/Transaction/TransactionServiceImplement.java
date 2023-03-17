package com.Asset.BlackDoorzHotel.Service.Transaction;

import com.Asset.BlackDoorzHotel.DAO.roomRepository;
import com.Asset.BlackDoorzHotel.DAO.transactionRepository;
import com.Asset.BlackDoorzHotel.DTO.Room.RoomDto;
import com.Asset.BlackDoorzHotel.DTO.Transaction.TransactionDto;
import com.Asset.BlackDoorzHotel.DTO.Transaction.TransactionInsertDto;
import com.Asset.BlackDoorzHotel.Entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImplement implements transactionService{

    @Autowired
    private transactionRepository transactionRepo;

    @Autowired
    private roomRepository roomRepos;

    @Override
    public List<TransactionDto> getalltransaction(String custname, String roomnumb) {
        var listreserv = transactionRepo.getallreservation(custname, roomnumb);

        List<TransactionDto> baru = new ArrayList<>();

        for(TransactionDto a : listreserv){
            if(a.getCekout().isAfter(LocalDate.now())){
                baru.add(a);
            }
        }

        return baru;
    }

    @Override
    public List<RoomDto> getallfromroom(String typeroom) {
        return roomRepos.getlistroombytype(typeroom);
    }

    @Override
    public Long totalhalaman(String custname, String roomnumb) {
        Integer totaldataperhalamnan = 4;
        Double data = (double) transactionRepo.totaldata(custname, roomnumb);
        Long nilaibalik = (long) (Math.ceil(data/totaldataperhalamnan));
        return nilaibalik;
    }

    @Override
    public boolean deletedtransaction(Integer idreserv) {
        try {
            var ubahtrans = transactionRepo.findById(idreserv).get();
            var updateroom = roomRepos.findById(ubahtrans.getRoomNumber()).get();
            updateroom.setReserved(false);
            roomRepos.save(updateroom);
            transactionRepo.deleteById(idreserv);
            return true;
        } catch (Exception exception){
            return false;
        }
    }

    @Override
    public RoomDto getroomforreserve(String roomnumb) {
        var data = roomRepos.findById(roomnumb).get();
        RoomDto baru = new RoomDto(data.getRoomNumber(), data.getType(),
                        data.getPrice(), data.getReserved(), data.getConfirmed());
        return baru;
    }

    @Override
    public void postreserved(TransactionInsertDto dto) {
        try {
            Transaction baru = new Transaction(dto.getIdreseved(), dto.getRoomNumber(),
                    dto.getCustUsername(), dto.getCekin(), dto.getCekout(), false, false);
            transactionRepo.save(baru);
            var a = roomRepos.findById(dto.getRoomNumber()).get();
            a.setReserved(true);
            roomRepos.save(a);
        } catch (Exception exception) {
        }
    }

    @Override
    public TransactionDto getconfirm(String user) {
        return transactionRepo.getlasttransactionbyuser(user);
    }

    @Override
    public void postconfirm(Integer idreserved) {
        var datatrans= transactionRepo.findById(idreserved).get();
        datatrans.setStatus(true);
        var datarom = roomRepos.findById(datatrans.getRoomNumber()).get();
        datarom.setConfirmed(true);
        transactionRepo.save(datatrans);
        roomRepos.save(datarom);
    }

    @Override
    public List<TransactionDto> getalltransbyuser(String username) {
        var cek = transactionRepo.getlisttransbycust(username);
        return cek;
    }
}
