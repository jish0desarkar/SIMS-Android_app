package com.simshospitals;

public class detail {
    String ipid;
    String patientname;
    String age;
    String agetype;
    String admitdatetime;
    String dischargedatetime;
    String consultant;
    String billamount;
    String discount;
    String authorisedby;
    String reason;
    String requestedby;

    public detail(String  ipid,
                  String patientname,
                  String age,
                  String agetype,
                  String admitdatetime,
                  String dischargedatetime,
                  String consultant,
                  String billamount ,
                  String discount,
                  String authorisedby,
                  String reason,
                  String requestedby)
    {
        this.ipid=ipid;
        this.patientname=patientname;
        this.age=age;
        this.agetype=agetype;
        this.admitdatetime=admitdatetime;
        this.dischargedatetime=dischargedatetime;
        this.consultant=consultant;
        this.billamount=billamount;
        this.discount=discount;
        this.authorisedby=authorisedby;
        this.reason=reason;
        this.requestedby=requestedby;

    }

    public String getIpid()
    {
        return this.ipid;
    }
    public String getPatientname()
    {
        return  this.patientname;
    }
    public String getAge()
    {
        return this.age;
    }
    public String getAgetype()
    {
        return  this.agetype;
    }
    public String getAdmitdatetime()
    {
        return this.admitdatetime;
    }
    public String getDischargedatetime()
    {
        return this.dischargedatetime;
    }
    public String getConsultant()
    {
        return this.consultant;
    }
    public String getBillamount()
    {
        return this.billamount;
    }
    public String getDiscount()
    {
        return this.discount;
    }
    public String getAuthorisedby()
    {
        return this.authorisedby;
    }
    public String getReason()
    {
        return this.reason;
    }
    public  String getRequestedby()
    {
        return this.requestedby;
    }


}
