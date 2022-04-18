package com.moto.service.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.service.entidades.Moto;
import com.moto.service.servicio.MotoService;

@RestController
@RequestMapping("/moto")
public class MotoController {

	@Autowired
	private MotoService motoService; // Inyección de dependencias

	@GetMapping
	public ResponseEntity<List<Moto>> listararMotos() {
		List<Moto> moto = motoService.getAll();
		if (moto.isEmpty()) {
			return ResponseEntity.noContent().build(); // retorna que no tiene contenido
		}
		return ResponseEntity.ok(moto); // Retorna la lista de usuarios
	}

	@GetMapping("/{id}")
	public ResponseEntity<Moto> obtenerMoto(@PathVariable("id") int id) { // Para buscar al Usuario por medio de
																			// un ID
		Moto moto = motoService.getMotoById(id);
		if (moto == null) {
			return ResponseEntity.notFound().build(); // Retorna un valor vacio
		}
		return ResponseEntity.ok(moto);
	}

	@PostMapping
	public ResponseEntity<Moto> guardarMoto(@RequestBody Moto moto) {
		Moto nuevaMoto = motoService.save(moto);
		return ResponseEntity.ok(nuevaMoto);
	}

	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Moto>> listarMotoPorUsuarioId(@PathVariable("usuarioId") int id) {
		List<Moto> moto = motoService.byUsuarioId(id);
		if (moto.isEmpty()) {
			return ResponseEntity.noContent().build(); // retorna que no tiene contenido
		}
		return ResponseEntity.ok(moto); // Retorna la lista de carros
	}
}
