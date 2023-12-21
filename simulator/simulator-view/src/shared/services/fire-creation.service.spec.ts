import { TestBed } from '@angular/core/testing';

import { FireCreationService } from './fire-creation.service';

describe('FireCreationService', () => {
  let service: FireCreationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FireCreationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
