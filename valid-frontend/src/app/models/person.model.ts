export class PersonIdProcess {
    id: number = 0;
    processed: boolean = false;
}

export class Person extends PersonIdProcess {
    name: string = "";
    lastname: string = "";
    processed: boolean = false;
}
