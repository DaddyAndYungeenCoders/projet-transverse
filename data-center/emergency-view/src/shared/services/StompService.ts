import * as Stomp from 'stompjs';
import {Injectable} from '@angular/core';
import SockJS from 'sockjs-client';
import {WEBSERVER_PORT} from '../types/constants/shared-constants';

@Injectable({
  providedIn: 'root'
})
export class StompService {
  stompClient = Stomp.over(new SockJS(`http://localhost:${WEBSERVER_PORT}/sba-websocket`));

  subscribe(topic: string, callback: any): void {
    const connected: boolean = this.stompClient.connected;
    if (connected) {
      this.subscribeToTopic(topic, callback);
      return;
    }

    this.stompClient.connect({}, (): any => {
      this.subscribeToTopic(topic, callback);
    })
  }

  subscribeToTopic(topic: string, callback: any): void {
    this.stompClient.subscribe(topic, () => {
      callback();
    })
  }
}
