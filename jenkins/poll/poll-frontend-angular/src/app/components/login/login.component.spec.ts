import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { of, throwError } from 'rxjs';
import { LoginComponent } from './login.component';

// ❌ NE PAS IMPORTER le vrai AuthenticationService
// ✅ Créer un mock manuellement (aucun lien avec le vrai)
class MockAuthenticationService {
  login(email: string, password: string) {
    return of({});
  }
}

class MockRouter {
  navigateByUrl(url: string) {
    return url;
  }
}

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let mockAuthService: MockAuthenticationService;
  let mockRouter: MockRouter;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginComponent],
      imports: [ReactiveFormsModule],
      providers: [
        { provide: AuthenticationService, useClass: MockAuthenticationService },
        { provide: Router, useClass: MockRouter }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    mockAuthService = TestBed.inject(AuthenticationService);
    mockRouter = TestBed.inject(Router);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call login and navigate on success', () => {
    spyOn(mockAuthService, 'login').and.returnValue(of({}));
    const navSpy = spyOn(mockRouter, 'navigateByUrl');

    component.credentials.setValue({ email: 'test@example.com', password: 'password' });
    component.onSubmit();

    expect(mockAuthService.login).toHaveBeenCalledWith('test@example.com', 'password');
    expect(navSpy).toHaveBeenCalledWith('/');
  });

  it('should set error message on login failure', () => {
    spyOn(mockAuthService, 'login').and.returnValue(
      throwError(() => ({ message: 'Invalid credentials' }))
    );

    component.credentials.setValue({ email: 'wrong@example.com', password: 'wrong' });
    component.onSubmit();

    expect(component.error).toBe('Invalid credentials');
  });
});
