import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroFallaComponent } from './registro-falla.component';

describe('RegistroFallaComponent', () => {
  let component: RegistroFallaComponent;
  let fixture: ComponentFixture<RegistroFallaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistroFallaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistroFallaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
