package com.sharpsan.xposed.xhidesmsheader;

import android.view.View;
import android.widget.LinearLayout;
import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

public class Main implements IXposedHookInitPackageResources {
	View widgetHeader;

	@Override
	public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {

		if (!resparam.packageName.equals("com.android.mms")) {
			return;
		}

		resparam.res.hookLayout("com.android.mms", "layout", "widget", new XC_LayoutInflated() {
			@Override
			public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {

				widgetHeader = liparam.view.findViewById(liparam.res.getIdentifier("widget_header", "id", "com.android.mms"));
				widgetHeader.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
			}
		});
	}
}