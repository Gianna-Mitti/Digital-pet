const form = document.getElementById('form');
const inputs = document.querySelectorAll('#form input');

const expressions = {
	name: /^[a-zA-ZÀ-ÿ\s]{1,40}$/,
	surname: /^[\w'\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{1,40}$/,
	phone:/^\d{0,14}$/,
	mail: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
	password: /^.{6,12}$/,
}

const fields = {
	name: false,
	surname: false,
	phone: false,
	mail: false,
	password: false,
}

const validateForm = (e) => {
	switch (e.target.id) {
		case "name":
			validateField(expressions.name, e.target, 'name');
		break;
		case "surname":
			validateField(expressions.surname, e.target, 'surname');
		break;
		case "phone":
			validateField(expressions.phone, e.target, 'phone');
		break;
		case "mail":
			validateField(expressions.mail, e.target, 'mail');
		break;
		case "password":
			validateField(expressions.password, e.target, 'password');
			validatePasswordi();
		break;
		case "passwordi":
			validatePasswordi();
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

const validatePasswordi = () => {
	const inputPassword = document.getElementById('password');
	const inputPasswordi = document.getElementById('passwordi');

	if(inputPassword.value !== inputPasswordi.value){
		document.getElementById(`group-passwordi`).classList.add('form-group-wrong');
		document.getElementById(`group-passwordi`).classList.remove('form-group-right');
		document.querySelector(`#group-passwordi i`).classList.add('fa-times-circle');
		document.querySelector(`#group-passwordi i`).classList.remove('fa-check-circle');
		document.querySelector(`#group-passwordi .form-input-error`).classList.add('form-input-error-active');
		fields['password'] = false;
	} else {
		document.getElementById(`group-passwordi`).classList.remove('form-group-wrong');
		document.getElementById(`group-passwordi`).classList.add('form-group-right');
		document.querySelector(`#group-passwordi i`).classList.remove('fa-times-circle');
		document.querySelector(`#group-passwordi i`).classList.add('fa-check-circle');
		document.querySelector(`#group-passwordi .form-input-error`).classList.remove('form-input-error-active');
		fields['password'] = true;
	}
}



inputs.forEach((input) => {
	input.addEventListener('keyup', validateForm);
	input.addEventListener('blur', validateForm);
});


