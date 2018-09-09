package as.bwei.com.login.model;

/**
 * Created by HP on 2018/9/6.
 */

public interface ModelRegirec {
    void getData(String name,String password,ModelRegirectImpl.Result result);
    void getLoginData(String ed_name, String ed_passwordstr, ModelRegirectImpl.Result result);
}
