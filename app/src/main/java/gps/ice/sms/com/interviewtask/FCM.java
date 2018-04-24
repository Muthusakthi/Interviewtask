package gps.ice.sms.com.interviewtask;

import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by mipstech i5 2 on 06-Mar-18.
 */

public class FCM extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        Toast.makeText(FCM.this,token,Toast.LENGTH_LONG).show();
    }
}
