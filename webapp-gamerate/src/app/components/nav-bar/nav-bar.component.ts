import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  title: string = 'GameRate';
  menuItems = [
    {linkId: 1, linkName: 'Home', linkUrl:'home'},
    {linkId: 2, linkName: 'Games', linkUrl:'game'}
  ]

  constructor() { }

  ngOnInit(): void {
  }
}