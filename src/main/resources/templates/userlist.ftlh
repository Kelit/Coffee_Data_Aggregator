<#import "parts/common.ftlh" as c>
<@c.page>
<div class="row">
    <div class="col-sm-4">
        <div class="col-sm-5">
            <div class="form-group">
                <label for="newUserName">Логин</label>
                <input type="text" class="form-control" id="newUserName"  placeholder="Логин">
                <label for="newUserNamePassword">Пароль</label>
                <input type="password" class="form-control" id="newUserNamePassword"  placeholder="Пароль">
                <label for="newUserNameEmail">Email</label>
                <input type="email" class="form-control" id="newUserNameEmail"  placeholder="email">
                <label for="newUserNamePhone">Телефон</label>
                <input type="tel" class="form-control" id="newUserNamePhone"  placeholder="Пароль">
            </div>
            <div class="form-check">
                <input type="checkbox" class="form-check-input" id="newUserNameActive">
                <label class="form-check-label" for="newUserNameActive">Запись пользователя активна ?<label id="valueRecord"></label></label>
            </div>
            <button class="btn btn-sm btn-primary" id="createUserBtn" type="submit">Создать пользователя</button>
        </div>
    </div>
    <div class="col-sm-8 container-fluid">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Имя пользователя</th>
                <th scope="col">Email</th>
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
                    <td>${user.email}</td>
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
    </div>
</div>
</@c.page>