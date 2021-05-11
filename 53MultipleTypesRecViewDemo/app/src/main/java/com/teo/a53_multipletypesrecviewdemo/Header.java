package com.teo.a53_multipletypesrecviewdemo;

public class Header implements AdapterData {
    private String text;

    public Header(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
