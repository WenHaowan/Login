package as.bwei.com.login.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import as.bwei.com.login.R;
import as.bwei.com.login.presenter.RegirectPresenterImpl;
import as.bwei.com.login.presenter.RegirectView;

public class MainActivity extends AppCompatActivity implements RegirectView, View.OnClickListener {

    private Button btn_login;
    private Button btn_regirect;
    private EditText ed_login;
    private EditText ed_password;
    private RegirectPresenterImpl regirectPresenter;
    private String ed_name;
    private String ed_passwordstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        onClick();

    }

    private void onClick() {

//       事件
        btn_login.setOnClickListener(this);
        btn_regirect.setOnClickListener(this);

    }

    private void initView() {
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_regirect = (Button) findViewById(R.id.btn_regirect);
        ed_login = (EditText) findViewById(R.id.ed_login);
        ed_password = (EditText) findViewById(R.id.ed_password);

  // 出事化Presenter
        regirectPresenter = new RegirectPresenterImpl(MainActivity.this);
    }

    @Override
    public void onClick(View v) {

        ed_name = ed_login.getText().toString();
        ed_passwordstr = ed_password.getText().toString();
        switch (v.getId()){
            case R.id.btn_login:
                    Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                    startActivity(intent);
                    regirectPresenter.login(ed_name,ed_passwordstr);
                break;
            case R.id.btn_regirect:
                regirectPresenter.regirect(ed_name,ed_passwordstr);
                break;
        }
    }

    @Override
    public void showData(final String resultData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, resultData, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
