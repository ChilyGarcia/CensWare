import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DibujarComponent } from './dibujar.component';

describe('DibujarComponent', () => {
  let component: DibujarComponent;
  let fixture: ComponentFixture<DibujarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DibujarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DibujarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
