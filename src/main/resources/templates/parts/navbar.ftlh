<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">
        <img src="/static/images/coffee_logo.png" alt="" width="30" height="24" class="d-inline-block align-top">
        Coffee manager
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto" >
                <a href="/orders"><button type="button" class="btn btn-info">Заказы</button></a>
        </ul>
        <ul class="navbar-nav mr-auto" >
                <a href="/products"><button type="button" class="btn btn-info" id="refreshesData" >Товары</button></a>
        </ul>
        <ul class="navbar-nav mr-auto" >
                <a href="#"><button type="button" class="btn btn-info">Статистика</button></a>
        </ul>
        <ul class="navbar-nav mr-auto" >
                <a href="/userlist"><button type="button" class="btn btn-info">Пользователи</button></a>
        </ul>
        <ul class="d-flex justify-content-end navbar-nav">
            <li class="nav-item">
                <span class="mr-5">Login</span>
            </li>
        </ul>
        <ul class="d-flex justify-content-end navbar-nav">
            <li class="nav-item">
                <a href="/logout"><button class="btn btn-outline-danger my-2 my-sm-1"
                                          type="button">Выйти</button></a>
            </li>
        </ul>
    </div>
</nav>
