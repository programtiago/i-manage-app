import { Employee } from "./Employee";

export interface User {
    username: number,
    password: string,
    createdAt: Date,
    updatedAt: Date,
    userRoles: string[],
    employee: Employee
}