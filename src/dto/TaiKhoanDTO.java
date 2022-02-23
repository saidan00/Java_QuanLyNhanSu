/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author ADMIN
 */
public class TaiKhoanDTO {
	private String TenDangNhap;
	private String MatKhau;

	// get
	public String getTenDangNhap() {
		return TenDangNhap;
	}

	public String getMatKhau() {
		return MatKhau;
	}

	// set
	public void setTenDangNhap(String tendangnhap) {
		TenDangNhap = tendangnhap;

	}

	public void setMatKhau(String matkhau) {
		MatKhau = matkhau;
	}
}
