package com.sportmax.ipfformula;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment3Exercises#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment3Exercises extends Fragment {
    private EditText edSquat, edBench, edDeadlift;
    private EditText edSquat2, edBench2, edDeadlift2;
    private EditText edSquat3, edBench3, edDeadlift3;
    private EditText edSquat4, edBench4, edDeadlift4;
    private EditText edSquat5, edBench5, edDeadlift5;
    private TextView txtPlusTotal1, txtResult1, txtBodyWightFrag1;
    private TextView txtPlusTotal2, txtResult2,txtBodyWightFrag2;
    private TextView txtPlusTotal3, txtResult3,txtBodyWightFrag3;
    private TextView txtPlusTotal4, txtResult4,txtBodyWightFrag4;
    private TextView txtPlusTotal5, txtResult5,txtBodyWightFrag5;
    private ImageView imgAddExercises;
    private ImageView imgDel2;
    private ImageView imgDel3;
    private ImageView imgDel4;
    private ImageView imgDel5;
    private double totalFragment1 = 0.00, squat1 = 0.00, bench1 = 0.00, deadlift1 = 0.00;
    private double totalFragment2 = 0.00, squat2 = 0.00, bench2 = 0.00, deadlift2 = 0.00;
    private double totalFragment3 = 0.00, squat3 = 0.00, bench3 = 0.00, deadlift3 = 0.00;
    private double totalFragment4 = 0.00, squat4 = 0.00, bench4 = 0.00, deadlift4 = 0.00;
    private double totalFragment5 = 0.00, squat5 = 0.00, bench5 = 0.00, deadlift5 = 0.00;
    public double bw1,bw2,bw3,bw4,bw5,bwFromActivity;
    int addNewPersonForWatch = 0;
    public String strResult,sTotal,sBodyweight;

    private LinearLayout ll1,ll2,ll3,ll4,ll5;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public interface onTransferTotalListener {
        void transferTotal(double d);
    }

    private onTransferTotalListener transferTotalListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            transferTotalListener = (onTransferTotalListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
    }

    public Fragment3Exercises() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment3Exercises.
     */
    // TODO: Rename and change types and number of parameters
    static Fragment3Exercises newInstance(String param1, String param2) {
        Fragment3Exercises fragment = new Fragment3Exercises();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment3_exercises, container, false);
        sTotal=getResources().getString(R.string.total_);
        sBodyweight=getResources().getString(R.string.bodyweight);

        edSquat = rootView.findViewById(R.id.ed_squat_fragment_1);
        edBench = rootView.findViewById(R.id.ed_bench_fragment_1);
        edDeadlift = rootView.findViewById(R.id.ed_deadlift_fragment_1);
        txtBodyWightFrag1=rootView.findViewById(R.id.txt_body_weight_frag1);
        txtBodyWightFrag1.setText(" 1) "+sBodyweight);
        txtResult1 = rootView.findViewById(R.id.txt_result_fragment_1);
        txtPlusTotal1 = rootView.findViewById(R.id.txt_plus_3_exercises_1);
        edSquat.addTextChangedListener(watcher);
        edBench.addTextChangedListener(watcher);
        edDeadlift.addTextChangedListener(watcher);

        edSquat2 = rootView.findViewById(R.id.ed_squat_fragment_2);
        edBench2 = rootView.findViewById(R.id.ed_bench_fragment_2);
        edDeadlift2 = rootView.findViewById(R.id.ed_deadlift_fragment_2);
        txtBodyWightFrag2=rootView.findViewById(R.id.txt_body_weight_frag2);
        txtBodyWightFrag2.setText(" 2) "+sBodyweight);
        txtResult2 = rootView.findViewById(R.id.txt_result_fragment_2);
        txtPlusTotal2 = rootView.findViewById(R.id.txt_plus_3_exercises_2);
        edSquat2.addTextChangedListener(watcher);
        edBench2.addTextChangedListener(watcher);
        edDeadlift2.addTextChangedListener(watcher);


        edSquat3 = rootView.findViewById(R.id.ed_squat_fragment_3);
        edBench3 = rootView.findViewById(R.id.ed_bench_fragment_3);
        edDeadlift3 = rootView.findViewById(R.id.ed_deadlift_fragment_3);
        txtBodyWightFrag3=rootView.findViewById(R.id.txt_body_weight_frag3);
        txtBodyWightFrag3.setText(" 3) "+sBodyweight);
        txtResult3 = rootView.findViewById(R.id.txt_result_fragment_3);
        txtPlusTotal3 = rootView.findViewById(R.id.txt_plus_3_exercises_3);
        edSquat3.addTextChangedListener(watcher);
        edBench3.addTextChangedListener(watcher);
        edDeadlift3.addTextChangedListener(watcher);

        edSquat4 = rootView.findViewById(R.id.ed_squat_fragment_4);
        edBench4 = rootView.findViewById(R.id.ed_bench_fragment_4);
        edDeadlift4 = rootView.findViewById(R.id.ed_deadlift_fragment_4);
        txtBodyWightFrag4=rootView.findViewById(R.id.txt_body_weight_frag4);
        txtBodyWightFrag4.setText(" 4) "+sBodyweight);
        txtResult4 = rootView.findViewById(R.id.txt_result_fragment_4);
        txtPlusTotal4 = rootView.findViewById(R.id.txt_plus_3_exercises_4);
        edSquat4.addTextChangedListener(watcher);
        edBench4.addTextChangedListener(watcher);
        edDeadlift4.addTextChangedListener(watcher);

        edSquat5 = rootView.findViewById(R.id.ed_squat_fragment_5);
        edBench5 = rootView.findViewById(R.id.ed_bench_fragment_5);
        edDeadlift5 = rootView.findViewById(R.id.ed_deadlift_fragment_5);
        txtBodyWightFrag5=rootView.findViewById(R.id.txt_body_weight_frag5);
        txtBodyWightFrag5.setText(" 5) "+sBodyweight);
        txtResult5 = rootView.findViewById(R.id.txt_result_fragment_3);
        txtPlusTotal5 = rootView.findViewById(R.id.txt_plus_3_exercises_3);
        edSquat5.addTextChangedListener(watcher);
        edBench5.addTextChangedListener(watcher);
        edDeadlift5.addTextChangedListener(watcher);

        ImageView imgDel1 = rootView.findViewById(R.id.img_del1);
        imgDel2=rootView.findViewById(R.id.img_del2);
        imgDel3=rootView.findViewById(R.id.img_del3);
        imgDel4=rootView.findViewById(R.id.img_del4);
        imgDel5=rootView.findViewById(R.id.img_del5);
        imgDel1.setOnClickListener(onClickDeletePerson);
        imgDel2.setOnClickListener(onClickDeletePerson);
        imgDel3.setOnClickListener(onClickDeletePerson);
        imgDel4.setOnClickListener(onClickDeletePerson);
        imgDel5.setOnClickListener(onClickDeletePerson);

        imgAddExercises = rootView.findViewById(R.id.img_add_exercises);


        ll1 = rootView.findViewById(R.id.linearLayout_frag_1);
        ll1.setBackgroundResource(R.drawable.back);

        ll2 = rootView.findViewById(R.id.linearLayout_frag_2);
        ll2.setVisibility(View.GONE);
        ll3 = rootView.findViewById(R.id.linearLayout_frag_3);
        ll3.setVisibility(View.GONE);
        ll4 = rootView.findViewById(R.id.linearLayout_frag_4);
        ll4.setVisibility(View.GONE);
        ll5 = rootView.findViewById(R.id.linearLayout_frag_5);
        ll5.setVisibility(View.GONE);

        imgAddExercises.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewPersonForWatch++;
                if(addNewPersonForWatch==5){imgAddExercises.setVisibility(GONE);}
                if(ll1.getVisibility()==GONE){
                    ll1.setBackgroundResource(R.drawable.back);
                    ll1.setVisibility(VISIBLE);

                }else{
                    if(ll2.getVisibility()==GONE){
                        ll2.setBackgroundResource(R.drawable.back);
                        ll2.setVisibility(VISIBLE);

                    }else{
                        if(ll3.getVisibility()==GONE){
                            ll3.setBackgroundResource(R.drawable.back);
                            ll3.setVisibility(VISIBLE);
                        }else {
                            if (ll4.getVisibility() == GONE) {
                                ll4.setBackgroundResource(R.drawable.back);
                                ll4.setVisibility(VISIBLE);
                            } else {
                                if (ll5.getVisibility() == GONE) {
                                    ll5.setBackgroundResource(R.drawable.back);
                                    ll5.setVisibility(VISIBLE);
                                }
                            }
                        }
                    }
                }
            }
        });
        return rootView;
    }

    private View.OnClickListener onClickDeletePerson=new OnClickListener() {
        @Override
        public void onClick(View v) {
            addNewPersonForWatch--;
            if(addNewPersonForWatch<5){
                imgAddExercises.setVisibility(VISIBLE);
            }
            switch (v.getId()){
                case R.id.img_del1:
                    edSquat.setText("");
                    edBench.setText("");
                    edDeadlift.setText("");
                    squat1=0.00;bench1=0.00;deadlift1=0.00;
                    txtBodyWightFrag1.setText(" 1) "+sBodyweight+"0.00");txtPlusTotal1.setText(sTotal+ "0.00");txtResult1.setText("0.00");
                    ll1.setVisibility(View.GONE);
                    break;
                case R.id.img_del2:
                    edSquat2.setText("");
                    edBench2.setText("");
                    edDeadlift2.setText("");
                    squat2=0.00;
                    bench2=0.00;
                    deadlift2=0.00;
                    txtBodyWightFrag2.setText(" 2) "+sBodyweight+"0.00");
                    txtPlusTotal2.setText(sTotal+ "0.00");
                    txtResult2.setText("0.00");
                    ll2.setVisibility(View.GONE);
                    break;
                case R.id.img_del3:
                    edSquat3.setText("");edBench3.setText("");edDeadlift3.setText("");
                    squat3=0.00;bench3=0.00;deadlift3=0.00;
                    txtBodyWightFrag3.setText(" 3) "+sBodyweight+"0.00");txtPlusTotal3.setText(sTotal+ "0.00");txtResult3.setText("0.00");
                    ll3.setVisibility(View.GONE);
                    break;
                case R.id.img_del4:
                    edSquat4.setText("");edBench4.setText("");edDeadlift4.setText("");
                    squat4=0.00;bench4=0.00;deadlift4=0.00;
                    txtBodyWightFrag4.setText(" 4) "+sBodyweight+"0.00");txtPlusTotal4.setText(sTotal+ "0.00");txtResult4.setText("0.00");
                    ll4.setVisibility(View.GONE);
                    break;
                case R.id.img_del5:
                    edSquat5.setText("");edBench5.setText("");edDeadlift5.setText("");
                    squat5=0.00;bench5=0.00;deadlift5=0.00;
                    txtBodyWightFrag5.setText(" 5) "+sBodyweight+"0.00");txtPlusTotal5.setText(sTotal+ "0.00");txtResult5.setText("0.00");
                    ll5.setVisibility(View.GONE);
                    break;
            }
        }
    };


    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(ll1.hasFocus()){
               // bw1=bwFromActivity;
                if(!txtResult1.getText().equals("0.00")){};
            if (edSquat.getEditableText()==s) {
                if (s.length() == 0 || s.toString().charAt(0) == '.') {
                    squat1 = 0.00;
                } else {
                    squat1 = Double.parseDouble(s.toString());
                }
            } else {
                if (edBench.getEditableText()==s) {
                    if (s.length() == 0 || s.toString().charAt(0) == '.') {
                        bench1 = 0.00;
                    } else {
                        bench1 = Double.parseDouble(s.toString());
                    }
                } else {
                    if (edDeadlift.getEditableText()==s) {
                        if (s.length() == 0 || s.toString().charAt(0) == '.') {
                            deadlift1 = 0.00;
                        } else {
                            deadlift1 = Double.parseDouble(s.toString());
                        }
                    }
                }
            }
                totalFragment1 = squat1 + bench1 + deadlift1;
                txtPlusTotal1.setText(sTotal + totalFragment1);
                transferTotalListener.transferTotal(totalFragment1);
                bw1=bwFromActivity;
                txtBodyWightFrag1.setText(" 1) "+sBodyweight+bw1);
                txtResult1.setText(strResult);

            }
            if(ll2.hasFocus()){
                //bw2=bwFromActivity;
                if (edSquat2.getEditableText()==s) {
                    if (s.length() == 0 || s.toString().charAt(0) == '.') {
                        squat2 = 0.00;
                    } else {
                        squat2 = Double.parseDouble(s.toString());
                    }
                }else{
                    if (edBench2.getEditableText()==s) {
                        if (s.length() == 0 || s.toString().charAt(0) == '.') {
                                bench2 = 0.00;
                            } else {
                                bench2 = Double.parseDouble(s.toString());
                            }
                        } else {
                            if (edDeadlift2.getEditableText()==s) {
                                if (s.length() == 0 || s.toString().charAt(0) == '.') {
                                    deadlift2 = 0.00;
                                } else {
                                    deadlift2 = Double.parseDouble(s.toString());
                                }
                            }
                        }
                    }

                        totalFragment2 = squat2 + bench2 + deadlift2;
                        txtPlusTotal2.setText(sTotal+ totalFragment2);
                        transferTotalListener.transferTotal(totalFragment2);
                        bw2=bwFromActivity;
                        txtBodyWightFrag2.setText(" 2) "+sBodyweight+bw2);
                        txtResult2.setText(strResult);
            }
            if(ll3.hasFocus()){
                // bw3=bwFromActivity;
                if (edSquat3.getEditableText()==s) {
                    if (s.length() == 0 || s.toString().charAt(0) == '.') {
                        squat3 = 0.00;
                    } else {
                        squat3 = Double.parseDouble(s.toString());
                    }
                }else{
                    if (edBench3.getEditableText()==s) {
                        if (s.length() == 0 || s.toString().charAt(0) == '.') {
                            bench3 = 0.00;
                        } else {
                            bench3 = Double.parseDouble(s.toString());
                        }
                    } else {
                        if (edDeadlift3.getEditableText()==s) {
                            if (s.length() == 0 || s.toString().charAt(0) == '.') {
                                deadlift3 = 0.00;
                            } else {
                                deadlift3 = Double.parseDouble(s.toString());
                            }
                        }
                    }
                }

                totalFragment3 = squat3 + bench3 + deadlift3;
                txtPlusTotal3.setText(sTotal+ totalFragment3);
                transferTotalListener.transferTotal(totalFragment3);
                bw3=bwFromActivity;
                txtBodyWightFrag3.setText(" 3) "+sBodyweight+bw3);
                txtResult3.setText(strResult);
            }
            if(ll4.hasFocus()){
                // bw3=bwFromActivity;
                if (edSquat4.getEditableText()==s) {
                    if (s.length() == 0 || s.toString().charAt(0) == '.') {
                        squat4 = 0.00;
                    } else {
                        squat4 = Double.parseDouble(s.toString());
                    }
                }else{
                    if (edBench4.getEditableText()==s) {
                        if (s.length() == 0 || s.toString().charAt(0) == '.') {
                            bench4 = 0.00;
                        } else {
                            bench4 = Double.parseDouble(s.toString());
                        }
                    } else {
                        if (edDeadlift4.getEditableText()==s) {
                            if (s.length() == 0 || s.toString().charAt(0) == '.') {
                                deadlift4 = 0.00;
                            } else {
                                deadlift4 = Double.parseDouble(s.toString());
                            }
                        }
                    }
                }

                totalFragment4 = squat4 + bench4 + deadlift4;
                txtPlusTotal4.setText(sTotal+ totalFragment4);
                transferTotalListener.transferTotal(totalFragment4);
                bw4=bwFromActivity;
                txtBodyWightFrag4.setText(" 4) "+sBodyweight+bw4);
                txtResult4.setText(strResult);
            }
            if(ll5.hasFocus()){
                // bw3=bwFromActivity;
                if (edSquat5.getEditableText()==s) {
                    if (s.length() == 0 || s.toString().charAt(0) == '.') {
                        squat5 = 0.00;
                    } else {
                        squat5 = Double.parseDouble(s.toString());
                    }
                }else{
                    if (edBench5.getEditableText()==s) {
                        if (s.length() == 0 || s.toString().charAt(0) == '.') {
                            bench5 = 0.00;
                        } else {
                            bench5 = Double.parseDouble(s.toString());
                        }
                    } else {
                        if (edDeadlift5.getEditableText()==s) {
                            if (s.length() == 0 || s.toString().charAt(0) == '.') {
                                deadlift5 = 0.00;
                            } else {
                                deadlift5 = Double.parseDouble(s.toString());
                            }
                        }
                    }
                }

                totalFragment5 = squat5 + bench5 + deadlift5;
                txtPlusTotal5.setText(sTotal+ totalFragment5);
                transferTotalListener.transferTotal(totalFragment5);
                bw5=bwFromActivity;
                txtBodyWightFrag5.setText(" 5) "+sBodyweight+bw5);
                txtResult5.setText(strResult);
            }
            }
        };
}



