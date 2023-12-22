import { TestBed } from '@angular/core/testing';

import { AbstractMarkerService } from './abstract-marker.service';

describe('AbstractMarkerService', () => {
  let service: AbstractMarkerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AbstractMarkerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
