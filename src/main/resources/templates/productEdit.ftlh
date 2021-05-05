<#import "parts/common.ftlh" as c>
<@c.page>
    <form action="" method="post">
        <div class="row">
                <div class="form-group-inline">
                    <label for="productName">Наименование</label>
                    <input type="text" class="form-control" id="productName" name="productname" value="${product_info.productName}"
                           placeholder="Наименование">
                    <input type="hidden" value="${product_info.productId}" name="userId">
                </div>
                <div class="form-group-inline">
                    <label for="productPrice">Цена</label>
                    <input type="text" class="form-control" id="productPrice" name="productprice" value="${product_info.productPrice}"
                           placeholder="Цена">
                </div>
                <div class="form-group-inline">
                    <label for="productStock">Вналичии</label>
                    <input type="text" class="form-control" id="productStock" name="productstock" value="${product_info.productStock}"
                           placeholder="Кол-во">
                </div>
                <div class="form-group-inline">
                    <label for="productIcon">Изображение</label>
                    <input type="text" class="form-control" id="productIcon" name="producticon" value="${product_info.productIcon}"
                           placeholder="Ссылка">
                </div>
                <div class="form-group-inline">
                    <label for="productDesc">Описание</label>
                    <input type="text" class="form-control" id="productDesc" name="productdesc" value="${product_info.productDescription}"
                           placeholder="Описание">
                </div>
<#--                <div class="form-check form-check-inline">-->
<#--                    <label>Состояние учетной записи:</label>-->
<#--                </div>-->
<#--                <div class="form-check form-check-inline">-->
<#--                    <input class="form-check-input" type="radio" name="active" id="activeRadiosTrue" value="true"-->
<#--                           <#if user.active>checked<#else>unchecked</#if>-->
<#--                    <label class="form-check-label" for="activeRadiosTrue">-->
<#--                        Активна-->
<#--                    </label>-->
<#--                </div>-->
<#--                <div class="form-check form-check-inline">-->
<#--                    <input class="form-check-input" type="radio" name="active" id="activeRadiosFalse" value="false"-->
<#--                           <#if user.active>unchecked<#else>checked</#if>>-->
<#--                    <label class="form-check-label" for="activeRadiosFalse">-->
<#--                        Не активна-->
<#--                    </label>-->
                </div>
                <button type="submit" class="btn btn-primary">Редактировать</button>
    </form>
</@c.page>