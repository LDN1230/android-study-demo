package ldn.KingOfHonor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import ldn.KingOfHonor.R;
import ldn.KingOfHonor.model.Equipment;

/**
 * @author: LDN
 * @date: 2020/5/17
 */
public class Fragment2 extends Fragment {

    private View view;
    private int totalPage;//总页数
    private int mPageSize = 16;//每页容量
    private ArrayList<Equipment> equipmentArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment2, container, false);
        initView();

        initData();
        totalPage = (int) Math.ceil(equipmentArrayList.size() * 1.0 / mPageSize);
        return view;
    }

    private void initData() {
    }

    private void initView() {
    }
}
