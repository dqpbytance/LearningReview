package com.example.learingreview.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.learingreview.R;

public class NetStateFragment extends Fragment {
    private View view;
    private TextView net_state;
    private NetworkBroadcastReceiver broadcastReceiver;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_netstate, container, false);
        net_state=view.findViewById(R.id.tv_net_state);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        broadcastReceiver = new NetworkBroadcastReceiver();
        getActivity().getApplicationContext().registerReceiver(broadcastReceiver, intentFilter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println(getClass().toString());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver != null)
            getActivity().getApplicationContext().unregisterReceiver(broadcastReceiver);
    }

    public class NetworkBroadcastReceiver extends BroadcastReceiver {
        private final String TAG = "NetworkChangedReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            //获取所有当前已有连接上状态的网络连接的信息
            Network[] networks = connMgr.getAllNetworks();

            //用于记录最后的网络连接信息
            int result = 0;//mobile false = 1, mobile true = 2, wifi = 4

            Log.d(TAG, "onReceive: "+networks.length);
            //通过循环将网络信息逐个取出来
            for (int i = 0; i < networks.length; i++) {
                //获取ConnectivityManager对象对应的NetworkInfo对象
                NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);

                //检测到有数据连接，但是并连接状态未生效，此种状态为wifi和数据同时已连接，以wifi连接优先
                if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE && !networkInfo.isConnected()) {
                    result+=1;
                }

                //检测到有数据连接，并连接状态已生效，此种状态为只有数据连接，wifi并未连接上
                if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE && networkInfo.isConnected()) {
                    result+=2;
                }

                //检测到有wifi连接，连接状态必为true
                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    result+=4;
                }
            }

            //因为存在上述情况的组合情况，以组合相加的唯一值作为最终状态的判断
            switch (result) {
                case 0:
                    net_state.setText("无网络连接");
                    break;
                case 2:
                    net_state.setText("正在使用数据网络");
                    break;
                case 4:
                    net_state.setText("现在正在使用wifi网络");
                    break;
                case 5:
                    net_state.setText("正在使用网络和数据连接");
                    break;
            }
        }
    }
}
