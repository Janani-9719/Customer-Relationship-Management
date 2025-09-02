import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-customer-form',
  imports: [FormsModule,ReactiveFormsModule,CommonModule],
  templateUrl: './customer-form.html',
  styleUrl: './customer-form.scss'
})
export class CustomerForm {
  @Input() customer:any;
  customerForm:FormGroup
  
  constructor(private formBuilder: FormBuilder , private http:HttpClient) {
    this.customerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: [''],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: [''],
      address: [''],
      city: [''],
      state: [''],
      country: ['']
    });
  }


  onSubmit() {
    if (this.customerForm.valid) {
      const customerData = this.customerForm.value;

      fetch('http://localhost:8080/api/customers', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(customerData)
      })
        .then(response => {
          if (!response.ok) {
            throw new Error('Failed to save customer');
          }
          return response.json();
        })
        .then(data => {
          alert('Customer saved successfully!');
        })
        .catch(error => {
          alert('Error: ' + error.message);
        });
    } else {
      alert('Form is invalid. Please fill out all required fields.');
    }
  }
}
