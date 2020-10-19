package com.example.rubbish;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.rubbish.beas.ShangchengParser;
import com.example.rubbish.beas.ShangpinEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoueucheActivity  extends Activity implements View.OnClickListener
{
    private static final int INITIALIZE = 0;
    private ListView mListView;// 列表
    private ListAdapter mListAdapter;// adapter
    private List<ShangpinEntity> mListData= new ArrayList<ShangpinEntity>();// 数据
    private List<ShangpinEntity> ListData;
    private boolean isBatchModel;// 是否可删除模式
    private RelativeLayout mBottonLayout;
    private CheckBox mCheckAll; // 全选 全不选
    private TextView mEdit; // 切换到删除模式
    private TextView mPriceAll; // 商品总价
    private TextView mSelectNum; // 选中数量
    private TextView mFavorite; // 移到收藏夹
    private TextView mDelete; // 删除 结算
    private float totalPrice = 0; // 商品总价
    /**
     * 批量模式下，用来记录当前选中状态
     */
    private SparseArray<Boolean> mSelectState = new SparseArray<Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gouwuche);
        //getData();
        initView();
        initListener();
        loadData();
    }

    private void doDelete(List<Integer> ids) {
        for (int i = 0; i < mListData.size(); i++) {
            String dataId = mListData.get(i).getId();
            for (int j = 0; j < ids.size(); j++) {
                String deleteId = ids.get(j).toString();
                if (dataId.equals(deleteId)) {
                    mListData.remove(i);
                    i--;
                    ids.remove(j);
                    j--;
                }
            }
        }

        refreshListView();
        mSelectState.clear();
        totalPrice = 0;
        mSelectNum.setText("已选" + 0 + "件商品");
        mPriceAll.setText("￥" + 0.00 + "");
        mCheckAll.setChecked(false);

    }

    private final List<Integer> getSelectedIds() {
        ArrayList<Integer> selectedIds = new ArrayList<Integer>();
        for (int index = 0; index < mSelectState.size(); index++) {
            if (mSelectState.valueAt(index)) {
                selectedIds.add(mSelectState.keyAt(index));
            }
        }
        return selectedIds;
    }

    private void initView() {

        mBottonLayout = (RelativeLayout) findViewById(R.id.cart_rl_allprie_total);
        mCheckAll = (CheckBox) findViewById(R.id.check_box);
        mEdit = (TextView) findViewById(R.id.subtitle);
        mPriceAll = (TextView) findViewById(R.id.tv_cart_total);
        mSelectNum = (TextView) findViewById(R.id.tv_cart_select_num);
        mFavorite = (TextView) findViewById(R.id.tv_cart_move_favorite);
        mDelete = (TextView) findViewById(R.id.tv_cart_buy_or_del);
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setSelector(R.drawable.list_selector);

    }

    private void initListener() {
        mEdit.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mCheckAll.setOnClickListener(this);

    }

    private void loadData() {
        new LoadDataTask().execute(new Params(INITIALIZE));
    }

    private void refreshListView() {
        if (mListAdapter == null) {
            mListAdapter = new ListAdapter();
            mListView.setAdapter(mListAdapter);
            mListView.setOnItemClickListener(mListAdapter);

        } else {
            mListAdapter.notifyDataSetChanged();

        }
    }

    private List<ShangpinEntity> getData1() {
        int maxId = 0;
        if (mListData != null && mListData.size() > 0)
            maxId = Integer.parseInt(mListData.get(mListData.size() - 1).getId());
        List<ShangpinEntity> result = new ArrayList<ShangpinEntity>();
        ShangpinEntity data = null;
        for (int i = 1; i < mListData.size(); i++) {
            data = new ShangpinEntity();
            data.setId(maxId + i + 1 + "");// 从最大Id的下一个开始
            data.setName(mListData.get(i).getName());
            data.setCarNum(1);
            data.setPrice(mListData.get(i).getPrice());
            result.add(data);
        }
        return result;
    }

    class Params {
        int op;

        public Params(int op) {
            this.op = op;
        }

    }

    class Result {
        int op;
        List<ShangpinEntity> list;
    }

    private class LoadDataTask extends AsyncTask<Params, Void, Result> {
        @Override
        protected Result doInBackground(Params... params) {
            Params p = params[0];
            Result result = new Result();
            result.op = p.op;
            getData();
            //mListData.addAll(ListData);
            System.out.println(mListData);
            result.list =mListData ;;
            return result;
        }

        @Override
        protected void onPostExecute(Result result) {
            super.onPostExecute(result);
            if (result.op == INITIALIZE) {
                mListData = result.list;
            } else {
                mListData.addAll(result.list);
                Toast.makeText(getApplicationContext(), "添加成功！", Toast.LENGTH_SHORT).show();
            }

            refreshListView();
        }

    }

    private class ListAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {



        @Override
        public int getCount() {
            System.out.println("fgthyjuhygfdcsx"+mListData.size());
            return mListData.size();

        }

        @Override
        public Object getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            System.out.println("======================" + mListData.size());
            ViewHolder holder = null;
            View view = convertView;
            if (view == null) {
                view = LayoutInflater.from(GoueucheActivity.this).inflate(R.layout.cart_list_item, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            ShangpinEntity data = mListData.get(position);
            bindListItem(holder, data);
            holder.add.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    int _id = Integer.parseInt(mListData.get(position).getId());

                    boolean selected = mSelectState.get(_id, false);

                    mListData.get(position).setCount(Integer.parseInt(mListData.get(position).getCount()) + 1 + "");

                    notifyDataSetChanged();

                    if (selected) {
                        totalPrice += Float.parseFloat(mListData.get(position).getPrice());
                        mPriceAll.setText("积分" + totalPrice + "");

                    }

                }
            });

            holder.red.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    // TODO Auto-generated method stub
                    if (mListData.get(position).getCount().equals("1"))
                        return;

                    int _id = Integer.parseInt(mListData.get(position).getId());

                    boolean selected = mSelectState.get(_id, false);
                    mListData.get(position).setCount(Integer.parseInt(mListData.get(position).getCount()) - 1 + "");
                    notifyDataSetChanged();

                    if (selected) {
                        totalPrice -= Float.parseFloat(mListData.get(position).getPrice());
                        mPriceAll.setText("积分" + totalPrice + "");

                    }

                }
            });
            return view;
        }

        private void bindListItem(ViewHolder holder, ShangpinEntity data) {

            holder.shopName.setText("1加1女装店");
            holder.content.setText(data.getName());
            holder.price.setText("积分     " + data.getPrice());
            Glide.with(GoueucheActivity.this)
                    .load(data.getUrl())
                    .into(holder.image);
           //holder.image.setImageUrl(data.getUrl());
            holder.carNum.setText(data.getCount() + "");
            int _id = Integer.parseInt(data.getId());
            boolean selected = mSelectState.get(_id, false);
            holder.checkBox.setChecked(selected);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ShangpinEntity bean = mListData.get(position);

            ViewHolder holder = (ViewHolder) view.getTag();
            int _id = Integer.parseInt(bean.getId());

            boolean selected = !mSelectState.get(_id, false);
            holder.checkBox.toggle();
            if (selected) {
                mSelectState.put(_id, true);
                totalPrice += Float.parseFloat(bean.getCount()) * Float.parseFloat(bean.getPrice());
            } else {
                mSelectState.delete(_id);
                totalPrice -= Float.parseFloat(bean.getCount()) * Float.parseFloat(bean.getPrice());
            }
            mSelectNum.setText("已选" + mSelectState.size() + "件商品");
            mPriceAll.setText("积分   " + totalPrice + "");
            if (mSelectState.size() == mListData.size()) {
                mCheckAll.setChecked(true);
            } else {
                mCheckAll.setChecked(false);
            }

        }

    }

    class ViewHolder {
        CheckBox checkBox;

        ImageView image;
        TextView shopName;
        TextView content;
        TextView carNum;
        TextView price;
        TextView add;
        TextView red;

        public ViewHolder(View view) {
            checkBox = (CheckBox) view.findViewById(R.id.check_box);
            shopName = (TextView) view.findViewById(R.id.tv_source_name);
            image = (ImageView) view.findViewById(R.id.iv_adapter_list_pic);
            content = (TextView) view.findViewById(R.id.tv_intro);
            carNum = (TextView) view.findViewById(R.id.tv_num);
            price = (TextView) view.findViewById(R.id.tv_price);
            add = (TextView) view.findViewById(R.id.tv_add);
            red = (TextView) view.findViewById(R.id.tv_reduce);

        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {

            case R.id.subtitle:
                isBatchModel = !isBatchModel;
                if (isBatchModel) {
                    mEdit.setText(getResources().getString(R.string.menu_enter));
                    mDelete.setText(getResources().getString(R.string.menu_del));
                    mBottonLayout.setVisibility(View.GONE);
                    mFavorite.setVisibility(View.VISIBLE);

                } else {
                    mEdit.setText(getResources().getString(R.string.menu_edit));

                    mFavorite.setVisibility(View.GONE);
                    mBottonLayout.setVisibility(View.VISIBLE);
                    mDelete.setText(getResources().getString(R.string.menu_sett));
                }

                break;

            case R.id.check_box:
                if (mCheckAll.isChecked()) {

                    totalPrice = 0;
                    if (mListData != null) {
                        mSelectState.clear();
                        int size = mListData.size();
                        if (size == 0) {
                            return;
                        }
                        for (int i = 0; i < size; i++) {
                            int _id = Integer.parseInt(mListData.get(i).getId());
                            mSelectState.put(_id, true);
                            totalPrice += Float.parseFloat(Integer.parseInt(mListData.get(i).getCount()) * Float.parseFloat(mListData.get(i).getPrice()) + "");
                        }
                        refreshListView();
                        mPriceAll.setText("￥" + totalPrice + "");
                        mSelectNum.setText("已选" + mSelectState.size() + "件商品");

                    }
                } else {
                    if (mListAdapter != null) {
                        totalPrice = 0;
                        mSelectState.clear();
                        refreshListView();
                        mPriceAll.setText("￥" + 0.00 + "");
                        mSelectNum.setText("已选" + 0 + "件商品");

                    }
                }
                break;

            case R.id.tv_cart_buy_or_del:
                if (isBatchModel) {
                    List<Integer> ids = getSelectedIds();
                    doDelete(ids);
                } else {
                    Toast.makeText(getApplicationContext(), "结算", 0).show();
                }

                break;

            default:
                break;
        }
    }


    //获取购物车列表的请求
    private void getData() {

        //通过Volley连接服务器获取数据
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("goods_name", "商品：上衣");
        map.put("goods_image", "file:///android_asset/avatar0.jpg");
        map.put("num", "1");
        map.put("goods_title", "2019-3-26");
        map.put("goods_price", "800");
        map.put("goods_id", "1000");
        /*Map<String, Object> map1=new HashMap<String, Object>();
        map1.put("name", "商品：小汽车");
        map1.put("image", "file:///android_asset/wj4.jpg");
        map1.put("num", "数量：1");
        map1.put("time", "时间：2019-9-27");
        map1.put("jiage", "价格：200积分");
        map1.put("shangdian_name", "LEGO");
        Map<String, Object> map2=new HashMap<String, Object>();
        map2.put("name", "商品：强美饮料");
        map2.put("image", "file:///android_asset/js3.jpg");
        map2.put("num", "数量：1");
        map2.put("time", "时间：2019-10-1");
        map2.put("jiage", "价格：500积分");
        map2.put("shangdian_name", "启德酒水");*/
        list.add(map);
        //list.add(map1);
        //list.add(map2);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonMessage = new JSONArray(list);
        JSONObject json1 = null;
        JSONObject json = null;
        try {
            json1 = jsonObject.put("datas",jsonMessage);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mListData.addAll(ShangchengParser.getGouwucheParser(json1));
        mListAdapter = new ListAdapter();
        mListView.setAdapter(mListAdapter);

    }
}







