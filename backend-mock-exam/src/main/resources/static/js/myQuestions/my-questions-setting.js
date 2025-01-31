
$(document).ready(function() {
    $(document).on("click", ".fa-circle-xmark", function() {
        if (confirm('하위에 속한 문항들이 모두 삭제됩니다. \n난이도를 삭제할까요?')) {

            const level = $(this).data('level');

            $.ajax({
                url: '/my-questions/setting/delete',
                method: 'post',
                data: { level: level },
                success: function(response) {
                    $('#ajax-container').html(response);
                },
                error: function(xhr, status, error) {
                    console.error('AJAX 요청 실패:', error);
                }
            })
        }
    })

    $(document).on("click", ".add-level", function() {
        if (confirm('난이도를 추가할까요?')) {
            $.ajax({
                url: '/my-questions/setting/add',
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