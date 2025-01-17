$(document).ready(function () {
    // 전체, 정답만, 오답만 옵션에 따른 출력

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