package JavaBean;

/**
 * Created by Administrator on 2015/5/21.
 */
public class ResultData {
    private FXJG fxjg;
    private String bagang;
    private String tzms;
    private String bzts;
    private String jl;
    private String yyzd;
    private String jkshjj;

    public FXJG getFxjg() {
        return fxjg;
    }

    public void setFxjg(FXJG fxjg) {
        this.fxjg = fxjg;
    }

    public String getBagang() {
        return bagang;
    }

    public void setBagang(String bagang) {
        this.bagang = bagang;
    }

    public String getTzms() {
        return tzms;
    }

    public void setTzms(String tzms) {
        this.tzms = tzms;
    }

    public String getBzts() {
        return bzts;
    }

    public void setBzts(String bzts) {
        this.bzts = bzts;
    }

    public String getJl() {
        return jl;
    }

    public void setJl(String jl) {
        this.jl = jl;
    }

    public String getYyzd() {
        return yyzd;
    }

    public void setYyzd(String yyzd) {
        this.yyzd = yyzd;
    }

    public String getJkshjj() {
        return jkshjj;
    }

    public void setJkshjj(String jkshjj) {
        this.jkshjj = jkshjj;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "fxjg=" + fxjg +
                ", bagang='" + bagang + '\'' +
                ", tzms='" + tzms + '\'' +
                ", bzts='" + bzts + '\'' +
                ", jl='" + jl + '\'' +
                ", yyzd='" + yyzd + '\'' +
                ", jkshjj='" + jkshjj + '\'' +
                '}';
    }
}
