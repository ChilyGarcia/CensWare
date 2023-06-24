import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuditoriasGestionComponent } from './auditorias-gestion.component';

describe('AuditoriasGestionComponent', () => {
  let component: AuditoriasGestionComponent;
  let fixture: ComponentFixture<AuditoriasGestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuditoriasGestionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuditoriasGestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
