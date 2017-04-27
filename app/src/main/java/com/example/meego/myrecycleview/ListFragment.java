package com.example.meego.myrecycleview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {
    private static final String TAG = ListFragment.class.getSimpleName();
    private static final String ARGS_PAGE = "args page";
    private int mPage;

    private RecyclerView mRecycleView;
    private List<String> mDatas;

    public static ListFragment newInstance(int page) {
        Bundle bundle = new Bundle();

        bundle.putInt(ARGS_PAGE, page);
        ListFragment listFragment = new ListFragment();
        listFragment.setArguments(bundle);

        return listFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPage = getArguments().getInt(ARGS_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, getString(R.string.page_num, mPage));
        initData();

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mRecycleView = (RecyclerView) view.findViewById(R.id.rv_list);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //mRecycleView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        //mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(4, DividerItemDecoration.HORIZONTAL));
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecycleView.setAdapter(new MyRecycleViewAdapter());
        return view;
    }

    private void initData() {
        mDatas = new ArrayList<>();

        for (int i = 0; i < mPage; i++) {
            mDatas.add(i, "" + (char)('A' + i));
        }
    }

    class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.list_item, parent, false));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.imageView.setImageResource(R.mipmap.ic_launcher);
            holder.textView.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView;

            MyViewHolder(View itemView) {
                super(itemView);

                imageView = (ImageView) itemView.findViewById(R.id.iv_list_item);
                textView = (TextView) itemView.findViewById(R.id.tv_list_item);
            }
        }
    }
}
