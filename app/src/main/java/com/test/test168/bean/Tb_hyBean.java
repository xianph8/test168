package com.test.test168.bean;


import java.io.Serializable;

/**
 * 会员表的bean层
 */
public class Tb_hyBean implements Serializable {


    /**
     * 优惠方式    折扣价
     */
    public static final int DISCOUNT_PRICE = 1;
    /**
     * 优惠方式    会员价
     */
    public static final int MEMBERSHIP_PRICE = 2;
    public String id = "saf";
    /**
     * 会员分类ID
     */
    public String id_hyfl = "f";

    /**
     * 会员类型标识
     */
    public String flag_yhlx = "gfd";

    /**
     * 折扣
     */
    public Double zk = 0.0;
    /**
     * 姓名
     */
    public String name = "assfsa";

    /**
     * 会员卡卡号
     */
    public String membercard = "2134";
    /**
     * 手机
     */
    public String phone = "436457878";//Mobilephone 手机                varchar(50)
    /**
     * 地址
     */
    public String address = "fghgjhl";//dz 地址                                  varchar(200)
    /**
     * qq号
     */
    public String qq = "898989";//QQ                                       varchar(50)
    /**
     * 邮箱
     */
    public String email = "afds@4e54";//email 邮箱                            varchar(50)
    /**
     * 微信号
     */
    public String MMno = "atrytyi";//MMno微信号                             varchar(50)
    /**
     * 会员生日
     */
    public String hysr = "416748";
    /**
     * 生日
     */
    public String birthday = "6878";
    /**
     * 是否为农历
     */
    public Integer flag_nl;
    /**
     * 性别
     */
    public Integer flag_sex=1;
    /**
     * 商户ID
     */
    public String id_shop = "87";
    /**
     * 电话
     */
    private String tel = "123548100";//tel 电话                                varchar(50)
    /**
     * 邮编
     */
    private String zipcode = "0314";
    /**
     * 创建日期
     */
    private String rq_b = "034867";

    /**
     * 停用日期
     */
    private String rq_e = "68790";

    /**
     * 是否停用
     */
    private Integer flag_stop = 0;//flag_stop 停用                    int
    /**
     * 建立 日期
     */
    private String rq_create = "035484";//rq_create 建立日期                datetime

    /**
     * 数据交换更新标识
     */
    private Integer nlast = 0;

    @Override
    public String toString() {
        return "Tb_hyBean{" +
                "id='" + id + '\'' +
                ", id_shop='" + id_shop + '\'' +
                ", name='" + name + '\'' +
                ", membercard='" + membercard + '\'' +
                ", qq='" + qq + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", MMno='" + MMno + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", birthday='" + birthday + '\'' +
                ", hysr='" + hysr + '\'' +
                ", flag_nl='" + flag_nl + '\'' +
                ", id_hyfl='" + id_hyfl + '\'' +
                ", flag_yhlx='" + flag_yhlx + '\'' +
                ", zk='" + zk + '\'' +
                ", rq_b='" + rq_b + '\'' +
                ", rq_e='" + rq_e + '\'' +
                ", flag_stop=" + flag_stop +
                ", rq_create='" + rq_create + '\'' +
                ", nlast=" + nlast +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_hyfl() {
        return id_hyfl;
    }

    public void setId_hyfl(String id_hyfl) {
        this.id_hyfl = id_hyfl;
    }

    public String getFlag_yhlx() {
        return flag_yhlx;
    }

    public void setFlag_yhlx(String flag_yhlx) {
        this.flag_yhlx = flag_yhlx;
    }

    public Double getZk() {
        return zk;
    }

    public void setZk(Double zk) {
        this.zk = zk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMembercard() {
        return membercard;
    }

    public void setMembercard(String membercard) {
        this.membercard = membercard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMMno() {
        return MMno;
    }

    public void setMMno(String MMno) {
        this.MMno = MMno;
    }

    public String getHysr() {
        return hysr;
    }

    public void setHysr(String hysr) {
        this.hysr = hysr;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getFlag_nl() {
        return flag_nl;
    }

    public void setFlag_nl(Integer flag_nl) {
        this.flag_nl = flag_nl;
    }

    public Integer getFlag_sex() {
        return flag_sex;
    }

    public void setFlag_sex(Integer flag_sex) {
        this.flag_sex = flag_sex;
    }

    public String getId_shop() {
        return id_shop;
    }

    public void setId_shop(String id_shop) {
        this.id_shop = id_shop;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getRq_b() {
        return rq_b;
    }

    public void setRq_b(String rq_b) {
        this.rq_b = rq_b;
    }

    public String getRq_e() {
        return rq_e;
    }

    public void setRq_e(String rq_e) {
        this.rq_e = rq_e;
    }

    public Integer getFlag_stop() {
        return flag_stop;
    }

    public void setFlag_stop(Integer flag_stop) {
        this.flag_stop = flag_stop;
    }

    public String getRq_create() {
        return rq_create;
    }

    public void setRq_create(String rq_create) {
        this.rq_create = rq_create;
    }

    public Integer getNlast() {
        return nlast;
    }

    public void setNlast(Integer nlast) {
        this.nlast = nlast;
    }
}
