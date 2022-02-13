package com.example.parking;

public class ParkQuantity {
    private String Area;
    private String Quota;
    private String QuotaGuest;
    private String AllowGuest;
    private String Total;
    private String MembersCars;
    private String MaxGuest;
    private String GuestCars;

    public ParkQuantity() {
        this.Area="";
        this.Quota="";
        this.QuotaGuest="";
        this.AllowGuest="";
        this.Total="";
        this.MembersCars="";
        this.MaxGuest="";
        this.GuestCars="";
    }

    public ParkQuantity(String Area,String Quota,String QuotaGuest,String AllowGuest,String Total,String MembersCars,String MaxGuest,String GuestCars) {
        this.Area=Area;
        this.Quota=Quota;
        this.QuotaGuest=QuotaGuest;
        this.AllowGuest=AllowGuest;
        this.Total=Total;
        this.MembersCars=MembersCars;
        this.MaxGuest=MaxGuest;
        this.GuestCars=GuestCars;
    }

    public ParkQuantity(ParkQuantity parkquantity) {
        this.Area=parkquantity.Area;
        this.Quota=parkquantity.Quota;
        this.QuotaGuest=parkquantity.QuotaGuest;
        this.AllowGuest=parkquantity.AllowGuest;
        this.Total=parkquantity.Total;
        this.MembersCars=parkquantity.MembersCars;
        this.MaxGuest=parkquantity.MaxGuest;
        this.GuestCars=parkquantity.GuestCars;
    }

    public ParkQuantity getParkQuantity() {
        return this;
    }

    public void setArea(String Area){
        this.Area=Area;
    }

    public String getArea(){
        return this.Area;
    }

    public void setQuota(String Quota){
        this.Quota=Quota;
    }

    public String getQuota(){
        return this.Quota;
    }

    public void setQuotaGuest(String QuotaGuest){
        this.QuotaGuest=QuotaGuest;
    }

    public String getQuotaGuest(){
        return this.QuotaGuest;
    }

    public void setAllowGuest(String AllowGuest){
        this.AllowGuest=AllowGuest;
    }

    public String getAllowGuest(){
        return this.AllowGuest;
    }

    public void setTotal(String Total){
        this.Total=Total;
    }

    public String getTotal(){
        return this.Total;
    }

    public void setMembersCars(String MembersCars){
        this.MembersCars=MembersCars;
    }

    public String getMembersCars(){
        return this.MembersCars;
    }

    public void setMaxGuest(String MaxGuest){
        this.MaxGuest=MaxGuest;
    }

    public String getMaxGuest(){
        return this.MaxGuest;
    }

    public void setGuestCars(String GuestCars){
        this.GuestCars=GuestCars;
    }

    public String getGuestCars(){
        return this.GuestCars;
    }

    public String toString(){
        return "Area="+Area+"\nQuota="+Quota+"\nTotal="+Total;
    }
}
