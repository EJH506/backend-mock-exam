
$(document).ready(function () {

    // '메모 선택' 버튼 누르면
    $('.select-start').click(function () {
        // 버튼 변경
        $(this).parent().css('display', 'none');
        $(this).parent().next().css('display', 'flex');

        // 리스트 변경 (선택가능모드)
        $('#NormalList').css('display', 'none');
        $('#DeleteForm').css('display', 'initial');
    })

    // '선택 취소' 버튼 누르면
    $('.select-cancel').click(function () {
        // 버튼 변경
        $(this).parent().css('display', 'none');
        $(this).parent().prev().css('display', 'flex');

        // 리스트 변경 (메모읽기모드)
        $('#NormalList').css('display', 'flex');
        $('#DeleteForm input[type="checkbox"]').prop('checked', false);

        // change 이벤트 수동 트리거
        // prop('checked', ...)로 값을 변경한건 change 이벤트 발생X
        $('#DeleteForm input[type="checkbox"]').trigger('change');
        
        $('#DeleteForm').css('display', 'none');
    })

    // DeleteForm 안의 체크박스 상태가 변경될 때마다
    $('#DeleteForm input[type="checkbox"]').change(function() {

        // 하나라도 선택되어있으면 버튼 변경 (전체 선택 > 선택 삭제)
        if ($('#DeleteForm input[type="checkbox"]:checked').length > 0) {
            $('.select-all').css('display', 'none');
            $('.select-submit').css('display', 'flex');

        // 전혀 선택되어 있지 않으면 버튼 변경 (선택 삭제 > 전체 선택)
        } else {
            $('.select-all').css('display', 'flex');
            $('.select-submit').css('display', 'none');
        }
    });

    // 전체 선택 누르면
    $('.select-all').click(function () {
        // checkbox 전체선택
        $('#DeleteForm input[type="checkbox"]').prop('checked', true);

        // change 이벤트 수동 트리거
        $('#DeleteForm input[type="checkbox"]').trigger('change');
    })
    
    // 선택 삭제 누르면 폼 전송
    $('.select-submit').click(function() {
        if (confirm('삭제한 메모는 복구할 수 없습니다. \n 정말로 삭제할까요?')) {
            $('#DeleteForm').submit();
        }
    })
})