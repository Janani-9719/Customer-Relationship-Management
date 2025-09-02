import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Header } from './components/header/header';
import { Home } from "./components/home/home";
import {  HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Header, Home, HttpClientModule ],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  
}
