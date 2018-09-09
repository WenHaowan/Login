package as.bwei.com.login.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import as.bwei.com.login.R;
import as.bwei.com.login.adapter.XListViewAdapter;
import as.bwei.com.login.bean.DataUser;
import as.bwei.com.login.utils.NetUtils;
import as.bwei.com.login.widget.CustomView;
import xlistview.bawei.com.xlistviewlibrary.XListView;

public class ShowActivity extends AppCompatActivity {

    private EditText edt;
    private Button but;
    private CustomView afl_cotent;
    private LayoutInflater layoutInflater;
    private TextView tvAttrTag;
    private List<String> list2 = new ArrayList<>();

    private XListView x_list_view;
    private String api = "https://www.zhaoapi.cn/product/searchProducts?keywords=%E7%AC%94%E8%AE%B0%E6%9C%AC&page=";
    private int page=1;
    private NetUtils netUtils;
    private ArrayList<DataUser.DataBean> dataBeanList = new ArrayList<>();
    private XListViewAdapter xListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        x_list_view = (XListView) findViewById(R.id.x_list_view);
        //开启刷新
        x_list_view.setPullLoadEnable(true);
        x_list_view.setPullRefreshEnable(true);
        //设置监听回调
        x_list_view.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                //上拉刷新
                page=1;
                netUtils.getDataFromService(api+page);
            }

            @Override
            public void onLoadMore() {
                //下拉加载
                page+=1;
                netUtils.getDataFromService(api+page);
            }
        });
        //设置adapter展示数据
        xListViewAdapter = new XListViewAdapter(ShowActivity.this, dataBeanList);
        x_list_view.setAdapter(xListViewAdapter);
        //调用工具类
        netUtils = NetUtils.getInstance();
        netUtils.setNetCallback(new NetUtils.NetCallback() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                DataUser dataUser = gson.fromJson(result, DataUser.class);
                if (page==1){
                    //将其他页面数据清除
                    dataBeanList.clear();
                }
                //累积把加载的数据放到集合中
                dataBeanList.addAll(dataUser.getData());
                //刷新适配器
                xListViewAdapter.notifyDataSetChanged();
                //停止刷新和加载
                x_list_view.stopRefresh();
                x_list_view.stopLoadMore();
            }
        });
        netUtils.getDataFromService(api+page);
        //流失
        initView();
    }
    //流失
    private void initView() {
        edt = (EditText) findViewById(R.id.edt);
        but = (Button) findViewById(R.id.but);
        afl_cotent = (CustomView) findViewById(R.id.afl_cotent);
        layoutInflater = LayoutInflater.from(this);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edt.getText().toString();
                View item = layoutInflater.inflate(R.layout.sub_item, null);
                tvAttrTag = (TextView) item.findViewById(R.id.tv_attr_tag);
                list2.add(s);
                for (int i = 0; i < list2.size(); i++) {
                    tvAttrTag.setText(list2.get(i));
                }
                afl_cotent.addView(item);
            }
        });
    }
}
