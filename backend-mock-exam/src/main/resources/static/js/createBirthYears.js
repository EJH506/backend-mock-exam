
document.addEventListener('DOMContentLoaded', function() {

    const $birthYearSelectBox = document.querySelector('#birthYear');

    const currentYear = new Date().getFullYear();

    for (let i=0; i<100; i++) {
        const $option = document.createElement('option');
        $option.value = currentYear - i;
        $option.textContent = currentYear - i + '년';
        $birthYearSelectBox.append($option);
    }

});