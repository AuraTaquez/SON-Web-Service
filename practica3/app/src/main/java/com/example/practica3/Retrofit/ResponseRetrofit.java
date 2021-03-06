package com.example.practica3.Retrofit;

public class ResponseRetrofit {

    private String code;
    private String name;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseRetrofit() {
    }

    /**
     *
     * @param code
     * @param name
     */
    public ResponseRetrofit(String code, String name) {
        super();
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
