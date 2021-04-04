import { HttpClient, HttpParams, HttpRequest } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  fullName = ""
  email = ""
  password = ""

  signUpSuccess = false
  errorFound = false
  errorMsg: any;
  constructor(private router : Router, private httpClient : HttpClient) { }

  ngOnInit(): void {
  }

  signup(){

    this.errorFound = false;
    this.signUpSuccess = false;
    let body = {'fullName': this.fullName, 'email': this.email, 'password': this.password}
    
    
    this.httpClient.post('http://localhost:8080/add-user',body)
    .subscribe(data => 
      {
        this.signUpSuccess  = true
        this.router.navigateByUrl('/signin');
      }, 
      error => {
        this.errorFound = true
        this.errorMsg = error.error.exception;
      });
  }

}
