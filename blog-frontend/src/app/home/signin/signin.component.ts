import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
}) 


export class SigninComponent implements OnInit {
  errorMsg: any;
  errorFound = false;
  loginsuccess = false;
  username = ""
  password = ""

 
  constructor(private router : Router, private httpClient : HttpClient) { }

  ngOnInit(): void {
 
  }

  public login(){
    this.errorFound = false;
    this.loginsuccess = false;
    let body = new HttpParams();
    body = body.set('user', this.username);
    body = body.set('pass', this.password);

    
    this.httpClient.post('http://localhost:8080/doLogin',body,{headers : {"withCredentials" : "true"}})
    .subscribe(data => 
      {
        this.loginsuccess  = true
        this.router.navigateByUrl('/blog');
      }, 
      error => {
        this.errorFound = true
        this.errorMsg = error.error.exception;
      });
  }
}
