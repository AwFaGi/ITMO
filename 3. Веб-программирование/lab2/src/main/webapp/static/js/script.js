/* check y input */
const y_value_input = document.querySelector("#y_value");
const submit_button = document.querySelector("div.form_wrapper > input[type=submit]");
const message_place = document.querySelector("#y_message");
y_value_input.addEventListener('input', validateY);

function validateY(){
    let text = y_value_input.value;
    let number_text = Number(text);
    if ( text==="" || isNaN(number_text) || !isFinite(number_text) || number_text < -3 || number_text > 3){
        submit_button.disabled = true;
        message_place.innerHTML = "<p>Неверное значение<br>Требуется от -3 до 3</p>";
    } else {
        submit_button.disabled = false;
        message_place.innerHTML = "";
    }
}

validateY();
/* --- end --- */

/* turn R checkbox into pseudo-radiobutton */
const checkboxes_r = document.querySelectorAll("#form_right > div.checkbox_wrapper > input[type=checkbox]");
const checkboxes_r_len = checkboxes_r.length;
for (let i = 0; i < checkboxes_r_len; i++) {
    const element = checkboxes_r[i];
    element.addEventListener('change', update_checkboxes_r);
}

function update_checkboxes_r(event){
    for (let i = 0; i < checkboxes_r_len; i++){
        checkboxes_r[i].checked = false;
    }
    event.target.checked = true;
    redraw_canvas(event.target.getAttribute("value"));
}
/* --- end --- */

/* turn X checkbox into pseudo-radiobutton */
const checkboxes_x = document.querySelectorAll("#form_left > div.checkbox_wrapper > input[type=checkbox]");
const checkboxes_x_len = checkboxes_x.length;
for (let i = 0; i < checkboxes_x_len; i++) {
    const element = checkboxes_x[i];
    element.addEventListener('change', update_checkboxes_x);
}

function update_checkboxes_x(event){
    for (let i = 0; i < checkboxes_x_len; i++){
        checkboxes_x[i].checked = false;
    }
    event.target.checked = true;
}
/* --- end --- */

/* select first x checkbox */
document.querySelector("#form_left > div.checkbox_wrapper > input[type=checkbox]").checked = true;
/* --- end --- */

/* select first r checkbox */
document.querySelector("#form_right > div.checkbox_wrapper > input[type=checkbox]").checked = true;
/* --- end --- */

/* --- draw onto canvas --- */
const canvas = document.getElementById("big_canvas");
const context = canvas.getContext("2d");
const canvas_scale = 30;
context.translate(canvas.width/2, canvas.height/2);
context.scale(1,-1);
redraw_canvas(1);

canvas.addEventListener("click", function(e) {
    getCursorPosition(canvas, e)
});

function getCursorPosition(canvas, event) {
    const rect = canvas.getBoundingClientRect();

    // calculate click place
    let x = (event.clientX - rect.left) - canvas.width/2;
    let y = canvas.height/2 - (event.clientY - rect.top);

    // normalise click place
    x = x / canvas_scale;
    y = y / canvas_scale;

    const r = document.querySelector("#form_right > div.checkbox_wrapper > input[type=checkbox]:checked")
        .getAttribute("value");

    let form = document.querySelector("#place_for_form > form");
    form.querySelector("#form_left input[type=checkbox]:checked").value = x.toFixed(2);
    form.querySelector("#form_center #y_value").value = y.toFixed(2);
    form.submit();

}

function redraw_axes(){
    context.fillStyle = "rgb(1, 1, 1)"

    // y - axe
    context.beginPath();
    context.moveTo(0, -canvas.height/2 + 1);
    context.lineTo(0, canvas.height/2 - 1);
    context.closePath();
    context.stroke();

    // x - axe
    context.beginPath();
    context.moveTo(-canvas.width/2 + 1, 0);
    context.lineTo(canvas.width/2 - 1, 0);
    context.closePath();
    context.stroke();
}
function redraw_labels(r_value){
    context.fillStyle = "rgb(1, 1, 1)"

    // left
    context.beginPath();
    context.moveTo(-Math.floor(r_value * canvas_scale), -5);
    context.lineTo(-Math.floor(r_value * canvas_scale), 5);
    context.stroke();

    // half-left
    context.beginPath();
    context.moveTo(-Math.floor(r_value/2 * canvas_scale), -5);
    context.lineTo(-Math.floor(r_value/2 * canvas_scale), 5);
    context.stroke();

    // right
    context.beginPath();
    context.moveTo(Math.floor(r_value * canvas_scale), -5);
    context.lineTo(Math.floor(r_value * canvas_scale), 5);
    context.stroke();

    // half-right
    context.beginPath();
    context.moveTo(Math.floor(r_value/2 * canvas_scale), -5);
    context.lineTo(Math.floor(r_value/2 * canvas_scale), 5);
    context.stroke();

    // top
    context.beginPath();
    context.moveTo(-5, Math.floor(r_value * canvas_scale));
    context.lineTo(5, Math.floor(r_value * canvas_scale));
    context.stroke();

    // half-top
    context.beginPath();
    context.moveTo(-5, Math.floor(r_value/2 * canvas_scale));
    context.lineTo(5, Math.floor(r_value/2 * canvas_scale));
    context.stroke();

    // bottom
    context.beginPath();
    context.moveTo(-5, -Math.floor(r_value * canvas_scale));
    context.lineTo(5, -Math.floor(r_value * canvas_scale));
    context.stroke();

    // half-bottom
    context.beginPath();
    context.moveTo(-5, -Math.floor(r_value/2 * canvas_scale));
    context.lineTo(5, -Math.floor(r_value/2 * canvas_scale));
    context.stroke();
}
function redraw_points(){
    context.save();
    for (let point of items){
        switch (point[2]){
            case 1:
                context.fillStyle = "#ff0000";
                break;
            case 1.5:
                context.fillStyle = "#ff7f00";
                break;
            case 2:
                context.fillStyle = "#ffff00";
                break;
            case 2.5:
                context.fillStyle = "#00ff00";
                break;
            case 3:
                context.fillStyle = "#0000ff";
                break;
            default:
                context.fillStyle = "#8f00ff";
        }
        context.beginPath(); //Start path
        context.arc(point[0] * canvas_scale, point[1] * canvas_scale,
            4, 0, Math.PI * 2
        );
        context.fill();
    }
    context.restore();
}
function redraw_canvas(r_value){

    context.clearRect(-canvas.width/2, -canvas.height/2, canvas.width, canvas.height);

    context.fillStyle = "rgb(51, 153, 255)";

    // 2 quarter
    context.beginPath();
    context.moveTo(0, Math.floor(r_value/2 * canvas_scale));
    context.lineTo(-Math.floor(r_value * canvas_scale), 0);
    context.lineTo(0, 0);
    context.closePath();
    context.fill();

    // 3 quarter
    context.beginPath();
    context.arc(0,0, Math.floor(r_value * canvas_scale), Math.PI, Math.PI*3/2);
    context.moveTo(0,0);
    context.lineTo(-Math.floor(r_value * canvas_scale), 0);
    context.lineTo(0, -Math.floor(r_value * canvas_scale));
    context.closePath();
    context.fill();

    // 4 quarter
    context.beginPath();
    context.moveTo(0, - Math.floor(r_value * canvas_scale));
    context.lineTo(Math.floor(r_value * canvas_scale), - Math.floor(r_value * canvas_scale));
    context.lineTo(Math.floor(r_value * canvas_scale), 0);
    context.lineTo(0,0);
    context.closePath();
    context.fill();

    // axes
    redraw_axes();

    // labels
    redraw_labels(r_value);

    //points
    redraw_points();
}
/* --- end --- */