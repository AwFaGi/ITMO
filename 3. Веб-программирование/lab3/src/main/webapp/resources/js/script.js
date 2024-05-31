const submit_button = document.querySelector("div.form_wrapper > input[type=submit]");

const canvas = document.getElementById("big_canvas");
const context = canvas.getContext("2d");
const canvas_scale = 30;
context.translate(canvas.width/2, canvas.height/2);
context.scale(1,-1);

/* check y input */
const y_value_input = document.getElementById("thisIsForm:y_value");
const y_message_place = document.querySelector("#y_message");
y_value_input.addEventListener('input', () => validateY(true));

function validateY(recursion){
    let text = y_value_input.value;
    let number_text = Number(text);
    if ( text==="" || isNaN(number_text) || !isFinite(number_text) || number_text < -5 || number_text > 5){
        submit_button.disabled = true;
        y_message_place.innerHTML = "<p>Неверное значение<br>Требуется от -5 до 5</p>";
    } else {
        submit_button.disabled = false;
        y_message_place.innerHTML = "";
    }
    if (recursion){
        validateR(false);
    }
}

validateY(false);
/* --- end --- */

/* check r input */
const r_value_input = document.getElementById("thisIsForm:r_value");
const r_message_place = document.querySelector("#r_message");
r_value_input.addEventListener('input', () => validateR(true));

function validateR(recursion){
    let text = r_value_input.value;
    let number_text = Number(text);
    let mysmth;
    if ( text==="" || isNaN(number_text) || !isFinite(number_text) || number_text < 1 || number_text > 4){
        submit_button.disabled = true;
        r_message_place.innerHTML = "<p>Неверное значение<br>Требуется от 1 до 4</p>";
        mysmth = false;
    } else {
        submit_button.disabled = false;
        r_message_place.innerHTML = "";
        mysmth = true;
        redraw_canvas(number_text);
    }
    if (recursion){
        validateY(false);
    }
    return mysmth;
}

validateR(false);
/* --- end --- */

function updateX(){
    const field = document.getElementById("thisIsForm:theBestX");
    const value = document.getElementById("thisIsForm:x_value").selectedIndex - 3;
    if (!field || !value){
        field.value = 0;
        return false;
    }
    field.value = value;
}

/* --- draw onto canvas --- */

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

    document.getElementById("thisIsForm:theBestX").value = x.toFixed(2);
    document.getElementById("thisIsForm:y_value").value = y.toFixed(2);
    submit_button.click();
    document.getElementById("thisIsForm:x_value").selectedIndex = 0;
    document.getElementById("thisIsForm:theBestX").value = -3;

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
function redraw_points(r_value){
    context.save();
    let table = document.getElementById("maintable");
    for (let row of table.rows)
    {
        try{

            if(row.cells[0].innerText === "" || row.cells[1].innerText === ""
                || row.cells[2].innerText==="" || row.cells[5].innerText === ""){
                continue;
            }

            let point_x = Number(row.cells[0].innerText);
            let point_y = Number(row.cells[1].innerText);
            let point_r = Number(row.cells[2].innerText);

            if(isNaN(point_x) || isNaN(point_y)){
                continue;
            }

            if (point_r === Number(r_value)){
                if (row.cells[5].innerText === "HIT"){
                    context.fillStyle = "#00ff00";
                }else {
                    context.fillStyle = "#ff0000";
                }
            }else {
                context.fillStyle = "#ffff00";
            }

            context.beginPath(); //Start path
            context.arc(point_x * canvas_scale, point_y * canvas_scale,
                4, 0, Math.PI * 2
            );
            context.fill();

        } catch (e){
            console.log(e);
        }

    }

    context.restore();
}
function redraw_canvas(r_value){

    context.clearRect(-canvas.width/2, -canvas.height/2, canvas.width, canvas.height);

    context.fillStyle = "rgb(51, 153, 255)";

    // 1 quarter
    context.beginPath();
    context.moveTo(0, Math.floor(r_value/2 * canvas_scale));
    context.lineTo(Math.floor(r_value * canvas_scale), Math.floor(r_value/2 * canvas_scale));
    context.lineTo(Math.floor(r_value * canvas_scale), 0);
    context.lineTo(0, 0);
    context.closePath();
    context.fill();


    // 2 quarter
    context.beginPath();
    context.arc(0,0, Math.floor(r_value * canvas_scale), Math.PI/2, Math.PI);
    context.moveTo(0, 0);
    context.lineTo(0, Math.floor(r_value * canvas_scale));
    context.lineTo(-Math.floor(r_value * canvas_scale), 0);
    context.closePath();
    context.fill();

    // 3 quarter


    // 4 quarter
    context.beginPath();
    context.moveTo(0, - Math.floor(r_value * canvas_scale));
    context.lineTo(Math.floor(r_value * canvas_scale), 0);
    context.lineTo(0,0);
    context.closePath();
    context.fill();

    // axes
    redraw_axes();

    // labels
    redraw_labels(r_value);

    //points
    redraw_points(r_value);
}

function mybigupdater(){
    const r = document.getElementById("thisIsForm:r_value").value;
    if(!validateR(false)){
        return false;
    }
    redraw_canvas(r);
}

setInterval(mybigupdater, 2);
/* --- end --- */