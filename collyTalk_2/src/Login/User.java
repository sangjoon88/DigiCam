package Login;

public class User {
    private String id;
    private String pw;
    private String name;
    private String nickName;
    private String hp;
    
    public User() {};

    public User(String id, String pw, String name, String nickName, String hp) {
        setId(id);
        setPw(pw);
        setName(name);
        setNickName(nickName);
        setHp(hp);
    }
    public User(String id) {
        setId(id);
    }
    
    public String getHp() {
        return id;
    }
    public void setHp(String hp) {
        this.hp = hp;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || !(o instanceof User)) {
            return false;
        }
        User temp = (User)o;

        return id.equals(temp.getId());
    }

    @Override
    public String toString() {
        String info = "Id: " + id + "\n";
        info += "Pw: " + pw + "\n";
        info += "Name: " + name + "\n";
        info += "NickName: " + nickName + "\n";
        info += "HP: " + hp + "\n";
        return info;
    }
}