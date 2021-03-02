import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Person, PersonIdProcess } from '../models/person.model';

const PERSONS_PATH = `${environment.API_URL}/valid/persons`;

@Injectable({
  providedIn: 'root'
})

export class PersonService {


  constructor(private http: HttpClient) { }


  add(person: Person) {
    return this.http.post<any>(PERSONS_PATH, person);
  }


  findAll() {
    return this.http.get<any>(PERSONS_PATH);
  }


  edited(persons: PersonIdProcess[]) {
    return this.http.put<any>(PERSONS_PATH, persons);
  }


}
