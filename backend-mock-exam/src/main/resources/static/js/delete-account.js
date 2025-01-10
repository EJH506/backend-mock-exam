document.addEventListener('DOMContentLoaded', function() {

    document.forms['deleteAccountForm'].addEventListener('submit', function(evt) {
        evt.preventDefault();

        if (confirm("탈퇴한 계정은 복구할 수 없습니다. \n정말 탈퇴하시겠습니까?")) {
            this.submit();
        } else {
            return false;
        }
    })
});