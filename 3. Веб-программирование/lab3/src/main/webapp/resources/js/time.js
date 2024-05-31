const bigAndWonderfulTime = document.getElementById("interactive_time");
function update_bigAndWonderfulTime(){
    let date = new Date();
    bigAndWonderfulTime.innerText = date.toLocaleString("ru-RU", {
        hour12: false
    });
}

update_bigAndWonderfulTime();
setInterval(update_bigAndWonderfulTime, 12*1000);