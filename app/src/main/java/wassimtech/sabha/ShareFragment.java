package wassimtech.sabha;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends Fragment {


    public ShareFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final Button other= view.findViewById(R.id.shareonOther);
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBodyText = getString(R.string.share_other_text);
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Sabha - سبحة");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(intent, "Choose sharing method"));
            }
        });

        Button copyLink = view.findViewById(R.id.shareonCopyLink);
        copyLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("label", getResources().getString(R.string.app_link));
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(getContext(), getResources().getString(R.string.link_copied_to_clipboard), Toast.LENGTH_SHORT).show();
            }
        });


        Button facebook = view.findViewById(R.id.shareonFacebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   boolean facebookAppFound = false;
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_other_text));
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(getString(R.string.app_link)));

                PackageManager pm = getContext().getPackageManager();
                List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
                for (final ResolveInfo app : activityList) {
                    if ((app.activityInfo.packageName).contains("com.facebook.katana")) {
                        final ActivityInfo activityInfo = app.activityInfo;
                        final ComponentName name = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);
                        shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                        shareIntent.setComponent(name);
                        facebookAppFound = true;
                        break;
                    }
                }
                if (!facebookAppFound) {
                    Toast.makeText(getContext(), "this device doesn't have the Facebook app please choose another way to share", Toast.LENGTH_SHORT).show();
                    other.callOnClick();

                }
                getContext().startActivity(shareIntent);
*/

               Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);

                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,  getString(R.string.app_link));
                PackageManager pm = getContext().getPackageManager();
                List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
            if (!activityList.isEmpty()) {
                for (final ResolveInfo app : activityList) {
                    if ((app.activityInfo.name).contains("facebook")) {
                        final ActivityInfo activity = app.activityInfo;
                        final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
                        shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                        shareIntent.setComponent(name);
                        startActivity(shareIntent);
                        break;
                    }
                }
            }else {
                Toast.makeText(getContext(), getResources().getString(R.string.udnthvfb), Toast.LENGTH_LONG).show();
                other.callOnClick();

            }

            }
        });

        Button msngr = view.findViewById(R.id.shareonMessenger);
        msngr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,getString(R.string.share_other_text));
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.facebook.orca");
                try {
                    startActivity(sendIntent);
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(), getResources().getString(R.string.udnthvmessanger), Toast.LENGTH_LONG).show();
                    other.callOnClick();
                }
            }
        });

        Button Twitter = view.findViewById(R.id.shareonTwitter);
        Twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringBuilder tweetUrl = new StringBuilder("https://twitter.com/intent/tweet?text=");

                tweetUrl.append(TextUtils.isEmpty(getString(R.string.share_other_text)) ? urlEncode(getString(R.string.app_link)) : urlEncode(getString(R.string.share_other_text)));
                if (!TextUtils.isEmpty(getString(R.string.app_link))) {
                    tweetUrl.append("&url=");
                }
                if (!TextUtils.isEmpty("")) {
                    tweetUrl.append("&via=");
                    tweetUrl.append(urlEncode(""));
                }
                if (!TextUtils.isEmpty("")) {
                    tweetUrl.append("&hastags=");
                    tweetUrl.append(urlEncode(""));
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl.toString()));
                List<ResolveInfo> matches = getContext().getPackageManager().queryIntentActivities(intent, 0);
                if (!matches.isEmpty()) {
                    for (ResolveInfo info : matches) {
                        if (info.activityInfo.packageName.toLowerCase().startsWith("com.twitter")) {
                            intent.setPackage(info.activityInfo.packageName);
                        }
                    }
                    getContext().startActivity(intent);
                }else {
                    Toast.makeText(getContext(), getResources().getString(R.string.udnthvtwitter), Toast.LENGTH_LONG).show();
                    other.callOnClick();
                }
            }

            public  String urlEncode(String s) {
                try {
                    return URLEncoder.encode(s, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    Log.wtf("wth", "UTF-8 should always be supported", e);
                    throw new RuntimeException("URLEncoder.encode() failed for " + s);
                }
            }
        });

        Button WhatsApp = view.findViewById(R.id.shareonWhatsApp);
        WhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getContext().getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                if (intent != null){
                    Intent shareonwhats = new Intent();
                    shareonwhats.setAction(Intent.ACTION_SEND);
                    shareonwhats.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_other_text));
                    shareonwhats.setPackage("com.whatsapp");
                    shareonwhats.setType("text/plain");
                    getContext().startActivity(Intent.createChooser(shareonwhats,"Share Via"));

                }else {
                    Toast.makeText(getContext(),  getResources().getString(R.string.udnthvwhatsapp), Toast.LENGTH_LONG).show();
                    other.callOnClick();
                }
            }
        });


    }
}
