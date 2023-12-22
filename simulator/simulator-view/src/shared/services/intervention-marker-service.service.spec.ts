import { TestBed } from '@angular/core/testing';

import { InterventionMarkerServiceService } from './intervention-marker-service.service';

describe('InterventionMarkerServiceService', () => {
  let service: InterventionMarkerServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InterventionMarkerServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
