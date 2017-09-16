package cn.cmkj.auction.ui;

import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.cmkj.auction.R;
import cn.cmkj.auction.app.BaseActivity;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    public static final int REQ_LOGIN = 1000;

    @ViewInject(R.id.title_back)
    private ImageButton titleBack;
    @ViewInject(R.id.title_text)
    private TextView titleText;
    @ViewInject(R.id.title_menu1)
    private Button menuButton;
    @ViewInject(R.id.edit_username)
    private EditText usernameText;
    @ViewInject(R.id.edit_password)
    private EditText passwordText;
    @ViewInject(R.id.checkbox_password)
    private CheckBox mCbDisplayPassword;
    @ViewInject(R.id.text_forget_password)
    private TextView forgetText;
    @ViewInject(R.id.btn_login)
    private Button loginBtn;

    private Handler hander = new Handler(){
        public void handleMessage(android.os.Message msg) {
            usernameText.setFocusable(true);
            usernameText.setFocusableInTouchMode(true);
            usernameText.requestFocus();
            showInputMethod(LoginActivity.this, getCurrentFocus());
            usernameText.setSelection(usernameText.getText().toString().length());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        titleText.setText("登录");
        menuButton.setText("注册");
        hander.sendEmptyMessageDelayed(1, 200);
        mCbDisplayPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    passwordText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordText.setSelection(passwordText.getText().toString().length());
                }else {
                    passwordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordText.setSelection(passwordText.getText().toString().length());
                }
            }
        });
        titleBack.setOnClickListener(this);
        menuButton.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        forgetText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_menu1:
                break;
            case R.id.btn_login:
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.text_forget_password:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideKeyboard(getCurrentFocus());
    }
}
