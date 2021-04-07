package a.b.c;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import a.b.c.base.LazyFragment;

/**
 * create by duxl 2021/4/6
 */
public class ListFragment extends LazyFragment {

    RecyclerView mRecyclerView;
    String mArgs;

    public static ListFragment newInstance(String text) {
        Bundle args = new Bundle();
        args.putString("text", text);
        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = getArguments().getString("text");
    }

    //    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_list, container, false);
//        return v;
//    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void onLazyHiddenChanged(boolean isVisible, boolean isFirstVisible) {
        if (isVisible) {
            Toast.makeText(getContext(), mArgs + " 可见", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onLazyViewCreated(View v) {
        mRecyclerView = v.findViewById(R.id.recyclerView);
        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View item = getLayoutInflater().inflate(R.layout.adapter_listitem, parent, false);
                return new RecyclerView.ViewHolder(item) {

                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                TextView tvLeft = holder.itemView.findViewById(R.id.tv_value_left);
                tvLeft.setText(String.valueOf(position));

                TextView tvCenter = holder.itemView.findViewById(R.id.tv_value_center);
                tvCenter.setText("【" + mArgs + "】");

                TextView tvRight = holder.itemView.findViewById(R.id.tv_value_right);
                tvRight.setText("Right");

            }

            @Override
            public int getItemCount() {
                return 30;
            }
        });
    }
}
