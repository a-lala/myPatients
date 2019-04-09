package com.ba.mypatient;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.card.MaterialCardView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

/**
 * TODO: document your custom view class.
 */
public class PatientView extends MaterialCardView {
    // for logging
    private static final String LOGTAG = "Patient View";

    // how many days to show, defaults to six weeks, 42 days
    private static final int DAYS_COUNT = 42;

    // default date format
    private static final String NAME = "محمود محمد";

    // date format
    private String name;

    // current displayed month
    private Calendar currentDate = Calendar.getInstance();

    //event handling
    private EventHandler eventHandler = null;

    // internal components
    private LinearLayout header;
    private ImageView btnPrev;
    private ImageView btnNext;
    private TextView txtDate;
    private GridView grid;

    // seasons' rainbow
    int[] rainbow = new int[]{
            R.color.summer,
            R.color.fall,
            R.color.winter,
            R.color.spring
    };

    // month-season association (northern hemisphere, sorry australia :)
    int[] monthSeason = new int[]{2, 2, 3, 3, 3, 0, 0, 0, 1, 1, 1, 2};
    private TextView tv_name;

    public PatientView(Context context) {
        super(context);
    }

    public PatientView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs);
    }

    public PatientView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);
    }

    /**
     * Load control xml layout
     */
    private void initControl(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.patient_view, this);

        loadDateFormat(attrs);
        assignUiElements();
        assignClickHandlers();

//        updateCalendar();
    }

    private void loadDateFormat(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.PatientView);

        try {
            // try to load provided date format, and fallback to default otherwise
            name = ta.getString(R.styleable.PatientView_p_name);
            if (name == null)
                name = NAME;
        } finally {
            ta.recycle();
        }
    }

    private void assignUiElements() {
        // layout is inflated, assign local variables to components
        tv_name = (TextView)findViewById(R.id.tv_name);
        tv_name.setText(name);
    }

    private void assignClickHandlers() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onPress(0);
            }
        });
        // add one month and refresh UI
//        btnNext.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                currentDate.add(Calendar.MONTH, 1);
////                updateCalendar();
//            }
//        });
//
//        // subtract one month and refresh UI
//        btnPrev.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                currentDate.add(Calendar.MONTH, -1);
////                updateCalendar();
//            }
//        });
//
//        // long-pressing a day
//        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//
//            @Override
//            public void onItemClick(AdapterView<?> view, View cell, int position, long id) {
//                // handle long-press
//                if (eventHandler == null)
//                    return;
//
////                view.setBackgroundResource(R.drawable.reminder);
//                Date date = (Date) view.getItemAtPosition(position);
//                eventHandler.onDayLongPress(date);
//
//                HashSet<Date> events = new HashSet<>();
//                events.add(date);
////                updateCalendar(events);
//                return;
//            }
//        });
    }
    public void onPress(int id) {
        Fragment f = new CustomDateDetailsFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
//        args.putString("time", );
        f.setArguments(args);
        loadfragment(f);
    }

    Boolean loadfragment(Fragment fragment) {
        if (fragment != null) {
            ((Activity) getContext()).getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    /**
     * Assign event handler to be passed needed events
     */
    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    /**
     * This interface defines what events to be reported to
     * the outside world
     */
    public interface EventHandler {
        void onDayLongPress(Date date);
    }}
