import { Component } from '@angular/core';
import { OAuthService, JwksValidationHandler, AuthConfig } from 'angular-oauth2-oidc';
import { Router } from '@angular/router';

export const authConfig: AuthConfig = {
  issuer: 'https://accounts.google.com',
  redirectUri: window.location.origin,
  ////URL of the SPA to redirect the user after silent refresh
  //silentRefreshRedirectUri: window.location.origin + '/silent-refresh.html',
  clientId: '831923798914-nfo49p29s4kce3dcbakc930riv2rq4d2.apps.googleusercontent.com',
  strictDiscoveryDocumentValidation: false,
  //scope: 'openid profile email',
  showDebugInformation: true,
  sessionChecksEnabled: true
};

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'InfiniteScroll';
  constructor(private oauthService: OAuthService, private router: Router) {
    this.oauthService.configure(authConfig);
    this.oauthService.tokenValidationHandler = new JwksValidationHandler();
    this.oauthService.loadDiscoveryDocumentAndTryLogin();
  }

  login() {
    this.oauthService.initImplicitFlow();
    this.router.navigate(['/users']);
  }

  logout() {
    this.oauthService.logOut();
    this.router.navigate(['/not-logged-in']);
  }

  get givenName() {
    const claims = this.oauthService.getIdentityClaims();
    //console.log(claims);
    if (!claims) {
      return null;
    }
    return claims['name'];
  }

  get profilePicPath(){
    const claims = this.oauthService.getIdentityClaims();
    //console.log(claims);
    if (!claims) {
      return null;
    }
    return claims['picture'];
  }
}

