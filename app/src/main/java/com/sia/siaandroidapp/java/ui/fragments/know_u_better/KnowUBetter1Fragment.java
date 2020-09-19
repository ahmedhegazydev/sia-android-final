package com.sia.siaandroidapp.java.ui.fragments.know_u_better;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.model.Subject;
import com.sia.siaandroidapp.java.ui.adapters.recycler.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

//public class KnowUBetter1Fragment extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {
public class KnowUBetter1Fragment extends DaggerFragment implements MyRecyclerViewAdapter.ItemClickListener {

    private static final String TAG = "KnowUBetter1Fragment";
    //    @BindView(R.id.gridMultiSubjects)
//    GridView gridMultiSubjects;
    @BindView(R.id.recyclerFavSubjects)
    RecyclerView recyclerFavSubjects;
    @BindView(R.id.etSearchSubjects)
    EditText etSearchSubjects;
    private List<Subject> subjects = new ArrayList<>();
    private MyRecyclerViewAdapter adapter = null;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.e(TAG, "afterTextChanged: " + s.toString());
            adapter.getFilter().filter(s.toString());
        }
    };

    public static KnowUBetter1Fragment newInstance() {
        Bundle args = new Bundle();
        KnowUBetter1Fragment fragment = new KnowUBetter1Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    // newInstance constructor for creating fragment with arguments
    public static KnowUBetter1Fragment newInstance(int page, String title) {
        KnowUBetter1Fragment fragmentFirst = new KnowUBetter1Fragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_know_you_1,
                container, false);
        ButterKnife.bind(KnowUBetter1Fragment.this, rootView);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        gridMultiSubjects.setAdapter(new FavSubjectsAdapter(getActivity(), getDummyList()));
//        gridMultiSubjects.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
//        gridMultiSubjects.setMultiChoiceModeListener(new MultiChoiceModeListener(gridMultiSubjects));


        // set up the RecyclerView
        recyclerFavSubjects.setLayoutManager(new GridLayoutManager(getActivity(),
                4));
        adapter = new MyRecyclerViewAdapter(getActivity(), getDummyList());
        adapter.setClickListener(this);
        recyclerFavSubjects.setAdapter(adapter);


        etSearchSubjects.addTextChangedListener(textWatcher);

    }

    private List<Subject> getDummyList() {
        subjects.clear();
        subjects.add(new Subject("Hrj", R.drawable.math_subject, R.drawable.math_subject_selected));
        subjects.add(new Subject("Helo", R.drawable.math_subject, R.drawable.math_subject_selected));
        subjects.add(new Subject("Hwow", R.drawable.math_subject, R.drawable.math_subject_selected));
        subjects.add(new Subject("Hgggg", R.drawable.math_subject, R.drawable.math_subject_selected));
        subjects.add(new Subject("Her", R.drawable.math_subject, R.drawable.math_subject_selected));
        subjects.add(new Subject("Hoo", R.drawable.math_subject, R.drawable.math_subject_selected));
        subjects.add(new Subject("Hdd", R.drawable.math_subject, R.drawable.math_subject_selected));


        return this.subjects;
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.e(TAG, "onItemClick: " + position);

        TextView tvSubjectTitle = view.findViewById(R.id.tvSubjectTitle);
        ImageView ivSubjectImage = view.findViewById(R.id.ivSubjectImage);
        Subject subject = this.subjects.get(position);

        if (view.getTag() != null) {
            //this view is selected
            ivSubjectImage.setImageResource(subject.getUnSelectedImage());
            view.setTag(null);
            setColor(tvSubjectTitle, R.color.dim_gray);

        } else {
            //this view is not selected
            ivSubjectImage.setImageResource(subject.getSelectedImage());
            view.setTag(true);
            setColor(tvSubjectTitle, R.color.colorBlue);


        }
    }


    private void setColor(TextView tvSubjectTitle, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tvSubjectTitle.setTextColor(getActivity().getColor(color));
        } else {
            tvSubjectTitle.setTextColor(ContextCompat.getColor(getActivity(),
                    color));
        }
    }


}
