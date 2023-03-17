package com.Asset.BlackDoorzHotel.DAO;

import com.Asset.BlackDoorzHotel.DTO.Customer.CustomerDto;
import com.Asset.BlackDoorzHotel.Entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface custRepository extends JpaRepository<Customer, String> {

    @Query("""
            SELECT COUNT(*)
            FROM Customer as cust
            WHERE cust.Username = :username
            """)
    public Long cekcustomerterdaftar(@Param("username") String usernmae);

    @Query("""
            SELECT new com.Asset.BlackDoorzHotel.DTO.Customer.CustomerDto(cust.Username, cust.Password,
                cust.Name, cust.Email, cust.Address)
            FROM Customer as cust
            WHERE cust.Username LIKE %:custnum%
            """)
    public List<CustomerDto> getlistcust(@Param("custnum") String custnumber,
                                         Pageable paging);

    @Query("""
            SELECT COUNT(*)
            FROM Customer as cust
            WHERE cust.Username LIKE %:custnum%
            """)
    public Long totaldata(@Param("custnum") String custnnumber);

    @Query("""
            SELECT cust.Name
            FROM Customer as cust
            WHERE cust.Username =:custnumber
            """)
    public String getcustomername(@Param("custnumber") String customernumber);

}
