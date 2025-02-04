
$(document).ready(function() {
    // 성별은 하나만 선택 가능
    const $checkboxes = $('.select-gender').find('input:checkbox');
    $checkboxes.on("change", function() {
        $checkboxes.not(this).prop("checked", false);
    })
})