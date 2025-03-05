import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email = '';
  password = '';

  constructor(private http:HttpClient, private router: Router) { }

  onSubmit() {
    const loginData = {
      username: this.email, 
      password: this.password
    };

    this.http.post<any>('http://localhost:8080/api/token', loginData)
      .subscribe(response => {
        localStorage.setItem('token', response.token);
        this.router.navigate(['/admin/dashboard']);
      }, error => {
        alert('Đăng nhập thất bại, vui lòng kiểm tra lại thông tin!');
      });
  }
}
