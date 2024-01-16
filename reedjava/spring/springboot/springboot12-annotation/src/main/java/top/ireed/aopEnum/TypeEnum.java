package top.ireed.aopEnum;

public enum TypeEnum {

    type01("type01","001"),
    type02("type02","002"),
    type03("type03","003"),
    type100("错误类型","100");

    private final String name;
    private final String num;


    TypeEnum(String name, String num) {
        this.name = name;
        this.num = num;
    }

    public static TypeEnum getNameEnum(String name){
        for (TypeEnum value : TypeEnum.values()) {
            if (value.name.equals(name)) {
                return value;
            }
        }
        return type100;
    }
}
