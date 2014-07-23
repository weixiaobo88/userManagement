$(document).ready(function(){
    $.get("/web/user/all").then(function(data){
        _(data).each(function(user){
            $("#user-table").find("tbody").append("<tr><td><input type=\"checkbox\" /></td><td>"+user.id+"</td><td>"
                +user.name+"</td><td>"+user.email+"</td><td>"+user.age+"</td><td><button class=\"btn\">删除 </button></td></tr>");
        })
    });
})