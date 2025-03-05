import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  private apiUrl = 'http://localhost:8080/api/file/get/';

  constructor(private http: HttpClient) {}

  getImageUrl(imageFileName: string | undefined): Observable<string> {
      return this.http.get<{ filePath: string }>(`${this.apiUrl}${imageFileName}`);
  }
}
