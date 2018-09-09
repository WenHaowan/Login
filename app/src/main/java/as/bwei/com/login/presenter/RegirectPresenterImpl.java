package as.bwei.com.login.presenter;

import java.sql.ResultSetMetaData;

import as.bwei.com.login.model.ModelRegirectImpl;

/**
 * Created by HP on 2018/9/6.
 */
public class RegirectPresenterImpl implements RegirectPresenter, ModelRegirectImpl.Result {

    private RegirectView regirectView;
    private ModelRegirectImpl modelRegirect;

    public RegirectPresenterImpl(RegirectView regirectView) {
        this.regirectView = regirectView;
        modelRegirect = new ModelRegirectImpl();
    }

    //    注册的接口实现类
    public void regirect(String ed_name, String ed_passwordstr) {

        modelRegirect.getData(ed_name,ed_passwordstr,RegirectPresenterImpl.this);

    }

    //          登录的接口
    public void login(String ed_name, String ed_passwordstr) {
        modelRegirect.getLoginData(ed_name, ed_passwordstr,RegirectPresenterImpl.this);

    }

    @Override
    public void Errer() {

    }

    @Override
    public void Seccesful(String resultData) {
        regirectView.showData(resultData);
    }

}
