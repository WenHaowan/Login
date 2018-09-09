package as.bwei.com.login.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HP on 2018/9/6.
 */

public class ModelRegirectImpl implements ModelRegirec{
    //网络请求
    @Override
    public void getData(String name, String password, final ModelRegirectImpl.Result result) {
        FormBody body = new FormBody.Builder()
                .add("mobile",name)
                .add("password",password)
                .build();
        Request build = new Request
                .Builder()
                .url("https://www.zhaoapi.cn/user/reg")
                .post(body)
                .build();
        new OkHttpClient().newCall(build)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //接口回调
                        result.Errer();
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        result.Seccesful(response.body().string());
                    }
                });
    }

    @Override
    public void getLoginData(String ed_name, String ed_passwordstr, final ModelRegirectImpl.Result result) {
        FormBody body = new FormBody.Builder()
                .add("mobile",ed_name)
                .add("password",ed_passwordstr)
                .build();

        Request build = new Request
                .Builder()
                .url("https://www.zhaoapi.cn/user/login")
                .post(body)
                .build();

        new OkHttpClient().newCall(build)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //接口回调
                        result.Errer();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        result.Seccesful(response.body().string());
                    }
                });
    }

    public interface Result {
        void Errer();
        void Seccesful(String string);
    }
}
