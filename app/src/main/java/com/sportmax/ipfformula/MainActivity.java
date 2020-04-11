package com.sportmax.ipfformula;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.math.BigDecimal;

import static java.lang.Math.log;

public class MainActivity extends AppCompatActivity implements Fragment3Exercises.onTransferTotalListener{
    TextView txtResult, txtShowHideExercises, txtCategory, txtBodyWeightCategory,txtNameCategory,txtNameWeight,txtIn;
    EditText edBodyWeight, edTotal;
    double total = 0;
    double ipfPoints = 0.00, bw = 0;
    BigDecimal bigDecimal;

    private boolean isFragmentDisplayed = false;

    RadioGroup rgMaleFemale, rgClassicEquipped, rgLiftbp, rgSquatdl;
    RadioButton rbMale,rbFemale, rbPowerlifting, rbClassic,rbEquipped, rbBenchpress, rbSquat,rbDeadlift;
    ImageView imgShowExercise,imgFlagKZ;

    double[][] MPC ={{0, 0, 150, 140, 130, 120, 110, 95, 80},
            {0, 0, 180, 160, 150, 140, 130, 110, 95},
            {0, 0, 220, 200, 180, 160, 150, 130, 110},
            {0, 0, 260, 230, 210, 190, 170, 150, 130},
            {0, 0, 300, 260, 230, 210, 190, 170, 150},
            {420, 380, 340, 290, 260, 230, 210, 190, 170},
            {480, 440, 380, 320, 290, 260, 230, 210, 190},
            {540, 500, 420, 350, 320, 290, 260, 230, 210},
            {600, 550, 450, 380, 350, 320, 290, 260, 230},
            {650, 590, 490, 420, 380, 350, 320, 290, 260},
            {700, 630, 530, 460, 410, 380, 350, 310, 290},
            {740, 670, 560, 490, 440, 410, 375, 330, 310},
            {770, 700, 600, 520, 470, 440, 400, 350, 325},
            {800, 730, 630, 550, 500, 460, 420, 370, 340}};
    double[][] WPC ={{0,0,125,110,100,92.5,85,77.5,70},
            {0,0,150,125,110,100,92.5,85,77.5},
            {0,0,175,140,125,110,100,92.5,85},
            {250,225,200,155,140,125,110,100,92.5},
            {270,250,225,170,155,140,125,110,100},
            {300,275,250,185,170,155,140,125,110},
            {330,300,270,200,185,170,155,140,125},
            {360,325,290,230,200,185,170,155,140},
            {390,350,320,260,230,200,185,170,155},
            {420,380,350,290,260,230,200,185,170},
            {450,400,365,315,290,260,230,200,185}};

    double[][] MBC ={{0,0,50,45,40,35,30,25,20},
            {0,0,57.5,50,45,40,35,30,25},
            {0,0,65,57.5,50,45,40,35,30},
            {0,0,72.5,65,57.5,50,45,40,35},
            {100,90,80,72.5,65,57.5,50,45,40},
            {115,100,90,80,72.5,65,57.5,50,45},
            {130,115,100,90,80,72.5,65,57.5,50},
            {145,130,115,100,90,80,72.5,65,57.5},
            {160,145,130,115,100,90,80,72.5,65},
            {175,160,145,130,115,100,87.5,80,72.5},
            {190,175,160,140,125,110,95,87.5,80},
            {205,190,170,150,135,120,102.5,95,87.5},
            {220,200,180,160,145,130,110,102.5,95},
            {230,210,190,170,155,140,120,110,102.5}};
    double[][] WBC ={{0,0,40,35,30,27.5,25,22.5,20},
            {0,0,45,40,35,30,27.5,25,22.5},
            {0,0,50,45,40,35,30,27.5,25},
            {65,60,55,50,45,40,35,30,27.5},
            {72.5,65,60,55,50,45,40,35,30},
            {80,72.5,65,60,55,50,45,40,35},
            {87.5,80,72.5,65,60,55,50,45,40},
            {95,87.5,80,72.5,65,60,55,50,45},
            {105,95,87.5,80,72.5,65,60,55,50},
            {115,105,95,87.5,80,72.5,65,60,55},
            {125,115,105,95,87.5,80,72.5,65,60}};
    double[][] MPE ={{0,0,170,150,140,130,120,110,100},
            {0,0,210,180,160,150,130,120,110},
            {0,0,250,210,190,170,150,140,130},
            {0,0,300,250,230,200,180,160,150},
            {450,400,350,290,260,230,210,190,170},
            {520,470,400,320,290,260,230,210,190},
            {590,540,445,360,320,290,260,230,210},
            {660,600,495,400,350,320,290,260,230},
            {720,660,535,440,390,360,320,290,260},
            {780,710,570,480,430,390,350,310,290},
            {830,750,600,520,470,420,380,330,310},
            {870,790,640,560,500,460,400,350,330},
            {900,820,680,600,530,490,430,375,350},
            {930,840,720,620,550,510,460,400,375}};
    double[][] WPE ={{0,0,140,120,110,100,90,80,70},
            {0,0,165,135,120,110,100,90,80},
            {0,0,200,155,135,120,110,100,90},
            {310,265,230,175,150,135,120,110,100},
            {340,287.5,255,190,165,150,135,120,110},
            {370,315,280,210,180,165,150,135,120},
            {400,342.5,305,230,200,180,165,150,135},
            {440,375,330,255,220,200,180,165,150},
            {470,400,360,285,250,230,200,180,165},
            {500,435,395,330,285,260,220,200,180},
            {530,460,415,360,320,285,250,220,200}};
    double[][] MBE ={{0,0,55,50,45,40,35,30,25},
            {0,0,62.5,55,50,45,40,35,30},
            {0,0,70,62.5,55,50,45,40,35},
            {0,0,80,70,62.5,55,50,45,40},
            {115,100,90,80,70,62.5,55,50,45},
            {130,115,100,90,80,70,62.5,55,50},
            {152.5,135,115,105,90,80,70,62.5,55},
            {175,155,135,120,102.5,90,77.5,70,62.5},
            {195,175,155,135,115,100,85,77.5,70},
            {215,195,175,150,130,110,92.5,85,77.5},
            {235,210,190,160,140,120,100,92.5,85},
            {250,225,200,170,150,130,110,100,92.5},
            {265,240,215,180,160,140,120,110,100},
            {275,250,225,190,170,150,130,120,110}};
    double[][] WBE ={{0,0,45,40,35,30,27.5,25,22.5},
            {0,0,50,45,40,35,30,27.5,25},
            {0,0,55,50,45,40,35,30,27.5},
            {80,72.5,62.5,55,50,45,40,35,30},
            {90,80,70,62.5,55,50,45,40,35},
            {100,87.5,77.5,70,62.5,55,50,45,40},
            {110,95,85,77.5,70,62.5,55,50,45},
            {120,102.5,92.5,85,77.5,70,62.5,55,50},
            {130,110,100,92.5,85,77.5,70,62.5,55},
            {142.5,120,110,100,92.5,85,77.5,70,62.5},
            {155,130,120,110,100,92.5,85,77.5,70}};
    double[][] MWX;//временный массив для нахождения выполненного норматива
    int[] menWeight ={32,36,40,44,48,53,59,66,74,83,93,105,120,1000};//мужские весовые категории
    int[] womenWeight ={32,36,40,43,47,52,57,63,72,84,1000};//женские весовые категории
    int[] mwxWeight;//временный массив для выбора весовой категории в методе chooseSportsCategory


    private static final double C1_MCL = 310.6700, C2_MCL = 857.7850, C3_MCL = 53.2160, C4_MCL = 147.0835;
    private static final double C1_MCB = 86.4745, C2_MCB = 259.1550, C3_MCB = 17.5785, C4_MCB = 53.1220;
    private static final double C1_MEL = 387.2650, C2_MEL = 1121.2800, C3_MEL = 80.6324, C4_MEL = 222.4896;
    private static final double C1_MEB = 133.9400, C2_MEB = 441.4650, C3_MEB = 35.3938, C4_MEB = 113.0057;
    private static final double C1_WCL = 125.1435, C2_WCL = 228.0300, C3_WCL = 34.5246, C4_WCL = 86.8301;
    private static final double C1_WCB = 25.0485, C2_WCB = 43.8480, C3_WCB = 6.7172, C4_WCB = 13.9520;
    private static final double C1_WEL = 176.5800, C2_WEL = 373.3150, C3_WEL = 48.4534, C4_WEL = 110.0103;
    private static final double C1_WEB = 49.1060, C2_WEB = 124.2090, C3_WEB = 23.1990, C4_WEB = 67.4926;

    private static final double C1_MCS = 123.1000, C2_MCS = 363.0850, C3_MCS = 25.1667, C4_MCS = 75.4311;
    private static final double C1_MES = 150.4850, C2_MES = 446.4450, C3_MES = 36.5155, C4_MES = 103.7061;
    private static final double C1_WCS = 50.4790, C2_WCS = 105.6320, C3_WCS = 19.1846, C4_WCS = 56.2215;
    private static final double C1_WES = 74.6855, C2_WES = 171.5850, C3_WES = 21.9475, C4_WES = 52.2948;

    private static final double C1_MCD = 103.5355, C2_MCD = 224.7650, C3_MCD = 15.3714, C4_MCD = 31.5022;
    private static final double C1_MED = 110.1350, C2_MED = 263.6600, C3_MED = 14.9960, C4_MED = 23.0110;
    private static final double C1_WCD = 47.1360, C2_WCD = 67.3490, C3_WCD = 9.1555, C4_WCD = 13.6700;
    private static final double C1_WED = 51.0020, C2_WED = 69.8265, C3_WED = 8.5802, C4_WED = 5.7258;

    double[] MCL ={C1_MCL,C2_MCL,C3_MCL,C4_MCL};
    double[] MCB ={C1_MCB, C2_MCB, C3_MCB, C4_MCB};
    double[] MEL ={C1_MEL, C2_MEL, C3_MEL, C4_MEL};
    double[] MEB ={C1_MEB, C2_MEB, C3_MEB, C4_MEB};
    double[] WCL ={C1_WCL, C2_WCL, C3_WCL, C4_WCL};
    double[] WCB ={C1_WCB, C2_WCB, C3_WCB, C4_WCB};
    double[] WEL ={C1_WEL, C2_WEL, C3_WEL, C4_WEL};
    double[] WEB ={C1_WEB, C2_WEB, C3_WEB, C4_WEB};
    double[] MCS ={C1_MCS, C2_MCS, C3_MCS, C4_MCS};
    double[] MES ={C1_MES, C2_MES, C3_MES, C4_MES};
    double[] WCS ={C1_WCS, C2_WCS, C3_WCS, C4_WCS};
    double[] WES ={C1_WES, C2_WES, C3_WES, C4_WES};
    double[] MCD ={C1_MCD, C2_MCD, C3_MCD, C4_MCD};
    double[] MED ={C1_MED, C2_MED, C3_MED, C4_MED};
    double[] WCD ={C1_WCD, C2_WCD, C3_WCD, C4_WCD};
    double[] WED ={C1_WED, C2_WED, C3_WED, C4_WED};

    double[] CX;

    Fragment3Exercises frag3Exercises;

    String[] arrayStringCategory;//массив названий выполненых нормативов

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            Intent intent=new Intent(this,AboutActivity.class);
            startActivity(intent);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if(Locale.getDefault().getLanguage()=="ru"){
//            localeRu=true;
//        }else{localeRu=false;}

        arrayStringCategory=new String[]{getString(R.string.msmk),getString(R.string.ms),getString(R.string.kms),getString(R.string.first_category),
                getString(R.string.second_category),getString(R.string.third_category),getString(R.string.first_subjunior_category),
                getString(R.string.second_subjunior_category),getString(R.string.third_subjunior_category)};

        txtCategory=findViewById(R.id.txt_category);
        txtBodyWeightCategory=findViewById(R.id.txt_bodyweight_category);
        txtNameCategory=findViewById(R.id.txt_name_category);
        txtNameWeight=findViewById(R.id.txt_name_weight);
        txtIn=findViewById(R.id.txt_in);
        imgFlagKZ=findViewById(R.id.imgFlagKZ);

        txtResult = findViewById(R.id.txt_result);
        edBodyWeight = findViewById(R.id.ed_body_weight);
        edTotal = findViewById(R.id.ed_total);

        txtShowHideExercises=findViewById(R.id.txt_show_hide_exercises);
        imgShowExercise = findViewById(R.id.img_Show_Exercise);
        imgShowExercise.setOnClickListener(onImgShowExerciseClickListener);


        edBodyWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //если строка ввода пустая или первый символ = '.', присваиваем переменной bw (вес спортсмена) значение 0,00
                if (s.length() == 0 || s.toString().charAt(0) == '.') {
                    bw = 0.00;
                } else {
                    bw = Double.parseDouble(s.toString());
                }
                if (total > 0 & bw > 40) {
                    ipfPoints = getIpfPoints(CX[0], CX[1], CX[2], CX[3], bw, total);
                    if (ipfPoints > 0) {
                        txtResult.setText(Double.toString(ipfPoints));
                        if(rbSquat.isChecked()||rbDeadlift.isChecked()){
                            txtCategory.setText("");
                            txtBodyWeightCategory.setText("");
                        }else{
                            chooseSportsCategory(bw,total);}
                    } else {
                        txtResult.setText("0.00");
                    }
                } else {
                    txtResult.setText("0.00");
                }
            }
        });

        edTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("") || s.toString().charAt(0) == '.') {
                    total = 0.00;
                } else {
                    total = Double.parseDouble(edTotal.getText().toString());
                }
                if (bw > 40 & s.length() != 0) {
                    ipfPoints = getIpfPoints(CX[0], CX[1], CX[2], CX[3], bw, total);
                    if (ipfPoints > 0) {
                        txtResult.setText(ipfPoints+"");
                        if(rbSquat.isChecked()||rbDeadlift.isChecked()){
                            txtCategory.setText("");
                            txtBodyWeightCategory.setText("");
                        }else{
                        chooseSportsCategory(bw,total);}
                    } else {
                        txtResult.setText("0.00");
                    }
                }
            }
        });

        rgMaleFemale=findViewById(R.id.rg_male_female);
        rgClassicEquipped=findViewById(R.id.rg_classic_equipped);
        rgLiftbp = findViewById(R.id.rg_lift_bp);
        rgSquatdl = findViewById(R.id.rg_squat_dl);

        rbMale=findViewById(R.id.rb_Male);
        rbFemale=findViewById(R.id.rb_Female);
        rbClassic=findViewById(R.id.rb_Classic);
        rbEquipped=findViewById(R.id.rb_Equipped);
        rbPowerlifting=findViewById(R.id.rb_Powerlifting);
        rbBenchpress=findViewById(R.id.rb_Benchpress);
        rbSquat=findViewById(R.id.rb_Squat);
        rbDeadlift=findViewById(R.id.rb_Deadlift);

        rgMaleFemale.setOnCheckedChangeListener(onRadioGroupCheckedChangeListener);
        rgClassicEquipped.setOnCheckedChangeListener(onRadioGroupCheckedChangeListener);
        rgSquatdl.setOnCheckedChangeListener(onRadioGroupCheckedChangeListener);
        rgLiftbp.setOnCheckedChangeListener(onRadioGroupCheckedChangeListener);

        ChooseConstants();

    }

    ImageView.OnClickListener onImgShowExerciseClickListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!isFragmentDisplayed) {
                txtShowHideExercises.setText(R.string.hide_exercises);
                imgShowExercise.setImageResource(R.drawable.arrow_cloud_up_upload_icon_123722_48);
                showFragment();
                frag3Exercises.addNewPersonForWatch=1;
            }else{
                txtShowHideExercises.setText(R.string.show_exercises);
                imgShowExercise.setImageResource(R.drawable.arrow_down_download_save_icon_123720_48);
                closeFragment();
            }
        }
    };

    RadioGroup.OnCheckedChangeListener onRadioGroupCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId!=-1){
                RadioButton rb = findViewById(group.getCheckedRadioButtonId());
                ChooseConstants();
                if(total > 0 & bw > 40){ ipfPoints = getIpfPoints(CX[0], CX[1], CX[2], CX[3], bw, total); txtResult.setText(ipfPoints+"");}
                if(rb.isChecked()) {
                    if(checkedId == R.id.rb_Powerlifting){
                        txtShowHideExercises.setText(R.string.show_exercises);
                        txtShowHideExercises.setVisibility(View.VISIBLE);
                        imgShowExercise.setImageResource(R.drawable.arrow_down_download_save_icon_123720_48);
                        imgShowExercise.setVisibility(View.VISIBLE);}else{
                        if(checkedId==R.id.rb_Squat||checkedId==R.id.rb_Benchpress||checkedId==R.id.rb_Deadlift){
                            if(isFragmentDisplayed){closeFragment();}
                            imgShowExercise.setVisibility(View.GONE);
                            txtShowHideExercises.setVisibility(View.GONE);}
                    }//здесь какбы делаем группу из четырех переключателей, чтоб горе только один переключатель из двух разных групп
                    if (checkedId == R.id.rb_Powerlifting || checkedId == R.id.rb_Benchpress) {
                        rgSquatdl.clearCheck();
                    } else if (checkedId == R.id.rb_Squat || checkedId == R.id.rb_Deadlift) {
                        rgLiftbp.clearCheck();
                    }
                }
            }
        }
    };

    private void ChooseConstants(){
            if(rbMale.isChecked()){
                mwxWeight=menWeight;
                if(rbClassic.isChecked()){
                    if(rbPowerlifting.isChecked()){CX=MCL;MWX=MPC;}
                    if(rbBenchpress.isChecked()){CX=MCB;MWX=MBC;}
                    if(rbSquat.isChecked()){CX=MCS;}
                    if(rbDeadlift.isChecked()){CX=MCD;}
                    } else{
                    if(rbEquipped.isChecked()){
                        if(rbPowerlifting.isChecked()){CX=MEL;MWX=MPE;}
                        if(rbBenchpress.isChecked()){CX=MEB;MWX=MBE;}
                        if(rbSquat.isChecked()){CX=MES;}
                        if(rbDeadlift.isChecked()){CX=MED;}
                    }
                }
            }else{
                if(rbFemale.isChecked()){
                    mwxWeight=womenWeight;
                    if(rbClassic.isChecked()){
                        if(rbPowerlifting.isChecked()){CX=WCL;MWX=WPC;}
                        if(rbBenchpress.isChecked()){CX=WCB;MWX=WBC;}
                        if(rbSquat.isChecked()){CX=WCS;}
                        if(rbDeadlift.isChecked()){CX=WCD;}
                    } else{
                        if(rbEquipped.isChecked()){
                            if(rbPowerlifting.isChecked()){CX=WEL;MWX=WPE;}
                            if(rbBenchpress.isChecked()){CX=WEB;MWX=WBE;}
                            if(rbSquat.isChecked()){CX=WES;}
                            if(rbDeadlift.isChecked()){CX=WED;}
                        }
                    }
                }
            }
        }

        private double getIpfPoints(double C1, double C2, double C3, double C4, double bw, double total) {
            ipfPoints = 500 + 100 * (total - (C1 * log(bw) - C2)) / (C3 * log(bw) - C4);
            bigDecimal = new BigDecimal(ipfPoints);
            return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
        }

        private void showFragment(){
            frag3Exercises=Fragment3Exercises.newInstance("","");
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame_for_fragments,frag3Exercises).commit();
            isFragmentDisplayed = true;
            edTotal.setEnabled(false);
        }
        private void closeFragment(){
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.remove(frag3Exercises).commit();
            isFragmentDisplayed=false;
            edTotal.setEnabled(true);
        }

    @Override
    public void transferTotal(double d) {
        total = d;
        edTotal.setText(String.valueOf(total));
        frag3Exercises.bwFromActivity=bw;
        if (bw > 40) {
            ipfPoints = getIpfPoints(CX[0], CX[1], CX[2], CX[3], bw, total);
        }
        if (ipfPoints > 0) {
            txtResult.setText(ipfPoints+"");
            frag3Exercises.strResult=ipfPoints+"";
        } else {
            txtResult.setText("0.00");
        }
    }

    private void chooseSportsCategory(double bw, double total){
        int category = 0, ii = 0,jj = 0;
        for (int i=0;i<mwxWeight.length;i++){
            if(bw<=mwxWeight[i]){
                category=mwxWeight[i];
                ii=i;i=13;
            }
        }
        for(int j=0;j<MWX[0].length;j++){
            if(total>=MWX[ii][j]){
                jj=j;j=10;
            }
        }
        if(category==1000){
            txtBodyWeightCategory.setText("+"+mwxWeight[mwxWeight.length-2]);
        }else{
            txtBodyWeightCategory.setText("-"+category);
        }
        if(total<MWX[ii][MWX[ii].length-1]){
            txtCategory.setText("0");
        }else {
            txtCategory.setText(arrayStringCategory[jj]);
        }
    }
}
