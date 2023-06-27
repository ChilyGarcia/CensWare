import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaracterizacionExcelComponent } from './caracterizacion-excel.component';

describe('CaracterizacionExcelComponent', () => {
  let component: CaracterizacionExcelComponent;
  let fixture: ComponentFixture<CaracterizacionExcelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CaracterizacionExcelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CaracterizacionExcelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
