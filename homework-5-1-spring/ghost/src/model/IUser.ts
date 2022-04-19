export interface AuthUser {
    id: number
    username: string
    fullName: string
    email: string
    authorities: Authorities[]
}

export interface AuthData {
    username: string
    password: string
}

export interface Authorities {
    id: number
    authority: string
}