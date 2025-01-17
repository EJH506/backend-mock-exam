$(document).ready(function() {
    // 오답노트에 문항 저장/저장해제
    $(".saveToggle").click(function() {

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
                    alert("오류가 발생했습니다. 다시 시도해주세요.");
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
                // 저장된 문항에 별표
                $.each(selectedWrongQuestions, function(index, item) {
                    const findToggle = $(this).closest('.answersText').prev().find('.saveToggle');
                    findToggle.html('<i class="fa-solid fa-star"></i>');
                });
            },
            error: function(xhr, status, error) {
                console.error('AJAX 요청 실패:', error);
            }
        });
    });
});