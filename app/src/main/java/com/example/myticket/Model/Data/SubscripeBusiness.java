package com.example.myticket.Model.Data;

public  class  SubscripeBusiness
{


    static private int experttype;
    static private Long expertId;
    static private long packageId;
    static private long promoId;
    static private double promocode;
    static private double packagePrice;
    static private int months;
    static private String packageName;
    static private String expertName;
    static private boolean haspromo ;

    public static boolean isHaspromo() {
        return haspromo;
    }

    public static void setHaspromo(boolean haspromo) {
        SubscripeBusiness.haspromo = haspromo;
    }

    public static String getPackageName() {
        return packageName;
    }

    public static void setPackageName(String packageName) {
        SubscripeBusiness.packageName = packageName;
    }

    public static String getExpertName() {
        return expertName;
    }

    public static void setExpertName(String expertName) {
        SubscripeBusiness.expertName = expertName;
    }

    public static Long getExpertId() {
        return expertId;
    }

    public static void setExpertId(Long expertId) {
        SubscripeBusiness.expertId = expertId;
    }

    public static long getPackageId() {
        return packageId;
    }

    public static void setPackageId(long packageId) {
        SubscripeBusiness.packageId = packageId;
    }

    public static long getPromoId() {
        return promoId;
    }

    public static void setPromoId(long promoId) {
        SubscripeBusiness.promoId = promoId;
    }

    public static double getPromocode() {
        return promocode;
    }

    public static void setPromocode(double promocode) {
        SubscripeBusiness.promocode = promocode;
    }

    public static double getPackagePrice() {
        return packagePrice;
    }

    public static void setPackagePrice(double packagePrice) {
        SubscripeBusiness.packagePrice = packagePrice;
    }

    public static int getMonths() {
        return months;
    }

    public static void setMonths(int months) {
        SubscripeBusiness.months = months;
    }

    public static int getExperttype() {
        return experttype;
    }

    public static void setExperttype(int experttype) {
        SubscripeBusiness.experttype = experttype;
    }


}
