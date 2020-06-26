package bean;

/**
 * projectName:  com.zhy
 * packageName: bean
 * date: 2020-06-24 19:31
 * copyright(c) 2020 南晓18卓工 邱依良
 */
public class User {
    private String username;
    private String password;
    private String authority;
    private String tel;
    public User() {
    }

    public User(String username, String password, String authority,String tel) {
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.tel = tel;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authority='" + authority + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}