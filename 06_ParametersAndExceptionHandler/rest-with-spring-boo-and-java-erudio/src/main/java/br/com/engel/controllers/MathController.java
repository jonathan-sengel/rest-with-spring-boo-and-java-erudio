package br.com.engel.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.engel.exceptions.UnsupportedMathOperationException;
import br.com.engel.utils.MathUtils;

@RestController
public class MathController {

	private final AtomicLong counter = new AtomicLong();

	@GetMapping(value = "/sum/{numberOne}/{numberTwo}")
	public Double sum(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) {
		if(MathUtils.validateNumbers(numberOne, numberTwo)) {
			return MathUtils.sum(numberOne, numberTwo);
		}
		return null;
	}

	@GetMapping(value = "/sub/{n1}/{n2}")
	public Double sub(@PathVariable(value = "n1") String n1, @PathVariable(value = "n2") String n2) {
		if(MathUtils.validateNumbers(n1, n2)) {
			return MathUtils.subtract(n1, n2);
		}
		return null;
	}

	@GetMapping(value = "/mult/{n1}/{n2}")
	public Double mult(@PathVariable(value = "n1") String n1, @PathVariable(value = "n2") String n2) {
		if(MathUtils.validateNumbers(n1, n2)) {
			return MathUtils.multiply(n1, n2);
		}
		return null;
	}

	@GetMapping(value = "/div/{n1}/{n2}")
	public Double div(@PathVariable(value = "n1") String n1, @PathVariable(value = "n2") String n2) {
		if(MathUtils.validateNumbers(n1, n2)) {
			return MathUtils.divide(n1, n2);
		}
		return null;
	}

	@GetMapping(value = "/med/{n1}/{n2}")
	public Double med(@PathVariable(value = "n1") String n1, @PathVariable(value = "n2") String n2) {
		if(MathUtils.validateNumbers(n1, n2)) {
			return MathUtils.average(n1, n2);
		}
		return null;
	}

	@GetMapping(value = "/raiz/{n1}")
	public Double raiz(@PathVariable(value = "n1") String n1) {
		if(MathUtils.validateNumbers(n1)) {
			return MathUtils.sqrt(n1);
		}
		return null;
	}
}
