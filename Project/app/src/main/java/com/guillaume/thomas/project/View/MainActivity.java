package com.guillaume.thomas.project.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.guillaume.thomas.project.R;

public class MainActivity extends AppCompatActivity
{
    private Button start_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start_btn = (Button)findViewById(R.id.start_btn);

        start_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent researchActivity = new Intent(MainActivity.this, ResearchActivity.class);
                startActivity(researchActivity);
            }
        });
    }
}
