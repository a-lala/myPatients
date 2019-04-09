package com.ba.mypatient;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.HashSet;

/**
 * TODO: document your custom view class.
 */
public class MyCustomDate extends LinearLayout {
    // for logging
    private static final String LOGTAG = "Custom Date View";

    // how many days to show, defaults to six weeks, 42 days
    private static final int DAYS_COUNT = 42;

    // default date format
    private static final String DATE_TIME = "10:30";
    private static final String NAME = "محمود محمد";

    // date format
    private String dateFormat;

    //event handling
    private EventHandler eventHandler = null;

    // internal components
    public TextView tv_name;
    public TextView tv_date_time;


    // seasons' rainbow
    int[] rainbow = new int[]{
            R.color.summer,
            R.color.fall,
            R.color.winter,
            R.color.spring
    };

    // month-season association (northern hemisphere, sorry australia :)
    int[] monthSeason = new int[]{2, 2, 3, 3, 3, 0, 0, 0, 1, 1, 1, 2};
    private String date_time;
    private String name;
    private String back_color;
    private boolean submit;

    public MyCustomDate(Context context) {
        super(context);
    }

    public MyCustomDate(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs);
    }

    public MyCustomDate(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initControl(context, attrs);
    }

    /**
     * Load control xml layout
     */
    private void initControl(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.my_custom_date, this);

        loadDataFormat(attrs);
        assignUiElements();
        assignClickHandlers();

        update();
    }

    private void loadDataFormat(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.MyCustomDate);

        try {
            // try to load provided date format, and fallback to default otherwise
            date_time = ta.getString(R.styleable.MyCustomDate_date_time);
            name = ta.getString(R.styleable.MyCustomDate_name);
            back_color = ta.getString(R.styleable.MyCustomDate_exampleColor);
            submit = ta.getBoolean(R.styleable.MyCustomDate_submit,false);
            if (date_time == null)
                date_time = DATE_TIME;
            if (name == null)
                name = NAME;
            if (back_color == null)
                back_color = "#FFFFFFFF";
        } finally {
            ta.recycle();
        }
    }

    private void assignUiElements() {
        // layout is inflated, assign local variables to components
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_name.setText(name);
        tv_date_time = (TextView) findViewById(R.id.tv_date_time);
        tv_date_time.setText(date_time);
        tv_name.setBackgroundColor(Color.parseColor(back_color));
        if (submit == true)
            ((Button) findViewById(R.id.btn_submit)).setVisibility(VISIBLE);
    }

    private void assignClickHandlers() {
        // add one month and refresh UI
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "MyCustomDate clicked", Toast.LENGTH_SHORT).show();
                onPress(0);
            }
        });
    }

    /**
     * Display dates correctly in grid
     */
    public void update() {
//        update(null);
    }

    /**
     * Display dates correctly in grid
     */
    public void update(HashSet<Date> events) {
//        ArrayList<Date> cells = new ArrayList<>();
//        Calendar calendar = (Calendar) currentDate.clone();
//
//        // determine the cell for current month's beginning
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
//        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;
//
//        // move calendar backwards to the beginning of the week
//        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);
//
//        // fill cells
//        while (cells.size() < DAYS_COUNT) {
//            cells.add(calendar.getTime());
//            calendar.add(Calendar.DAY_OF_MONTH, 1);
//        }
//
//
//
//        // update title
//        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
//        txtDate.setText(sdf.format(currentDate.getTime()));
//
//        // set header color according to current season
//        int month = currentDate.get(Calendar.MONTH);
//        int season = monthSeason[month];
//        int color = rainbow[season];
//
//        header.setBackgroundColor(getResources().getColor(color));
    }

//
//    private class CalendarAdapter extends ArrayAdapter<Date> {
//        // days with events
//        private HashSet<Date> eventDays;
//
//        // for view inflation
//        private LayoutInflater inflater;
//
//        public CalendarAdapter(Context context, ArrayList<Date> days, HashSet<Date> eventDays) {
//            super(context, R.layout.control_calendar_day, days);
//            this.eventDays = eventDays;
//            inflater = LayoutInflater.from(context);
//        }
//
//        @Override
//        public View getView(int position, View view, ViewGroup parent) {
//            // day in question
//            Date date = getItem(position);
//            int day = date.getDate();
//            int month = date.getMonth();
//            int year = date.getYear();
//
//            // today
//            Date today = new Date();
//
//            // inflate item if it does not exist yet
//            if (view == null)
//                view = inflater.inflate(R.layout.control_calendar_day, parent, false);
//
//            // if this day has an event, specify event image
//            view.setBackgroundResource(0);
//            if (eventDays != null)
//            {
//                for (Date eventDate : eventDays)
//                {
//                    if (eventDate.getDate() == day &&
//                            eventDate.getMonth() == month &&
//                            eventDate.getYear() == year)
//                    {
//                        // mark this day for event
//                        view.setBackgroundResource(R.drawable.reminder);
//                        break;
//                    }
//                }
//            }
//
//            // clear styling
//            ((TextView) view).setTypeface(null, Typeface.NORMAL);
//            ((TextView) view).setTextColor(Color.BLACK);
//
//            if (month != today.getMonth() || year != today.getYear()) {
//                // if this day is outside current month, grey it out
//                ((TextView) view).setTextColor(getResources().getColor(R.color.greyed_out));
//            } else if (day == today.getDate()) {
//                // if it is today, set it to blue/bold
//                ((TextView) view).setTypeface(null, Typeface.BOLD);
//                ((TextView) view).setTextColor(getResources().getColor(R.color.today));
//            }
//
//            // set text
//            ((TextView) view).setText(String.valueOf(date.getDate()));
//
//            return view;
//        }
//    }
//

    /**
     * Assign event handler to be passed needed events
     */
    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }


    public void onPress(int id) {
//        Fragment f = new CustomDateDetailsFragment();
//        Bundle args = new Bundle();
//        args.putString("name", name);
//        args.putString("time", date_time);
//        f.setArguments(args);
//        loadfragment(f);
    }

    public void setDateText(String date) {
        tv_date_time.setText(date);
    }


    /**
     * This interface defines what events to be reported to
     * the outside world
     */
    public interface EventHandler {
        void onPress(int id);
    }

    Boolean loadfragment(Fragment fragment) {
        if (fragment != null) {
            ((Activity) getContext()).getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.date_fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
