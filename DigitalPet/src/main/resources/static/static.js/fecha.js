const fechaNacimiento = document.getElementById("fechaNacimiento");
const edad = document.getElementById("edad");

const calcularEdad = (fechaNacimiento) => {
    const fechaActual = new Date();
    const anoActual = parseInt(fechaActual.getFullYear());
    const mesActual = parseInt(fechaActual.getMonth()) + 1;
    const diaActual = parseInt(fechaActual.getDate());

    // 2016-07-11
    const anoNacimiento = parseInt(String(fechaNacimiento).substring(0, 4));
    const mesNacimiento = parseInt(String(fechaNacimiento).substring(5, 7));
    const diaNacimiento = parseInt(String(fechaNacimiento).substring(8, 10));

    let edad = anoActual - anoNacimiento;
    if (mesActual < mesNacimiento) {
        edad--;
    } else if (mesActual === mesNacimiento) {
        if (diaActual < diaNacimiento) {
            edad--;
        }
        if(diaActual > diaNacimiento){
            diaNacimiento = diaNacimiento + mes[mesNacimiento - 1];
            mesNacimiento = mesNacimiento - 1;
        }
    
        if(mesActual>mesNacimiento){
            mesNacimiento = mesNacimiento + 12;
            anoNacimiento = anoNacimiento - 1;
        }
    
        let d = diaNacimiento - diaActual;
        let m = mesNacimiento - mesActual;
        let y = anoNacimiento - anoActual;
    }
    return edad;
};

window.addEventListener('load', function () {

    fechaNacimiento.addEventListener('change', function () {
        if (this.value) {
            edad.innerText = `La edad es: ${calcularEdad(this.value)}`;
            edad.innerText = `año` + y;
            edad.innerText = `mes` + m;
            edad.innerText = `dia` + d;

        }
    });

});