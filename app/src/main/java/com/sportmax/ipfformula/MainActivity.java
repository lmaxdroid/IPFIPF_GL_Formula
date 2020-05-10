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
import static java.lang.Math.pow;

public class MainActivity extends AppCompatActivity implements Fragment3Exercises.onTransferTotalListener{
    TextView txtResult, txtShowHideExercises, txtCategory, txtBodyWeightCategory,txtNameCategory,txtNameWeight,txtIn;
    EditText edBodyWeight, edTotal;
    double total = 0;
    double bw = 0, ipfGL=0.0;
    BigDecimal bigDecimal;

    private boolean isFragmentDisplayed = false;

    RadioGroup rgMaleFemale, rgClassicEquipped, rgLiftbp;
    RadioButton rbMale,rbFemale, rbPowerlifting, rbClassic,rbEquipped, rbBenchpress;
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

    private static final double A_MEP = 1236.25115,  B_MEP = 1449.21864,  C_MEP = 0.01644;
    private static final double A_MCP = 1199.72839,  B_MCP = 1025.18162,  C_MCP = 0.00921;
    private static final double A_MEB = 381.22073,   B_MEB = 733.79378,   C_MEB = 0.02398;
    private static final double A_MCB = 320.98041,   B_MCB = 281.40258,   C_MCB = 0.01008;
    private static final double A_WEP = 758.63878,   B_WEP = 949.31382,   C_WEP = 0.02435;
    private static final double A_WCP = 610.32796 ,  B_WCP = 1045.59282,  C_WCP = 0.03048;
    private static final double A_WEB = 221.82209,   B_WEB = 357.00377,   C_WEB = 0.02937;
    private static final double A_WCB = 142.40398,   B_WCB = 442.52671,   C_WCB = 0.04724;

    double[] MEP_GL ={A_MEP,B_MEP,C_MEP};
    double[] MCP_GL ={A_MCP,B_MCP,C_MCP};
    double[] MEB_GL ={A_MEB,B_MEB,C_MEB};
    double[] MCB_GL ={A_MCB,B_MCB,C_MCB};
    double[] WEP_GL ={A_WEP,B_WEP,C_WEP};
    double[] WCP_GL ={A_WCP,B_WCP,C_WCP};
    double[] WEB_GL ={A_WEB,B_WEB,C_WEB};
    double[] WCB_GL ={A_WCB,B_WCB,C_WCB};


    double[] CX;

    Fragment3Exercises frag3Exercises;

    String[] arrayStringCategory;//массив названий выполненых нормативов

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.action_search:
               intent=new Intent(this,PdfActivity.class);
               startActivity(intent);
                break;
            case R.id.action_show_normatives:
                intent=new Intent(this,Recycler_Normatives.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        txtShowHideExercises.setOnClickListener(onImgShowExerciseClickListener);
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
                    ipfGL=getIpfGLCoefficient(CX[0],CX[1],CX[2],bw,total);
                    if (ipfGL > 0) {
                        txtResult.setText(Double.toString(ipfGL));
                            chooseSportsCategory(bw,total);
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
                    ipfGL=getIpfGLCoefficient(CX[0],CX[1],CX[2],bw,total);
                    if (ipfGL > 0) {
                        txtResult.setText(ipfGL+"");
                        chooseSportsCategory(bw,total);
                    } else {
                        txtResult.setText("0.00");
                    }
                }
            }
        });

        rgMaleFemale=findViewById(R.id.rg_male_female);
        rgClassicEquipped=findViewById(R.id.rg_classic_equipped);
        rgLiftbp = findViewById(R.id.rg_lift_bp);

        rbMale=findViewById(R.id.rb_Male);
        rbFemale=findViewById(R.id.rb_Female);
        rbClassic=findViewById(R.id.rb_Classic);
        rbEquipped=findViewById(R.id.rb_Equipped);
        rbPowerlifting=findViewById(R.id.rb_Powerlifting);
        rbBenchpress=findViewById(R.id.rb_Benchpress);

        rgMaleFemale.setOnCheckedChangeListener(onRadioGroupCheckedChangeListener);
        rgClassicEquipped.setOnCheckedChangeListener(onRadioGroupCheckedChangeListener);
        rgLiftbp.setOnCheckedChangeListener(onRadioGroupCheckedChangeListener);

        ChooseConstantsGL();
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
                ChooseConstantsGL();
                if(total > 0 & bw > 40){
                    ipfGL=getIpfGLCoefficient(CX[0],CX[1],CX[2],bw,total);
                    txtResult.setText(ipfGL+"");
                    chooseSportsCategory(bw,total);
                }
                if(rb.isChecked()) {
                    if(checkedId == R.id.rb_Powerlifting){
                        txtShowHideExercises.setText(R.string.show_exercises);
                        txtShowHideExercises.setVisibility(View.VISIBLE);
                        imgShowExercise.setImageResource(R.drawable.arrow_down_download_save_icon_123720_48);
                        imgShowExercise.setVisibility(View.VISIBLE);}else{
                        if(checkedId==R.id.rb_Benchpress){
                            if(isFragmentDisplayed){closeFragment();}
                            imgShowExercise.setVisibility(View.GONE);
                            txtShowHideExercises.setVisibility(View.GONE);}
                    }
                }
            }
        }
    };
//Выбираем коэффициенты в зависимости от того какой radioButton выбран
    private void ChooseConstantsGL(){
        if(rbMale.isChecked()){
            mwxWeight=menWeight;
            if(rbClassic.isChecked()){
                if(rbPowerlifting.isChecked()){CX=MCP_GL;MWX=MPC;}
                if(rbBenchpress.isChecked()){CX=MCB_GL;MWX=MBC;}
            } else{
                if(rbEquipped.isChecked()){
                    if(rbPowerlifting.isChecked()){CX=MEP_GL;MWX=MPE;}
                    if(rbBenchpress.isChecked()){CX=MEB_GL;MWX=MBE;}
                }
            }
        }else{
            if(rbFemale.isChecked()){
                mwxWeight=womenWeight;
                if(rbClassic.isChecked()){
                    if(rbPowerlifting.isChecked()){CX=WCP_GL;MWX=WPC;}
                    if(rbBenchpress.isChecked()){CX=WCB_GL;MWX=WBC;}
                } else{
                    if(rbEquipped.isChecked()){
                        if(rbPowerlifting.isChecked()){CX=WEP_GL;MWX=WPE;}
                        if(rbBenchpress.isChecked()){CX=WEB_GL;MWX=WBE;}
                    }
                }
            }
        }
    }

        private double getIpfGLCoefficient(double A, double B, double C, double bw,double total){
        ipfGL=100/(A-(B*pow(Math.E,(-C*bw))));
        bigDecimal=new BigDecimal(ipfGL*total);
            return bigDecimal.setScale(3, BigDecimal.ROUND_HALF_EVEN).doubleValue();
        }

        private void showFragment(){
            frag3Exercises=Fragment3Exercises.newInstance("","");
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame_for_fragments,frag3Exercises).commit();
            isFragmentDisplayed = true;
            edTotal.setEnabled(false);
        }
        public void closeFragment(){
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.remove(frag3Exercises).commit();
            isFragmentDisplayed=false;
            txtShowHideExercises.setText(R.string.show_exercises);
            imgShowExercise.setImageResource(R.drawable.arrow_down_download_save_icon_123720_48);
            edTotal.setEnabled(true);
            txtShowHideExercises.setText(R.string.show_exercises);
            imgShowExercise.setImageResource(R.drawable.arrow_down_download_save_icon_123720_48);
        }

    @Override
    public void transferTotal(double d) {
        total = d;
        edTotal.setText(String.valueOf(total));
        frag3Exercises.bwFromActivity=bw;
        if (bw > 40) {
            ipfGL = getIpfGLCoefficient(CX[0], CX[1], CX[2], bw, total);
        }
        if (ipfGL > 0) {
            txtResult.setText(ipfGL+"");
            frag3Exercises.strResult=ipfGL+"";
        } else {
            txtResult.setText("0.00");
        }
    }

    // Смотрим какой норматив получился и выводим его снизу
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
//нажимаешь на место где показывается выполненный норматив и открывается меню нормативы
    public void onClickCategory(View view) {
        Intent intent=new Intent(this,Recycler_Normatives.class);
        startActivity(intent);
    }
}
