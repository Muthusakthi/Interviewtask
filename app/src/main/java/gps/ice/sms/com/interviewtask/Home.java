package gps.ice.sms.com.interviewtask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by mipstech i5 2 on 12-Feb-18.
 */


public class Home extends Activity
{
    Button api,map,firebase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        api = (Button)findViewById(R.id.button);
        map=(Button)findViewById(R.id.button1);
        firebase=(Button)findViewById(R.id.button2);
        api.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Home.this,dummyapicall.class);
                startActivity(a);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(Home.this,mapdesign.class);
                startActivity(b);
            }
        });
        firebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(Home.this,FCM.class);
                startActivity(c);
            }
        });
    }
}
