/**
 * 这里的逻辑可以有多种变换:
 * 1. 我们可以要求,一旦有选中的,就全部取消,只有全部没选中才选中
 * 2. 我们可以要求,除非全部选中才取消,否则就是全选
 * 3. 我们可以要求,部分选中都会引起总checkbox的变化,勾选的时候可以选择全部取消还是全部选中,模仿gmail
 */
function select_all_or_select_none(all_selector_view_element){
    var any_one_is_selected = false;
    $("#user-table").find("tbody").find("input[type=checkbox]").each(function(){
        var is_selected =$(this).prop("checked")
        if(is_selected && !any_one_is_selected){
            any_one_is_selected = true;
        }
    });
    $("#user-table").find("tbody").find("input[type=checkbox]").prop("checked",!any_one_is_selected);

    $(all_selector_view_element).prop("checked", !any_one_is_selected);

}