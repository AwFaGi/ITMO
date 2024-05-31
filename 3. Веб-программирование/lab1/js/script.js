/* check y input */
const y_value_input = document.querySelector("#y_value");
const submit_button = document.querySelector("div.form_wrapper > input[type=submit]");
const message_place = document.querySelector("#y_message");
y_value_input.addEventListener('input', validateY);

function validateY(){
    let text = y_value_input.value;
    let number_text = Number(text);
    if ( text==="" || isNaN(number_text) || !isFinite(number_text) || number_text < -3 || number_text > 5){
        submit_button.disabled = true;
        message_place.innerHTML = "<p>Неверное значение<br>Требуется от -3 до 5</p>";
    } else {
        submit_button.disabled = false;
        message_place.innerHTML = "";
    }
}

validateY();
/* --- end --- */

/* turn checkbox into pseudo-radiobutton */
const checkboxes = document.querySelectorAll("div.checkbox_wrapper > input[type=checkbox]");
const checkboxes_len = checkboxes.length;
for (let i = 0; i < checkboxes_len; i++) {
    const element = checkboxes[i];
    element.addEventListener('change', update_checkboxes);
}

function update_checkboxes(event){
    for (let i = 0; i < checkboxes_len; i++){
        checkboxes[i].checked = false;
    }
    event.target.checked = true;
}
/* --- end --- */

/* select first radiobutton */
document.querySelector("div.radio_wrapper > input[type=radio]").checked = true;
/* --- end --- */

/* select first checkbox */
document.querySelector("div.checkbox_wrapper > input[type=checkbox]").checked = true;
/* --- end --- */