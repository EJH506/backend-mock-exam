
document.addEventListener('DOMContentLoaded', function() {

    const currentYear = new Date().getFullYear();
    const birthData = $('#birthYear').data('birth-year');

    for (let i=0; i<100; i++) {
        const $option = document.createElement('option');
        $option.value = currentYear - i;
        $option.textContent = currentYear - i + '년';
        $('#birthYear').append($option);
        if ($option.value == birthData) {
            $option.selected = true;
        }
    }

});