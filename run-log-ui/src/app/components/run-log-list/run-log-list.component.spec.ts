import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RunLogListComponent } from './run-log-list.component';

describe('RunLogListComponent', () => {
  let component: RunLogListComponent;
  let fixture: ComponentFixture<RunLogListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RunLogListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RunLogListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
