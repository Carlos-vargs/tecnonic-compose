package com.example.tecnonic_compose

import com.google.gson.annotations.SerializedName

// Data Model for KPI
data class KPIData(
    @SerializedName("HierarchyDate") val year: String,
    @SerializedName("KPI Total_Price") val totalPrice: Double
)
// Data Model for Payment Methods
data class PaymentData(
    @SerializedName("HierarchyOrder") val paymentMethod: String,
    @SerializedName("Sum of Quantity") val quantity: Int
)

data class ShippingData(
    @SerializedName("HierarchyShip") val hierarchyShip: String,
    @SerializedName("Sales Amount by ship") val salesAmountByShip: Int
)

data class TaxShippingData(
    @SerializedName("HierarchyShip") val hierarchyShip: String,
    @SerializedName("Sum of Tax_Amount") val sumOfTaxAmount: Int
)

data class QuantityProductData(
    @SerializedName("HierarchyClassification") val hierarchyClassification: String,
    @SerializedName("Sum of Quantity") val sumOfQuantity: Int
)

data class ShippingByFiscalYearData(
    @SerializedName("HierarchyFiscal") val hierarchyFiscal: String,
    @SerializedName("Sum of Shipping_Cost") val sumOfShippingCost: Int
)

data class SoldByBrandData(
    @SerializedName("HierarchyClassification") val hierarchyClassification: String,
    @SerializedName("KPI Total_Price") val kpiTotalPrice: Double,
    @SerializedName("Sum of Quantity") val sumOfQuantity: Int
)

data class SalesByShippingCompanyData(
    @SerializedName("Shipping_Company") val shippingCompany: String,
    @SerializedName("Sales Amount by ship") val salesAmountByShip: Int
)

data class OrderStatusData(
    @SerializedName("HierarchyOrder") val hierarchyOrder: String,
    @SerializedName("KPI_Order_Quantity_Delivery") val kpiOrderQuantityDelivery: Int
)

data class SalesAndQuantityByMonthData(
    @SerializedName("HierarchyDate") val hierarchyDate: String,
    @SerializedName("KPI Total_Price") val kpiTotalPrice: Double,
    @SerializedName("Sum of Quantity") val sumOfQuantity: Int
)




