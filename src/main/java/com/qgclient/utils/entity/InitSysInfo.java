package com.qgclient.utils.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
public class InitSysInfo {
	private VersionInfo adrversioninfo;  // app 升级信息
	private List<List<Integer>> game24list;

	public VersionInfo getAdrversioninfo() {
		return adrversioninfo;
	}

	public void setAdrversioninfo(VersionInfo adrversioninfo) {
		this.adrversioninfo = adrversioninfo;
	}

	public List<List<Integer>> getGame24list() {
		return game24list;
	}

	public void setGame24list(List<List<Integer>> game24list) {
		this.game24list = game24list;
	}

	public class VersionInfo {
		private String nowversion; // 新版本号
		private int autoupdate;   // 1 可更新
		private String forceupdateversion; // 强制更新版本号
		private String apkdownurl;
		private String versiondesc;

		public String getNowversion() {
			return nowversion;
		}

		public void setNowversion(String nowversion) {
			this.nowversion = nowversion;
		}

		public String getForceupdateversion() {
			return forceupdateversion;
		}

		public void setForceupdateversion(String forceupdateversion) {
			this.forceupdateversion = forceupdateversion;
		}

		public String getApkdownurl() {
			return apkdownurl;
		}

		public void setApkdownurl(String apkdownurl) {
			this.apkdownurl = apkdownurl;
		}

		public int getAutoupdate() {
			return autoupdate;
		}

		public void setAutoupdate(int autoupdate) {
			this.autoupdate = autoupdate;
		}

		public String getVersiondesc() {
			return versiondesc;
		}

		public void setVersiondesc(String versiondesc) {
			this.versiondesc = versiondesc;
		}
	}
}
