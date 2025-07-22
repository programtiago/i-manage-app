import { Employee } from "./Employee";

export interface User {
    id: number,
    username: number,
    password: string,
    createdAt: Date,
    updatedAt: Date,
    userRoles: string[],
    employee: Employee
}