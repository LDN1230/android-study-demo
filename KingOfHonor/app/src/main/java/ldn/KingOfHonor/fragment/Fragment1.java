package ldn.KingOfHonor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;

import ldn.KingOfHonor.R;
import ldn.KingOfHonor.sqlite.HeroSQLiteHelper;

/**
 * @author: LDN
 * @date: 2020/5/17
 */
public class Fragment1 extends Fragment {

    private View view; //该Fragment的布局
    private HeroSQLiteHelper mySQLiteHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 注册 EventBus
        EventBus.getDefault().register(this);
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment1, container, false);
        mySQLiteHelper= new HeroSQLiteHelper(getContext());

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
