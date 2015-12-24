package com.mikes.pluginmaintest;

/**
 * 插件实体
 * @author mikes
 *
 */
public class PluginBean {
	public String pakageName;
	public String label;

	public String getPakageName() {
		return pakageName;
	}

	public void setPakageName(String pakageName) {
		this.pakageName = pakageName;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "PluginBean [pakageName=" + pakageName + ", label=" + label
				+ "]";
	}

}
