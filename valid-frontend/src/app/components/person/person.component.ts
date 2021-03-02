import { stringify } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Person, PersonIdProcess } from '../../models/person.model';
import { PersonService } from '../../services/person.service';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {

  persons: Person[] = [];
  private processPersons: { [key: number]: boolean } = {};



  formPerson = new FormGroup({
    name: new FormControl(),
    lastname: new FormControl(),
  });

  constructor(private personServ: PersonService) {
    this.processPersons = {};
  }

  ngOnInit(): void {
    this.onFindPersons();
    this.processPersons = {};
  }

  onSavePerson() {
    const person = new Person();
    person.name = this.formPerson.value.name;
    person.lastname = this.formPerson.value.lastname;
    person.processed = false;
    if (!person.name?.length || !person.lastname?.length) return;
    this.personServ.add(person).subscribe(
      (res) => {
        this.formPerson.reset()
        this.onFindPersons();
        window.alert("Registro exitoso");
      },
      (err) => {
        console.error("err:", err);
        window.alert(err);
      }
    )
  }

  onFindPersons(): void {
    this.persons = [];
    this.personServ.findAll().subscribe(
      (res) => {
        this.persons = res;
      },
      (err) => {
        window.alert(err);
      }
    )
  }

  onSavePersons(): void {
    let personsIds = new Array<PersonIdProcess>()
    for (const key in this.processPersons) {
      const value = this.processPersons[key];
      const id = parseInt(key);
      const processed = this.persons.find(x => x.id == id)?.processed;
      if (processed == !undefined && processed != value) {
        const person = new PersonIdProcess();
        person.id = id;
        person.processed = value;
        personsIds.push(person);
      }
    }
    if (personsIds.length == 0) return alert("no hay personas por procesar");
    this.personServ.edited(personsIds).subscribe(
      (res) => {
        window.alert("se actualizaron: " + res);
      },
      (err) => { window.alert(err); }
    )
  }

  onChangePerson(e: any, id: number): void {
    this.processPersons[id] = e.target.checked;
  }

}
