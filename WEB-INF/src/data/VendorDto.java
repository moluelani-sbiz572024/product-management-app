package data;

/**
 * 仕入れ先データ受け渡しクラス
 */
public final class VendorDto {
    private int vendorCode = 0;		// 仕入れ先コード

    // アクセサメソッド：仕入れ先コード
    public int getVendorCode()                { return this.vendorCode; }
    public void setVendorCode(int vendorCode) { this.vendorCode = vendorCode; }
}
