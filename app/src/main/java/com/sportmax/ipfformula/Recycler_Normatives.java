package com.sportmax.ipfformula;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Recycler_Normatives extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_normatives);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setTitle(R.string.categories);
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeButtonEnabled(true);
        }


RecyclerView rv=findViewById(R.id.user_recycler_view);
rv.setLayoutManager(new LinearLayoutManager(this));
        final List<Integer> mL = new ArrayList<>();
        mL.add(R.drawable.poewr_class_men);
        mL.add(R.drawable.poewr_class_women);
        mL.add(R.drawable.bp_men_no_ekipa);
        mL.add(R.drawable.bp_women_no_ekipa);
        mL.add(R.drawable.p_men_ekipa);
        mL.add(R.drawable.p_women_ekipa);
        mL.add(R.drawable.bp_men_ekipa);
        mL.add(R.drawable.bp_women_ekipa);
        UserAdapter adapter=new UserAdapter(mL);
        rv.setAdapter(adapter);
    }
    public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
        final List<Integer> mL;

        UserAdapter(List<Integer> mL) {
            this.mL = mL;
        }

        @NonNull
        @Override
        public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Достаём необходимые компоненты для создания View объекта
            Context context=parent.getContext();
            LayoutInflater inflater=LayoutInflater.from(context);
            // Создаём View объект из XML макета
            View userItemView=inflater.inflate(R.layout.list_item_view,parent,false);
            // Создаём объект UserViewHolder

            return new UserViewHolder(userItemView);
        }

        @Override
        public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
            int tempInt=mL.get(position);
            holder.bind(tempInt);
        }

        @Override
        public int getItemCount() {
            return mL.size();
        }

        class UserViewHolder extends RecyclerView.ViewHolder {
            private ImageView imgNormative;
            UserViewHolder(@NonNull View itemView) {
                super(itemView);
                imgNormative=itemView.findViewById(R.id.imageView9);
            }
            void bind(int tempInt){
                imgNormative.setImageResource(tempInt);
            }
        }
    }
}
