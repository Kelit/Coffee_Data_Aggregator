<#import "parts/common.ftlh" as c>
<@c.page>
<div class="row">
    <div class="col-sm-3">
        <div class="col-sm-3">
            <div class="form-group">
                <label for="newUserName">Логин</label>
                <input type="text" class="form-control" id="newUserName"  placeholder="Логин">
                <label for="newUserNamePassword">Пароль</label>
                <input type="text" class="form-control" id="newUserNamePassword"  placeholder="Пароль">
            </div>
            <div class="form-check">
                <input type="checkbox" class="form-check-input" id="newUserNameActive">
                <label class="form-check-label" for="newUserNameActive">Запись пользователя активна ?<label id="valueRecord"></label></label>
            </div>
            <button class="btn btn-sm btn-primary" id="createUserBtn" type="submit">Создать пользователя</button>
        </div>
    </div>
    <div class="col-sm-9 container-fluid">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Имя пользователя</th>
                <th scope="col">Роль</th>
                <th scope="col">Активность</th>
                <th scope="col">Редактировать</th>
                <th scope="col">Удалить</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <th score="row">${user.username}</th>
                    <td>
                        <#list user.roles as role>
                            ${role.role_name}<#sep>,
                        </#list>
                    </td>
                    <td><#if user.active>
                            <i class="fas fa-check"></i>
                        <#else>
                            <i class="fas fa-times"></i>
                        </#if>
                    </td>
                    <td><a href="/userList/${user.id}"><i class="fas fa-user-edit"></i></a> </td>
                    <td><button type="button" class="btn btn-sm btn-primary ">
                            Удалить</button></tr></td>
                </tr>
            </#list>
            </tbody>
        </table>
        <script>
            $('#createUserBtn').click(function(){
                let newUser = $('#newUserName').val();
                let newUserPassword = $('#newUserNamePassword').val();
                let checkState = $("#newUserNameActive").is(":checked") ? "true" : "false";
                if(newUser == "" || newUserPassword == "")
                    alert("Пожалуйста внимательно заполните поля пользователя");
                else {
                    $.ajax({
                        type: 'POST',
                        url:  'calendarui/api/create-user',
                        data: JSON.stringify({username: newUser, password: newUserPassword, active: checkState}),
                        contentType: 'application/json',
                        success: function (data) {
                            document.getElementById("newUserName").value = "";
                            document.getElementById("newUserNamePassword").value = "";
                            document.location.reload();
                            console.log('пользователь создан: ' + data);
                        },
                        error: function (data) {
                            console.log('Не удалось создать пользователя: ' + data);
                        }
                    });
                }
            });
        </script>
    </div>

</div>

</@c.page>