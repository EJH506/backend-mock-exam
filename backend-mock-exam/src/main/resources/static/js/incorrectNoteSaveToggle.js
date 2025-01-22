$(document).ready(function() {
    // 오답노트에 문항 저장/저장해제
    $(document).on("click", ".saveToggle", function() {
        console.log("클릭");
        let isSaved = $(this).find("i").hasClass('fa-solid');
        const questionId = $(this).data('question-id');
        const userId = $(this).data('user-id');

        const $this = $(this);

        $.ajax({
            url: '/incorrect-note/saveToggle',
            method: 'post',
            data: {
                userId: userId,
                questionId: questionId,
                isSaved: isSaved
            },
            success: function(response) {
                if (response.success) {

                    if (!isSaved) {
                        $this.html('<i class="fa-solid fa-star"></i>');
                    } else {
                        $this.html('<i class="fa-regular fa-star"></i>');
                    }

                } else {
                    if (confirm("로그인이 필요한 서비스 입니다. \n로그인 하시겠습니까?")) {
                        location.href="/auth/sign-in";
                    }
                }
            },
            error: function(xhr, status, error) {
                console.error('AJAX 요청 실패:', error);
            }
        });
    })

    // 오답 전체 오답노트에 저장
    $('.saveIncorrectAll').click(function() {

        const selectedWrongQuestions = $('.wrong');
        const userId = $(selectedWrongQuestions).eq(0).closest('.answersText').prev().find('.saveToggle').data('user-id');
        let wrongQuestions = [];

        $.each(selectedWrongQuestions, function(index, item) {
            const findToggle = $(this).closest('.answersText').prev().find('.saveToggle');
            const questionId = $(findToggle).data('question-id');
            const isSaved = $(findToggle).find('i').hasClass('fa-solid');
            wrongQuestions.push({ questionId, isSaved });
        });

        $.ajax({
            url: '/incorrect-note/saveIncorrectAll',
            method: 'post',
            contentType: 'application/json',
            data: JSON.stringify ({
                userId: userId,
                wrongQuestions: wrongQuestions
            }),
            success: function(response) {
                if (response.savedQuestionId != null) {
                    // 저장된 문항에 별표
                    $.each(selectedWrongQuestions, function(index, item) {
                        const findToggle = $(this).closest('.answersText').prev().find('.saveToggle');
                        findToggle.html('<i class="fa-solid fa-star"></i>');
                    });
                } else {
                    if (confirm("로그인이 필요한 서비스 입니다. \n로그인 하시겠습니까?")) {
                        location.href="/auth/sign-in";
                    }
                }
            },
            error: function(xhr, status, error) {
                console.error('AJAX 요청 실패:', error);
            }
        });
    });
});