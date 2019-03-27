package com.vectorinc.ezinwavictor.gpa.Year_Six;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vectorinc.ezinwavictor.gpa.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class second_semester_yr6 extends Fragment {
    private LinearLayout parentLinearLayout;
    private TextView Resulto, levelcomment, gpatext;
    private Button btnAddfield, btnSubmit, btnPreview, btnDelete, stats;
    private String[] grades;
    int Grade;
    private View rowView;
    private Menu preview, save;
    Spinner spinner;
    public static final String SECOND_SEMESTER_YEAR1 = "Second Semester yr6";


    List<LinearLayout> LinearParentArrayLsit = new ArrayList<>();
    List<EditText> CreditLoad = new ArrayList<>();
    List<EditText> CourseName = new ArrayList<>();
    List<Spinner> SpinnerArrayList = new ArrayList<>();
    List<String> CoursenamesText = new ArrayList<>();
    private int LinearParent;
    Intent intent;
    private String coursename;
    private String[] edittextItems;
    private View view;


    public second_semester_yr6() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_firstsemesteryr1, container, false);
        preview = view.findViewById(R.id.Preview);


        setHasOptionsMenu(true);

        Initialization(view);
        //INFLATE THE LAYOUT TO 5COUNT
        for (int i = 0; i < 5; i++) {
            final View rowView = inflater.inflate(R.layout.field, null);
            parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());

        }

        CheckSavedPreviewOncreate();

        //RESTORE ON SAVED INSTANCES
        if (savedInstanceState == null) {

        } else {
            try {
                ParentSavedInstances();
                final ArrayList<LinearLayout> savedgetpreview = new ArrayList<>();
                final ArrayList<EditText> savedgetEdittexts = new ArrayList<>();
                final ArrayList<Spinner> savedgetSpinner = new ArrayList<>();
                final ArrayList<EditText> savedgetCreditLoad = new ArrayList<>();
                final ArrayAdapter savedarrayList = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_selectable_list_item, grades);
                savedarrayList.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                Initialization(view);
                GetparentInput();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        savedgetEdittexts.clear();
                        savedgetpreview.clear();
                        savedgetSpinner.clear();
                        savedgetCreditLoad.clear();
                        String preview_course_text = savedInstanceState.getString("COURSE", "");
                        String preview_spinner_items = savedInstanceState.getString("SPINNER", "");
                        String preview_creditload_texts = savedInstanceState.getString("CREDIT_LOAD", "");
                        String[] credit_load_items = preview_creditload_texts.split(",");
                        String[] spinner_items = preview_spinner_items.split(",");
                        String[] item_courses = preview_course_text.split(",");
                        List<String> courseList = new ArrayList<>();
                        List<String> spinnerList = new ArrayList<>();
                        List<String> creditLoadList = new ArrayList<>();
                        spinnerList.clear();
                        courseList.clear();
                        creditLoadList.clear();
                        for (int i = 0; i < item_courses.length; i++) {
                            courseList.add(item_courses[i]);

                        }
                        for (int i = 0; i < spinner_items.length; i++) {
                            spinnerList.add(spinner_items[i]);
                        }
                        for (int i = 0; i < credit_load_items.length; i++) {
                            creditLoadList.add(credit_load_items[i]);
                        }

                        for (int i = 0; i < courseList.size(); i++) {
                            savedgetpreview.add((LinearLayout) parentLinearLayout.getChildAt(i));
                            savedgetEdittexts.add((EditText) savedgetpreview.get(i).getChildAt(0));
                            savedgetEdittexts.get(i).setText(courseList.get(i));
                            Log.d("CourseName List", courseList.get(i));
                        }
                        for (int i = 0; i < spinnerList.size(); i++) {
                            savedgetpreview.add((LinearLayout) parentLinearLayout.getChildAt(i));
                            SpinnerArrayList.add((Spinner) savedgetpreview.get(i).getChildAt(1));
                            int spinnerPosition1 = savedarrayList.getPosition(spinnerList.get(i));
                            SpinnerArrayList.get(i).setSelection(spinnerPosition1);

                            Log.d("SpinnerList ", spinnerList.get(i));
                        }
                        for (int i = 0; i < creditLoadList.size(); i++) {
                            savedgetpreview.add((LinearLayout) parentLinearLayout.getChildAt(i));
                            savedgetCreditLoad.add((EditText) savedgetpreview.get(i).getChildAt(2));
                            savedgetCreditLoad.get(i).setText(creditLoadList.get(i));
                            Log.d("CourseName List", creditLoadList.get(i));
                        }
                        ButtonsVisibityCheckMinusButton();


                    }
                }, 100);
            } catch (Exception e) {
            }
        }

        return view;


    }

    //INFLATE OPTIONS MENU
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.year_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);


    }

    //WORK ON ITEMS ON OPTIONS MENU
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();
        //SAVE RESULT ITEMS
        if (id == R.id.SaveResult) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Save Result?");
            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SaveSharedPreference();
                    Toast.makeText(getContext(), "Saved Successfully ", Toast.LENGTH_SHORT).show();

                }
            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.create().show();


        }
        //PREVIEW RESULT ITEM
        if (id == R.id.Preview) {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Show Saved Result ?");
            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferences sharedPref = getContext().getSharedPreferences(SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
                    int v = sharedPref.getInt("PARENT_COUNT", 0);
                    if (v == 0) {
                        Toast.makeText(getContext(), "ERROR!.. Data Not Found", Toast.LENGTH_SHORT).show();

                    } else {

                        GetSavedPreview();
                    }
                }
            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.create().show();


        }
        //RESET CURRENT INPUT
        if (id == R.id.ResetInput) {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Reset & Erase Texts");
            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int count = parentLinearLayout.getChildCount();
                    for (int i = 0; i < count; i++) {
                        LinearLayout linearLayout1 = (LinearLayout) parentLinearLayout.getChildAt(i);
                        LinearParentArrayLsit.add(linearLayout1);
                        CreditLoad.add((EditText) LinearParentArrayLsit.get(i).getChildAt(2));
                        SpinnerArrayList.add((Spinner) LinearParentArrayLsit.get(i).getChildAt(1));
                        CourseName.add((EditText) LinearParentArrayLsit.get(i).getChildAt(0));
                        SpinnerArrayList.get(i).setSelection(0);
                        CourseName.get(i).setText("");
                        CreditLoad.get(i).setText("");
                    }
                    Snackbar.make(view.findViewById(R.id.rootLayout1), "Input Cleared Successfully", Snackbar.LENGTH_SHORT).show();
                }
            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.create().show();

        }
        //RESET DATA FROM DATABASE
        if (id == R.id.Reset_Saved_Input) {

            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Erase all saved Data for this semester");
            alertDialog.setIcon(R.drawable.ic_warning_black_24dp);
            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    SharedPreferences preferences = getContext().getSharedPreferences(SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("COURSE", null);
                    editor.putString("SPINNER", null);
                    editor.putString("CREDIT_LOAD", null);
                    editor.putInt("PARENT_COUNT", 0);
                    editor.putInt("LOAD_ADDITION", 0);
                    editor.apply();
                    int count = parentLinearLayout.getChildCount();
                    for (int i = 0; i < count; i++) {
                        LinearLayout linearLayout1 = (LinearLayout) parentLinearLayout.getChildAt(i);
                        LinearParentArrayLsit.add(linearLayout1);
                        CreditLoad.add((EditText) LinearParentArrayLsit.get(i).getChildAt(2));
                        SpinnerArrayList.add((Spinner) LinearParentArrayLsit.get(i).getChildAt(1));
                        CourseName.add((EditText) LinearParentArrayLsit.get(i).getChildAt(0));
                        SpinnerArrayList.get(i).setSelection(0);
                        CourseName.get(i).setText("");
                        CreditLoad.get(i).setText("");
                    }
                    Snackbar.make(view.findViewById(R.id.rootLayout1), "DataBase Data Erased Successfully", Snackbar.LENGTH_SHORT).show();

                }
            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.create().show();


        }
        return super.onOptionsItemSelected(item);
    }

    private void Initialization(final View view) {
        btnAddfield = view.findViewById(R.id.add_field_button);
        parentLinearLayout = view.findViewById(R.id.parent_linear_layout);
        levelcomment = view.findViewById(R.id.levelcomment);
        gpatext = view.findViewById(R.id.gpaunits);

        btnDelete = view.findViewById(R.id.preview);
        btnSubmit = view.findViewById(R.id.calculate);
        grades = getResources().getStringArray(R.array.arrayGrade);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetparentInput();
                SaveSharedPreference();


            }
        });


        btnAddfield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowView = getLayoutInflater().inflate(R.layout.field, null);
                int vector = parentLinearLayout.getChildCount();
                parentLinearLayout.addView(rowView);
                ButtonsVisibityCheckMinusButton();


            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowView = getLayoutInflater().inflate(R.layout.field, null);
                int vector = parentLinearLayout.getChildCount();
                parentLinearLayout.removeViewAt(vector - 1);
                ButtonsVisibityCheckMinusButton();


            }
        });
    }


    private void ButtonsVisibityCheckMinusButton() {
        if (parentLinearLayout.getChildCount() == 1) {
            btnDelete.setVisibility(View.INVISIBLE);
        } else {
            btnDelete.setVisibility(View.VISIBLE);
        }
        if (parentLinearLayout.getChildCount() == 23) {
            btnAddfield.setVisibility(View.INVISIBLE);
        } else {
            btnAddfield.setVisibility(View.VISIBLE);
        }
    }

    private void Parent() {


        parentLinearLayout.removeAllViewsInLayout();


        for (int index_unit = 0; index_unit < ParentCheck(); index_unit++) {

            LayoutInflater inflater = getActivity().getLayoutInflater();
            final View rowView = inflater.inflate(R.layout.field, null);
            parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());


        }


    }


    private double SpinnerCalculation(Spinner spinner, EditText editTextMultiple) {


        if ((spinner.getSelectedItem().toString().isEmpty()) && ((editTextMultiple.getText().toString().isEmpty()))) {
            return 0;


        } else if (spinner.getSelectedItem().toString().isEmpty() || (editTextMultiple.getText().toString().isEmpty())) {

            if (spinner.getSelectedItem().toString().isEmpty()) {

                TextView textView = (TextView) spinner.getSelectedView();

                textView.setError("");
                Snackbar.make(view.findViewById(R.id.rootLayout1), "Select Grade", Snackbar.LENGTH_SHORT).show();
            } else {
                TextView textView = (TextView) spinner.getSelectedView();
                textView.setError(null);
            }

            if (editTextMultiple.getText().toString().isEmpty()) {
                editTextMultiple.setError("" + "");
                editTextMultiple.setFocusable(true);
                Snackbar.make(view.findViewById(R.id.rootLayout1), "Input Credit Load", Snackbar.LENGTH_SHORT).show();
            } else {
                editTextMultiple.setError(null);
            }
            return 0;

        } else {


            if ((!spinner.getSelectedItem().toString().equals("")) && (!editTextMultiple.getText().toString().equals(""))) {

                if (spinner.getSelectedItem().toString().equals("")) {
                    Grade = 0;
                } else if (spinner.getSelectedItem().toString().equals("A")) {
                    Grade = 5;
                } else if (spinner.getSelectedItem().toString().equals("B")) {
                    Grade = 4;
                } else if (spinner.getSelectedItem().toString().equals("C")) {
                    Grade = 3;
                } else if (spinner.getSelectedItem().toString().equals("D")) {
                    Grade = 2;
                } else if (spinner.getSelectedItem().toString().equals("E")) {
                    Grade = 1;
                } else if (spinner.getSelectedItem().toString().equals("F")) {
                    Grade = 0;
                } else
                    Grade = 0;


            }


        }

        SharedPreferences sharedPref = getContext().getSharedPreferences(SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();


        int creditload = Integer.parseInt(editTextMultiple.getText().toString());
        int GradesAll = Grade * creditload;
        double multiply = Grade * creditload;
        String doubletext = String.valueOf(multiply);


        String[] credit_load_items = editTextMultiple.getText().toString().split(",");
        double[] newPercentage = new double[credit_load_items.length];
        final List<String> creditLoadList = new ArrayList<>();
        final List<String> DoubleList = new ArrayList<>();
        DoubleList.add(doubletext);

        StringBuilder gradesm = new StringBuilder();

        for (String s : DoubleList) {

            gradesm.append(s);
            gradesm.append(",");
        }
        editor.putString("GRADES", gradesm.toString());
        editor.apply();

        for (int i = 0; i < credit_load_items.length; i++) {
            creditLoadList.add(credit_load_items[i]);
        }

        for (int i = 0; i < DoubleList.size(); i++) {

        }
        for (int i = 0; i < newPercentage.length; ++i) {
            System.out.println(i + "\n" + DoubleList.get(i) + "\n" + newPercentage[i]);

        }


        return multiply;
    }

    // Method to get Both Spinner Input And Creditload


    private void GetparentInput() {
        try {

            ArrayList<LinearLayout> savedLinearLayouts = new ArrayList<>();
            ArrayList<EditText> savedEdittext = new ArrayList<>();
            ArrayList<EditText> savedCreditLoad = new ArrayList<>();
            ArrayList<Spinner> spinnerArrayListnew = new ArrayList<>();
            SharedPreferences preferences = getContext().getSharedPreferences(SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();

            ArrayList<String> newCourseName = new ArrayList<>();
            ArrayList<String> newSpinnerItem = new ArrayList<>();
            ArrayList<String> newCreditLoad = new ArrayList<>();
            int ok = 0;


            int vec = parentLinearLayout.getChildCount();
            int v = 0;
            for (int i = 0; i < vec; i++) {
                LinearLayout newLayout = (LinearLayout) parentLinearLayout.getChildAt(i);
                savedLinearLayouts.add(newLayout);
                savedEdittext.add((EditText) savedLinearLayouts.get(i).getChildAt(0));
                spinnerArrayListnew.add((Spinner) savedLinearLayouts.get(i).getChildAt(1));
                savedCreditLoad.add((EditText) savedLinearLayouts.get(i).getChildAt(2));
                newCourseName.add(savedEdittext.get(i).getText().toString());
                newSpinnerItem.add(spinnerArrayListnew.get(i).getSelectedItem().toString());
                newCreditLoad.add(savedCreditLoad.get(i).getText().toString());

            }
            int total = 0;
            double Spinnertotal = 0;

            for (int i = 0; i < savedLinearLayouts.size(); i++) {

                Spinnertotal += SpinnerCalculation(spinnerArrayListnew.get(i), savedCreditLoad.get(i));
                total += Integer.parseInt(savedCreditLoad.get(i).getText().toString());
                String spin = String.valueOf(Spinnertotal);
                editor.putString("TOTAL_SPINNER", spin);
                editor.putInt("CREDIT_LOAD_TOTAL", total);

                double me = Spinnertotal / total;

                DecimalFormat gpa_format = new DecimalFormat("#.#");
                String final_gpa = gpa_format.format(me);
                gpatext.setText("[ GP : " + final_gpa + " ]");

                if (me >= 4.5) {
                    levelcomment.setText(" FIRST CLASS ");

                } else if ((me >= 3.5) && (me < 4.5)) {
                    levelcomment.setText(" SECOND CLASS UPPER ");
                } else if ((me >= 2.5) && (me < 3.5)) {
                    levelcomment.setText(" SECOND CLASS LOWER ");
                } else if ((me >= 1.5) && (me < 2.5)) {
                    levelcomment.setText(" PASS ");
                } else {
                    levelcomment.setText("FAILED");
                }

                System.out.print(levelcomment.getText().toString());
                SaveSharedPreference();
                String levelGrade = levelcomment.getText().toString();
                String gp = gpatext.getText().toString();
                editor.putString("GRADE_LEVEL", levelGrade);
                editor.putString("GP", final_gpa);

                editor.putInt("ADDSK", total);
                editor.apply();
                SaveSharedPreference();

            }


        } catch (Exception e) {

        }
    }


    private void SaveSharedPreference() {
        ArrayList<LinearLayout> savedLinearLayouts = new ArrayList<>();
        ArrayList<EditText> savedEdittext = new ArrayList<>();
        ArrayList<EditText> savedCreditLoad = new ArrayList<>();
        ArrayList<Spinner> spinnerArrayListnew = new ArrayList<>();
        SharedPreferences preferences = getContext().getSharedPreferences(SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        ArrayList<String> newCourseName = new ArrayList<>();
        ArrayList<String> newSpinnerItem = new ArrayList<>();
        ArrayList<String> newCreditLoad = new ArrayList<>();
        int ok = 0;


        int vec = parentLinearLayout.getChildCount();
        int v = 0;
        for (int i = 0; i < vec; i++) {
            LinearLayout newLayout = (LinearLayout) parentLinearLayout.getChildAt(i);
            savedLinearLayouts.add(newLayout);
            savedEdittext.add((EditText) savedLinearLayouts.get(i).getChildAt(0));
            spinnerArrayListnew.add((Spinner) savedLinearLayouts.get(i).getChildAt(1));
            savedCreditLoad.add((EditText) savedLinearLayouts.get(i).getChildAt(2));
            newCourseName.add(savedEdittext.get(i).getText().toString());
            newSpinnerItem.add(spinnerArrayListnew.get(i).getSelectedItem().toString());
            newCreditLoad.add(savedCreditLoad.get(i).getText().toString());

        }


        StringBuilder courseNmaeStringBuilder = new StringBuilder();
        StringBuilder SpinnerStringBuilder = new StringBuilder();
        StringBuilder CreditLoadStringBuilder = new StringBuilder();
        for (String s : newCourseName) {
            courseNmaeStringBuilder.append(s);
            courseNmaeStringBuilder.append(",");
        }
        for (String s : newSpinnerItem) {
            SpinnerStringBuilder.append(s);
            SpinnerStringBuilder.append(",");
        }
        for (String s : newCreditLoad) {

            CreditLoadStringBuilder.append(s);
            CreditLoadStringBuilder.append(",");
        }


        editor.putString("COURSE", courseNmaeStringBuilder.toString());
        editor.putString("SPINNER", SpinnerStringBuilder.toString());
        editor.putString("CREDIT_LOAD", CreditLoadStringBuilder.toString());
        editor.putInt("PARENT_COUNT", vec);

        editor.apply();
        Log.d("COURSE NAME ITEMS", courseNmaeStringBuilder.toString());
        Log.d("SPINNER ITEMS", SpinnerStringBuilder.toString());

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        SharedPreferences sharedPref = getContext().getSharedPreferences(SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        ArrayList<LinearLayout> OnsavedLinear = new ArrayList<>();
        ArrayList<EditText> onsavedEdittext = new ArrayList<>();
        ArrayList<EditText> onsavedCreditLoad = new ArrayList<>();
        ArrayList<Spinner> onSavedspinnerArrayListnew = new ArrayList<>();

        ArrayList<String> onsavednewCourseName = new ArrayList<>();
        ArrayList<String> OnsavednewSpinnerItem = new ArrayList<>();
        ArrayList<String> OnsavednewCreditLoad = new ArrayList<>();


        int vec = parentLinearLayout.getChildCount();
        editor.putInt("SAVED_PARENT_COUNT", vec);
        editor.apply();


        for (int i = 0; i < vec; i++) {
            LinearLayout onsavedNewLayout = (LinearLayout) parentLinearLayout.getChildAt(i);
            OnsavedLinear.add(onsavedNewLayout);
            onsavedEdittext.add((EditText) OnsavedLinear.get(i).getChildAt(0));
            onSavedspinnerArrayListnew.add((Spinner) OnsavedLinear.get(i).getChildAt(1));
            onsavedCreditLoad.add((EditText) OnsavedLinear.get(i).getChildAt(2));
            onsavednewCourseName.add(onsavedEdittext.get(i).getText().toString());
            OnsavednewSpinnerItem.add(onSavedspinnerArrayListnew.get(i).getSelectedItem().toString());
            OnsavednewCreditLoad.add(onsavedCreditLoad.get(i).getText().toString());

        }

        StringBuilder SavedcourseNmaeStringBuilder = new StringBuilder();
        StringBuilder SavedSpinnerStringBuilder = new StringBuilder();
        StringBuilder SavedCreditLoadStringBuilder = new StringBuilder();
        for (String s : onsavednewCourseName) {
            SavedcourseNmaeStringBuilder.append(s);
            SavedcourseNmaeStringBuilder.append(",");
        }
        for (String s : OnsavednewSpinnerItem) {
            SavedSpinnerStringBuilder.append(s);
            SavedSpinnerStringBuilder.append(",");
        }
        for (String s : OnsavednewCreditLoad) {
            SavedCreditLoadStringBuilder.append(s);
            SavedCreditLoadStringBuilder.append(",");
        }

        outState.putString("COURSE", SavedcourseNmaeStringBuilder.toString());
        outState.putString("SPINNER", SavedSpinnerStringBuilder.toString());
        outState.putString("CREDIT_LOAD", SavedCreditLoadStringBuilder.toString());

        Log.d("SavedInstances Courses", SavedcourseNmaeStringBuilder.toString());
        Log.d("SavedInstances Spinner", SavedSpinnerStringBuilder.toString());
        Log.d("Saved CreditLoad", SavedCreditLoadStringBuilder.toString());

        super.onSaveInstanceState(outState);

    }


    public void GetSavedPreview() {
        try {


            final ArrayList<LinearLayout> getpreview = new ArrayList<>();
            final ArrayList<EditText> getEdittexts = new ArrayList<>();
            final ArrayList<Spinner> getSpinner = new ArrayList<>();
            final ArrayList<EditText> getCreditLoad = new ArrayList<>();
            final ArrayAdapter arrayList = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_selectable_list_item, grades);
            arrayList.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
            Parent();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences sharedPref = getContext().getSharedPreferences(SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
                    String preview_course_text = sharedPref.getString("COURSE", "");
                    String preview_spinner_items = sharedPref.getString("SPINNER", "");
                    String preview_creditload_texts = sharedPref.getString("CREDIT_LOAD", "");
                    String[] credit_load_items = preview_creditload_texts.split(",");
                    String[] spinner_items = preview_spinner_items.split(",");
                    String[] item_courses = preview_course_text.split(",");
                    final List<String> courseList = new ArrayList<>();
                    final List<String> spinnerList = new ArrayList<>();
                    final List<String> creditLoadList = new ArrayList<>();

                    for (int i = 0; i < item_courses.length; i++) {
                        courseList.add(item_courses[i]);

                    }
                    for (int i = 0; i < spinner_items.length; i++) {
                        spinnerList.add(spinner_items[i]);
                    }
                    for (int i = 0; i < credit_load_items.length; i++) {
                        creditLoadList.add(credit_load_items[i]);
                    }

                    for (int i = 0; i < courseList.size(); i++) {
                        getpreview.add((LinearLayout) parentLinearLayout.getChildAt(i));
                        getEdittexts.add((EditText) getpreview.get(i).getChildAt(0));
                        getEdittexts.get(i).setText(courseList.get(i));
                        Log.d("CourseName List", courseList.get(i));
                    }
                    for (int i = 0; i < spinnerList.size(); i++) {
                        getpreview.add((LinearLayout) parentLinearLayout.getChildAt(i));
                        getSpinner.add((Spinner) getpreview.get(i).getChildAt(1));
                        int spinnerPosition1 = arrayList.getPosition(spinnerList.get(i));
                        getSpinner.get(i).setSelection(spinnerPosition1);

                        Log.d("SpinnerList ", spinnerList.get(i));
                    }
                    for (int i = 0; i < creditLoadList.size(); i++) {
                        getpreview.add((LinearLayout) parentLinearLayout.getChildAt(i));
                        getCreditLoad.add((EditText) getpreview.get(i).getChildAt(2));
                        getCreditLoad.get(i).setText(creditLoadList.get(i));

                        Log.d("CourseName List", creditLoadList.get(i));
                    }


                    Snackbar.make(view.findViewById(R.id.rootLayout1), "Saved Data Reloaded Successfully", Snackbar.LENGTH_SHORT).show();
                    ButtonsVisibityCheckMinusButton();


                }

            }, 100);
        } catch (Exception e) {
        }
    }

    private int ParentCheck() {
        SharedPreferences sharedPref = getContext().getSharedPreferences(SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
        int Vector = sharedPref.getInt("PARENT_COUNT", 0);

        if (Vector > parentLinearLayout.getChildCount()) {

            return Vector;
        } else if (Vector < parentLinearLayout.getChildCount()) {
            return Vector;

        } else if (Vector == parentLinearLayout.getChildCount()) {
            return Vector;

        }
        return Vector;
    }

    private int ParentCheckSaved() {
        SharedPreferences sharedPref = getContext().getSharedPreferences(SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
        int Vector = sharedPref.getInt("SAVED_PARENT_COUNT", 0);

        if (Vector > parentLinearLayout.getChildCount()) {

            return Vector;
        } else if (Vector < parentLinearLayout.getChildCount()) {
            return Vector;

        } else if (Vector == parentLinearLayout.getChildCount()) {
            return Vector;

        }
        return Vector;
    }

    private void ParentSavedInstances() {


        parentLinearLayout.removeAllViewsInLayout();


        for (int index_unit = 0; index_unit < ParentCheckSaved(); index_unit++) {

            LayoutInflater inflater = getActivity().getLayoutInflater();
            final View rowView = inflater.inflate(R.layout.field, null);
            parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());


        }
    }

    private void CheckSavedPreviewOncreate() {
        SharedPreferences sharedPref = getContext().getSharedPreferences(SECOND_SEMESTER_YEAR1, MODE_PRIVATE);
        int parent_count = sharedPref.getInt("PARENT_COUNT", 0);
        if (parent_count == 0) {

        } else {
            GetSavedPreview();
        }


    }

}
