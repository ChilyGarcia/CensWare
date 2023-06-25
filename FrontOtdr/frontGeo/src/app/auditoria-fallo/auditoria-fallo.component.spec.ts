import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuditoriaFalloComponent } from './auditoria-fallo.component';

describe('AuditoriaFalloComponent', () => {
  let component: AuditoriaFalloComponent;
  let fixture: ComponentFixture<AuditoriaFalloComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuditoriaFalloComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuditoriaFalloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
