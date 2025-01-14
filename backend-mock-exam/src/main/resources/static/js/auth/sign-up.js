$(document).ready(function() {

    // 아이디 중복 여부 비동기
    $('#accountId').on('blur', function() {
        const accountId = $(this).val();

        $.ajax({
            url: '/auth/check-id',
            type: 'POST',
            data: { accountId: accountId },
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
            success: function(response) {
                if (response) {
                    $('#accountIdError').text("이미 존재하는 아이디 입니다.");
                } else {
                    $('#accountIdError').text("");
                }
            }
        });
    });
});