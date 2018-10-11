package com.example.vinhmai.job_manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Job {
    private String title;
    private String content;
    private Date dateFinish;
    private Date timeFinish;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public Date getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(Date timeFinish) {
        this.timeFinish = timeFinish;
    }



    public Job(String title, String content, Date dateFinish, Date  timeFinish ){
        super();
        this.title = title;
        this.content = content;
        this.dateFinish = dateFinish;
        this.timeFinish = timeFinish;
    }
    public Job(){
        super();
    }

    public String getDateFormat(Date d)
    {
        SimpleDateFormat dft=new
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dft.format(d);
    }

    public String getHourFormat(Date d)
    {
        SimpleDateFormat dft=new
                SimpleDateFormat("hh:mm a", Locale.getDefault());
        return dft.format(d);
    }
    @Override
    public String toString() {
        return this.title+"-"+
                getDateFormat(this.dateFinish)+"-"+
                getHourFormat(this.timeFinish);
    }

}
