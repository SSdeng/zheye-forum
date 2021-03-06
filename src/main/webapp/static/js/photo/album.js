// 构造【相册】信息
function getAlbum(data) {
    var showPhoto_all = "";
    var listAlbums = data.listAlbums;

    for (var i = 0; i < listAlbums.length; i++) {
        var listAlbum = listAlbums[i];

        // 相册展示
        var showPhoto_Fid_id = "showPhoto_Fid_" + listAlbum.fid;
        $("#showPhoto_Fid").attr("id", showPhoto_Fid_id);
        // 相框
        $("#showPhoto_a").attr("href", APP_PATH + "/photo.jsp?" + listAlbum.fid);
        // 相片
        if (listAlbum.photo == "" || listAlbum.photo == null) {    // 相册为空
            $("#showPhoto_img").attr("src", APP_PATH + "/static/img/photo/wuzhaopian.png");
        } else {
            $("#showPhoto_img").attr("src", listAlbum.photo);
        }
        // 按钮
        $("#showPhoto_button").html(listAlbum.name);
        // 删除
        $("#showPhoto_del").attr("onclick", "deleteAlbum('" + listAlbum.fid + "')");

        showPhoto_all = showPhoto_all + $("#showPhoto_hide").html();

        // 复原
        $("#" + showPhoto_Fid_id).attr("id", "showPhoto_Fid");
    }

    return showPhoto_all;
}