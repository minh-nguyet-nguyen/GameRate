import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router'; 

@Component({
  selector: 'login',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
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
      this.router.navigate(['/home']);
		} else {
      this.login.reset();
			this.error = "Please enter username and password";
		}
	}
}
