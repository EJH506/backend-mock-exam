$(document).ready(function() {
    // 오답노트에 문항 저장/저장해제
    $(".saveToggle").click(function() {

        let isSaved = $(this).find("i").hasClass('fa-solid');
        const questionId = $(this).data('question-id');
        const userId = $(this).data('user-id');



        const $this = $(this);

        $.ajax({
            url: '/incorrect-note/saveToggle',
            method: "POST",
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
});