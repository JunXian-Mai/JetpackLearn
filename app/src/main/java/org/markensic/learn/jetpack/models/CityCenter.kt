package org.markensic.learn.jetpack.models

sealed class CityCenter(val value: String, val belong: String) {
    data class Province(val province: String, val short: String) : CityCenter(province, short)

    data class City(val city: String, val province: String) : CityCenter(city, province)

    data class WebSite(val email: String, val city: String) : CityCenter(email, city)
}


val provinces = arrayOf(
    CityCenter.Province("北京市", "京"),
    CityCenter.Province("河北省", "冀"),
    CityCenter.Province("内蒙古自治区", "内蒙古"),
    CityCenter.Province("吉林省", "吉"),
    CityCenter.Province("上海市", "沪"),
    CityCenter.Province("浙江省", "浙"),
    CityCenter.Province("福建省", "闽"),
    CityCenter.Province("山东省", "鲁"),
    CityCenter.Province("湖北省", "鄂"),
    CityCenter.Province("广东省", "粤"),
    CityCenter.Province("海南省", "琼"),
    CityCenter.Province("四川省", "川"),
    CityCenter.Province("云南省", "滇"),
    CityCenter.Province("陕西省", "陕"),
    CityCenter.Province("青海省", "青"),
    CityCenter.Province("新疆维吾尔自治区", "新"),
    CityCenter.Province("澳门特别行政区", "澳"),
    CityCenter.Province("天津市", "津"),
    CityCenter.Province("山西省", "晋"),
    CityCenter.Province("辽宁省", "辽"),
    CityCenter.Province("黑龙江省", "黑"),
    CityCenter.Province("江苏省", "苏"),
    CityCenter.Province("安徽省", "皖"),
    CityCenter.Province("江西省", "赣"),
    CityCenter.Province("河南省", "豫"),
    CityCenter.Province("湖南省", "湘"),
    CityCenter.Province("广西壮族自治区", "桂"),
    CityCenter.Province("重庆市", "渝"),
    CityCenter.Province("贵州省", "黔"),
    CityCenter.Province("西藏自治区", "藏"),
    CityCenter.Province("甘肃省", "甘"),
    CityCenter.Province("宁夏回族自治区", "宁"),
    CityCenter.Province("香港特别行政区", "港"),
    CityCenter.Province("台湾省", "台"),
)

val capitals = arrayOf(
    CityCenter.City("北京", "北京市"),
    CityCenter.City("北京A", "北京市"),
    CityCenter.City("北京B", "北京市"),
    CityCenter.City("北京C", "北京市"),
    CityCenter.City("石家庄", "河北省"),
    CityCenter.City("石家庄A", "河北省"),
    CityCenter.City("石家庄B", "河北省"),
    CityCenter.City("石家庄C", "河北省"),
    CityCenter.City("呼和浩特", "内蒙古自治区"),
    CityCenter.City("呼和浩特A", "内蒙古自治区"),
    CityCenter.City("呼和浩特B", "内蒙古自治区"),
    CityCenter.City("呼和浩特C", "内蒙古自治区"),
    CityCenter.City("长春", "吉林省"),
    CityCenter.City("长春A", "吉林省"),
    CityCenter.City("长春B", "吉林省"),
    CityCenter.City("长春C", "吉林省"),
    CityCenter.City("上海", "上海市"),
    CityCenter.City("上海A", "上海市"),
    CityCenter.City("上海B", "上海市"),
    CityCenter.City("上海C", "上海市"),
    CityCenter.City("杭州", "浙江省"),
    CityCenter.City("杭州A", "浙江省"),
    CityCenter.City("杭州B", "浙江省"),
    CityCenter.City("杭州C", "浙江省"),
    CityCenter.City("福州", "福建省"),
    CityCenter.City("福州A", "福建省"),
    CityCenter.City("福州B", "福建省"),
    CityCenter.City("福州C", "福建省"),
    CityCenter.City("济南", "山东省"),
    CityCenter.City("济南A", "山东省"),
    CityCenter.City("济南B", "山东省"),
    CityCenter.City("济南C", "山东省"),
    CityCenter.City("武汉", "湖北省"),
    CityCenter.City("武汉A", "湖北省"),
    CityCenter.City("武汉B", "湖北省"),
    CityCenter.City("武汉C", "湖北省"),
    CityCenter.City("广州", "广东省"),
    CityCenter.City("广州A", "广东省"),
    CityCenter.City("广州B", "广东省"),
    CityCenter.City("广州C", "广东省"),
    CityCenter.City("海口", "海南省"),
    CityCenter.City("海口A", "海南省"),
    CityCenter.City("海口B", "海南省"),
    CityCenter.City("海口C", "海南省"),
    CityCenter.City("成都", "四川省"),
    CityCenter.City("成都A", "四川省"),
    CityCenter.City("成都B", "四川省"),
    CityCenter.City("成都C", "四川省"),
    CityCenter.City("昆明", "云南省"),
    CityCenter.City("昆明A", "云南省"),
    CityCenter.City("昆明B", "云南省"),
    CityCenter.City("昆明C", "云南省"),
    CityCenter.City("西安", "陕西省"),
    CityCenter.City("西安A", "陕西省"),
    CityCenter.City("西安B", "陕西省"),
    CityCenter.City("西安C", "陕西省"),
    CityCenter.City("西宁", "青海省"),
    CityCenter.City("西宁A", "青海省"),
    CityCenter.City("西宁B", "青海省"),
    CityCenter.City("西宁C", "青海省"),
    CityCenter.City("乌鲁木齐", "新疆维吾尔自治区"),
    CityCenter.City("乌鲁木齐A", "新疆维吾尔自治区"),
    CityCenter.City("乌鲁木齐B", "新疆维吾尔自治区"),
    CityCenter.City("乌鲁木齐C", "新疆维吾尔自治区"),
    CityCenter.City("澳门", "澳门特别行政区"),
    CityCenter.City("澳门A", "澳门特别行政区"),
    CityCenter.City("澳门B", "澳门特别行政区"),
    CityCenter.City("澳门C", "澳门特别行政区"),
    CityCenter.City("天津", "天津市"),
    CityCenter.City("天津A", "天津市"),
    CityCenter.City("天津B", "天津市"),
    CityCenter.City("天津C", "天津市"),
    CityCenter.City("太原", "山西省"),
    CityCenter.City("太原A", "山西省"),
    CityCenter.City("太原B", "山西省"),
    CityCenter.City("太原C", "山西省"),
    CityCenter.City("沈阳", "辽宁省"),
    CityCenter.City("沈阳A", "辽宁省"),
    CityCenter.City("沈阳B", "辽宁省"),
    CityCenter.City("沈阳C", "辽宁省"),
    CityCenter.City("哈尔滨", "黑龙江省"),
    CityCenter.City("哈尔滨A", "黑龙江省"),
    CityCenter.City("哈尔滨B", "黑龙江省"),
    CityCenter.City("哈尔滨C", "黑龙江省"),
    CityCenter.City("南京", "江苏省"),
    CityCenter.City("南京A", "江苏省"),
    CityCenter.City("南京B", "江苏省"),
    CityCenter.City("南京C", "江苏省"),
    CityCenter.City("合肥", "安徽省"),
    CityCenter.City("合肥A", "安徽省"),
    CityCenter.City("合肥B", "安徽省"),
    CityCenter.City("合肥C", "安徽省"),
    CityCenter.City("南昌", "江西省"),
    CityCenter.City("南昌A", "江西省"),
    CityCenter.City("南昌B", "江西省"),
    CityCenter.City("南昌C", "江西省"),
    CityCenter.City("郑州", "河南省"),
    CityCenter.City("郑州A", "河南省"),
    CityCenter.City("郑州B", "河南省"),
    CityCenter.City("郑州C", "河南省"),
    CityCenter.City("长沙", "湖南省"),
    CityCenter.City("长沙A", "湖南省"),
    CityCenter.City("长沙B", "湖南省"),
    CityCenter.City("长沙C", "湖南省"),
    CityCenter.City("南宁", "广西壮族自治区"),
    CityCenter.City("南宁A", "广西壮族自治区"),
    CityCenter.City("南宁B", "广西壮族自治区"),
    CityCenter.City("南宁C", "广西壮族自治区"),
    CityCenter.City("重庆", "重庆市"),
    CityCenter.City("重庆A", "重庆市"),
    CityCenter.City("重庆B", "重庆市"),
    CityCenter.City("重庆C", "重庆市"),
    CityCenter.City("贵阳", "贵州省"),
    CityCenter.City("贵阳A", "贵州省"),
    CityCenter.City("贵阳B", "贵州省"),
    CityCenter.City("贵阳C", "贵州省"),
    CityCenter.City("拉萨", "西藏自治区"),
    CityCenter.City("拉萨A", "西藏自治区"),
    CityCenter.City("拉萨B", "西藏自治区"),
    CityCenter.City("拉萨C", "西藏自治区"),
    CityCenter.City("兰州", "甘肃省"),
    CityCenter.City("兰州A", "甘肃省"),
    CityCenter.City("兰州B", "甘肃省"),
    CityCenter.City("兰州C", "甘肃省"),
    CityCenter.City("银川", "宁夏回族自治区"),
    CityCenter.City("银川A", "宁夏回族自治区"),
    CityCenter.City("银川B", "宁夏回族自治区"),
    CityCenter.City("银川C", "宁夏回族自治区"),
    CityCenter.City("香港", "香港特别行政区"),
    CityCenter.City("香港A", "香港特别行政区"),
    CityCenter.City("香港B", "香港特别行政区"),
    CityCenter.City("香港C", "香港特别行政区"),
    CityCenter.City("台北", "台湾省"),
    CityCenter.City("台北A", "台湾省"),
    CityCenter.City("台北B", "台湾省"),
    CityCenter.City("台北C", "台湾省"),
)

val emails = arrayOf(
    CityCenter.WebSite("北京@email.com", "北京"),
    CityCenter.WebSite("北京A@email.com", "北京A"),
    CityCenter.WebSite("北京B@email.com", "北京B"),
    CityCenter.WebSite("北京C@email.com", "北京C"),
    CityCenter.WebSite("石家庄@email.com", "石家庄"),
    CityCenter.WebSite("石家庄A@email.com", "石家庄A"),
    CityCenter.WebSite("石家庄B@email.com", "石家庄B"),
    CityCenter.WebSite("石家庄C@email.com", "石家庄C"),
    CityCenter.WebSite("呼和浩特@email.com", "呼和浩特"),
    CityCenter.WebSite("呼和浩特A@email.com", "呼和浩特A"),
    CityCenter.WebSite("呼和浩特B@email.com", "呼和浩特B"),
    CityCenter.WebSite("呼和浩特C@email.com", "呼和浩特C"),
    CityCenter.WebSite("长春@email.com", "长春"),
    CityCenter.WebSite("长春A@email.com", "长春A"),
    CityCenter.WebSite("长春B@email.com", "长春B"),
    CityCenter.WebSite("长春C@email.com", "长春C"),
    CityCenter.WebSite("上海@email.com", "上海"),
    CityCenter.WebSite("上海A@email.com", "上海A"),
    CityCenter.WebSite("上海B@email.com", "上海B"),
    CityCenter.WebSite("上海C@email.com", "上海C"),
    CityCenter.WebSite("杭州@email.com", "杭州"),
    CityCenter.WebSite("杭州A@email.com", "杭州A"),
    CityCenter.WebSite("杭州B@email.com", "杭州B"),
    CityCenter.WebSite("杭州C@email.com", "杭州C"),
    CityCenter.WebSite("福州@email.com", "福州"),
    CityCenter.WebSite("福州A@email.com", "福州A"),
    CityCenter.WebSite("福州B@email.com", "福州B"),
    CityCenter.WebSite("福州C@email.com", "福州C"),
    CityCenter.WebSite("济南@email.com", "济南"),
    CityCenter.WebSite("济南A@email.com", "济南A"),
    CityCenter.WebSite("济南B@email.com", "济南B"),
    CityCenter.WebSite("济南C@email.com", "济南C"),
    CityCenter.WebSite("武汉@email.com", "武汉"),
    CityCenter.WebSite("武汉A@email.com", "武汉A"),
    CityCenter.WebSite("武汉B@email.com", "武汉B"),
    CityCenter.WebSite("武汉C@email.com", "武汉C"),
    CityCenter.WebSite("广州@email.com", "广州"),
    CityCenter.WebSite("广州A@email.com", "广州A"),
    CityCenter.WebSite("广州B@email.com", "广州B"),
    CityCenter.WebSite("广州C@email.com", "广州C"),
    CityCenter.WebSite("海口@email.com", "海口"),
    CityCenter.WebSite("海口A@email.com", "海口A"),
    CityCenter.WebSite("海口B@email.com", "海口B"),
    CityCenter.WebSite("海口C@email.com", "海口C"),
    CityCenter.WebSite("成都@email.com", "成都"),
    CityCenter.WebSite("成都A@email.com", "成都A"),
    CityCenter.WebSite("成都B@email.com", "成都B"),
    CityCenter.WebSite("成都C@email.com", "成都C"),
    CityCenter.WebSite("昆明@email.com", "昆明"),
    CityCenter.WebSite("昆明A@email.com", "昆明A"),
    CityCenter.WebSite("昆明B@email.com", "昆明B"),
    CityCenter.WebSite("昆明C@email.com", "昆明C"),
    CityCenter.WebSite("西安@email.com", "西安"),
    CityCenter.WebSite("西安A@email.com", "西安A"),
    CityCenter.WebSite("西安B@email.com", "西安B"),
    CityCenter.WebSite("西安C@email.com", "西安C"),
    CityCenter.WebSite("西宁@email.com", "西宁"),
    CityCenter.WebSite("西宁A@email.com", "西宁A"),
    CityCenter.WebSite("西宁B@email.com", "西宁B"),
    CityCenter.WebSite("西宁C@email.com", "西宁C"),
    CityCenter.WebSite("乌鲁木齐@email.com", "乌鲁木齐"),
    CityCenter.WebSite("乌鲁木齐A@email.com", "乌鲁木齐A"),
    CityCenter.WebSite("乌鲁木齐B@email.com", "乌鲁木齐B"),
    CityCenter.WebSite("乌鲁木齐C@email.com", "乌鲁木齐C"),
    CityCenter.WebSite("澳门@email.com", "澳门"),
    CityCenter.WebSite("澳门A@email.com", "澳门A"),
    CityCenter.WebSite("澳门B@email.com", "澳门B"),
    CityCenter.WebSite("澳门C@email.com", "澳门C"),
    CityCenter.WebSite("天津@email.com", "天津"),
    CityCenter.WebSite("天津A@email.com", "天津A"),
    CityCenter.WebSite("天津B@email.com", "天津B"),
    CityCenter.WebSite("天津C@email.com", "天津C"),
    CityCenter.WebSite("太原@email.com", "太原"),
    CityCenter.WebSite("太原A@email.com", "太原A"),
    CityCenter.WebSite("太原B@email.com", "太原B"),
    CityCenter.WebSite("太原C@email.com", "太原C"),
    CityCenter.WebSite("沈阳@email.com", "沈阳"),
    CityCenter.WebSite("沈阳A@email.com", "沈阳A"),
    CityCenter.WebSite("沈阳B@email.com", "沈阳B"),
    CityCenter.WebSite("沈阳C@email.com", "沈阳C"),
    CityCenter.WebSite("哈尔滨@email.com", "哈尔滨"),
    CityCenter.WebSite("哈尔滨A@email.com", "哈尔滨A"),
    CityCenter.WebSite("哈尔滨B@email.com", "哈尔滨B"),
    CityCenter.WebSite("哈尔滨C@email.com", "哈尔滨C"),
    CityCenter.WebSite("南京@email.com", "南京"),
    CityCenter.WebSite("南京A@email.com", "南京A"),
    CityCenter.WebSite("南京B@email.com", "南京B"),
    CityCenter.WebSite("南京C@email.com", "南京C"),
    CityCenter.WebSite("合肥@email.com", "合肥"),
    CityCenter.WebSite("合肥A@email.com", "合肥A"),
    CityCenter.WebSite("合肥B@email.com", "合肥B"),
    CityCenter.WebSite("合肥C@email.com", "合肥C"),
    CityCenter.WebSite("南昌@email.com", "南昌"),
    CityCenter.WebSite("南昌A@email.com", "南昌A"),
    CityCenter.WebSite("南昌B@email.com", "南昌B"),
    CityCenter.WebSite("南昌C@email.com", "南昌C"),
    CityCenter.WebSite("郑州@email.com", "郑州"),
    CityCenter.WebSite("郑州A@email.com", "郑州A"),
    CityCenter.WebSite("郑州B@email.com", "郑州B"),
    CityCenter.WebSite("郑州C@email.com", "郑州C"),
    CityCenter.WebSite("长沙@email.com", "长沙"),
    CityCenter.WebSite("长沙A@email.com", "长沙A"),
    CityCenter.WebSite("长沙B@email.com", "长沙B"),
    CityCenter.WebSite("长沙C@email.com", "长沙C"),
    CityCenter.WebSite("南宁@email.com", "南宁"),
    CityCenter.WebSite("南宁A@email.com", "南宁A"),
    CityCenter.WebSite("南宁B@email.com", "南宁B"),
    CityCenter.WebSite("南宁C@email.com", "南宁C"),
    CityCenter.WebSite("重庆@email.com", "重庆"),
    CityCenter.WebSite("重庆A@email.com", "重庆A"),
    CityCenter.WebSite("重庆B@email.com", "重庆B"),
    CityCenter.WebSite("重庆C@email.com", "重庆C"),
    CityCenter.WebSite("贵阳@email.com", "贵阳"),
    CityCenter.WebSite("贵阳A@email.com", "贵阳A"),
    CityCenter.WebSite("贵阳B@email.com", "贵阳B"),
    CityCenter.WebSite("贵阳C@email.com", "贵阳C"),
    CityCenter.WebSite("拉萨@email.com", "拉萨"),
    CityCenter.WebSite("拉萨A@email.com", "拉萨A"),
    CityCenter.WebSite("拉萨B@email.com", "拉萨B"),
    CityCenter.WebSite("拉萨C@email.com", "拉萨C"),
    CityCenter.WebSite("兰州@email.com", "兰州"),
    CityCenter.WebSite("兰州A@email.com", "兰州A"),
    CityCenter.WebSite("兰州B@email.com", "兰州B"),
    CityCenter.WebSite("兰州C@email.com", "兰州C"),
    CityCenter.WebSite("银川@email.com", "银川"),
    CityCenter.WebSite("银川A@email.com", "银川A"),
    CityCenter.WebSite("银川B@email.com", "银川B"),
    CityCenter.WebSite("银川C@email.com", "银川C"),
    CityCenter.WebSite("香港@email.com", "香港"),
    CityCenter.WebSite("香港A@email.com", "香港A"),
    CityCenter.WebSite("香港B@email.com", "香港B"),
    CityCenter.WebSite("香港C@email.com", "香港C"),
    CityCenter.WebSite("台北@email.com", "台北"),
    CityCenter.WebSite("台北A@email.com", "台北A"),
    CityCenter.WebSite("台北B@email.com", "台北B"),
    CityCenter.WebSite("台北C@email.com", "台北C"),
)
