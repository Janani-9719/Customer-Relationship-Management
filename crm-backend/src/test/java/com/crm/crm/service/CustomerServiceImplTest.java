package com.crm.crm.service;

import com.crm.crm.application.service.impl.CustomerServiceImpl;
import com.crm.crm.application.service.web.dto.CustomerResponseDTO;
import com.crm.crm.domain.modal.Customer;
import com.crm.crm.domain.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCustomers_shouldReturnList() {

        Customer customer = new Customer();
        customer.setAccountId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john@example.com");
        customer.setPhoneNumber("1234567890");
        customer.setAddress("123 St");
        customer.setCity("City");
        customer.setState("State");
        customer.setCountry("Country");

        when(repository.findAll()).thenReturn(List.of(customer));

        List<CustomerResponseDTO> result = customerService.getAllCustomers();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).firstName());
        assertEquals("Doe", result.get(0).lastName());
        assertEquals("john@example.com", result.get(0).email());
    }
}