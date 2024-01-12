import { TestBed } from '@angular/core/testing';

import { FirestationMarkerServiceService } from './firestation-marker-service.service';

describe('FirestationMarkerServiceService', () => {
  let service: FirestationMarkerServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FirestationMarkerServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
