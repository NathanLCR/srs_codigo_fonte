import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CrudEquipamentoComponent } from './crud-equipamento.component';

describe('CrudEquipamentoComponent', () => {
  let component: CrudEquipamentoComponent;
  let fixture: ComponentFixture<CrudEquipamentoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrudEquipamentoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrudEquipamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
