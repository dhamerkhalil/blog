import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  statusList : any;
  errorFound = false;
  errorMsg: any;
  postText = ''; 
  commentText = '';
  publishPostSuccess = false;
  errorLogoutFound = false;
  logoutSuccess = false;
  errorLogoutMsg: any; 
  errorDeletePostFound = false;
  deletePostSuccess =  false;
  errorDeleteCommentFound: boolean = false;
  deleteCommentSuccess: boolean = false;
  publishCommentSuccess: boolean = false;
  errorPostMsg: any;
  errorCommentMsg: any;
  currentUser: any;
  userName = ''
  errorCommentFound: boolean = false;

  constructor(private router : Router, private httpClient : HttpClient) {
  }
  
  ngOnInit(): void {
    this.getCurrentUser();
   this.getAllStatus();
  }

  public getAllStatus(){
    this.httpClient.get('http://localhost:8080/status')
    .subscribe(data => 
      {
        this.statusList = data;
        console.log('status list size :' +  this.statusList.length)
      }, 
      error => {
        this.errorFound = true
        this.errorMsg = error.error.exception;
      });
  }

  public publishPost(){
    this.errorFound = false;
    this.publishPostSuccess = false;
    this.httpClient.post('http://localhost:8080/add-status',{'text': this.postText})
    .subscribe(data => 
      {
        this.publishPostSuccess  = true
        this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
          this.router.navigate(['/blog']);});
      }, 
      error => {
        this.errorFound = true
        this.errorMsg = error.error.exception;
      });
  }

  public logOut(){
    this.errorLogoutFound = false;
    this.httpClient.get('http://localhost:8080/logout');
    console.log('logged out')
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
    this.router.navigate(['/login']);});
  }

  public deletePost(postId: number){
    this.errorDeletePostFound = false;
    this.deletePostSuccess = false;
    this.httpClient.delete('http://localhost:8080/delete-status/'+postId)
    .subscribe(data => 
      {
        this.deletePostSuccess  = true
        this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
          this.router.navigate(['/blog']);});
      }, 
      error => {
        this.errorDeletePostFound = true
        this.errorPostMsg = error.error.exception;
      });

  }

  public publishComment(statusId : number, commentText : any){
    this.errorCommentFound = false;
    this.publishCommentSuccess = false;
    this.httpClient.post('http://localhost:8080/add-comment/' + statusId,{ text : commentText.value})
    .subscribe(data => 
      {
        this.publishCommentSuccess  = true;
        this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
          this.router.navigate(['/blog']);});
      }, 
      error => {
        this.errorFound = true
        this.errorMsg = error.error.exception;
      });
  }

 public deleteComment(commentId : number){
  this.errorDeleteCommentFound = false;
  this.deleteCommentSuccess = false;
  this.httpClient.delete('http://localhost:8080/delete-comment/'+commentId)
  .subscribe(data => 
    {
      this.deleteCommentSuccess  = true
      this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
        this.router.navigate(['/blog']);});
    }, 
    error => {
      this.errorDeleteCommentFound = true
      this.errorCommentMsg = error.error.exception;
    });
 }

public getCurrentUser(){
  this.httpClient.get('http://localhost:8080/current-logged-user')
  .subscribe(data => 
    {
      this.currentUser = data
      console.log('current logged user'+ this.currentUser.fullName)
      console.log('current logged user'+ this.currentUser.id)
      this.userName = this.currentUser.fullName
    }, 
    error => {
      console.log('no logged user')
      this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
        this.router.navigate(['/login']);});
    });
}

}
