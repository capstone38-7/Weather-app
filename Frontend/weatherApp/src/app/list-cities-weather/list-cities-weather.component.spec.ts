import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListCitiesWeatherComponent } from './list-cities-weather.component';

describe('ListCitiesWeatherComponent', () => {
  let component: ListCitiesWeatherComponent;
  let fixture: ComponentFixture<ListCitiesWeatherComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListCitiesWeatherComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListCitiesWeatherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
