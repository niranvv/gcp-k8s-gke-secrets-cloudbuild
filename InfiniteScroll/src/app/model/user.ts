export class User {
    id: number;
    firstname: string;
    lastname: string;
    gender: string;
    dob: Date;
    email: string;
    city: string;
}

export class PageUser {
    content : User[];
    totalElements : number;
    totalPages: number;
    size: number;

}
