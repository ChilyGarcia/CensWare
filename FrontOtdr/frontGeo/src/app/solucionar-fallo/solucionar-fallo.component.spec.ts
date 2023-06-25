import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolucionarFalloComponent } from './solucionar-fallo.component';

describe('SolucionarFalloComponent', () => {
  let component: SolucionarFalloComponent;
  let fixture: ComponentFixture<SolucionarFalloComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SolucionarFalloComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SolucionarFalloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
