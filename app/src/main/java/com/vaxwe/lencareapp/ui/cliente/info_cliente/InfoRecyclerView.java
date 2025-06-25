package com.vaxwe.lencareapp.ui.cliente.info_cliente;

public class InfoRecyclerView {

    private String TXTInformacionT0,TXTInformacionT1,TXTInformacionT2,TXTInformacionT3,TXTInformacionT4,TXTInformacionT5,TXTInformacionT6;
    private boolean Info_explandible;

    public boolean isInfo_explandible() {
        return Info_explandible;
    }

    public void setInfo_explandible(boolean info_explandible) {
        Info_explandible = info_explandible;
    }

    public String getTXTInformacionT0() {
        return TXTInformacionT0;
    }

    public void setTXTInformacionT0(String TXTInformacionT0) {
        this.TXTInformacionT0 = TXTInformacionT0;
    }

    public InfoRecyclerView(String TXTInformacionT0, String TXTInformacionT1, String TXTInformacionT2, String TXTInformacionT3, String TXTInformacionT4, String TXTInformacionT5, String TXTInformacionT6) {
        this.TXTInformacionT0 = TXTInformacionT0;
        this.TXTInformacionT1 = TXTInformacionT1;
        this.TXTInformacionT2 = TXTInformacionT2;
        this.TXTInformacionT3 = TXTInformacionT3;
        this.TXTInformacionT4 = TXTInformacionT4;
        this.TXTInformacionT5 = TXTInformacionT5;
        this.TXTInformacionT6 = TXTInformacionT6;
        this.Info_explandible = false;
    }

    public String getTXTInformacionT1() {
        return TXTInformacionT1;
    }

    public void setTXTInformacionT1(String TXTInformacionT1) {
        this.TXTInformacionT1 = TXTInformacionT1;
    }

    public String getTXTInformacionT2() {
        return TXTInformacionT2;
    }

    public void setTXTInformacionT2(String TXTInformacionT2) {
        this.TXTInformacionT2 = TXTInformacionT2;
    }

    public String getTXTInformacionT3() {
        return TXTInformacionT3;
    }

    public void setTXTInformacionT3(String TXTInformacionT3) {
        this.TXTInformacionT3 = TXTInformacionT3;
    }

    public String getTXTInformacionT4() {
        return TXTInformacionT4;
    }

    public void setTXTInformacionT4(String TXTInformacionT4) {
        this.TXTInformacionT4 = TXTInformacionT4;
    }

    public String getTXTInformacionT5() {
        return TXTInformacionT5;
    }

    public void setTXTInformacionT5(String TXTInformacionT5) {
        this.TXTInformacionT5 = TXTInformacionT5;
    }

    public String getTXTInformacionT6() {
        return TXTInformacionT6;
    }

    public void setTXTInformacionT6(String TXTInformacionT6) {
        this.TXTInformacionT6 = TXTInformacionT6;
    }

    @Override
    public String toString() {
        return "InfoRecyclerView{" +
                "TXTInformacionT0='" + TXTInformacionT0 + '\'' +
                "TXTInformacionT1='" + TXTInformacionT1 + '\'' +
                ", TXTInformacionT2='" + TXTInformacionT2 + '\'' +
                ", TXTInformacionT3='" + TXTInformacionT3 + '\'' +
                ", TXTInformacionT4='" + TXTInformacionT4 + '\'' +
                ", TXTInformacionT5='" + TXTInformacionT5 + '\'' +
                ", TXTInformacionT6='" + TXTInformacionT6 + '\'' +
                '}';
    }
}
