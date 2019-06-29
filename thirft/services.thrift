service Servicios{
	i32 IniciarSesion(
		1:string usuario,
		2:string password
	)

	i32 RegistrarPerito(
		1:string nombre,
		2:string apellidoP,
		3:string apellidoM,
		4:string cargo,
		5:string usuario,
		6:string password,
		7:i32 rol
	)
}