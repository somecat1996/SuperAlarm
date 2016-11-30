package superalarm.firsttry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import basic_class.UserManager;

;

public class WelcomeActivity extends Activity {
    public static WelcomeActivity instance = null;
    private EditText aUserName, aPassWord;
    private Button aLogin, aRegister;
    private UserManager userManager = new UserManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        instance = this;

        aUserName = (EditText) findViewById(R.id.EtxtUserName);
        aPassWord = (EditText) findViewById(R.id.EtxtPassWord);
        aLogin = (Button) findViewById(R.id.BtnLogin);
        aRegister = (Button) findViewById(R.id.BtnRegister);

        aLogin.setOnClickListener(BtnLoginOnClick);
        aRegister.setOnClickListener(BtnRegisterOnClick);
        }

        //点击“登录”按钮，接收讯息：用户名+密码
        private View.OnClickListener BtnLoginOnClick=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thisUserName = aUserName.getText().toString();
                String thisPassWord = aPassWord.getText().toString();

                logIn(thisUserName, thisPassWord);
//
//                //下接密码判断机制……
//                if (!thisUserName.matches("123123")) {
//                    Toast.makeText(WelcomeActivity.this,R.string.errname,Toast.LENGTH_SHORT).show();
//                }
//                else if (!thisPassWord.matches("abcabcabc")) {
//                    Toast.makeText(WelcomeActivity.this,R.string.errpassword,Toast.LENGTH_SHORT).show();
//                }
//                else{//跳转至已登陆
//                Intent it = new Intent();
//                it.setClass(WelcomeActivity.this, PresonalInformationHaveLogin.class);
//                startActivity(it);
//                finish();}
            }
        };
            //点击“注册”按钮，跳转至注册信息填写界面
            private View.OnClickListener BtnRegisterOnClick = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent();
                    it.setClass(WelcomeActivity.this, LoginActivity.class);
                    startActivity(it);
                    //finish();
                }
            };

    public void logIn(String username, String password) {
        JsonTask2 jsonTask = new JsonTask2();
        jsonTask.execute("http://www.wangjksjtu.com.cn:2117/items/","GET", "User", username,
                password);
    }

    class JsonTask2 extends JsonTask{
        @Override
        protected void onPostExecute(String result) {
            if (Objects.equals(result, "success")) {
                Intent it = new Intent();
                it.setClass(WelcomeActivity.this, PresonalInformationHaveLogin.class);
                startActivity(it);
            }
            else if (Objects.equals(result, "failure")) {
                Toast.makeText(WelcomeActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(WelcomeActivity.this, "网络无连接", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
