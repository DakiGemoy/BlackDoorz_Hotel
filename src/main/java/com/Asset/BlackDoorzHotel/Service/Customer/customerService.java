package com.Asset.BlackDoorzHotel.Service.Customer;

import com.Asset.BlackDoorzHotel.DTO.Customer.CustomerDto;
import com.Asset.BlackDoorzHotel.DTO.Customer.CustomerInsertDto;
import com.Asset.BlackDoorzHotel.DTO.Customer.CustomerUpdateDto;
import com.Asset.BlackDoorzHotel.DTO.Transaction.TransactionDto;

import java.util.List;

public interface customerService {
    public List<CustomerDto> getlistcust(String custnumb, Integer currentapge);
    public Long totalhalaman(String custnumb);
    public Long totalhalamantransaksi(String custnumb);
    public Boolean cekdelete(String custnumb);
    public List<TransactionDto> getlisttransofcust(String custnunmb);
    public String getcustomername(String custnumber);
    public CustomerUpdateDto getcustbycustname(String custname);
    public void postupdate(CustomerUpdateDto dto);
    public void postregist(CustomerInsertDto dto);
}
