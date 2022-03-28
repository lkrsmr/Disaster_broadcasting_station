package Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoBean {
    private int id;             //主键
    private String position;     //地理位置
    private float magnitude;    //震级
    private String dateTime;      //日期时间
    private float longitude;   //经度
    private float latitude;     //维度
    private int depth;          //深度
    private String date;      //日期，不带时间
    private String url;        //跳转链接

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public float getMagnitude() {

        return magnitude;
    }

    public void setMagnitude(float magnitude) {
        this.magnitude = magnitude;
    }

    public String getDateTime() {

        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longtitude) {
        this.longitude = longtitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
