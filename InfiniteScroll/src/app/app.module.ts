import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { MatInputModule } from '@angular/material';
import { MatFormFieldModule } from '@angular/material';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { AngularmaterialModule } from './material/angularmaterial/angularmaterial.module';
import { HomeComponent } from './home/home.component';
import { Routes, RouterModule } from '@angular/router';
import { OAuthModule } from 'angular-oauth2-oidc';

const appRoutes: Routes=[
  {path:'home',component: HomeComponent},
  {path:'users',component: UserComponent},
];

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    InfiniteScrollModule,
    MatFormFieldModule,
    MatInputModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularmaterialModule,
    FormsModule,
    ReactiveFormsModule,
    OAuthModule.forRoot(),
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    )
  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }