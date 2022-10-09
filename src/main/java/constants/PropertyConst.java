package constants;
//アプリケーションスコープのパラメータ名を定義するEnumクラス
//「性質」の定義

public enum PropertyConst {

    //ペッパー文字列
    PEPPER("pepper");

    private final String text;
    private PropertyConst(final String text) {
        this.text = text;
    }

    public String getValue() {
        return this.text;
    }
}