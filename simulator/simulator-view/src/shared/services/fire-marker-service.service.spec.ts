import { TestBed } from '@angular/core/testing';

import { FireMarkerServiceService } from './fire-marker-service.service';

describe('FireMarkerServiceService', () => {
  let service: FireMarkerServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FireMarkerServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
