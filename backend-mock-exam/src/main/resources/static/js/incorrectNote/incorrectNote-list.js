
$(document).ready(function() {
    // 제목눌러 상세보기
    $(".title").click(function() {
        $(".systemInput_md").removeClass("on");
        $(this).closest(".systemInput_md").addClass("on");
        $(".content").find("li").removeClass("correct");
        // $(".content").css("display", "none");
        // $(".content").find("li").removeClass("correct");
        // $(this).next().css("display", "initial");
    });

    // 정답보기 눌러 정답에 스타일
    $(".viewAnswer").click(function() {
        $(this).prev().find(".true").addClass("correct");
    })
});
