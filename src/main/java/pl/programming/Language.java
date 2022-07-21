package pl.programming;

public class Language {
    public String displayName;
    public String code;

    Language(String name, String code)
    {
        this.displayName=name;
        this.code=code;
    }

    public String getCode() {
        return code;
    }
    public String toString() {
        return this.displayName;
    }
}
