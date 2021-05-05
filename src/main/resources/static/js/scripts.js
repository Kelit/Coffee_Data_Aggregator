$(document).ready(function () {
    if(window.location.pathname === "/products"){
        $.ajax(
            {
                type: 'GET',
                url: '/coffee/api/get-product',
                contentType: 'application/json',
                success: function (data) {
                    console.log(data)
                    for(const c in data){
                        $('#typeProduct').append('<tr>'+
                                                    '<th scope="row"><a href='+'product/'+data[c].productId+'>'+data[c].productId+'</a></th>'+
                                                    '<td>'+data[c].productName+'</td>'+
                                                    '<td>'+data[c].productPrice+'</td>'+
                                                    '<td>'+data[c].productStock+'</td>'+
                                                    '<td><img src='+data[c].productIcon+'' +
                            '                            width="210" height="250">'+'</td>'+
                                                    '<td>'+data[c].productDescription+'</td>'+
                                                +'</tr>')
                        }
                },
                error: function () {
                    console.log("Ошибка")
                }})
    }
});
$('#createUserBtn').click(function(){
    let newUser = $('#newUserName').val();
    let newUserPassword = $('#newUserNamePassword').val();
    let newUserEmail = $('#newUserNameEmail').val();
    let newUserPhone = $('#newUserNamePhone').val();
    let checkState = $("#newUserNameActive").is(":checked") ? "true" : "false";
    if(newUser == "" || newUserPassword == "")
        alert("Пожалуйста внимательно заполните поля пользователя");
    else {
        $.ajax({
            type: 'POST',
            url:  'coffee/api/create-user',
            data: JSON.stringify({username: newUser, password: newUserPassword, email:newUserEmail,
                                        phone:newUserPhone, active: checkState}),
            contentType: 'application/json',
            success: function (data) {
                document.getElementById("newUserName").value = "";
                document.getElementById("newUserNamePassword").value = "";
                document.getElementById("newUserNameEmail").value = "";
                document.getElementById("newUserNamePhone").value = "";
                document.location.reload();
                console.log('пользователь создан: ' + data);
            },
            error: function (data) {
                console.log('Не удалось создать пользователя: ' + data);
            }
        });
    }
});