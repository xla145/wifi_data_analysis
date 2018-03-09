package com.wzxy.service.model.sourcedata;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SourceData entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "source_data")
public class SourceData implements java.io.Serializable {

	// Fields

	@Id
	private Long id;
	private String wid;
	private String mmac;
	private String rate;
	private String wssid;
	private String wmac;
	private String time;
	private String lat;
	private String lon;
	private String addr;
	private String mac;
	private String rssi;
	private String ranges;
	private String ts;
	private String tmc;
	private String tc;
	private String ds;
	private String essid0;
	private String essid1;
	private String essid2;
	private String essid3;
	private String essid4;
	private String essid5;
	private String essid6;

	// Constructors

	/** default constructor */
	public SourceData() {
	}

	/** full constructor */
	public SourceData(String wid, String mmac, String rate, String wssid,
			String wmac, String time, String lat, String lon, String addr,
			String mac, String rssi, String ranges, String ts, String tmc,
			String tc, String ds, String essid0, String essid1, String essid2,
			String essid3, String essid4, String essid5, String essid6) {
		this.wid = wid;
		this.mmac = mmac;
		this.rate = rate;
		this.wssid = wssid;
		this.wmac = wmac;
		this.time = time;
		this.lat = lat;
		this.lon = lon;
		this.addr = addr;
		this.mac = mac;
		this.rssi = rssi;
		this.ranges = ranges;
		this.ts = ts;
		this.tmc = tmc;
		this.tc = tc;
		this.ds = ds;
		this.essid0 = essid0;
		this.essid1 = essid1;
		this.essid2 = essid2;
		this.essid3 = essid3;
		this.essid4 = essid4;
		this.essid5 = essid5;
		this.essid6 = essid6;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWid() {
		return this.wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}

	public String getMmac() {
		return this.mmac;
	}

	public void setMmac(String mmac) {
		this.mmac = mmac;
	}

	public String getRate() {
		return this.rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getWssid() {
		return this.wssid;
	}

	public void setWssid(String wssid) {
		this.wssid = wssid;
	}

	public String getWmac() {
		return this.wmac;
	}

	public void setWmac(String wmac) {
		this.wmac = wmac;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLat() {
		return this.lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return this.lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getRssi() {
		return this.rssi;
	}

	public void setRssi(String rssi) {
		this.rssi = rssi;
	}

	public String getRanges() {
		return this.ranges;
	}

	public void setRanges(String ranges) {
		this.ranges = ranges;
	}

	public String getTs() {
		return this.ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public String getTmc() {
		return this.tmc;
	}

	public void setTmc(String tmc) {
		this.tmc = tmc;
	}

	public String getTc() {
		return this.tc;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}

	public String getDs() {
		return this.ds;
	}

	public void setDs(String ds) {
		this.ds = ds;
	}

	public String getEssid0() {
		return this.essid0;
	}

	public void setEssid0(String essid0) {
		this.essid0 = essid0;
	}

	public String getEssid1() {
		return this.essid1;
	}

	public void setEssid1(String essid1) {
		this.essid1 = essid1;
	}

	public String getEssid2() {
		return this.essid2;
	}

	public void setEssid2(String essid2) {
		this.essid2 = essid2;
	}

	public String getEssid3() {
		return this.essid3;
	}

	public void setEssid3(String essid3) {
		this.essid3 = essid3;
	}

	public String getEssid4() {
		return this.essid4;
	}

	public void setEssid4(String essid4) {
		this.essid4 = essid4;
	}

	public String getEssid5() {
		return this.essid5;
	}

	public void setEssid5(String essid5) {
		this.essid5 = essid5;
	}

	public String getEssid6() {
		return this.essid6;
	}

	public void setEssid6(String essid6) {
		this.essid6 = essid6;
	}

	@Override
	public String toString() {
		return "SourceData [id=" + id + ", wid=" + wid + ", mmac=" + mmac
				+ ", rate=" + rate + ", wssid=" + wssid + ", wmac=" + wmac
				+ ", time=" + time + ", lat=" + lat + ", lon=" + lon
				+ ", addr=" + addr + ", mac=" + mac + ", rssi=" + rssi
				+ ", ranges=" + ranges + ", ts=" + ts + ", tmc=" + tmc
				+ ", tc=" + tc + ", ds=" + ds + ", essid0=" + essid0
				+ ", essid1=" + essid1 + ", essid2=" + essid2 + ", essid3="
				+ essid3 + ", essid4=" + essid4 + ", essid5=" + essid5
				+ ", essid6=" + essid6 + "]";
	}

}
