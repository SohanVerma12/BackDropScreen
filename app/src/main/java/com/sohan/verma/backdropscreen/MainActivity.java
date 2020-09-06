package com.sohan.verma.backdropscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RelativeLayout frontLayout;
    private LinearLayout backLayout;
    private TextView textView;
    private RadioButton rbYes, rbNo;
    private RelativeLayout.LayoutParams lp;
    MenuItem itemCheck;
    boolean showBackLayout = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        frontLayout = findViewById(R.id.front_layout);
        backLayout = findViewById(R.id.back_layout);
        textView = (TextView) findViewById(R.id.text);
        rbYes = findViewById(R.id.rb_ya);
        rbNo = findViewById(R.id.rbno);

        initListener ();

        setSupportActionBar(toolbar);
    }

    private void initListener () {
        rbYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("You Choose Yes");
            }
        });

        rbNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("You Choose No");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_item, menu);
        itemCheck = menu.findItem(R.id.action_pilih);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_pilih) {
            dropLayout ();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dropLayout () {
        showBackLayout = !showBackLayout;
        itemCheck.setIcon(ContextCompat.getDrawable(
                MainActivity.this, showBackLayout ? R.drawable.close : R.drawable.pilih
        ));

        lp = (RelativeLayout.LayoutParams) frontLayout.getLayoutParams();

        if (showBackLayout) {
            ValueAnimator var = ValueAnimator.ofInt(backLayout.getHeight());
            var.setDuration(100);
            var.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    lp.setMargins(0, (Integer) valueAnimator.getAnimatedValue(), 0, 0);
                    frontLayout.setLayoutParams(lp);
                }
            });

            var.start();
        }
        else {
            lp.setMargins(0,0,0,0);
            frontLayout.setLayoutParams(lp);
            TranslateAnimation anim = new TranslateAnimation(
                    0,0 , backLayout.getHeight(), 0
            );

            anim.setStartOffset(0);
            anim.setDuration(200);
            frontLayout.setAnimation(anim);
        }
    }

}










