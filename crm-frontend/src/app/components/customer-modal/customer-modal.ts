import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-customer-modal',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './customer-modal.html',
  styleUrl: './customer-modal.scss'
})
export class CustomerModal implements OnInit {
  @Input() customer:any;
  @Output() updateCustomer = new EventEmitter<any>();

customerForm!: FormGroup;

constructor(private fb: FormBuilder){}
  ngOnInit(): void {
    this.customerForm = this.fb.group({
      id:[''],
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
  ngOnChanges(): void {
    if(this.customer && this.customerForm){
      this.customerForm.patchValue(this.customer);
    }
  }
  onsubmit(): void {
    console.log('onsubmit called', this.customerForm.value, this.customerForm.valid);
    if (this.customerForm.valid) {
      this.updateCustomer.emit(this.customerForm.value); 
      (document.getElementById('closeEditModalBtn') as HTMLButtonElement)?.click();
    }
  }

}
