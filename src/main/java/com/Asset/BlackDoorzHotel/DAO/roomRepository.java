package com.Asset.BlackDoorzHotel.DAO;

import com.Asset.BlackDoorzHotel.DTO.Room.RoomDto;
import com.Asset.BlackDoorzHotel.Entity.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface roomRepository extends JpaRepository<Room, String> {

    @Query("""
            SELECT new com.Asset.BlackDoorzHotel.DTO.Room.RoomDto(rom.RoomNumber, rom.Type, 
                rom.Price, rom.Reserved, rom.Confirmed)
            FROM Room as rom
            WHERE rom.RoomNumber LIKE %:roomnumber%
            """)
    public List<RoomDto> getlistroom(@Param("roomnumber") String roomnumber,
                                     Pageable paging);
    @Query("""
            SELECT new com.Asset.BlackDoorzHotel.DTO.Room.RoomDto(rom.RoomNumber, rom.Type, 
                rom.Price, rom.Reserved, rom.Confirmed)
            FROM Room as rom
            WHERE rom.Type LIKE %:type% AND rom.Reserved = false
            """)
    public List<RoomDto> getlistroombytype(@Param("type") String roomtype);

    @Query("""
            SELECT COUNT(*)
            FROM Room as rom
            WHERE rom.RoomNumber LIKE %:roomnumber%
            """)
    public Long totaldata(@Param("roomnumber") String roomnumber);

    @Query("""
            SELECT COUNT(*)
            FROM Room as rom
            WHERE rom.RoomNumber = :roomnumb
            """)
    public Long cekroomterdaftar(@Param("roomnumb") String roomnumb);

    @Query("""
            SELECT new com.Asset.BlackDoorzHotel.DTO.Room.RoomDto(rom.RoomNumber, rom.Type, 
                rom.Price, rom.Reserved, rom.Confirmed) 
            FROM Room as rom
            WHERE rom.RoomNumber = :romnumb
            """)
    public RoomDto getroombyroomnumb(@Param("romnumb") String roomnum);
}
