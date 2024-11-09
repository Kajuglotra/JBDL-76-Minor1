package org.gfg.JBDL_76_Minor1.enums;

public enum Operator {
    EQUALS("="),
    LESS_THAN("<"),
    LESS_THAN_EQUAL("<="),
    IN("IN"),
    LIKE("LIKE");

    private String value;

    Operator(String value){
        this.value= value;
    }
    public String getValue(){
        return value;
    }
}