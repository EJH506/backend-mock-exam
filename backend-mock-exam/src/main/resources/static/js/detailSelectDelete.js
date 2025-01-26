
$(document).ready(function() {
    $('.btn-delete').click(function () {
        if (confirm('정말로 삭제할까요?')) {
            document.forms['deleteForm'].submit();
        }
    });
});