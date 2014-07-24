/**
 * 这里的逻辑可以有多种变换:
 * 1. 拼字符串来添加一行,这一行里有很多变化,比如tr要不要加id,checkbox要不要加id,要不要加全局变量等等.
 * 2. 引入view template
 */
$(document).ready(function(){
    $.get("/web/user/all").then(function(data){
        _(data).each(function(user){
            $("#user-table").find("tbody").append(
                    "<tr class=\"user_line\" ><td><input type=\"checkbox\" name=\"selected_user_id\" value=\""+user.id+"\"/></td><td>"+user.id+"</td><td>"
                +user.name+"</td><td>"+user.email+"</td><td>"+user.age
                +"</td><td><button class=\"btn\" onclick='remove_user( \""+user.id+"\", this)'>删除 </button></td></tr>");//angular.js的意义

        });
    });
});

function remove_user(user_id, user_view_element) {
   $(user_view_element).parents(".user_line").remove();

//    $.ajax({
//        url:"/web/user/",
//        method: "delete"
//    }).then(function(data){
//        $("#user-table").find("tbody").remove(user_view_element);
//    })

}



function delete_all_selected_users() {
    var selected_ids =[];
    var selected_checkbox = [];
    $("#user-table").find("input:checked").each(function(){
        selected_ids.push($(this).val());
        selected_checkbox.push(this);
    });

    $(selected_checkbox).parents(".user_line").remove();//这一行的改进不仅仅是一个简单的取巧,它是一种函数式思想的体现


// batch delete
//    $.ajax({
//        url:"/web/user/",
//        method: "delete"
//    }).then(function(data){
//    })
}