package data;

/**
 * 商品データ受け渡しクラス
 */
public final class ProductDto {
    private int id = 0;					// ID
    private int productCode = 0;		// 商品コード
    private String productName = "";	// 商品名
    private int price = 0;				// 単価
    private int stockQuantity = 0;		// 在庫数
    private int vendorCode = 0;			// 仕入れ先コード

    // 各カラムのゲッター（データ取得メソッド）とセッター（データ設定メソッド）を実装

    // アクセサメソッド：ID
    public int getId()        { return this.id; }
    public void setId(int id) { this.id = id; }

    // アクセサメソッド：商品コード
    public int getProductCode()                 { return this.productCode; }
    public void setProductCode(int productCode) { this.productCode = productCode; }

    // アクセサメソッド：商品名
    public String getProductName()                 { return this.productName; }
    public void setProductName(String productName) { this.productName = productName; }

    // アクセサメソッド：単価
    public int getPrice()           { return this.price; }
    public void setPrice(int price) { this.price = price; }

    // アクセサメソッド：在庫数
    public int getStockQuantity()                   { return this.stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    // アクセサメソッド：仕入れ先コード
    public int getVendorCode()                { return this.vendorCode; }
    public void setVendorCode(int vendorCode) { this.vendorCode = vendorCode; }
}
