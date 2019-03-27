package com.vectorinc.ezinwavictor.gpa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vectorinc.ezinwavictor.gpa.Year_Five.Tab_layout_yr5.tablayout_yr5;
import com.vectorinc.ezinwavictor.gpa.Year_Five.first_semester_yr5;
import com.vectorinc.ezinwavictor.gpa.Year_Five.second_semester_yr5;
import com.vectorinc.ezinwavictor.gpa.Year_Four.Tab_layout_yr4.tablayout_yr4;
import com.vectorinc.ezinwavictor.gpa.Year_Four.first_semester_yr4;
import com.vectorinc.ezinwavictor.gpa.Year_Four.second_semester_yr4;
import com.vectorinc.ezinwavictor.gpa.Year_One.Tablayout_Year_One.tablayoutyr1;
import com.vectorinc.ezinwavictor.gpa.Year_One.firstsemesteryr1;
import com.vectorinc.ezinwavictor.gpa.Year_One.secondsemesteryr1;
import com.vectorinc.ezinwavictor.gpa.Year_Six.Tab_Layout_yr6.tablayout_yr6;
import com.vectorinc.ezinwavictor.gpa.Year_Six.first_semester_yr6;
import com.vectorinc.ezinwavictor.gpa.Year_Six.second_semester_yr6;
import com.vectorinc.ezinwavictor.gpa.Year_Three.Tab_layout_yr3.tablayout_yr3;
import com.vectorinc.ezinwavictor.gpa.Year_Three.first_semester_yr3;
import com.vectorinc.ezinwavictor.gpa.Year_Three.second_semester_yr3;
import com.vectorinc.ezinwavictor.gpa.Year_Two.TabLayout_yr2.tablayout_year_2;
import com.vectorinc.ezinwavictor.gpa.Year_Two.first_semester_yr2;
import com.vectorinc.ezinwavictor.gpa.Year_Two.second_semester_yr2;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.DecimalFormat;


public class select_level extends AppCompatActivity {

    Spinner spinner_level;
    private AdView mAdView,mAdView1,mAdView3;
    private  AdRequest adRequest;
    android.support.v7.widget.Toolbar toolbar1;

    String[] level_yrs = {"Select Year", "Year One", "Year Two", "Year Three", "Year Four", "Year Five", "Year Six"};
    TextView level_txt_view, level_grade_view, total_yr_credit, level_txt_cumulative, total_crdit_cum, gp_cum_txt_view;
    Button show_the_level;


    double yearOne_Grade_Total, yearTow_Grade_Total, yearThree_Grade_Total, yearFour_Grade_Total, yearFive_Grade_Total, yearSix_Grade_Total;
    int YearOne_Total_CreditLoad, YearTwo_Total_CreditLoad, YearThree_Total_CreditLoad, YearFour_Total_CreditLoad, YearFive_Total_CreditLoad, YearSix_Total_CreditLoad;

    private static final String SAVED_LEVEL_RESULT_YR1 = "year_one_yr1";
    RelativeLayout selectlevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(null);
        selectlevel = findViewById(R.id.fullayout);
        setContentView(R.layout.activity_select_level);
        toolbar1 = findViewById(R.id.toolbar_select_level);
        setSupportActionBar(toolbar1);
        spinner_level = findViewById(R.id.spinner_year_selection);
        ArrayAdapter<String> level_adapter = new ArrayAdapter<>(this, R.layout.custom_spinner_item, level_yrs);
        level_adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner_level.setAdapter(level_adapter);
        level_txt_view = findViewById(R.id.select_level_yr_gp);
        level_grade_view = findViewById(R.id.select_level_yr_grade);
        total_yr_credit = findViewById(R.id.total_level_yr_creditload);
        total_crdit_cum = findViewById(R.id.total_credit_load_cumlative);
        level_txt_cumulative = findViewById(R.id.level_comment_cummlative);
        gp_cum_txt_view = findViewById(R.id.gp_cumlative);
        total_yr_credit.setVisibility(View.INVISIBLE);
        show_the_level = findViewById(R.id.show_level);
        level_txt_view.setVisibility(View.INVISIBLE);
        level_grade_view.setVisibility(View.INVISIBLE);
        show_the_level.setVisibility(View.INVISIBLE);
        CheckSpinner();
        CumulativeResult();

        mAdView = findViewById(R.id.adView);
        mAdView1 = findViewById(R.id.adView1);
        mAdView3 = findViewById(R.id.adView3);
       adRequest = new AdRequest.Builder().build();
       ShowBanner();





        show_the_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner_level.getSelectedItem().toString().equals("Year One")) {
                    Intent intent = new Intent(getApplicationContext(), tablayoutyr1.class);
                    startActivity(intent);
                } else if (spinner_level.getSelectedItem().toString().equals("Year Two")) {
                    Intent intent = new Intent(getApplicationContext(), tablayout_year_2.class);
                    startActivity(intent);
                } else if (spinner_level.getSelectedItem().toString().equals("Year Three")) {
                    Intent intent = new Intent(getApplicationContext(), tablayout_yr3.class);
                    startActivity(intent);
                } else if (spinner_level.getSelectedItem().toString().equals("Year Four")) {
                    Intent intent = new Intent(getApplicationContext(), tablayout_yr4.class);
                    startActivity(intent);
                } else if (spinner_level.getSelectedItem().toString().equals("Year Five")) {
                    Intent intent = new Intent(getApplicationContext(), tablayout_yr5.class);
                    startActivity(intent);
                } else if (spinner_level.getSelectedItem().toString().equals("Year Six")) {
                    Intent intent = new Intent(getApplicationContext(), tablayout_yr6.class);
                    startActivity(intent);
                } else {

                }
            }
        });


    }

    private void CheckSpinner() {

        spinner_level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ShowBanner();


                if (0 == position) {
                    level_txt_view.setVisibility(View.INVISIBLE);
                    level_grade_view.setVisibility(View.INVISIBLE);
                    show_the_level.setVisibility(View.INVISIBLE);
                    total_yr_credit.setVisibility(View.INVISIBLE);

                } else if (1 == position) {

                    show_the_level.setVisibility(View.VISIBLE);
                    total_yr_credit.setVisibility(View.VISIBLE);
                    level_txt_view.setVisibility(View.VISIBLE);
                    level_grade_view.setVisibility(View.VISIBLE);
                    SavedDataYearOne();

                } else if (2 == position) {
                    show_the_level.setVisibility(View.VISIBLE);
                    level_txt_view.setVisibility(View.VISIBLE);
                    total_yr_credit.setVisibility(View.VISIBLE);
                    level_grade_view.setVisibility(View.VISIBLE);
                    SavedDataYearTwo();


                } else if (3 == position) {
                    show_the_level.setVisibility(View.VISIBLE);
                    level_txt_view.setVisibility(View.VISIBLE);
                    total_yr_credit.setVisibility(View.VISIBLE);
                    level_grade_view.setVisibility(View.VISIBLE);
                    SavedDataYearThree();

                } else if (4 == position) {
                    show_the_level.setVisibility(View.VISIBLE);
                    total_yr_credit.setVisibility(View.VISIBLE);
                    level_txt_view.setVisibility(View.VISIBLE);
                    level_grade_view.setVisibility(View.VISIBLE);
                    SavedDataYearFour();

                } else if (5 == position) {
                    show_the_level.setVisibility(View.VISIBLE);
                    level_txt_view.setVisibility(View.VISIBLE);
                    total_yr_credit.setVisibility(View.VISIBLE);
                    level_grade_view.setVisibility(View.VISIBLE);
                    SavedDataYearFive();

                } else if (6 == position) {
                    show_the_level.setVisibility(View.VISIBLE);
                    total_yr_credit.setVisibility(View.VISIBLE);
                    level_txt_view.setVisibility(View.VISIBLE);
                    level_grade_view.setVisibility(View.VISIBLE);
                    SavedDataYearSix();

                } else {
                    level_txt_view.setVisibility(View.INVISIBLE);
                    level_grade_view.setVisibility(View.INVISIBLE);
                    show_the_level.setVisibility(View.INVISIBLE);
                    total_yr_credit.setVisibility(View.INVISIBLE);


                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ShowBanner();

            }
        });

    }

    private void SavedDataYearOne() {

        SharedPreferences firstsaved = getSharedPreferences(firstsemesteryr1.COURSE_NAME, MODE_PRIVATE);
        SharedPreferences secondsaved = getSharedPreferences(secondsemesteryr1.SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
        int CreditLoadTotalfirst = firstsaved.getInt("ADDSK", 0);
        int CreditLoadTotalSecond = secondsaved.getInt("ADDSK", 0);

        String Total_Spinner1 = firstsaved.getString("TOTAL_SPINNER", "");
        String Total_Spinner2 = secondsaved.getString("TOTAL_SPINNER", "");
        double first_semester_grade_total;
        double second_semester_grade_total;
        if (Total_Spinner1.isEmpty()) {
            first_semester_grade_total = 0;
        } else {
            first_semester_grade_total = Double.parseDouble(String.valueOf(Total_Spinner1));

        }
        if (Total_Spinner2.isEmpty()) {
            second_semester_grade_total = 0;
        } else {
            second_semester_grade_total = Double.parseDouble(String.valueOf(Total_Spinner2));

        }


        Log.d("SECOND_SEMESTER_TOTAL", " " + CreditLoadTotalSecond);
        Log.d("FIRST_SEMESTER_TOTAL", " " + CreditLoadTotalfirst);
        Log.d("SPINNER_TOTAL_FIRST", "" + Total_Spinner1);
        Log.d("SPINNER_TOTAL_SECOND", "" + Total_Spinner2);


        if (((first_semester_grade_total == 0) || (first_semester_grade_total == 0)) && ((Total_Spinner1.isEmpty()) || (Total_Spinner2.isEmpty()))) {
            level_txt_view.setText("GP:");
            level_grade_view.setText("GRADE:");
            total_yr_credit.setText("TOTAL CREDIT LOAD: ");

        } else {
            SharedPreferences savedResult_yr1 = getBaseContext().getSharedPreferences(SAVED_LEVEL_RESULT_YR1, MODE_PRIVATE);
            SharedPreferences.Editor preEdit = savedResult_yr1.edit();
            YearOne_Total_CreditLoad = CreditLoadTotalfirst + CreditLoadTotalSecond;
            yearOne_Grade_Total = first_semester_grade_total + second_semester_grade_total;
            double finalScore = yearOne_Grade_Total / YearOne_Total_CreditLoad;


            DecimalFormat gpa_format = new DecimalFormat("#.#");
            String final_gpa = gpa_format.format(finalScore);
            level_txt_view.setText("GP: " + final_gpa);
            total_yr_credit.setText("TOTAL CREDIT LOAD:  " + YearOne_Total_CreditLoad);

            if (finalScore >= 4.5) {
                level_grade_view.setText("GRADE:  FIRST CLASS ");

            } else if ((finalScore >= 3.5) && (finalScore < 4.5)) {
                level_grade_view.setText("GRADE:  SECOND CLASS UPPER ");
            } else if ((finalScore >= 2.5) && (finalScore < 3.5)) {
                level_grade_view.setText("GRADE:  SECOND CLASS LOWER ");
            } else if ((finalScore >= 1.5) && (finalScore < 2.5)) {
                level_grade_view.setText("GRADE:  PASS");
            } else {
                level_grade_view.setText("GRADE:  FAILED");
            }

        }


    }

    private void SavedDataYearTwo() {

        SharedPreferences firstsaved = getSharedPreferences(first_semester_yr2.COURSE_NAME, MODE_PRIVATE);
        SharedPreferences secondsaved = getSharedPreferences(second_semester_yr2.SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
        int CreditLoadTotalfirst = firstsaved.getInt("ADDSK", 0);
        int CreditLoadTotalSecond = secondsaved.getInt("ADDSK", 0);

        String Total_Spinner1 = firstsaved.getString("TOTAL_SPINNER", "");
        String Total_Spinner2 = secondsaved.getString("TOTAL_SPINNER", "");
        double first_semester_grade_total;
        double second_semester_grade_total;

        if (Total_Spinner1.isEmpty()) {
            first_semester_grade_total = 0;
        } else {
            first_semester_grade_total = Double.parseDouble(String.valueOf(Total_Spinner1));

        }
        if (Total_Spinner2.isEmpty()) {
            second_semester_grade_total = 0;
        } else {
            second_semester_grade_total = Double.parseDouble(String.valueOf(Total_Spinner2));

        }


        Log.d("SECOND_SEMESTER_TOTAL", " " + CreditLoadTotalSecond);
        Log.d("FIRST_SEMESTER_TOTAL", " " + CreditLoadTotalfirst);
        Log.d("SPINNER_TOTAL_FIRST", "" + Total_Spinner1);
        Log.d("SPINNER_TOTAL_SECOND", "" + Total_Spinner2);


        if (((first_semester_grade_total == 0) || (first_semester_grade_total == 0)) && ((Total_Spinner1.isEmpty()) || (Total_Spinner2.isEmpty()))) {
            level_txt_view.setText("GP:  ");
            level_grade_view.setText("GRADE: ");
            total_yr_credit.setText("TOTAL CREDITLOAD: ");

        } else {

            YearTwo_Total_CreditLoad = CreditLoadTotalfirst + CreditLoadTotalSecond;
            yearTow_Grade_Total = first_semester_grade_total + second_semester_grade_total;
            double finalScore = yearTow_Grade_Total / YearTwo_Total_CreditLoad;
            DecimalFormat gpa_format = new DecimalFormat("#.#");
            String final_gpa = gpa_format.format(finalScore);
            level_txt_view.setText("GP: " + final_gpa);
            total_yr_credit.setText("TOTAL CREDIT LOAD: " + YearTwo_Total_CreditLoad);

            if (finalScore >= 4.5) {
                level_grade_view.setText("GRADE:  FIRST CLASS ");

            } else if ((finalScore >= 3.5) && (finalScore < 4.5)) {
                level_grade_view.setText("GRADE:  SECOND CLASS UPPER ");
            } else if ((finalScore >= 2.5) && (finalScore < 3.5)) {
                level_grade_view.setText("GRADE:  SECOND CLASS LOWER ");
            } else if ((finalScore >= 1.5) && (finalScore < 2.5)) {
                level_grade_view.setText("GRADE:  PASS");
            } else {
                level_grade_view.setText("GRADE:  FAILED");
            }
        }


    }

    private void SavedDataYearThree() {

        SharedPreferences firstsaved = getSharedPreferences(first_semester_yr3.COURSE_NAME, MODE_PRIVATE);
        SharedPreferences secondsaved = getSharedPreferences(second_semester_yr3.SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
        int CreditLoadTotalfirst = firstsaved.getInt("ADDSK", 0);
        int CreditLoadTotalSecond = secondsaved.getInt("ADDSK", 0);

        String Total_Spinner1 = firstsaved.getString("TOTAL_SPINNER", "");
        String Total_Spinner2 = secondsaved.getString("TOTAL_SPINNER", "");
        double first_semester_grade_total;
        double second_semester_grade_total;
        if (Total_Spinner1.isEmpty()) {
            first_semester_grade_total = 0;
        } else {
            first_semester_grade_total = Double.parseDouble(String.valueOf(Total_Spinner1));
        }
        if (Total_Spinner2.isEmpty()) {
            second_semester_grade_total = 0;
        } else {
            second_semester_grade_total = Double.parseDouble(String.valueOf(Total_Spinner2));

        }


        Log.d("SECOND_SEMESTER_TOTAL", " " + CreditLoadTotalSecond);
        Log.d("FIRST_SEMESTER_TOTAL", " " + CreditLoadTotalfirst);
        Log.d("SPINNER_TOTAL_FIRST", "" + Total_Spinner1);
        Log.d("SPINNER_TOTAL_SECOND", "" + Total_Spinner2);


        if (((first_semester_grade_total == 0) || (first_semester_grade_total == 0)) && ((Total_Spinner1.isEmpty()) || (Total_Spinner2.isEmpty()))) {
            level_txt_view.setText("GP:  ");
            level_grade_view.setText("GRADE: ");
            total_yr_credit.setText("TOTAL CREDIT LOAD: ");

        } else {

            YearThree_Total_CreditLoad = CreditLoadTotalfirst + CreditLoadTotalSecond;
            yearThree_Grade_Total = first_semester_grade_total + second_semester_grade_total;
            double finalScore = yearThree_Grade_Total / YearThree_Total_CreditLoad;
            DecimalFormat gpa_format = new DecimalFormat("#.#");
            String final_gpa = gpa_format.format(finalScore);
            level_txt_view.setText("GP: " + final_gpa);
            total_yr_credit.setText("TOTAL CREDIT LOAD: " + YearThree_Total_CreditLoad);

            if (finalScore >= 4.5) {
                level_grade_view.setText("GRADE:  FIRST CLASS ");

            } else if ((finalScore >= 3.5) && (finalScore < 4.5)) {
                level_grade_view.setText("GRADE:  SECOND CLASS UPPER ");
            } else if ((finalScore >= 2.5) && (finalScore < 3.5)) {
                level_grade_view.setText("GRADE:  SECOND CLASS LOWER ");
            } else if ((finalScore >= 1.5) && (finalScore < 2.5)) {
                level_grade_view.setText("GRADE:  PASS");
            } else {
                level_grade_view.setText("GRADE:  FAILED");
            }
        }


    }

    private void SavedDataYearFour() {

        SharedPreferences firstsaved = getSharedPreferences(first_semester_yr4.COURSE_NAME, MODE_PRIVATE);
        SharedPreferences secondsaved = getSharedPreferences(second_semester_yr4.SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
        int CreditLoadTotalfirst = firstsaved.getInt("ADDSK", 0);
        int CreditLoadTotalSecond = secondsaved.getInt("ADDSK", 0);

        String Total_Spinner1 = firstsaved.getString("TOTAL_SPINNER", "");
        String Total_Spinner2 = secondsaved.getString("TOTAL_SPINNER", "");
        double first_semester_grade_total;
        double second_semester_grade_total;
        if (Total_Spinner1.isEmpty()) {
            first_semester_grade_total = 0;
        } else {
            first_semester_grade_total = Double.parseDouble(String.valueOf(Total_Spinner1));

        }
        if (Total_Spinner2.isEmpty()) {
            second_semester_grade_total = 0;
        } else {
            second_semester_grade_total = Double.parseDouble(String.valueOf(Total_Spinner2));

        }


        Log.d("SECOND_SEMESTER_TOTAL", " " + CreditLoadTotalSecond);
        Log.d("FIRST_SEMESTER_TOTAL", " " + CreditLoadTotalfirst);
        Log.d("SPINNER_TOTAL_FIRST", "" + Total_Spinner1);
        Log.d("SPINNER_TOTAL_SECOND", "" + Total_Spinner2);


        if (((first_semester_grade_total == 0) || (first_semester_grade_total == 0)) && ((Total_Spinner1.isEmpty()) || (Total_Spinner2.isEmpty()))) {
            level_txt_view.setText("GP:  ");
            level_grade_view.setText("GRADE: ");
            total_yr_credit.setText("TOTAL CREDIT LOAD:  ");

        } else {

            YearFour_Total_CreditLoad = CreditLoadTotalfirst + CreditLoadTotalSecond;
            yearFour_Grade_Total = first_semester_grade_total + second_semester_grade_total;
            double finalScore = yearFour_Grade_Total / YearFour_Total_CreditLoad;
            DecimalFormat gpa_format = new DecimalFormat("#.#");
            String final_gpa = gpa_format.format(finalScore);
            level_txt_view.setText("GP: " + final_gpa);
            total_yr_credit.setText("TOTAL CREDIT LOAD: " + YearFour_Total_CreditLoad);

            if (finalScore >= 4.5) {
                level_grade_view.setText("GRADE:  FIRST CLASS ");

            } else if ((finalScore >= 3.5) && (finalScore < 4.5)) {
                level_grade_view.setText("GRADE:  SECOND CLASS UPPER ");
            } else if ((finalScore >= 2.5) && (finalScore < 3.5)) {
                level_grade_view.setText("GRADE:  SECOND CLASS LOWER ");
            } else if ((finalScore >= 1.5) && (finalScore < 2.5)) {
                level_grade_view.setText("GRADE:  PASS");
            } else {
                level_grade_view.setText("GRADE:  FAILED");
            }
        }


    }

    private void SavedDataYearFive() {

        SharedPreferences firstsaved = getSharedPreferences(first_semester_yr5.COURSE_NAME, MODE_PRIVATE);
        SharedPreferences secondsaved = getSharedPreferences(second_semester_yr5.SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
        int CreditLoadTotalfirst = firstsaved.getInt("ADDSK", 0);
        int CreditLoadTotalSecond = secondsaved.getInt("ADDSK", 0);

        String Total_Spinner1 = firstsaved.getString("TOTAL_SPINNER", "");
        String Total_Spinner2 = secondsaved.getString("TOTAL_SPINNER", "");
        double first_semester_grade_total;
        double second_semester_grade_total;
        if (Total_Spinner1.isEmpty()) {
            first_semester_grade_total = 0;
        } else {
            first_semester_grade_total = Double.parseDouble(String.valueOf(Total_Spinner1));

        }
        if (Total_Spinner2.isEmpty()) {
            second_semester_grade_total = 0;
        } else {
            second_semester_grade_total = Double.parseDouble(String.valueOf(Total_Spinner2));

        }


        Log.d("SECOND_SEMESTER_TOTAL", " " + CreditLoadTotalSecond);
        Log.d("FIRST_SEMESTER_TOTAL", " " + CreditLoadTotalfirst);
        Log.d("SPINNER_TOTAL_FIRST", "" + Total_Spinner1);
        Log.d("SPINNER_TOTAL_SECOND", "" + Total_Spinner2);


        if (((first_semester_grade_total == 0) || (first_semester_grade_total == 0)) && ((Total_Spinner1.isEmpty()) || (Total_Spinner2.isEmpty()))) {
            level_txt_view.setText("GP: ");
            level_grade_view.setText("GRADE: ");
            total_yr_credit.setText("TOTAL CREDIT LOAD: ");

        } else {

            YearFive_Total_CreditLoad = CreditLoadTotalfirst + CreditLoadTotalSecond;
            yearFive_Grade_Total = first_semester_grade_total + second_semester_grade_total;
            double finalScore = yearFive_Grade_Total / YearFive_Total_CreditLoad;
            DecimalFormat gpa_format = new DecimalFormat("#.#");
            String final_gpa = gpa_format.format(finalScore);
            level_txt_view.setText("GP: " + final_gpa);
            total_yr_credit.setText("TOTAL CREDIT LOAD: " + YearFive_Total_CreditLoad);

            if (finalScore >= 4.5) {
                level_grade_view.setText("GRADE:  FIRST CLASS ");

            } else if ((finalScore >= 3.5) && (finalScore < 4.5)) {
                level_grade_view.setText("GRADE:  SECOND CLASS UPPER ");
            } else if ((finalScore >= 2.5) && (finalScore < 3.5)) {
                level_grade_view.setText("GRADE:  SECOND CLASS LOWER ");
            } else if ((finalScore >= 1.5) && (finalScore < 2.5)) {
                level_grade_view.setText("GRADE:  PASS");
            } else {
                level_grade_view.setText("GRADE:  FAILED");
            }
        }


    }

    private void SavedDataYearSix() {

        SharedPreferences firstsaved = getSharedPreferences(first_semester_yr6.COURSE_NAME, MODE_PRIVATE);
        SharedPreferences secondsaved = getSharedPreferences(second_semester_yr6.SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
        int CreditLoadTotalfirst = firstsaved.getInt("ADDSK", 0);
        int CreditLoadTotalSecond = secondsaved.getInt("ADDSK", 0);

        String Total_Spinner1 = firstsaved.getString("TOTAL_SPINNER", "");
        String Total_Spinner2 = secondsaved.getString("TOTAL_SPINNER", "");
        double first_semester_grade_total;
        double second_semester_grade_total;
        if (Total_Spinner1.isEmpty()) {
            first_semester_grade_total = 0;
        } else {
            first_semester_grade_total = Double.parseDouble(String.valueOf(Total_Spinner1));

        }
        if (Total_Spinner2.isEmpty()) {
            second_semester_grade_total = 0;
        } else {
            second_semester_grade_total = Double.parseDouble(String.valueOf(Total_Spinner2));

        }


        Log.d("SECOND_SEMESTER_TOTAL", " " + CreditLoadTotalSecond);
        Log.d("FIRST_SEMESTER_TOTAL", " " + CreditLoadTotalfirst);
        Log.d("SPINNER_TOTAL_FIRST", "" + Total_Spinner1);
        Log.d("SPINNER_TOTAL_SECOND", "" + Total_Spinner2);


        if (((first_semester_grade_total == 0) || (first_semester_grade_total == 0)) && ((Total_Spinner1.isEmpty()) || (Total_Spinner2.isEmpty()))) {
            level_txt_view.setText("GP: ");
            level_grade_view.setText("GRADE: ");
            total_yr_credit.setText("TOTAL CREDIT LOAD: ");

        } else {

            YearSix_Total_CreditLoad = CreditLoadTotalfirst + CreditLoadTotalSecond;
            yearSix_Grade_Total = first_semester_grade_total + second_semester_grade_total;
            double finalScore = yearSix_Grade_Total / YearSix_Total_CreditLoad;
            DecimalFormat gpa_format = new DecimalFormat("#.#");
            String final_gpa = gpa_format.format(finalScore);
            level_txt_view.setText("GP: " + final_gpa);
            total_yr_credit.setText("TOTAL CREDIT LOAD: " + YearSix_Total_CreditLoad);

            if (finalScore >= 4.5) {
                level_grade_view.setText("GRADE:  FIRST CLASS ");

            } else if ((finalScore >= 3.5) && (finalScore < 4.5)) {
                level_grade_view.setText("GRADE:  SECOND CLASS UPPER ");
            } else if ((finalScore >= 2.5) && (finalScore < 3.5)) {
                level_grade_view.setText("GRADE:  SECOND CLASS LOWER ");
            } else if ((finalScore >= 1.5) && (finalScore < 2.5)) {
                level_grade_view.setText("GRADE:  PASS");
            } else {
                level_grade_view.setText("GRADE:  FAILED");
            }
        }


    }


    @Override
    protected void onRestart() {
        spinner_level.setSelection(0);
        CumulativeResult();
        ShowBanner();
        super.onRestart();
    }

    private void CumulativeResult() {

        SavedDataYearOne();
        SavedDataYearTwo();
        SavedDataYearThree();
        SavedDataYearFour();
        SavedDataYearFive();
        SavedDataYearSix();
        int Total_Credit_Load_Cum = YearOne_Total_CreditLoad + YearTwo_Total_CreditLoad + YearThree_Total_CreditLoad + YearFour_Total_CreditLoad + YearFive_Total_CreditLoad + YearSix_Total_CreditLoad;
        double Total_Grade_Cumlative = yearOne_Grade_Total + yearTow_Grade_Total + yearThree_Grade_Total + yearFour_Grade_Total + yearFive_Grade_Total + yearSix_Grade_Total;
        double final_score = Total_Grade_Cumlative / Total_Credit_Load_Cum;
        DecimalFormat gpa_format = new DecimalFormat("#.#");
        String final_gpa = gpa_format.format(final_score);


        gp_cum_txt_view.setText("GP CUMULATIVE : " + final_gpa);
        total_crdit_cum.setText("CUMULATIVE TOTAL CREDIT LOAD: " + Total_Credit_Load_Cum);
        level_txt_cumulative.setText("CUMULATIVE GRADE: NOT AVAILABLE");


        if (final_score >= 4.5) {
            level_txt_cumulative.setText("CUMULATIVE GRADE:  FIRST CLASS ");

        } else if ((final_score >= 3.5) && (final_score < 4.5)) {
            level_txt_cumulative.setText("CUMULATIVE GRADE:  SECOND CLASS UPPER ");
        } else if ((final_score >= 2.5) && (final_score < 3.5)) {
            level_txt_cumulative.setText("CUMULATIVE GRADE:  SECOND CLASS LOWER ");
        } else if ((final_score >= 1.5) && (final_score < 2.5)) {
            level_txt_cumulative.setText("CUMULATIVE GRADE:  PASS");
        } else {
            level_txt_cumulative.setText("CUMULATIVE GRADE:  FAILED");
        }

        if (final_gpa.equals("NaN")) {
            gp_cum_txt_view.setText("GP CUMULATIVE: ");
            level_txt_cumulative.setText("CUMULATIVE GRADE:");
            total_crdit_cum.setText("CUMULATIVE TOTAL CREDIT LOAD:");

        }


    }


    private void YearOneSharedPrefDelete() {
        SharedPreferences YearOne_First = getBaseContext().getSharedPreferences(firstsemesteryr1.COURSE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editoryr1_first = YearOne_First.edit();
        editoryr1_first.putString("COURSE", null);
        editoryr1_first.putInt("ADDSK", 0);
        editoryr1_first.putString("SPINNER", null);
        editoryr1_first.putString("CREDIT_LOAD", null);
        editoryr1_first.putString("TOTAL_SPINNER", null);
        editoryr1_first.putInt("PARENT_COUNT", 0);
        editoryr1_first.putInt("LOAD_ADDITION", 0);
        editoryr1_first.apply();

        SharedPreferences YearOne_Sec = getBaseContext().getSharedPreferences(secondsemesteryr1.SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
        SharedPreferences.Editor editory1_sec = YearOne_Sec.edit();
        editory1_sec.putString("COURSE", null);
        editory1_sec.putString("SPINNER", null);
        editory1_sec.putString("CREDIT_LOAD", null);
        editory1_sec.putString("TOTAL_SPINNER", null);
        editory1_sec.putInt("ADDSK", 0);
        editory1_sec.putInt("PARENT_COUNT", 0);
        editory1_sec.putInt("LOAD_ADDITION", 0);
        editory1_sec.apply();


        level_grade_view.setText("GP: ");
        level_txt_view.setText("GRADE: ");
        total_yr_credit.setText("TOTAL CREDIT LOAD:");

    }

    private void YearTwoSharedPrefDelete() {
        SharedPreferences YearOne_First = getBaseContext().getSharedPreferences(first_semester_yr2.COURSE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editoryr1_first = YearOne_First.edit();
        editoryr1_first.putString("COURSE", null);
        editoryr1_first.putInt("ADDSK", 0);
        editoryr1_first.putString("SPINNER", null);
        editoryr1_first.putString("CREDIT_LOAD", null);
        editoryr1_first.putString("TOTAL_SPINNER", null);
        editoryr1_first.putInt("PARENT_COUNT", 0);
        editoryr1_first.putInt("LOAD_ADDITION", 0);
        editoryr1_first.apply();

        SharedPreferences YearOne_Sec = getBaseContext().getSharedPreferences(second_semester_yr2.SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
        SharedPreferences.Editor editory1_sec = YearOne_Sec.edit();
        editory1_sec.putString("COURSE", null);
        editory1_sec.putString("SPINNER", null);
        editory1_sec.putString("CREDIT_LOAD", null);
        editory1_sec.putString("TOTAL_SPINNER", null);
        editory1_sec.putInt("ADDSK", 0);
        editory1_sec.putInt("PARENT_COUNT", 0);
        editory1_sec.putInt("LOAD_ADDITION", 0);
        editory1_sec.apply();

        level_grade_view.setText("GP: ");
        level_txt_view.setText("GRADE:  ");
        total_yr_credit.setText("TOTAL CREDIT LOAD:");


    }

    private void YearThreeSharedPrefDelete() {
        SharedPreferences YearOne_First = getBaseContext().getSharedPreferences(first_semester_yr3.COURSE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editoryr1_first = YearOne_First.edit();
        editoryr1_first.putString("COURSE", null);
        editoryr1_first.putInt("ADDSK", 0);
        editoryr1_first.putString("SPINNER", null);
        editoryr1_first.putString("CREDIT_LOAD", null);
        editoryr1_first.putString("TOTAL_SPINNER", null);
        editoryr1_first.putInt("PARENT_COUNT", 0);
        editoryr1_first.putInt("LOAD_ADDITION", 0);
        editoryr1_first.apply();

        SharedPreferences YearOne_Sec = getBaseContext().getSharedPreferences(second_semester_yr3.SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
        SharedPreferences.Editor editory1_sec = YearOne_Sec.edit();
        editory1_sec.putString("COURSE", null);
        editory1_sec.putString("SPINNER", null);
        editory1_sec.putString("CREDIT_LOAD", null);
        editory1_sec.putString("TOTAL_SPINNER", null);
        editory1_sec.putInt("ADDSK", 0);
        editory1_sec.putInt("PARENT_COUNT", 0);
        editory1_sec.putInt("LOAD_ADDITION", 0);
        editory1_sec.apply();

        level_grade_view.setText("GP: ");
        level_txt_view.setText("GRADE: ");
        total_yr_credit.setText("TOTAL CREDIT LOAD:");


    }

    private void YearFourSharedPrefDelete() {
        SharedPreferences YearOne_First = getBaseContext().getSharedPreferences(first_semester_yr4.COURSE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editoryr1_first = YearOne_First.edit();
        editoryr1_first.putString("COURSE", null);
        editoryr1_first.putInt("ADDSK", 0);
        editoryr1_first.putString("SPINNER", null);
        editoryr1_first.putString("CREDIT_LOAD", null);
        editoryr1_first.putString("TOTAL_SPINNER", null);
        editoryr1_first.putInt("PARENT_COUNT", 0);
        editoryr1_first.putInt("LOAD_ADDITION", 0);
        editoryr1_first.apply();

        SharedPreferences YearOne_Sec = getBaseContext().getSharedPreferences(second_semester_yr4.SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
        SharedPreferences.Editor editory1_sec = YearOne_Sec.edit();
        editory1_sec.putString("COURSE", null);
        editory1_sec.putString("SPINNER", null);
        editory1_sec.putString("CREDIT_LOAD", null);
        editory1_sec.putString("TOTAL_SPINNER", null);
        editory1_sec.putInt("ADDSK", 0);
        editory1_sec.putInt("PARENT_COUNT", 0);
        editory1_sec.putInt("LOAD_ADDITION", 0);
        editory1_sec.apply();

        level_grade_view.setText("GP: ");
        level_txt_view.setText("GRADE:");
        total_yr_credit.setText("TOTAL CREDIT LOAD");
    }

    private void YearFiveSharedPrefDelete() {
        SharedPreferences YearOne_First = getBaseContext().getSharedPreferences(first_semester_yr5.COURSE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editoryr1_first = YearOne_First.edit();
        editoryr1_first.putString("COURSE", null);
        editoryr1_first.putInt("ADDSK", 0);
        editoryr1_first.putString("SPINNER", null);
        editoryr1_first.putString("CREDIT_LOAD", null);
        editoryr1_first.putString("TOTAL_SPINNER", null);
        editoryr1_first.putInt("PARENT_COUNT", 0);
        editoryr1_first.putInt("LOAD_ADDITION", 0);
        editoryr1_first.apply();

        SharedPreferences YearOne_Sec = getBaseContext().getSharedPreferences(second_semester_yr5.SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
        SharedPreferences.Editor editory1_sec = YearOne_Sec.edit();
        editory1_sec.putString("COURSE", null);
        editory1_sec.putString("SPINNER", null);
        editory1_sec.putString("CREDIT_LOAD", null);
        editory1_sec.putString("TOTAL_SPINNER", null);
        editory1_sec.putInt("ADDSK", 0);
        editory1_sec.putInt("PARENT_COUNT", 0);
        editory1_sec.putInt("LOAD_ADDITION", 0);
        editory1_sec.apply();
        level_grade_view.setText("GP:");
        level_txt_view.setText("GRADE: ");
        total_yr_credit.setText("TOTAL CREDITLOAD: ");
    }

    private void YearSixSharedPrefDelete() {
        SharedPreferences YearOne_First = getBaseContext().getSharedPreferences(first_semester_yr6.COURSE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editoryr1_first = YearOne_First.edit();
        editoryr1_first.putString("COURSE", null);
        editoryr1_first.putInt("ADDSK", 0);
        editoryr1_first.putString("SPINNER", null);
        editoryr1_first.putString("CREDIT_LOAD", null);
        editoryr1_first.putString("TOTAL_SPINNER", null);
        editoryr1_first.putInt("PARENT_COUNT", 0);
        editoryr1_first.putInt("LOAD_ADDITION", 0);
        editoryr1_first.apply();

        SharedPreferences YearOne_Sec = getBaseContext().getSharedPreferences(second_semester_yr6.SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
        SharedPreferences.Editor editory1_sec = YearOne_Sec.edit();
        editory1_sec.putString("COURSE", null);
        editory1_sec.putString("SPINNER", null);
        editory1_sec.putString("CREDIT_LOAD", null);
        editory1_sec.putString("TOTAL_SPINNER", null);
        editory1_sec.putInt("ADDSK", 0);
        editory1_sec.putInt("PARENT_COUNT", 0);
        editory1_sec.putInt("LOAD_ADDITION", 0);
        editory1_sec.apply();

        level_grade_view.setText("GP:  ");
        level_txt_view.setText("GRADE:");
        total_yr_credit.setText("TOTAL CREDIT LOAD:");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.select_level_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.delete_menu) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Alert");
            alertDialog.setIcon(R.drawable.ic_warning_black_24dp);
            alertDialog.setMessage("Erase and Reset all Saved Data");
            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    YearOneSharedPrefDelete();
                    YearTwoSharedPrefDelete();
                    YearThreeSharedPrefDelete();
                    YearFourSharedPrefDelete();
                    YearFiveSharedPrefDelete();
                    YearSixSharedPrefDelete();
                    Snackbar.make(findViewById(R.id.fullayout), "Data Erased Successfully", Snackbar.LENGTH_SHORT).show();
                    gp_cum_txt_view.setText("GP CUMULATIVE: ");
                    total_crdit_cum.setText("CUMULATIVE TOTAL CREDIT LOAD: ");
                    level_txt_cumulative.setText("CUMULATIVE GRADE: ");

                }
            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.create().show();

        }
        if (i == R.id.Clear_Year1){
            YearOneSharedPrefDelete();
            Snackbar.make(findViewById(R.id.fullayout), " Erased Successfully", Snackbar.LENGTH_SHORT).show();
        }
        if (i == R.id.Clear_Year2){
            YearTwoSharedPrefDelete();
            Snackbar.make(findViewById(R.id.fullayout), " Erased Successfully", Snackbar.LENGTH_SHORT).show();
        }
        if (i == R.id.Clear_Year3){
            YearThreeSharedPrefDelete();
            Snackbar.make(findViewById(R.id.fullayout), " Erased Successfully", Snackbar.LENGTH_SHORT).show();
        }
        if (i == R.id.Clear_Year4){
            YearFourSharedPrefDelete();
            Snackbar.make(findViewById(R.id.fullayout), " Erased Successfully", Snackbar.LENGTH_SHORT).show();
        }
        if (i == R.id.Clear_Year5){
            YearFiveSharedPrefDelete();
            Snackbar.make(findViewById(R.id.fullayout), " Erased Successfully", Snackbar.LENGTH_SHORT).show();
        }
        if (i == R.id.Clear_Year6){
            YearSixSharedPrefDelete();
            Snackbar.make(findViewById(R.id.fullayout), " Erased Successfully", Snackbar.LENGTH_SHORT).show();
        }
        if (i == R.id.Contact){
            try { Intent telegramIntent = new Intent(Intent.ACTION_VIEW);
                telegramIntent.setData(Uri.parse("https://t.me/joinchat/AAAAAFQq8L2tzRH3uJn8Sw"));
                startActivity(telegramIntent);

            } catch (Exception e) {
                Toast.makeText(this,"Telegram not Installed",Toast.LENGTH_SHORT).show();
                // show error message
            }
        }


        return super.onOptionsItemSelected(item);
    }
    private void ShowBanner(){
        mAdView.loadAd(adRequest);
        mAdView1.loadAd(adRequest);
        mAdView3.loadAd(adRequest);
    }

}