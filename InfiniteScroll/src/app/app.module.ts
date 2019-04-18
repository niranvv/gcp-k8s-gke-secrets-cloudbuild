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
import { NavigationComponent } from './navigation/navigation.component';
import { OAuthModule } from 'angular-oauth2-oidc';
import { AuthGuard } from './shared';
import { NotLoggedInComponent } from './not-logged-in/not-logged-in.component';

const appRoutes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'not-logged-in', component: NotLoggedInComponent },
  { path: 'users', component: UserComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', redirectTo: 'home' }
];

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    HomeComponent,
    NavigationComponent,
    NotLoggedInComponent
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