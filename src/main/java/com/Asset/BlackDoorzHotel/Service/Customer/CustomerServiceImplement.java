package com.Asset.BlackDoorzHotel.Service.Customer;

import com.Asset.BlackDoorzHotel.DAO.custRepository;
import com.Asset.BlackDoorzHotel.DAO.transactionRepository;
import com.Asset.BlackDoorzHotel.DTO.Customer.CustomerDto;
import com.Asset.BlackDoorzHotel.DTO.Customer.CustomerInsertDto;
import com.Asset.BlackDoorzHotel.DTO.Customer.CustomerUpdateDto;
import com.Asset.BlackDoorzHotel.DTO.Transaction.TransactionDto;
import com.Asset.BlackDoorzHotel.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.relational.core.sql.In;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImplement implements customerService{

    @Autowired
    private custRepository custRepos;

    @Autowired
    private transactionRepository transactionRepo;

    @Autowired
    private PasswordEncoder passwordEnco;

    @Override
    public List<CustomerDto> getlistcust(String custnumb, Integer currentpage) {
        Integer dataperhalaman = 4;
        Pageable paging = PageRequest.of(currentpage-1, dataperhalaman, Sort.by("Username"));
        return custRepos.getlistcust(custnumb, paging);
    }

    @Override
    public Long totalhalaman(String custnumb) {
        Long dataperhalaman = Long.valueOf(4);
        Double totaldata = (double) custRepos.totaldata(custnumb);
        Long hasil = (long) (Math.ceil(totaldata/dataperhalaman));
        return hasil;
    }

    @Override
    public Long totalhalamantransaksi(String custnumb) {
        Long dataperhalaman = Long.valueOf(4);
        Double totaldata = (double) transactionRepo.totaldatatransaksicustomer(custnumb);
        Long hasil = (long) (Math.ceil(totaldata/dataperhalaman));
        return hasil;
    }

    @Override
    public Boolean cekdelete(String custnumb) {
        if(transactionRepo.cekcustomerterhubung(custnumb)>0){
            return false;
        } else {
            custRepos.deleteById(custnumb);
            return true;
        }
    }

    @Override
    public List<TransactionDto> getlisttransofcust(String custnunmb) {
        Integer dataperhalamana = 4;
//        Pageable paging = PageRequest.of(halaman-1, dataperhalamana, Sort.by("IdReserve"));
        return transactionRepo.getlisttransbycust(custnunmb);
    }

    @Override
    public String getcustomername(String custnumber) {
        return custRepos.getcustomername(custnumber);
    }

    @Override
    public CustomerUpdateDto getcustbycustname(String custname) {
        try{
            var data = custRepos.findById(custname).get();
            CustomerUpdateDto baru = new CustomerUpdateDto(data.getUsername(),
                    data.getPassword(), data.getPassword(), data.getName(),
                    data.getEmail(), data.getAddress());
            return baru;
        } catch (Exception exception){
            return null;
        }
    }

    @Override
    public void postupdate(CustomerUpdateDto dto) {
        Customer baru = new Customer(dto.getUsername(), passwordEnco.encode(dto.getPassword()), dto.getName(), dto.getEmail(), dto.getAddress());
        custRepos.save(baru);
    }

    @Override
    public void postregist(CustomerInsertDto dto) {
        Customer baru = new Customer(dto.getUsername(), passwordEnco.encode(dto.getPassword()), dto.getName(), dto.getEmail(), dto.getAddress());
        custRepos.save(baru);
    }
}
