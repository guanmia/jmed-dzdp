package com.jmed.dzdp.domain.dto;

import java.util.List;

public class ProductDto {

	private String productId;
	private String productName;
	private int orderAmount;
	private int duration;	
	private String description;
	private float originalPrice;	
	private float settlePrice;	
	private int minOrders;
	private int maxOrders;	
	private List imageUrls;
	private String thumbUrl;
	private int rank;
	public ProductDto(String productId, String productName, int orderAmount, int duration, String description,
			float originalPrice, float settlePrice, int minOrders, int maxOrders, List imageUrls, String thumbUrl,
			int rank) {
		this.productId = productId;
		this.productName = productName;
		this.orderAmount = orderAmount;
		this.duration = duration;
		this.description = description;
		this.originalPrice = originalPrice;
		this.settlePrice = settlePrice;
		this.minOrders = minOrders;
		this.maxOrders = maxOrders;
		this.imageUrls = imageUrls;
		this.thumbUrl = thumbUrl;
		this.rank = rank;
	}
	public String getProductId() {
		return productId;
	}
	public String getProductName() {
		return productName;
	}
	public int getOrderAmount() {
		return orderAmount;
	}
	public int getDuration() {
		return duration;
	}
	public String getDescription() {
		return description;
	}
	public float getOriginalPrice() {
		return originalPrice;
	}
	public float getSettlePrice() {
		return settlePrice;
	}
	public int getMinOrders() {
		return minOrders;
	}
	public int getMaxOrders() {
		return maxOrders;
	}
	public List getImageUrls() {
		return imageUrls;
	}
	public String getThumbUrl() {
		return thumbUrl;
	}
	public int getRank() {
		return rank;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}
	public void setSettlePrice(float settlePrice) {
		this.settlePrice = settlePrice;
	}
	public void setMinOrders(int minOrders) {
		this.minOrders = minOrders;
	}
	public void setMaxOrders(int maxOrders) {
		this.maxOrders = maxOrders;
	}
	public void setImageUrls(List imageUrls) {
		this.imageUrls = imageUrls;
	}
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
}
