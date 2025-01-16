function signinConfirm() {
    if(confirm("로그인이 필요한 서비스입니다. \n로그인 하시겠습니까?")) {
        location.href="/auth/sign-in";
    }
}

$(document).ready(function () {

    $('.resultOption').click(function () {
        const option = $(this).attr('id');

        $.ajax({
            url: '/exam/result',
            method: 'GET',
            data: { option: option },
            success: function (response) {
                $('#viewQuestionsArea').html(response);
            },
            error: function(xhr, status, error) {
                console.error('AJAX 요청 실패:', error);
            }
        });
    });
});