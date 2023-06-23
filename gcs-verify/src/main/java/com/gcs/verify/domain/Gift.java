package com.gcs.verify.domain;

public class Gift {

    private Long giftId;

    private String giftName;

    private String giftPicture;

    private Long giftNum;

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftPicture() {
        return giftPicture;
    }

    public void setGiftPicture(String giftPicture) {
        this.giftPicture = giftPicture;
    }

    public Long getGiftNum() {
        return giftNum;
    }

    public void setGiftNum(Long giftNum) {
        this.giftNum = giftNum;
    }

    @Override
    public String toString() {
        return "Gift{" +
                "giftId=" + giftId +
                ", giftName='" + giftName + '\'' +
                ", giftPicture='" + giftPicture + '\'' +
                ", giftNum=" + giftNum +
                '}';
    }
}
