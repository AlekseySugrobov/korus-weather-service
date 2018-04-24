export class User{
  id?: number;
  email: string;
  password: string;
  firstName?: string;
  lastName?: string;

  constructor(){}

  public static isNull(user: User): boolean{
    return user.email === null &&
      user.password === null &&
      user.firstName === null &&
      user.lastName === null;
  }
}
