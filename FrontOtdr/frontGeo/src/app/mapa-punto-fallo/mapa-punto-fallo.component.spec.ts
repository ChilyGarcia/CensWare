import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MapaPuntoFalloComponent } from './mapa-punto-fallo.component';

describe('MapaPuntoFalloComponent', () => {
  let component: MapaPuntoFalloComponent;
  let fixture: ComponentFixture<MapaPuntoFalloComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MapaPuntoFalloComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MapaPuntoFalloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
