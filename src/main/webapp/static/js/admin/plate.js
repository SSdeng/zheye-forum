/* 板块新增预览 */
function onkeyupBoardAdd() {
    var bname = $.trim($("#bname_Add").val());   //去掉前后空格
    var count_num = chEnWordCount(bname);
    if (count_num > boardNameLength){
        layer.tips('不能超过【'+boardNameLength+'】个字符，当前数 - '+count_num, '#bname_Add', {
            tips: [1, '#ff6620'] //还可配置颜色
        });
        return false;
    } else {
        var index = layer.tips("满足");
        // 立即关闭
        layer.close(index);
        return true;
    }
}
/*新增板块信息*/
function boardAdd(){
    var bname = $.trim($("#bname_Add").val());   //去掉前后空格
    if (bname == ""){
        layer.tips('请输入板块名', '#bname_Add', {
            tips: [1, '#ff6620'] //还可配置颜色
        });
        return false;
    }
    if (!onkeyupBoardAdd()){
        return false;
    }
    //调ajax
    $.ajax({
        url: APP_PATH + "/zheye-forum/board/setBoard",
        data: $('#form_addBoard').serialize(),
        type: "POST",
        dataType: "json",
        success: function(data){
            // 状态码
            var code = data.code;
            // 提示信息
            var msg = data.msg;
            if (code == 200) {
                $('#board_Add').modal('hide');     // 关闭模态框
                $.ajax({
                    url: APP_PATH + "/zheye-forum/board/getBoard",
                    type: "get",
                    dataType: "json",
                    success: function(data){
                        // 状态码
                        var code = data.code;
                        // 提示信息
                        var msg = data.msg;
                        if (code == 200) {
                            /*########################################### 板块管理 ############################################################*/
                            $("#board_all").html(getBoardList(data.data));
                            //板块总数
                            $("#board_total").html('（' + data.data.total + '类）');
                            /*########################################### 板块管理-end ############################################################*/
                        } else if (code == 500) {
                            layer.msg(msg,{icon: 5});
                        }
                    },
                    error : function() {
                        layer.msg("出错！",{icon: 5});
                    }
                });
                layer.msg(msg);
            } else if (code == 404) {
                layer.tips(msg, '#bname_Add', {
                    tips: [1, '#ff6620'] //还可配置颜色
                });
            } else if (code == 500) {
                layer.msg(msg,{icon: 5});
            }
        },
        error : function() {
            layer.msg("出错！",{icon: 5});
        }
    });
}


/* 板块修改预览 */
function onkeyupBoardUpdate() {
    var bname = $.trim($("#boardEdit_new_name").val());   //去掉前后空格
    var count_num = chEnWordCount(bname);
    if (count_num > boardNameLength){
        layer.tips('不能超过【'+boardNameLength+'】个字符，当前数 - '+count_num, '#boardEdit_new_name', {
            tips: [1, '#ff6620'] //还可配置颜色
        });
        return false;
    } else {
        var index = layer.tips("满足");
        // 立即关闭
        layer.close(index);
        return true;
    }
}
/*板块显示-修改*/
function boardShow(bid, bname){
    // 原板块名
    $("#boardEdit_name").html(bname);
    // 新板块输入框
    $("#boardEdit_new_name").attr("onkeyup", "onkeyupBoardUpdate()");
    // 确定修改
    $("#boardEdit_submit").attr("onclick", "boardUpdate('" + bid + "')");
}
/*修改板块信息*/
function boardUpdate(bid){
    var bname = $.trim($("#boardEdit_new_name").val());   //去掉前后空格
    if (bname == ""){
        layer.tips('请输入板块名', '#boardEdit_new_name', {
            tips: [1, '#ff6620'] //还可配置颜色
        });
        return false;
    }
    if (!onkeyupBoardUpdate()){
        return false;
    }
    var data = {
        "bid": bid,
        "bname": bname
    }
    //调ajax
    $.ajax({
        url: APP_PATH + "/zheye-forum/board/updateBoard",
        data: data,
        type: "put",
        dataType: "json",
        success: function(data){
            // 状态码
            var code = data.code;
            // 提示信息
            var msg = data.msg;
            if (code == 200) {
                $('#board_Update').modal('hide');     // 关闭模态框
                $.ajax({
                    url: APP_PATH + "/zheye-forum/board/getBoard",
                    type: "get",
                    dataType: "json",
                    success: function(data){
                        // 状态码
                        var code = data.code;
                        // 提示信息
                        var msg = data.msg;
                        if (code == 200) {
                            /*########################################### 板块管理 ############################################################*/
                            $("#board_all").html(getBoardList(data.data));
                            //板块总数
                            $("#board_total").html('（' + data.data.total + '类）');
                            /*########################################### 板块管理-end ############################################################*/
                        } else if (code == 500) {
                            layer.msg(msg,{icon: 5});
                        }
                    },
                    error : function() {
                        layer.msg("出错！",{icon: 5});
                    }
                });
                layer.msg(msg);
            } else if (code == 404) {
                layer.tips(msg, '#boardEdit_new_name', {
                    tips: [1, '#ff6620'] //还可配置颜色
                });
            } else if (code == 500) {
                layer.msg(msg,{icon: 5});
            }
        },
        error : function() {
            layer.msg("出错！",{icon: 5});
        }
    });
}


/*删除确认框*/
function b_del(bid) {
    layer.confirm('确定删除该板块吗？<br>这将同时删除与该板块相关的所有信息<br>删除后无法恢复！', {
        btn:["确定","取消"],
        icon:2,
        title: "删除提示"
    }, function(){
        //点击确后关闭提示框
        layer.closeAll('dialog');
        boardDel(bid);
    });
}
/*删除板块信息*/
function boardDel(bid) {
    //调ajax
    $.ajax({
        url: APP_PATH + "/zheye-forum/board/deleteBoard/"+bid,
        data: $('#form_delBoard').serialize(),
        type: "delete",
        dataType: "json",
        success: function(data){
            // 状态码
            var code = data.code;
            // 提示信息
            var msg = data.msg;
            if (code == 200) {
                $('#board_Add').modal('hide');     // 关闭模态框
                $.ajax({
                    url: APP_PATH + "/zheye-forum/board/getBoard",
                    type: "get",
                    dataType: "json",
                    success: function(data){
                        // 状态码
                        var code = data.code;
                        // 提示信息
                        var msg = data.msg;
                        if (code == 200) {
                            /*########################################### 板块管理 ############################################################*/
                            $("#board_all").html(getBoardList(data.data));
                            //板块总数
                            $("#board_total").html('（' + data.data.total + '类）');
                            /*########################################### 板块管理-end ############################################################*/
                        } else if (code == 500) {
                            layer.msg(msg,{icon: 5});
                        }
                    },
                    error : function() {
                        layer.msg("出错！",{icon: 5});
                    }
                });
                layer.msg(msg);
            } else if (code == 500) {
                layer.msg(msg,{icon: 5});
            }
        },
        error : function() {
            layer.msg("出错！",{icon: 5});
        }
    });
}