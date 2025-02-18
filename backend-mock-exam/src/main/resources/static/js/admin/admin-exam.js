
$(document).ready(function() {
    $(document).on("click", ".fa-circle-xmark", function() {
        if (confirm('하위에 속한 문항들이 모두 삭제됩니다. \n주제를 삭제할까요?')) {

            const subject = $(this).data('subject');

            $.ajax({
                url: '/admin/subject/delete',
                method: 'post',
                data: { subject: subject },
                success: function(response) {
                    $('#ajax-container').html(response);
                },
                error: function(xhr, status, error) {
                    console.error('AJAX 요청 실패:', error);
                }
            })
        }
    })

    $(document).on("click", ".add-button", function() {
        if (confirm('주제를 추가할까요?')) {
            $.ajax({
                url: '/admin/subject/add',
                method: 'post',
                success: function(response) {
                    console.log(response);
                    $('#ajax-container').html(response);
                },
                error: function(xhr, status, error) {
                    console.error('AJAX 요청 실패:', error);
                }
            })
        }
    })
})