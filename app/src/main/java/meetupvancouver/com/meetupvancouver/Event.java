package meetupvancouver.com.meetupvancouver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//@JsonIgnoreProperties({"Date","EventDescription","HostName","Time","Location"})
public class Event {
    String EventName;
    String Date;
    String EventDescription;
    String EventID;
    String HostName;
    String LocationLat;
    String LocationLon;
    String Time;

    public String getLocationLat(){
        return LocationLat;
    }

    public String getLocationLon(){
        return LocationLon;
    }

    public String getEventID(){
        return EventID;
    }

    public String toString() { return "User{EventName='"+EventName+"', Date='"+Date+"', EventDescription="+EventDescription+", HostName='"+HostName+"', Time='"+Time+"'/'}"; }

    }
