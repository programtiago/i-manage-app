import { User } from "./User";

export interface Employee {
    workerNo: number,
    fullName: string,
    email: string,
    phoneNumber: string,
    recruitmentCompany: string,
    operation: string,
    department: string,
    birthdayDate: Date,
    age: number,
    genre: string,
    status: string,
    admissionDate: Date,
    registryDate: Date,
    user: User
}