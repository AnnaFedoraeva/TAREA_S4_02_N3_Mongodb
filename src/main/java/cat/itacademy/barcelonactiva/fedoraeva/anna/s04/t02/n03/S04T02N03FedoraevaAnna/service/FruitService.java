package cat.itacademy.barcelonactiva.fedoraeva.anna.s04.t02.n03.S04T02N03FedoraevaAnna.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.fedoraeva.anna.s04.t02.n03.S04T02N03FedoraevaAnna.domain.Fruit;
import cat.itacademy.barcelonactiva.fedoraeva.anna.s04.t02.n03.S04T02N03FedoraevaAnna.repository.FruitRepo;

@Service

public class FruitService {
	
	@Autowired
	FruitRepo repo;

	public Fruit save(Fruit fruit) {
		return repo.save(fruit);
	}

	public Optional<Fruit> findById(String id) {
		return repo.findById(id);
	}

	public ResponseEntity<?> update(String id, Fruit fruit) {
		Optional<Fruit> fruitOp = findById(id);
		if (fruitOp.isPresent()) {
			Fruit fruitToUpdate = fruitOp.get();
			fruitToUpdate.setName(fruit.getName());
			fruitToUpdate.setKg(fruit.getKg());
			return new ResponseEntity<>(repo.save(fruitToUpdate), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("The fruit with id " + id + " was not found.", HttpStatus.OK);
		}
	}

	public ResponseEntity<?> delete(String id) {
		Optional<Fruit> fruitOp = findById(id);
		if (fruitOp.isPresent()) {
			repo.deleteById(id);
			return new ResponseEntity<>("The fruit was deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("The fruit with id " + id + " was not found.", HttpStatus.OK);
		}
	}

	public ResponseEntity<?> findAll() {
		if (repo.findAll().isEmpty()) {
			return new ResponseEntity<>("The list is empty.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
		}
	}

	public ResponseEntity<?> getOne(String id) {
		Optional<Fruit> fruit = repo.findById(id);
		if (fruit.isPresent()) {
			return new ResponseEntity<>(fruit, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<>("The fruit with id " + id + " was not found.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
