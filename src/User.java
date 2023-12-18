public class User {
  private int id;
  private String email;
  private String userName;
  private String password;
  private Account account;

  public int getId() {
    return id;
  }

  public void setId(int newId) {
    this.id = newId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String newEmail) {
    this.email = newEmail;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String newUserName) {
    this.userName = newUserName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String newPassword) {
    this.password = newPassword;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account newAccount) {
    this.account = newAccount;
  }
}