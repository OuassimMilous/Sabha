package wassimtech.sabha;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class TasbihFragment extends Fragment {

    Button counterTV,addview,okay,cancel;
    int progressInt=0,thikrnum=1,ChhalMnThikr=1;
    ImageButton reset;
    ImageView ivedit,Setting;
    SharedPreferences[] sp = new SharedPreferences[1];

    LinearLayout list;

    String Lang;

    boolean started=false,onclickvibration,ontasbihachangevibration,LangaEverAsked;
    String[] queuedTasbihat = new String[100];
    Integer[] queuedTasbihatMarat = new Integer[100];


    TextView thikrTVarEn,thikrTVEn,thikrTV;

    public TasbihFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasbih, container, false);
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        sp[0] = this.getActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);
        onclickvibration=sp[0].getBoolean("onclickvibration",true);
        ontasbihachangevibration=sp[0].getBoolean("ontasbihachangevibration",true);


        reset = view.findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResetDialogClass resetDialogClass = new ResetDialogClass(getActivity());
                resetDialogClass.show();
            }
        });

        thikrTVarEn = view.findViewById(R.id.thikrEn);
        thikrTVEn = view.findViewById(R.id.thikrAREn);
        thikrTV = view.findViewById(R.id.thikrAR);


        Lang = Locale.getDefault().getLanguage();

    /* if (Lang.equals("ar")){
            thikrTVEn.setVisibility(View.GONE);
            thikrTVarEn.setVisibility(View.GONE);
        }else{
            thikrTVEn.setVisibility(View.VISIBLE);
            thikrTVarEn.setVisibility(View.VISIBLE);
        }*/

        LangaEverAsked=sp[0].getBoolean("LangaEverAsked",false);

        if (!LangaEverAsked) {


            final Dialog Langdialog = new Dialog(getActivity());

            Langdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            Langdialog.setContentView(R.layout.alert_dialog_lang);
            Langdialog.setCancelable(false);
            Langdialog.show();

            Button eng = Langdialog.findViewById(R.id.English);
            Button ara = Langdialog.findViewById(R.id.Arabic);

            eng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Locale locale = new Locale("en");
                    Locale.setDefault(locale);
                    Configuration configuration = new Configuration();
                    configuration.locale = locale;
                    getActivity().getBaseContext().getResources().updateConfiguration(configuration, getActivity().getBaseContext().getResources().getDisplayMetrics());
                    Lang = "en";
                  /*  getActivity().recreate();
                    FragmentManager fm;
                    fm = getActivity().getSupportFragmentManager();
                    TasbihFragment tasbihFragment = TasbihFragment.this;
                    if (fm.findFragmentByTag("tasbihFragment") == null) {
                        fm.beginTransaction().add(R.id.main_frame, tasbihFragment, "tasbihFragment").hide(tasbihFragment).commit();
                    }
                    fm.beginTransaction().show(tasbihFragment).commit();
                 */

                    SharedPreferences.Editor point = sp[0].edit();
                    point.putString("Lang", Lang);
                    LangaEverAsked = true;
                    point.putBoolean("LangaEverAsked", LangaEverAsked);
                    point.apply();

                    getActivity().finish();
                    Intent myIntent = new Intent(getActivity(), MainActivity.class);
                    startActivity(myIntent);
                }
            });

            ara.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Locale locale = new Locale("ar");
                    Locale.setDefault(locale);
                    Configuration configuration = new Configuration();
                    configuration.locale = locale;
                    getActivity().getBaseContext().getResources().updateConfiguration(configuration, getActivity().getBaseContext().getResources().getDisplayMetrics());
                    Lang="ar";
                  /*  getActivity().recreate();
                    FragmentManager fm;
                    fm = getActivity().getSupportFragmentManager();
                    TasbihFragment tasbihFragment = TasbihFragment.this;
                    if (fm.findFragmentByTag("tasbihFragment")==null) {
                        fm.beginTransaction().add(R.id.main_frame, tasbihFragment, "tasbihFragment").hide(tasbihFragment).commit();
                    }
            fm.beginTransaction().show(tasbihFragment).commit();
            */
                    SharedPreferences.Editor point = sp[0].edit();
                    point.putString("Lang", Lang);
                    LangaEverAsked = true;
                    point.putBoolean("LangaEverAsked", LangaEverAsked);
                    point.apply();

                    getActivity().finish();
                    Intent myIntent = new Intent(getActivity(), MainActivity.class);
                    startActivity(myIntent);


                }

            });
        }


        counterTV = view.findViewById(R.id.counterTV);
        counterTV.setText(getResources().getString(R.string.start));
        reset = view.findViewById(R.id.reset);
        reset.setEnabled(false);

        reset = view.findViewById(R.id.reset);
        ivedit = view.findViewById(R.id.IVedit);
        Setting = view.findViewById(R.id.settings);
        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsDialogClass stg=new SettingsDialogClass(getActivity());
                stg.show();
            }
        });

        sp[0] = this.getActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);
        onclickvibration=sp[0].getBoolean("onclickvibration",true);
        ontasbihachangevibration=sp[0].getBoolean("ontasbihachangevibration",true);




        ViewTreeObserver vto = counterTV.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {

                DisplayMetrics displayMetrics = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int height = displayMetrics.heightPixels;
                int width = displayMetrics.widthPixels;
                counterTV.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                if (height>width) {
                    if (counterTV.getWidth()>300) {
                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(counterTV.getWidth(), counterTV.getWidth());
                        counterTV.setLayoutParams(params);
                    }else {
                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width-(width/3),width-(width/3));
                        counterTV.setLayoutParams(params);
                    }


                }else {
                    if (counterTV.getHeight()>300) {
                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(counterTV.getHeight(), counterTV.getHeight());
                        counterTV.setLayoutParams(params);
                    }else {
                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(height-(height/3),height-(height/3));
                        counterTV.setLayoutParams(params);
                    }
                }
                if (counterTV.getWidth()!=counterTV.getWidth()) {
                    if (counterTV.getWidth() > counterTV.getHeight()) {
                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(counterTV.getHeight(), counterTV.getHeight());
                        counterTV.setLayoutParams(params);
                    } else {
                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(counterTV.getWidth(), counterTV.getWidth());
                        counterTV.setLayoutParams(params);
                    }
                }
            }
        });


        counterTV.setOnTouchListener(new View.OnTouchListener() {

            private int CLICK_ACTION_THRESHOLD = 200;
            private float startX;
            private float startY;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float centerX, centerY, touchX, touchY, radius;
                centerX = view.getWidth() / 2.0f;
                centerY = view.getHeight() / 2.0f;
                touchX = motionEvent.getX();
                touchY = motionEvent.getY();
                radius = centerX;

                if (Math.pow(touchX - centerX, 2) + Math.pow(touchY - centerY, 2) < Math.pow(radius, 2)) {

                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            startX = motionEvent.getX();
                            startY = motionEvent.getY();
                            break;
                        case MotionEvent.ACTION_UP:
                            float endX = motionEvent.getX();
                            float endY = motionEvent.getY();

                            if (isAClick(startX, endX, startY, endY)) {
                                if (!started) {
                                    iveditOnclick();
                                } else {
                                    onclickCounter();
                                }
                            }
                            break;
                    }

                    return false;
                } else {
                    return true;
                }
            }

            private boolean isAClick(float startX, float endX, float startY, float endY) {
                float differenceX = Math.abs(startX - endX);
                float differenceY = Math.abs(startY - endY);
                return !(differenceX > CLICK_ACTION_THRESHOLD || differenceY > CLICK_ACTION_THRESHOLD);
            }
        });


        ivedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iveditOnclick();

            }
        });

    }

    private void iveditOnclick() {
        final Dialog dialog = new Dialog(getContext());


        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_dialog_tasbih);
        dialog.setCancelable(true);
        list = dialog.findViewById(R.id.List);
        okay = dialog.findViewById(R.id.btnok);
        cancel = dialog.findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        final LayoutInflater layoutInflater = (LayoutInflater) getContext().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list.addView(layoutInflater.inflate(R.layout.alert_dialog_tasbih_item, null), 1, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ImageView rmv = list.getChildAt(1).findViewById(R.id.rmv);
        rmv.setVisibility(View.GONE);
        Spinner spinner = list.getChildAt(1).findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        List<String> tasbihat = new ArrayList<String>();
        Lang = Locale.getDefault().getLanguage();

        if (Lang.equals("en")){
            tasbihat.add(getResources().getString(R.string.tasbih_subhan_allah_ar_en));
            tasbihat.add(getResources().getString(R.string.tasbih_alhamdou_lilah_ar_en));
            tasbihat.add(getResources().getString(R.string.tasbih_allahu_akbar_ar_en));
            tasbihat.add(getResources().getString(R.string.tasbih_la_ilaha_ila_allah_ar_en));
            tasbihat.add(getResources().getString(R.string.tasbih_la_ilaha_ila_allah2_ar_en));
            tasbihat.add(getResources().getString(R.string.tasbih_astaghfiru_allah_ar_en));
            tasbihat.add(getResources().getString(R.string.tasbih_Allahuma_sali_wasalim_ar_en));
            tasbihat.add(getResources().getString(R.string.tasbih_la_hawla_ar_en));
            tasbihat.add(getResources().getString(R.string.tasbih_subhan_allah_wa_bihamdih_ar_en));
            tasbihat.add(getResources().getString(R.string.tasbih_la_ilaha_ila_allah3_ar_en));
        }else if (Lang.equals("ar")) {
            tasbihat.add(getResources().getString(R.string.tasbih_subhan_allah_arabic));
            tasbihat.add(getResources().getString(R.string.tasbih_alhamdou_lilah_arabic));
            tasbihat.add(getResources().getString(R.string.tasbih_allahu_akbar_arabic));
            tasbihat.add(getResources().getString(R.string.tasbih_la_ilaha_ila_allah_arabic));
            tasbihat.add(getResources().getString(R.string.tasbih_la_ilaha_ila_allah2_arabic));
            tasbihat.add(getResources().getString(R.string.tasbih_astaghfiru_allah_arabic));
            tasbihat.add(getResources().getString(R.string.tasbih_Allahuma_sali_wasalim_arabic));
            tasbihat.add(getResources().getString(R.string.tasbih_la_hawla_arabic));
            tasbihat.add(getResources().getString(R.string.tasbih_subhan_allah_wa_bihamdih_arabic));
            tasbihat.add(getResources().getString(R.string.tasbih_la_ilaha_ila_allah3_arabic));
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), R.layout.row_textview, tasbihat);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        list.addView(layoutInflater.inflate(R.layout.alert_dialog_tasbih_item_addbtn, null), 2, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        addview = dialog.findViewById(R.id.addview);

        addview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addviewOnclick();
            }
        });

        dialog.show();
        if (Lang.equals("en")) {
            addview.setText("Add Another Thikr");
        }else if (Lang.equals("ar")) {
            addview.setText("أضف ذكرا أخر");
        }

        okay.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ChhalMnThikr=list.getChildCount()-1;
            for (int i =1;i<=ChhalMnThikr-1;i++){
                progressInt=0;
                thikrnum=1;
                counterTV.setText(String.valueOf(progressInt));
                reset.setEnabled(true);
                Spinner spinner = list.getChildAt(i).findViewById(R.id.spinner);
                EditText marat = list.getChildAt(i).findViewById(R.id.edittext);
                try {
                    queuedTasbihat[i] = spinner.getSelectedItem().toString();
                    queuedTasbihatMarat[i] = Integer.parseInt(marat.getText().toString());
                    setthkirTv((queuedTasbihat[thikrnum]), Lang);

                    counterTV.setText(String.valueOf(progressInt));
                    reset.setEnabled(true);
                    started=true;
                    dialog.dismiss();
                }catch (Exception e){
                    Toast.makeText(getContext(), getResources().getString(R.string.you_cannot_leave_anything_empty), Toast.LENGTH_SHORT).show();
                }


            }
        }
    });

    }

    private void onclickCounter() {


        Vibrator v = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        }
        // SharedPreferences.Editor point = sp[0].edit();

        if (ChhalMnThikr>thikrnum) {

            if (v != null&&onclickvibration) {
                //noinspection deprecation
                v.vibrate(40);
            }
            counterTV.setText(String.valueOf(++progressInt));
            reset.setEnabled(true);

            //   point.putInt("progressInt", progressInt);
            //   point.apply();

            if (progressInt >= queuedTasbihatMarat[thikrnum]) {
                thikrnum++;
                if (ChhalMnThikr>thikrnum) {
                    setthkirTv((queuedTasbihat[thikrnum]), Lang);
                    //         point.putInt("thikrnum", thikrnum);
                    progressInt = 0;
                    counterTV.setText(String.valueOf(progressInt));
                    reset.setEnabled(true);
                    if (v != null&&ontasbihachangevibration) {
                        //noinspection deprecation
                        v.vibrate(500);
                    }
                }else {

                    if (v != null&&ontasbihachangevibration) {
                        //noinspection deprecation
                        v.vibrate(500);
                    }
                    Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            CustomDialogClass alr=new CustomDialogClass(getActivity());
                            alr.show();
                        }
                    },500);
                }
            }

        }else {
            CustomDialogClass alr=new CustomDialogClass(getActivity());
            alr.show();
        }


    }

    private void setthkirTv(String aren, String lang) {
       /* thikrTVarEn = getActivity().findViewById(R.id.thikrEn);
        thikrTVEn = getActivity().findViewById(R.id.thikrAREn);
        thikrTV = getActivity().findViewById(R.id.thikrAR);
*/
        if (lang.equals("ar")) {
            if (aren.equals(getResources().getString(R.string.tasbih_subhan_allah_arabic))) {
                thikrTV.setText(getResources().getString(R.string.tasbih_subhan_allah_arabic));
                thikrTVEn.setText(getResources().getString(R.string.tasbih_subhan_allah_ar_en));
                thikrTVarEn.setText(getResources().getString(R.string.tasbih_subhan_allah));
            } else if (aren.equals(getResources().getString(R.string.tasbih_alhamdou_lilah_arabic))) {
                thikrTV.setText(getResources().getString(R.string.tasbih_alhamdou_lilah_arabic));
                thikrTVEn.setText(getResources().getString(R.string.tasbih_alhamdou_lilah_ar_en));
                thikrTVarEn.setText(getResources().getString(R.string.tasbih_alhamdou_lilah));
            } else if (aren.equals(getResources().getString(R.string.tasbih_allahu_akbar_arabic))) {
                thikrTV.setText(getResources().getString(R.string.tasbih_allahu_akbar_arabic));
                thikrTVEn.setText(getResources().getString(R.string.tasbih_allahu_akbar));
                thikrTVarEn.setText(getResources().getString(R.string.tasbih_allahu_akbar_ar_en));
            } else if (aren.equals(getResources().getString(R.string.tasbih_la_ilaha_ila_allah_arabic))) {
                thikrTV.setText(getResources().getString(R.string.tasbih_la_ilaha_ila_allah_arabic));
                thikrTVEn.setText(getResources().getString(R.string.tasbih_la_ilaha_ila_allah));
                thikrTVarEn.setText(getResources().getString(R.string.tasbih_la_ilaha_ila_allah_ar_en));
            } else if (aren.equals(getResources().getString(R.string.tasbih_la_ilaha_ila_allah2_arabic))) {
                thikrTV.setText(getResources().getString(R.string.tasbih_la_ilaha_ila_allah2_arabic));
                thikrTVEn.setText(getResources().getString(R.string.tasbih_la_ilaha_ila_allah2));
                thikrTVarEn.setText(getResources().getString(R.string.tasbih_la_ilaha_ila_allah2_ar_en));
            } else if (aren.equals(getResources().getString(R.string.tasbih_astaghfiru_allah_arabic))) {
                thikrTV.setText(getResources().getString(R.string.tasbih_astaghfiru_allah_arabic));
                thikrTVEn.setText(getResources().getString(R.string.tasbih_astaghfiru_allah));
                thikrTVarEn.setText(getResources().getString(R.string.tasbih_astaghfiru_allah_ar_en));
            } else if (aren.equals(getResources().getString(R.string.tasbih_Allahuma_sali_wasalim_arabic))) {
                thikrTV.setText(getResources().getString(R.string.tasbih_Allahuma_sali_wasalim_arabic));
                thikrTVEn.setText(getResources().getString(R.string.tasbih_Allahuma_sali_wasalim));
                thikrTVarEn.setText(getResources().getString(R.string.tasbih_Allahuma_sali_wasalim_ar_en));
            } else if (aren.equals(getResources().getString(R.string.tasbih_la_hawla_arabic))) {
                thikrTV.setText(getResources().getString(R.string.tasbih_la_hawla_arabic));
                thikrTVEn.setText(getResources().getString(R.string.tasbih_la_hawla));
                thikrTVarEn.setText(getResources().getString(R.string.tasbih_la_hawla_ar_en));
            } else if (aren.equals(getResources().getString(R.string.tasbih_subhan_allah_wa_bihamdih_arabic))) {
                thikrTV.setText(getResources().getString(R.string.tasbih_subhan_allah_wa_bihamdih_arabic));
                thikrTVEn.setText(getResources().getString(R.string.tasbih_subhan_allah_wa_bihamdih));
                thikrTVarEn.setText(getResources().getString(R.string.tasbih_subhan_allah_wa_bihamdih_ar_en));
            } else if (aren.equals(getResources().getString(R.string.tasbih_la_ilaha_ila_allah3_arabic))) {
                thikrTV.setText(getResources().getString(R.string.tasbih_la_ilaha_ila_allah3_arabic));
                thikrTVEn.setText(getResources().getString(R.string.tasbih_la_ilaha_ila_allah3));
                thikrTVarEn.setText(getResources().getString(R.string.tasbih_la_ilaha_ila_allah3_ar_en));
            }
        }else {
            thikrTVarEn.setText(aren);
            if (aren.equals(getResources().getString(R.string.tasbih_subhan_allah_ar_en))) {
                thikrTVEn.setText(getResources().getString(R.string.tasbih_subhan_allah));
                thikrTV.setText(getResources().getString(R.string.tasbih_subhan_allah_arabic));
            } else if (aren.equals(getResources().getString(R.string.tasbih_alhamdou_lilah_ar_en))) {
                thikrTVEn.setText(getResources().getString(R.string.tasbih_alhamdou_lilah));
                thikrTV.setText(getResources().getString(R.string.tasbih_alhamdou_lilah_arabic));
            } else if (aren.equals(getResources().getString(R.string.tasbih_allahu_akbar_ar_en))) {
                thikrTVEn.setText(getResources().getString(R.string.tasbih_allahu_akbar));
                thikrTV.setText(getResources().getString(R.string.tasbih_allahu_akbar_arabic));
            } else if (aren.equals(getResources().getString(R.string.tasbih_la_ilaha_ila_allah_ar_en))) {
                thikrTVEn.setText(getResources().getString(R.string.tasbih_la_ilaha_ila_allah));
                thikrTV.setText(getResources().getString(R.string.tasbih_la_ilaha_ila_allah_arabic));
            } else if (aren.equals(getResources().getString(R.string.tasbih_la_ilaha_ila_allah2_ar_en))) {
                thikrTVEn.setText(getResources().getString(R.string.tasbih_la_ilaha_ila_allah2));
                thikrTV.setText(getResources().getString(R.string.tasbih_la_ilaha_ila_allah2_arabic));
            } else if (aren.equals(getResources().getString(R.string.tasbih_astaghfiru_allah_ar_en))) {
                thikrTVEn.setText(getResources().getString(R.string.tasbih_astaghfiru_allah));
                thikrTV.setText(getResources().getString(R.string.tasbih_astaghfiru_allah_arabic));
            } else if (aren.equals(getResources().getString(R.string.tasbih_Allahuma_sali_wasalim_ar_en))) {
                thikrTVEn.setText(getResources().getString(R.string.tasbih_Allahuma_sali_wasalim));
                thikrTV.setText(getResources().getString(R.string.tasbih_Allahuma_sali_wasalim_arabic));
            } else if (aren.equals(getResources().getString(R.string.tasbih_la_hawla_ar_en))) {
                thikrTVEn.setText(getResources().getString(R.string.tasbih_la_hawla));
                thikrTV.setText(getResources().getString(R.string.tasbih_la_hawla_arabic));
            } else if (aren.equals(getResources().getString(R.string.tasbih_subhan_allah_wa_bihamdih_ar_en))) {
                thikrTVEn.setText(getResources().getString(R.string.tasbih_subhan_allah_wa_bihamdih));
                thikrTV.setText(getResources().getString(R.string.tasbih_subhan_allah_wa_bihamdih_arabic));
            } else if (aren.equals(getResources().getString(R.string.tasbih_la_ilaha_ila_allah3_ar_en))) {
                thikrTVEn.setText(getResources().getString(R.string.tasbih_la_ilaha_ila_allah3));
                thikrTV.setText(getResources().getString(R.string.tasbih_la_ilaha_ila_allah3_arabic));
            }
        }
    }


    private void addviewOnclick() {
        final LayoutInflater layoutInflater = (LayoutInflater) getContext().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list.removeViewAt(list.getChildCount() - 1);
        list.addView(layoutInflater.inflate(R.layout.alert_dialog_tasbih_item, null), list.getChildCount(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        Spinner spinner = list.getChildAt(list.getChildCount() - 1).findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        List<String> tasbihat = new ArrayList<String>();
        Lang = Locale.getDefault().getLanguage();
        if (Lang.equals("en")){
            tasbihat.add(getResources().getString(R.string.tasbih_subhan_allah_ar_en));
            tasbihat.add(getResources().getString(R.string.tasbih_alhamdou_lilah_ar_en));
            tasbihat.add(getResources().getString(R.string.tasbih_allahu_akbar_ar_en));
            tasbihat.add(getResources().getString(R.string.tasbih_la_ilaha_ila_allah_ar_en));
            tasbihat.add(getResources().getString(R.string.tasbih_la_ilaha_ila_allah2_ar_en));
            tasbihat.add(getResources().getString(R.string.tasbih_astaghfiru_allah_ar_en));
            tasbihat.add(getResources().getString(R.string.tasbih_Allahuma_sali_wasalim_ar_en));
            tasbihat.add(getResources().getString(R.string.tasbih_la_hawla_ar_en));
            tasbihat.add(getResources().getString(R.string.tasbih_subhan_allah_wa_bihamdih_ar_en));
            tasbihat.add(getResources().getString(R.string.tasbih_la_ilaha_ila_allah3_ar_en));

        }else if (Lang.equals("ar")) {
            tasbihat.add(getResources().getString(R.string.tasbih_subhan_allah_arabic));
            tasbihat.add(getResources().getString(R.string.tasbih_alhamdou_lilah_arabic));
            tasbihat.add(getResources().getString(R.string.tasbih_allahu_akbar_arabic));
            tasbihat.add(getResources().getString(R.string.tasbih_la_ilaha_ila_allah_arabic));
            tasbihat.add(getResources().getString(R.string.tasbih_la_ilaha_ila_allah2_arabic));
            tasbihat.add(getResources().getString(R.string.tasbih_astaghfiru_allah_arabic));
            tasbihat.add(getResources().getString(R.string.tasbih_Allahuma_sali_wasalim_arabic));
            tasbihat.add(getResources().getString(R.string.tasbih_la_hawla_arabic));
            tasbihat.add(getResources().getString(R.string.tasbih_subhan_allah_wa_bihamdih_arabic));
            tasbihat.add(getResources().getString(R.string.tasbih_la_ilaha_ila_allah3_arabic));

        }


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), R.layout.row_textview, tasbihat);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        if (list.getChildCount()<98) {
            list.addView(layoutInflater.inflate(R.layout.alert_dialog_tasbih_item_addbtn, null), list.getChildCount(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));



            LinearLayout ll = (LinearLayout) list.getChildAt(list.getChildCount() - 1);
            addview = (Button) ll.getChildAt(0);
            if (Lang.equals("en")) {
                addview.setText("Add Another Thikr");
            }else if (Lang.equals("ar")) {
                addview.setText("أضف ذكرا أخر");
            }

            addview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addviewOnclick();
                }
            });
        }
        ImageView rmv= list.getChildAt(list.getChildCount() - 2).findViewById(R.id.rmv);
        rmv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.removeViewAt(list.getChildCount()-2);
            }
        });
    }
    public class CustomDialogClass extends Dialog implements
            android.view.View.OnClickListener {

        public Activity c;

        public Button Restartdif, Restart,Stop;

        public CustomDialogClass(Activity a) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.res_alretdialog);
            Restart = findViewById(R.id.Restart);
            Restartdif = findViewById(R.id.Restartdif);
            Stop = findViewById(R.id.Stop);
            this.setCanceledOnTouchOutside(true);



            Restart.setOnClickListener(this);
            Restartdif.setOnClickListener(this);
            Stop.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Restartdif:
                    for (int i = 0;i<100;i++){
                        if (queuedTasbihatMarat[i]!=null){
                            queuedTasbihatMarat[i]=null;
                        }
                    }

                    ChhalMnThikr=1;
                    progressInt=0;
                    counterTV.setText(getResources().getString(R.string.start));
                    reset.setEnabled(false);

                        iveditOnclick();

                        setthkirTv((queuedTasbihat[thikrnum]), Lang);

                        dismiss();

                    break;
                case R.id.Restart:

                    progressInt=0;
                    counterTV.setText(String.valueOf(progressInt));
                    reset.setEnabled(true);

                        setthkirTv((queuedTasbihat[thikrnum]), Lang);

                        dismiss();

                    break;
                case R.id.Stop:
                    for (int i = 0;i<100;i++){
                        if (queuedTasbihatMarat[i]!=null){
                            queuedTasbihatMarat[i]=null;
                        }
                    }
                    ChhalMnThikr=1;
                    progressInt=0;
                    thikrnum=1;

                    counterTV.setText(getResources().getString(R.string.start));
                    reset = getActivity().findViewById(R.id.reset);
                    reset.setEnabled(false);
                    started=false;

                            setthkirTv((queuedTasbihat[thikrnum]), Lang);

                            dismiss();
                    break;
                default:
                    break;
            }

        }
    }

    public class ResetDialogClass extends Dialog implements
            android.view.View.OnClickListener {

        public Activity c;

        public Button ResetAll, Reset,cancelha;

        public ResetDialogClass(Activity a) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.reset_alretdialog);
            Reset = findViewById(R.id.Reset);
            ResetAll = findViewById(R.id.ResetAll);
            cancelha = findViewById(R.id.cancelha);
            this.setCanceledOnTouchOutside(false);



            Reset.setOnClickListener(this);
            ResetAll.setOnClickListener(this);
            cancelha.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ResetAll:
                    for (int i = 0;i<100;i++){
                        if (queuedTasbihatMarat[i]!=null){
                            queuedTasbihatMarat[i]=null;
                        }
                    }

                    ChhalMnThikr=1;
                    progressInt=0;
                    thikrnum=1;
                    counterTV.setText(getResources().getString(R.string.start));
                    reset.setEnabled(false);
                    started=false;


                        setthkirTv((queuedTasbihat[thikrnum]), Lang);

                        dismiss();

                    break;
                case R.id.Reset:

                    progressInt=0;

                    counterTV.setText(String.valueOf(progressInt));
                    reset.setEnabled(true);

                        dismiss();

                            dismiss();

                    break;
                case R.id.cancelha:

                    dismiss();
                    break;
                default:
                    break;
            }

        }
    }



    public class SettingsDialogClass extends Dialog implements
            android.view.View.OnClickListener {

        public Activity c;

        CheckBox vibration_when_the_thikr_changes, vibration_on_every_tasbiha;
        RadioButton rbEn,rbAr;

        Button okaybt;
        public SettingsDialogClass(Activity a) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;
        }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.settings_alretdialog);
            this.setCanceledOnTouchOutside(true);



            vibration_when_the_thikr_changes = findViewById(R.id.vibration_when_the_thikr_changes);
            vibration_on_every_tasbiha = findViewById(R.id.vibration_on_every_tasbiha);
            okaybt = findViewById(R.id.okaybt);

            rbEn = findViewById(R.id.rbEn);
            rbAr = findViewById(R.id.rbAr);


            if (Locale.getDefault().getLanguage().equals("en")) {
                rbEn.setChecked(true);
                rbAr.setChecked(false);

            }else if (Locale.getDefault().getLanguage().equals("ar")){
                rbEn.setChecked(false);
                rbAr.setChecked(true);
            }
            rbEn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    rbAr.setChecked(!rbEn.isChecked());
                }
            });
            rbAr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    rbEn.setChecked(!rbAr.isChecked());
                }
            });

            okaybt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor point = sp[0].edit();

                    ontasbihachangevibration = vibration_when_the_thikr_changes.isChecked();
                    onclickvibration = vibration_on_every_tasbiha.isChecked();


                    if(rbEn.isChecked()&&Lang!="en"){
                        Locale locale = new Locale("en");
                        Locale.setDefault(locale);
                        Configuration configuration = new Configuration();
                        configuration.locale = locale;
                        getActivity().getBaseContext().getResources().updateConfiguration(configuration, getActivity().getBaseContext().getResources().getDisplayMetrics());
                        Lang="en";
                     /*   FragmentManager fm;
                        fm = getActivity().getSupportFragmentManager();
                        TasbihFragment tasbihFragment = TasbihFragment.this;
                        if (fm.findFragmentByTag("tasbihFragment")==null) {
                            fm.beginTransaction().add(R.id.main_frame, tasbihFragment, "tasbihFragment").hide(tasbihFragment).commit();
                        }
                        fm.beginTransaction().show(tasbihFragment).commit();
                      //  getActivity().recreate();
*/                   point.putBoolean("ontasbihachangevibration", ontasbihachangevibration);
                        point.putBoolean("onclickvibration", onclickvibration);
                        point.putString("Lang", Lang);
                        point.apply();
                        dismiss();

                        getActivity().finish();
                        Intent myIntent = new Intent(getActivity(), MainActivity.class);
                        startActivity(myIntent);

                    }else if (rbAr.isChecked()&&Lang!="ar"){
                        Locale locale = new Locale("ar");
                        Locale.setDefault(locale);
                        Configuration configuration = new Configuration();
                        configuration.locale = locale;
                        getActivity().getBaseContext().getResources().updateConfiguration(configuration, getActivity().getBaseContext().getResources().getDisplayMetrics());
                        Lang="ar";
                    /*    FragmentManager fm;
                        fm = getActivity().getSupportFragmentManager();
                        TasbihFragment tasbihFragment = TasbihFragment.this;
                        if (fm.findFragmentByTag("tasbihFragment")==null) {
                            fm.beginTransaction().add(R.id.main_frame, tasbihFragment, "tasbihFragment").hide(tasbihFragment).commit();
                        }
                        fm.beginTransaction().show(tasbihFragment).commit();
                      //  getActivity().recreate();*/
                        point.putBoolean("ontasbihachangevibration", ontasbihachangevibration);
                        point.putBoolean("onclickvibration", onclickvibration);
                        point.putString("Lang", Lang);
                        point.apply();
                        dismiss();

                        getActivity().finish();
                        Intent myIntent = new Intent(getActivity(), MainActivity.class);
                        startActivity(myIntent);
                    }else{
                        dismiss();

                    }



                }
            });
            if (!ontasbihachangevibration){
                vibration_when_the_thikr_changes.setChecked(false);
            }else {
                vibration_when_the_thikr_changes.setChecked(true);
            }
            if (!onclickvibration){
                vibration_on_every_tasbiha.setChecked(false);
            }else {
                vibration_on_every_tasbiha.setChecked(true);
            }

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Restartdif:
                    for (int i = 0;i<100;i++){
                        if (queuedTasbihatMarat[i]!=null){
                            queuedTasbihatMarat[i]=null;
                        }
                    }

                    ChhalMnThikr=1;
                    progressInt=0;
                    thikrnum=1;


                        iveditOnclick();

                        setthkirTv((queuedTasbihat[thikrnum]), Lang);
                      
                        dismiss();

                    break;
                case R.id.Restart:
                    progressInt=0;
                    counterTV.setText(String.valueOf(progressInt));
                    reset.setEnabled(true);
                    thikrnum=1;

                        setthkirTv((queuedTasbihat[thikrnum]), Lang);
                      
                        dismiss();

                    break;
                case R.id.Stop:
                    for (int i = 0;i<100;i++){
                        if (queuedTasbihatMarat[i]!=null){
                            queuedTasbihatMarat[i]=null;
                        }
                    }
                    ChhalMnThikr=1;
                    progressInt=0;
                    thikrnum=1;

                    counterTV.setText(getResources().getString(R.string.start));
                    reset = getActivity().findViewById(R.id.reset);
                    reset.setEnabled(false); started=false;

                        setthkirTv((queuedTasbihat[thikrnum]), Lang);
                        dismiss();

                    break;
                default:
                    break;
            }

        }
    }

}
