import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  allUser: User[] = [];
  page: number = 1;
  constructor(private service: UserService) { }
  displayedColumns: string[] = ['EmpID', 'FirstName', 'LastName','Gender', 'Email'];
  ngOnInit() {
    this.AllUserDetails();
  }

  AllUserDetails() {
    this.service.UserDetails(this.page).subscribe((res) => {
      this.allUser = res;
    });
  }

  onScroll() {
    this.page = this.page + 1;
    this.AllUserDetails();

  }

}