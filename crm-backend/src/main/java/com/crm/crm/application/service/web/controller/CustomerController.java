package com.crm.crm.application.service.web.controller;

import com.crm.crm.application.service.CustomerService;
import com.crm.crm.application.service.web.dto.CustomerRequestDTO;
import com.crm.crm.application.service.web.dto.CustomerResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CustomerController {

        @Autowired
        private CustomerService customerService;

        @PostMapping("/customers")
        public ResponseEntity saveCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
            CustomerResponseDTO customerResponseDTO = customerService.saveCustomer(customerRequestDTO);
            return new ResponseEntity<>(customerResponseDTO, HttpStatus.CREATED);
        }

        @GetMapping("/customers")
        public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
                List<CustomerResponseDTO> customers = customerService.getAllCustomers();
                return new ResponseEntity<>(customers, HttpStatus.OK);
        }

        @PutMapping("/customer/{id}")
        public ResponseEntity<CustomerResponseDTO> updateCustomer(
                        @PathVariable int id,
                        @RequestBody CustomerRequestDTO customerRequestDTO) {
                CustomerResponseDTO updatedCustomer = customerService.updateCustomer(id, customerRequestDTO);
                return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        }

        @DeleteMapping("/customer/{id}")
        public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
            customerService.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
}
