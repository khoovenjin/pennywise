package com.budgettracking.pennywise.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.budgettracking.pennywise.PennywiseApp;
import com.budgettracking.pennywise.R;
import com.budgettracking.pennywise.adapters.WelcomePagerAdapter;
import com.budgettracking.pennywise.custom.CrossfadePageTransformer;
import com.budgettracking.pennywise.ui.BaseFragment;
import com.budgettracking.pennywise.ui.MainActivity;
import com.viewpagerindicator.CirclePageIndicator;

public class LoginFragment extends BaseFragment implements View.OnClickListener  {

    public static final String TAG = LoginFragment.class.getSimpleName();

    private static final int RC_SIGN_IN = 0;
//    private GoogleApiClient mGoogleApiClient;


    // Firebase Console

    //Only on newer version.
    //Yikes

    //Not needed for user management.
    //Unused Component as Firebase requires newer SDK version

//    private boolean mIsResolving = false;
//    private boolean mShouldResolve = false;

    private ViewPager vpWelcome;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(Plus.API)
//                .addScope(new Scope(Scopes.PROFILE))
//                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        vpWelcome = (ViewPager)rootView.findViewById(R.id.vp_welcome);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        WelcomePagerAdapter welcomePagerAdapter = new WelcomePagerAdapter(getChildFragmentManager());
        vpWelcome.setAdapter(welcomePagerAdapter);
        vpWelcome.setPageTransformer(true, new CrossfadePageTransformer());
        CirclePageIndicator circlePageIndicator = (CirclePageIndicator)getView().findViewById(R.id.cpi_welcome);
        circlePageIndicator.setViewPager(vpWelcome);

//        getView().findViewById(R.id.sign_in_button).setOnClickListener(this);
        getView().findViewById(R.id.sign_in).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
//        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
//        mGoogleApiClient.disconnect();
    }


    @Override
    public void onClick(View view) {
//        if(view.getId() == R.id.sign_in_button) {
//            onSignInClicked();
//        } else
        if (view.getId() == R.id.sign_in) {
            showSignedInUI();
        }
    }

//    @Override
//    public void onConnectionFailed(ConnectionResult connectionResult) {
//        Log.d(TAG, "onConnectionFailed:" + connectionResult);
//        if (!mIsResolving && mShouldResolve) {
//            if (connectionResult.hasResolution()) {
//                try {
//                    connectionResult.startResolutionForResult(getActivity(), RC_SIGN_IN);
//                    mIsResolving = true;
//                } catch (IntentSender.SendIntentException e) {
//                    Log.e(TAG, "Could not resolve ConnectionResult.", e);
//                    mIsResolving = false;
//                    mGoogleApiClient.connect();
//                }
//            } else {
//                // Could not resolve the connection result, show the user an
//                // error dialog.
////                showErrorDialog(connectionResult);
//            }
//        } else {
//            // Show the signed-out UI
////            showSignedOutUI();
//        }
//    }


    private void showSignedInUI() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(PennywiseApp.getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getString(R.string.already_accepted_user_key), true);
        editor.apply();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        getActivity().finish();
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Log.d(TAG, "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);
//
//        if (requestCode == RC_SIGN_IN) {
//            // If the error resolution was not successful we should not resolve further.
//            if (resultCode != Activity.RESULT_OK) {
//                mShouldResolve = false;
//            }
//
//            mIsResolving = false;
//            mGoogleApiClient.connect();
//        }
    }
}
