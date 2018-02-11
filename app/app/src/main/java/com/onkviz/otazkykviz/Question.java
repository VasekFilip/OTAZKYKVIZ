package com.onkviz.otazkykviz;

public class Question {
    private int ID;
    private String QUESTION;
    private String OPT1;
    private String OPT2;
    private String OPT3;
    private String OPT4;
    private String ANSWER;
    public Question()
    {
        ID=0;
        QUESTION="";
        OPT1="";
        OPT2="";
        OPT3="";
        OPT4="";
        ANSWER="";
    }
    public Question(String qUESTION, String oPT1, String oPT2, String oPT3, String oPT4,
                    String aNSWER) {

        QUESTION = qUESTION;
        OPT1 = oPT1;
        OPT2 = oPT2;
        OPT3 = oPT3;
        OPT4 = oPT4;
        ANSWER = aNSWER;
    }
    public int getID()
    {
        return ID;
    }
    public String getQUESTION() {
        return QUESTION;
    }
    public String getOPT1() {
        return OPT1;
    }
    public String getOPT2() {
        return OPT2;
    }
    public String getOPT3() {
        return OPT3;
    }
    public String getOPT4() {
        return OPT4;
    }
    public String getANSWER() {
        return ANSWER;
    }
    public void setID(int id)
    {
        ID=id;
    }
    public void setQUESTION(String qUESTION) {
        QUESTION = qUESTION;
    }
    public void setOPT1(String oPTA) {
        OPT1 = oPTA;
    }
    public void setOPT2(String oPTB) {
        OPT2 = oPTB;
    }
    public void setOPT3(String oPTC) {
        OPT3 = oPTC;
    }
    public void setOPT4(String oPTC) {
        OPT4 = oPTC;
    }
    public void setANSWER(String aNSWER) {
        ANSWER = aNSWER;
    }

}
