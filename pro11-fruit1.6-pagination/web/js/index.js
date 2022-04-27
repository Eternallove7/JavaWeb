function delFruit(fid){
    if (confirm('是否确认删除？')){
        wondow.location.href='del.do?fid='+fid;
    }
}

function page(PageNo){
    window.location.href='index?pageNo='+PageNo;
}