import { CommonModule, DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CustomerModal } from '../customer-modal/customer-modal';

@Component({
  selector: 'app-home',
  imports: [CommonModule, CustomerModal],
  templateUrl: './home.html',
  styleUrl: './home.scss',
  providers:[DatePipe]
})
export class Home implements OnInit{
  
customers : any[] = [];
selectedCustomer: any;

constructor(private http: HttpClient){}

ngOnInit(): void {
  this.getCustomers(); 
}

  getCustomers() : void{
    this.http.get<any[]>('http://localhost:8080/api/customers').subscribe({
      next: (data) => {
        this.customers = data;
        console.log(this.customers);
      },
      error: (error) => {
        alert('There was an error: ' + error.message);
      }
    })
  }
  deleteCustomer(customerId: number): void {
    this.http.delete(`http://localhost:8080/api/customer/${customerId}`).subscribe({
      next: () => {
        alert(`Customer with ID ${customerId} deleted successfully.`);
        this.getCustomers();
      },
      error: (error) => {
        alert('There was an error: ' + error.message);
      }
    });
  }

 updateCustomer(customerData: any): void {
  console.log('Update customer called in home component', customerData);
    this.http.put(`http://localhost:8080/api/customer/${customerData.id}`, customerData).subscribe({
      next: () => {
        alert(`Customer with ID ${customerData.id} updated successfully.`);
        this.getCustomers();
      },
      error: (error) => {
        alert('There was an error: ' + error.message);
      }
    });
 }
}
