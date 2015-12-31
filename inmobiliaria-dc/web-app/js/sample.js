var Human = {
	firstName: '',
	lastName: '',
	gender: '',
	planetOfBirth: 'Earth',
	sayGender: function () {
		console.log(this.firstName + ' says my gender is ' + this.gender);
	},
	sayPlanet: function () {
		console.log(this.firstName + ' was born on ' + this.planetOfBirth);
	},
	sayFullName: function () {
		console.log('Full name is ' + this.firstName + this.lastName);
	}
};

var Engineer = Object.create(Human, {});
Engineer.calculateSquareArea = function(s1,s2) {
	return (s1 * s2);
}
Engineer.calculateCircleArea = function(radius) {
	return ( 3.1416 * radius * radius );
}

var Doctor = Object.create(Engineer, {});
Doctor.doPapper = function(title) {
	var papper = { 
		title: title,
		content: "Pure smoke!"
	};
	return tesis;
}
Doctor.licenceNumber = '';

var heril = Object.create(Engineer, {
	firstName: { value : "Heraldo"},
	lastName: { value: "Valenti" },
	gender : { value : "M" }
});

var edus = Object.create(Doctor, {
	firstName: { value : "Eduardo"},
	lastName: { value: "Nievas" },
	gender : { value : "M" },
	licenceNumber : { value: 1234 }
});