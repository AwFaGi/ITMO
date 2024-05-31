const tzInput = document.getElementById("bigTZ");

function getTimezone() {
    let offset = new Date().getTimezoneOffset();
    tzInput.value = -(offset / 60);
    tzInput.dispatchEvent(new Event("change"));
}

getTimezone();