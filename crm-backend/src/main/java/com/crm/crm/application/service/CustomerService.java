package com.crm.crm.application.service;

import com.crm.crm.application.service.web.dto.CustomerRequestDTO;
import com.crm.crm.application.service.web.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO);

    List<CustomerResponseDTO> getAllCustomers();

    CustomerResponseDTO updateCustomer(int id, CustomerRequestDTO customerRequestDTO);

    void deleteCustomer(int id);
}
