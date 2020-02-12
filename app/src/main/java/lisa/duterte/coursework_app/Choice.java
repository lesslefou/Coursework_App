package lisa.duterte.coursework_app;


public class Choice  {
    private int nameId;
    private int iconId;

    public int getName() { return this.nameId; }
    public int getIconId() { return this.iconId; }

    public void setName(int n) { this.nameId = n; }
    public void setIconId(int i) { this.iconId = i; }

    public Choice(int name, int icon) {
        setName(name);
        setIconId(icon);
    }

    public Choice(Choice c){
        setName(c.nameId);
        setIconId(c.iconId);

    }

}