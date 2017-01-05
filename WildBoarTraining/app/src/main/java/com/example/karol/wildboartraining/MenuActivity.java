package com.example.karol.wildboartraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
public class MenuActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activiti);
        //Tworzę powiązania między przyciskami a działaniem aplikacji
        View createPlanButton = findViewById(R.id.createPlan_button);
        createPlanButton.setOnClickListener(this);

        View showPlanButton = findViewById(R.id.showPlan_lable);
        showPlanButton.setOnClickListener(this);

        View optionButton = findViewById(R.id.option_button);
        optionButton.setOnClickListener(this);

    }
    public void ClickExit(View v){
        finish();
    }
    @Override
    public void onClick(View v){
        Intent intent = null;
        switch(v.getId()){
            case R.id.createPlan_button:
                intent = new Intent(this,CreatePlanActivity.class);
                startActivity(intent);
                break;
            case R.id.showPlan_lable:
                intent = new Intent(this,ShowPlanActivity.class);
                startActivity(intent);
                break;
            case R.id.option_button:
                intent = new Intent(this,OptionActivity.class);
                startActivity(intent);
                break;
            case R.id.exit_button:
                finish();
                break;

        }
    }
}
