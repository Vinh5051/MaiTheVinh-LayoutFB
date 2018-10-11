package com.example.vinhmai.job_manager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView tvDate, tvTime;
    EditText edJob, edContent;
    Button btnAdd, btnDate, btnTime;

    ArrayList<Job> arrJob = new ArrayList<Job>();
    ArrayAdapter<Job> arrAdapter =  null;

    ListView lvJob;
    Calendar cal;
    Date dateFinish;
    Date timeFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFormWidgets();
        getDefaultInfor();
        addEventFormWidgets();

    }

    public  void getFormWidgets(){
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
        edJob = findViewById(R.id.edJob);
        edContent = findViewById(R.id.edContent);
        btnAdd = findViewById(R.id.btnAdd);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);
        lvJob = findViewById(R.id.lvJob);

        arrAdapter = new ArrayAdapter<Job>(this, android.R.layout.simple_list_item_1,arrJob);
        lvJob.setAdapter(arrAdapter);
    }



    public void getDefaultInfor()
    {
        cal=Calendar.getInstance();
        SimpleDateFormat dft = null;
        dft=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate=dft.format(cal.getTime());
        tvDate.setText(strDate);
        dft=new SimpleDateFormat("hh:mm a",Locale.getDefault());
        String strTime=dft.format(cal.getTime());
        tvTime.setText(strTime);
        dft=new SimpleDateFormat("HH:mm",Locale.getDefault());
        tvTime.setTag(dft.format(cal.getTime()));

        edJob.requestFocus();
        dateFinish=cal.getTime();
        timeFinish=cal.getTime();
    }

    public void addEventFormWidgets() {
        btnDate.setOnClickListener(new MyButtonEvent());
        btnTime.setOnClickListener(new MyButtonEvent());
        btnAdd.setOnClickListener(new MyButtonEvent());
        lvJob.setOnItemClickListener(new MyListViewEvent());
        lvJob.setOnItemLongClickListener(new MyListViewEvent());
    }

    private class MyButtonEvent implements OnClickListener {
        @Override
        public void onClick(View v) {
            switch(v.getId())
            {
                case R.id.btnDate:
                    showDatePickerDialog();
                    break;
                case R.id.btnTime:
                    showTimePickerDialog();
                    break;
                case R.id.btnAdd:
                    processAddJob();
                    break;
            }
        }
    }
    public void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener callback=new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                tvDate.setText((dayOfMonth) +"/"+(monthOfYear+1)+"/"+year);
                cal.set(year, monthOfYear, dayOfMonth);
                dateFinish=cal.getTime();
            }
        };
        String s= tvDate.getText()+"";
        String strArrtmp[] = s.split("/");
        int ngay = Integer.parseInt(strArrtmp[0]);
        int thang = Integer.parseInt(strArrtmp[1])-1;
        int nam = Integer.parseInt(strArrtmp[2]);

        DatePickerDialog pic = new DatePickerDialog(MainActivity.this, callback, nam, thang, ngay);
        pic.setTitle("Chọn ngày hoàn thành");
        pic.show();
    }

    public void showTimePickerDialog() {
        TimePickerDialog.OnTimeSetListener callback=new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String s=hourOfDay +":"+minute;
                int hourTam=hourOfDay;

                if(hourTam>12) hourTam=hourTam-12;
                tvTime.setText (hourTam +":"+minute +(hourOfDay>12?" PM":" AM"));
                tvTime.setTag(s);

                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                timeFinish=cal.getTime();
            }
        };

        String s=tvTime.getTag()+"";
        String strArr[]=s.split(":");
        int gio=Integer.parseInt(strArr[0]);
        int phut=Integer.parseInt(strArr[1]);

        TimePickerDialog time=new TimePickerDialog(MainActivity.this, callback, gio, phut, true);
        time.setTitle("Chọn giờ hoàn thành");
        time.show();
    }

    public void processAddJob() {
        String title=edJob.getText()+"";
        String description=edContent.getText()+"";
        Job job=new Job(title, description, dateFinish, timeFinish);

        arrJob.add(job);
        arrAdapter.notifyDataSetChanged();

        edJob.setText("");
        edContent.setText("");
        edJob.requestFocus();
    }


    private class MyListViewEvent implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            arrJob.remove(arg2);
            arrAdapter.notifyDataSetChanged();
            return false;
        }

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(MainActivity.this,
                    arrJob.get(arg2).getContent(),
                    Toast.LENGTH_LONG).show();
        }

    }

}
