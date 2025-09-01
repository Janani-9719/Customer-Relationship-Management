package com.crm.crm.application.service.impl;

import com.crm.crm.application.service.CustomerService;
import com.crm.crm.application.service.web.dto.CustomerRequestDTO;
import com.crm.crm.application.service.web.dto.CustomerResponseDTO;
import com.crm.crm.domain.exception.DomainExceptions;
import com.crm.crm.domain.modal.Customer;
import com.crm.crm.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl  implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    private Validator validator;

    @Override
    public CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO) {
        if (customerRepository.existsByEmail(customerRequestDTO.email())){
            throw new DomainExceptions.ConflictException("Email already exists"+ customerRequestDTO.email());
        }
        Customer customer= mapToEntity(customerRequestDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return mapToResponseDTO(savedCustomer);

    }

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDTO updateCustomer(int id, CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new DomainExceptions.NotFoundException("Customer not found with id: " + id));
        Customer updatedCustomer = mapToEntity(customerRequestDTO);
        updatedCustomer.setAccountId(customer.getAccountId());
        Customer savedCustomer = customerRepository.save(updatedCustomer);
        return mapToResponseDTO(savedCustomer);
    }

    @Override
    public void deleteCustomer(int id) {
        if (!customerRepository.existsById(id)) {
                    throw new DomainExceptions.NotFoundException("Customer not found with id: " + id);
                }
                customerRepository.deleteById(id);
    }

    private Customer mapToEntity(CustomerRequestDTO dto) {
            Customer customer = new Customer();
            customer.setFirstName(dto.firstName());
            customer.setLastName(dto.lastName());
            customer.setEmail(dto.email());
            customer.setPhoneNumber(dto.phoneNumber());
            customer.setAddress(dto.address());
            customer.setCity(dto.city());
            customer.setState(dto.state());
            customer.setCountry(dto.country());
            return customer;
        }

        private CustomerRequestDTO mapToRequestDTO(Customer customer) {
            return new CustomerRequestDTO(
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getEmail(),
                    customer.getPhoneNumber(),
                    customer.getAddress(),
                    customer.getCity(),
                    customer.getState(),
                    customer.getCountry()
            );
        }

        private CustomerResponseDTO mapToResponseDTO(Customer customer) {
            return new CustomerResponseDTO(
                    customer.getAccountId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getEmail(),
                    customer.getPhoneNumber(),
                    customer.getAddress(),
                    customer.getCity(),
                    customer.getState(),
                    customer.getCountry()
            );
        }
}
