import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
    { path: 'login', loadChildren: () => import('./modules/login/login.module').then((m) => m.LoginModule) },
    { path: 'home', loadChildren: () => import('./modules/home/home.module').then((m) => m.HomeModule) },
    { path: 'game', loadChildren: () => import('./modules/game/game.module').then((m) => m.GameModule) },
    { path: '', loadChildren: () => import('./modules/login/login.module').then((m) => m.LoginModule) }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }