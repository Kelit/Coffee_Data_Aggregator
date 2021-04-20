<#import "parts/common.ftlh" as c>
<@c.page>
    <main class="container-fluid">
        <div class="d-flex">
            <div class="mr-auto p-2">
                <ul class="nav">
                    <li class="nav-item">
                        <button class="btn btn-outline-secondary btn-sm mr-3"
                                id="refreshesData"
                                type="button">Обновить</button>
                    </li>
                </ul>
            </div>
            <div class="p-2">
                <ul class="nav justify-content-right">
                    <li class="nav-item">
                        <input class="form-control"
                               id="typeDayName" type="text" placeholder="Введите номер заказа"
                               aria-label="Поиск">
                    </li>
                    <li class="nav-item">
                        <button class="btn btn-outline-success ml-3"
                                id="searchOrder"
                                type="button">Поиск</button>
                    </li>
                    <li class="nav-item">
                        <button class="btn btn-outline-danger ml-3"
                                id="editOrder"
                                type="button">Редактировать</button>
                    </li>
                </ul>
            </div>
        </div>
        <div class="calendar">
            <table class="table table-borderless tableProduct">
                <thead>
                <tr id="addProduct"></tr>
                </thead>
                <tbody id="typeProduct">
                </tbody>
            </table>
        </div>
    </main>

</@c.page>