import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router'; 

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @Output() messageEvent = new EventEmitter<string>();
  login: FormGroup;
  title: string = 'GameRate';
  error: string = "";

  constructor(private fb: FormBuilder, private router: Router) { }

  ngOnInit() {
    this.login = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  userLogin() {
    let username = this.login.value['username'];
    let password = this.login.value['password'];
		if ((username != null && username !== "") && (password != null && password !== "")) {
      this.login.reset();
      this.router.navigate(['/home'])
		} else {
      this.login.reset();
			this.error = "Please enter username and password";
		}
	}
}
