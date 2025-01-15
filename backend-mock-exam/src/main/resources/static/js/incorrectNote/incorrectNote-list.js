
$(document).ready(function() {
    // 제목눌러 상세보기
    $(".title").click(function() {
        $(".content").css("display", "none");
        $(".content").find("li").css("color", "initial");
        $(this).next().css("display", "initial");
    });

    // 정답보기 눌러 정답에 스타일
    $(".viewAnswer").click(function() {
        $(this).prev().find(".true").css("color", "red");
    })
});
