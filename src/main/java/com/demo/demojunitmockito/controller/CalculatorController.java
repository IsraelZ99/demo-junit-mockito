package com.demo.demojunitmockito.controller;

import com.demo.demojunitmockito.dto.CalculatorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {

    @GetMapping("/add")
    public Integer add(@RequestParam Integer number1, @RequestParam Integer number2) {
        return number1 + number2;
    }

    @GetMapping("sub/{num1}/{num2}")
    public Integer subtract(@PathVariable Integer num1, @PathVariable Integer num2) {
        return num1 > num2 ? num1 - num2 : num2 - num1;
    }

    @PostMapping("/mul")
    public ResponseEntity<Integer> multiply(@RequestBody CalculatorDTO calculator) {
        Integer result = calculator.getNumber1() * calculator.getNumber2();
        return new ResponseEntity<>(result,
                HttpStatus.OK);
    }

}
