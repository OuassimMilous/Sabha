package wassimtech.sabha;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;



/**
 * A simple {@link Fragment} subclass.
 */
public class MorningDuaFragment extends Fragment {

    public MorningDuaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_morning, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final MorningDuaFragment morningDuaFragment= this;


        TextView source = view.findViewById(R.id.source_morning_dua1);
        source.setMovementMethod(LinkMovementMethod.getInstance());

        ImageButton goback_morning = view.findViewById(R.id.goback_morning);
        goback_morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = fm.findFragmentByTag("MorningDuaFragment");
                if (fragment != null) {
                    fm.beginTransaction().hide(fragment).commit();
                }
            }
        });

        morningDuaFragment.getView().setFocusableInTouchMode(true);
        morningDuaFragment.getView().requestFocus();
        morningDuaFragment.getView().setOnKeyListener( new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    Fragment fragment = fm.findFragmentByTag("MorningDuaFragment");
                    if (fragment != null) {
                        fm.beginTransaction().hide(fragment).commit();
                    }
                    return true;
                }
                return false;
            }
        });
    }
}