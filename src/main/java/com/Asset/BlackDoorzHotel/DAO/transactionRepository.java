package com.Asset.BlackDoorzHotel.DAO;

import com.Asset.BlackDoorzHotel.DTO.Transaction.TransactionDto;
import com.Asset.BlackDoorzHotel.Entity.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface transactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("""
            SELECT COUNT(*)
            FROM Transaction as tra
                LEFT JOIN tra.customer as cust
            WHERE cust.Username = :custnumb
            """)
    public Long cekcustomerterhubung(@Param("custnumb") String custnumb);

    @Query("""
            SELECT COUNT(*)
            FROM Transaction as tra
            WHERE tra.RoomNumber = :roomnumb
            """)
    public Long cekroomterhubung(@Param("roomnumb") String roomnumb);

    @Query("""
            SELECT new com.Asset.BlackDoorzHotel.DTO.Transaction.TransactionDto(tra.Idreserved, tra.RoomNumber,
                tra.CustUsername, tra.Cekin, tra.Cekout, tra.Status, tra.IsExpired, rom.Price)
            FROM Transaction as tra
                LEFT JOIN tra.room as rom
            WHERE tra.CustUsername = :custnumb
            """)
    public List<TransactionDto> getlisttransbycust(@Param("custnumb") String custnumb);

    @Query("""
            SELECT COUNT(*)
            FROM Transaction as tra
            WHERE tra.CustUsername = :custnumb
            """)
    public Long totaldatatransaksicustomer(@Param("custnumb") String custnum);

    @Query("""
            SELECT new com.Asset.BlackDoorzHotel.DTO.Transaction.TransactionDto(tra.Idreserved,
                rom.RoomNumber, rom.Type, cust.Name, tra.Cekin, tra.Cekout, rom.Price, tra.Status, tra.IsExpired)
            FROM Transaction as tra
                LEFT JOIN tra.room as rom
                LEFT JOIN tra.customer as cust
            WHERE tra.RoomNumber = :roomnumb
            """)
    public List<TransactionDto> gettransbyroomnumb(@Param("roomnumb") String roomnumb);

    @Query("""
            SELECT new com.Asset.BlackDoorzHotel.DTO.Transaction.TransactionDto(tra.Idreserved,
                rom.RoomNumber, rom.Type, cust.Name, tra.Cekin, tra.Cekout, rom.Price, tra.Status, tra.IsExpired)
            FROM Transaction as tra
                LEFT JOIN tra.room as rom
                LEFT JOIN tra.customer as cust
            WHERE rom.RoomNumber LIKE %:roomnumb% AND cust.Name LIKE %:custname%
            """)
    public List<TransactionDto> getallreservation(@Param("custname") String custname,
                                                  @Param("roomnumb") String roomnumb);

    @Query("""
            SELECT COUNT(*)
            FROM Transaction as tra
                LEFT JOIN tra.room as rom
                LEFT JOIN tra.customer as cust
            WHERE rom.RoomNumber LIKE %:roomnumb% AND cust.Name LIKE %:custname%
            """)
    public Long totaldata(@Param("custname") String custname,
                          @Param("roomnumb") String roomnumb);

    @Query("""
            SELECT new com.Asset.BlackDoorzHotel.DTO.Transaction.TransactionDto( tra.Idreserved,
                tra.RoomNumber, rom.Type, tra.Cekin, tra.Cekout, rom.Price, tra.Status )
            FROM Transaction as tra
                LEFT JOIN tra.room as rom
                LEFT JOIN tra.customer as cust
            WHERE tra.CustUsername = :customer AND tra.IsExpired = false
            """)
    public TransactionDto getlasttransactionbyuser(@Param("customer") String user);
}
