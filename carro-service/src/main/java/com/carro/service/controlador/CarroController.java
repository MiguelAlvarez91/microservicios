package com.carro.service.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.carro.service.entidades.Carro;
import com.carro.service.servicio.CarroService;

@RestController
@RequestMapping("/carro")
public class CarroController {

	@Autowired
	private CarroService carroService; // Inyección de dependencias

	@GetMapping
	public ResponseEntity<List<Carro>> listararCarros() {
		List<Carro> carro = carroService.getAll();
		if (carro.isEmpty()) {
			return ResponseEntity.noContent().build(); // retorna que no tiene contenido
		}
		return ResponseEntity.ok(carro); // Retorna la lista de usuarios
	}

	@GetMapping("/{id}")
	public ResponseEntity<Carro> obtenerCarro(@PathVariable("id") int id) { // Para buscar al Usuario por medio de
																				// un ID
		Carro carro = carroService.getCarroById(id);
		if (carro == null) {
			return ResponseEntity.notFound().build(); // Retorna un valor vacio
		}
		return ResponseEntity.ok(carro);
	}

	@PostMapping
	public ResponseEntity<Carro> guardarCarro(@RequestBody Carro carro) {
		Carro nuevoCarro = carroService.save(carro);
		return ResponseEntity.ok(nuevoCarro);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Carro>> listarCarroPorUsuarioId(@PathVariable("usuarioId") int id){
		List<Carro> carro = carroService.byUsuarioId(id);
		if (carro.isEmpty()) {
			return ResponseEntity.noContent().build(); // retorna que no tiene contenido
		}
		return ResponseEntity.ok(carro); // Retorna la lista de carros
	}
	
}
