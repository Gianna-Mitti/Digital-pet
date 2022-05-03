const form = document.getElementById('form');
const inputs = document.querySelectorAll('#form input');

const expressions = {
    name: /^[a-zA-ZÀ-ÿ\s]{1,40}$/,
}

const fields = {
	name: false,
	fechaNac: false,
	sexo: false,
	especie: false,
	vacAplicadas: false,
}

const validateForm = (e) => {
	switch (e.target.id) {
		case "name":
			validateField(expressions.name, e.target, 'name');
		break;
		case "fechaNac":
			validateField(expressions.fechaNac, e.target, 'fechaNac');
		break;
		case "sexo":
			validateField(expressions.sexo, e.target, 'sexo');
		break;
		case "especie":
			validateField(expressions.especie, e.target, 'especie');
		break;
		case "vacAplicadas":
			validateField(expressions.vacAplicadas, e.target, 'vacAplicadas');
		break;
	}
}

const validateField = (expression, input, field) => {
	if(expression.test(input.value)){
		document.getElementById(`group-${field}`).classList.remove('form-group-wrong');
		document.getElementById(`group-${field}`).classList.add('form-group-right');
		document.querySelector(`#group-${field} i`).classList.add('fa-check-circle');
		document.querySelector(`#group-${field} i`).classList.remove('fa-times-circle');
		document.querySelector(`#group-${field} .form-input-error`).classList.remove('form-input-error-active');
		fields[field] = true;
	} else {
		document.getElementById(`group-${field}`).classList.add('form-group-wrong');
		document.getElementById(`group-${field}`).classList.remove('form-group-right');
		document.querySelector(`#group-${field} i`).classList.add('fa-times-circle');
		document.querySelector(`#group-${field} i`).classList.remove('fa-check-circle');
		document.querySelector(`#group-${field} .form-input-error`).classList.add('form-input-error-active');
		fields[field] = false;
	}
}


// const validatePasswordi = () => {
// 	const inputPassword = document.getElementById('password');
// 	const inputPasswordi = document.getElementById('passwordi');

// 	if(inputPassword.value !== inputPasswordi.value){
// 		document.getElementById(`group-passwordi`).classList.add('form-group-wrong');
// 		document.getElementById(`group-passwordi`).classList.remove('form-group-right');
// 		document.querySelector(`#group-passwordi i`).classList.add('fa-times-circle');
// 		document.querySelector(`#group-passwordi i`).classList.remove('fa-check-circle');
// 		document.querySelector(`#group-passwordi .form-input-error`).classList.add('form-input-error-active');
// 		fields['password'] = false;
// 	} else {
// 		document.getElementById(`group-passwordi`).classList.remove('form-group-wrong');
// 		document.getElementById(`group-passwordi`).classList.add('form-group-right');
// 		document.querySelector(`#group-passwordi i`).classList.remove('fa-times-circle');
// 		document.querySelector(`#group-passwordi i`).classList.add('fa-check-circle');
// 		document.querySelector(`#group-passwordi .form-input-error`).classList.remove('form-input-error-active');
// 		fields['password'] = true;
// 	}
// }
inputs.forEach((input) => {
	input.addEventListener('keyup', validateForm);
	input.addEventListener('blur', validateForm);
});

// form.addEventListener('submit', (e) => {
// 	const terms = document.getElementById('terms');
// 	if(!(fields.name && fields.surname && fields.mail && fields.password && terms.checked) ){
// 		e.preventDefault();
// 		document.getElementById('form-message').classList.add('form-message-active');
// 		setTimeout(() => {
// 			document.getElementById('form-message').classList.remove('form-message-active');
// 		}, 5000);
// 	} 
// });