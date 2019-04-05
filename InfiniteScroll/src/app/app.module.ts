import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { NgModule } from '@angular/core';
import {FormsModule,ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { MatInputModule } from '@angular/material';
import { MatFormFieldModule } from '@angular/material';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { AngularmaterialModule } from './material/angularmaterial/angularmaterial.module';


@NgModule({
  declarations: [
  AppComponent,
  UserComponent
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
  ],

  providers: [],
  bootstrap: [AppComponent]
  })
  export class AppModule { }