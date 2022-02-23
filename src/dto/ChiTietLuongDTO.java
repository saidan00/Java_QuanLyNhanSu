/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Admin
 */
public class ChiTietLuongDTO {
	private float TongSoNgay;
	private int SoNgayNghi;
	private int MaLuong;
	private int LuongCB;

	public ChiTietLuongDTO(ChiTietLuongDTO ct1) {
		this.TongSoNgay = ct1.TongSoNgay;
		this.SoNgayNghi = ct1.SoNgayNghi;
		this.MaLuong = ct1.MaLuong;
		this.LuongCB = ct1.LuongCB;
	}

	public float getTongSoNgay() {
		return TongSoNgay;
	}

	public void setTongSoNgay(float tongSoNgay) {
		TongSoNgay = tongSoNgay;
	}

	public int getSoNgayNghi() {
		return SoNgayNghi;
	}

	public void setSoNgayNghi(int soNgayNghi) {
		SoNgayNghi = soNgayNghi;
	}

	public int getMaLuong() {
		return MaLuong;
	}

	public void setMaLuong(int maLuong) {
		MaLuong = maLuong;
	}

	public int getLuongCB() {
		return LuongCB;
	}

	public void setLuongCB(int luongCB) {
		LuongCB = luongCB;
	}

}
