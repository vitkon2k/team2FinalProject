import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user-model/user.model';
import { UserService } from 'src/app/services/user-services/user.service';

@Component({
  selector: 'app-admin-user',
  templateUrl: './admin-user.component.html',
  styleUrls: ['./admin-user.component.css']
})
export class AdminUserComponent implements OnInit {

  users: User[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers() {
    this.userService.getUsers().subscribe(
      data => {
        console.log(data); 
        this.users = data;
      },
      error => console.log(error)
    );
  }
  

  deleteUser(id: number) {
    if (confirm('Bạn có chắc chắn muốn xóa người dùng này?')) {
      this.userService.deleteUser(id).subscribe(() => {
        this.users = this.users.filter(user => user.id !== id);
      });
    }
  }
  
}
