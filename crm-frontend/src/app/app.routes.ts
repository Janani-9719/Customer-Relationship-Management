import { Routes } from '@angular/router';
import { Home } from './components/home/home';
import { CustomerForm } from './components/customer-form/customer-form';

export const routes: Routes = [
    {path: '', component: Home},
    {path: 'registration', component: CustomerForm}
];
