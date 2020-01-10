package wassimtech.sabha;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 * A simple {@link Fragment} subclass.
 */
public class DuaFragment extends Fragment {


    public DuaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dua, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final FragmentManager fm = getActivity().getSupportFragmentManager();

        final MorningDuaFragment morningDuaFragment= new MorningDuaFragment();
        if (!morningDuaFragment.isAdded()) {
            fm.beginTransaction().add(R.id.main_frame, morningDuaFragment, "MorningDuaFragment").hide(morningDuaFragment).commit();
        }
        final EveningFragment eveningFragment= new EveningFragment();
        if (!eveningFragment.isAdded()) {
            fm.beginTransaction().add(R.id.main_frame, eveningFragment, "EveningFragment").hide(eveningFragment).commit();
        }
        final BeforeSleepingFragment beforeSleepingFragment= new BeforeSleepingFragment();
        if (!beforeSleepingFragment.isAdded()) {
            fm.beginTransaction().add(R.id.main_frame, beforeSleepingFragment, "BeforeSleepingFragment").hide(beforeSleepingFragment).commit();
        }
        final EatingDuaFragment eatingDuaFragment= new EatingDuaFragment();
        if (!eatingDuaFragment.isAdded()) {
            fm.beginTransaction().add(R.id.main_frame, eatingDuaFragment, "EatingDuaFragment").hide(eatingDuaFragment).commit();
        }
        final AfterThePrayerFragment afterThePrayerFragment= new AfterThePrayerFragment();
        if (!afterThePrayerFragment.isAdded()) {
            fm.beginTransaction().add(R.id.main_frame, afterThePrayerFragment, "AfterThePrayerFragment").hide(afterThePrayerFragment).commit();
        }
        final SuaratAlKahfFragment suaratAlKahfFragment= new SuaratAlKahfFragment();
        if (!afterThePrayerFragment.isAdded()) {
            fm.beginTransaction().add(R.id.main_frame, suaratAlKahfFragment, "suaratAlKahfFragment").hide(suaratAlKahfFragment).commit();
        }
        final SuaratAlMulkFragment suaratAlmulkFragment= new SuaratAlMulkFragment();
        if (!afterThePrayerFragment.isAdded()) {
            fm.beginTransaction().add(R.id.main_frame, suaratAlmulkFragment, "suaratAlmulkFragment").hide(suaratAlmulkFragment).commit();
        }
        final TextView morning_azkar = view.findViewById(R.id.morning_dua);
        morning_azkar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fm.beginTransaction().show(morningDuaFragment).commit();

            }
        });

    final TextView evening_dua = view.findViewById(R.id.evening_dua);
        evening_dua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fm.beginTransaction().show(eveningFragment).commit();

            }
        });

        final TextView before_sleeping_dua = view.findViewById(R.id.before_sleeping_dua);
        before_sleeping_dua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fm.beginTransaction().show(beforeSleepingFragment).commit();

            }
        });


        final TextView dua_before_and_after_eating = view.findViewById(R.id.eating_dua);
        dua_before_and_after_eating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction().show(eatingDuaFragment).commit();

            }
        });

        final TextView after_the_prayer_dua = view.findViewById(R.id.after_the_prayer_dua);
        after_the_prayer_dua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fm.beginTransaction().show(afterThePrayerFragment).commit();

            }
        });


        final TextView surat_al_kahf = view.findViewById(R.id.surat_al_kahf);
        surat_al_kahf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fm.beginTransaction().show(suaratAlKahfFragment).commit();

            }
        });
        final TextView surat_al_mulk = view.findViewById(R.id.surat_al_mulk);
        surat_al_mulk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fm.beginTransaction().show(suaratAlmulkFragment).commit();

            }
        });
    }
}
