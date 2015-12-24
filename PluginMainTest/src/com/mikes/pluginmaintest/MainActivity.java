package com.mikes.pluginmaintest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mikes.pluginmaintest.R;

public class MainActivity extends Activity {

	private LinearLayout mContainerPlugin;

	private List<PluginBean> plugins;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);

		mContainerPlugin = (LinearLayout) findViewById(R.id.id_container_plugin);
	}

	public void FindPlugin(View v) {
		mContainerPlugin.removeAllViews();
		this.plugins = findPlugins();
		attachPlugin(plugins);
	}

	/**
	 * 查找插件
	 * 
	 * @return
	 */
	private List<PluginBean> findPlugins() {
		List<PluginBean> plugins = new ArrayList<PluginBean>();
		// 遍历包名，来获取插件
		PackageManager pm = getPackageManager();
		List<PackageInfo> pkgs = pm
				.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
		for (PackageInfo pkg : pkgs) {
			// 包名
			String packageName = pkg.packageName;
			String sharedUserId = pkg.sharedUserId;
			if (!"com.mikes.plugin".equals(sharedUserId)
					|| "com.mikes.pluginmaintest".equals(packageName))
				continue;
			// 进程名
			String prcessName = pkg.applicationInfo.processName;
			// label，也就是appName了
			String label = pm.getApplicationLabel(pkg.applicationInfo)
					.toString();
			PluginBean plug = new PluginBean();
			plug.label = label;
			plug.pakageName = packageName;
			plugins.add(plug);
		}
		return plugins;
	}

	/**
	 * 加载插件列表
	 * 
	 * @param plugins
	 */
	private void attachPlugin(final List<PluginBean> plugins) {
		if (plugins.size() == 0) {
			Button btn = new Button(this);
			btn.setTextColor(Color.RED);
			btn.setText("没有找到插件");
			mContainerPlugin.addView(btn);
			return;
		}
		
		for (final PluginBean plugin : plugins) {
			System.out.println(plugin.pakageName);
			Button btn = new Button(this);
			btn.setTextColor(Color.parseColor("#6a6aa9"));
			btn.setText(plugin.label);
			mContainerPlugin.addView(btn);
			// 添加事件
			btn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent it = new Intent();
					it.setAction(plugin.pakageName);
					startActivity(it);
				}
			});
		}
	}
}
