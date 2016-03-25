package com.gcme.deeplife.Activities.Build;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gcme.deeplife.R;

/**
 * Created by rog on 11/7/2015.
 */


public class BuildFragment extends Fragment {

    public static final String ARG_PAGE = "page";

    private int mPageNumber;
    ImageView iv_build_image;

    RadioGroup rdGroup;
    RadioButton rb_yes;
    RadioButton rb_no;
    TextView tv_qdisc, tv_note;

    public static BuildFragment create(int pageNumber) {
        BuildFragment fragment = new BuildFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.winfragment, container, false);
        tv_qdisc = (TextView) rootView.findViewById(R.id.win_question);
        rb_yes = (RadioButton) rootView.findViewById(R.id.rb_yes);
        rb_no = (RadioButton) rootView.findViewById(R.id.rb_no);
        tv_note = (TextView) rootView.findViewById(R.id.win_note);
        iv_build_image = (ImageView) rootView.findViewById(R.id.win_image);

        iv_build_image.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.buildicon));

        if(getPageNumber()<BuildActivity.questions.size()) {
            tv_qdisc.setText(BuildActivity.questions.get(getPageNumber()).getDescription());
            tv_note.setText(BuildActivity.questions.get(getPageNumber()).getNote());

            //if already answered , update the radio buttons
            if(BuildActivity.answers.get(getPageNumber()).equals("Yes")){
                rb_yes.setChecked(true);
                rb_no.setChecked(false);
                BuildActivity.mPager.setSwipeable(true);
            }
            else if(BuildActivity.answers.get(getPageNumber()).equals("No")){
                rb_no.setChecked(true);
                rb_yes.setChecked(false);
                BuildActivity.mPager.setSwipeable(true);

            }

            else{
                BuildActivity.mPager.setSwipeable(false);
            }

        }


        rb_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuildActivity.answer_index = 0;
                BuildActivity.answers.set(mPageNumber, BuildActivity.answerchoices.get(BuildActivity.answer_index));
                BuildActivity.mPager.setSwipeable(true);
            }
        });

        rb_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuildActivity.answer_index = 1;
                BuildActivity.answers.set(mPageNumber, BuildActivity.answerchoices.get(BuildActivity.answer_index));
                BuildActivity.mPager.setSwipeable(true);
            }
        });



        return rootView;
    }


    public int getPageNumber() {
        return mPageNumber;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}