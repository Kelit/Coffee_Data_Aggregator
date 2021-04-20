$('#refreshesData').click(
    function (){
        $.ajax(
            {
                type: 'GET',
                url: '/coffee/api/get-coffee',
                contentType: 'application/json',
                success: function (data) {
                    console.log(data)
                    $('#addProduct').append('<th scope="col">'+data+'</th>')
                },
                error: function () {
                    console.log("Ошибка")
                }})
    }
);