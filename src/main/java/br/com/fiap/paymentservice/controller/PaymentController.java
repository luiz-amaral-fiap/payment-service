package br.com.fiap.paymentservice.controller;

import br.com.fiap.paymentservice.model.PaymentDTO;
import br.com.fiap.paymentservice.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/payment-service", produces = "application/json")
@Api(value = "Payment", description = "Payment Service REST API")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @ApiOperation(httpMethod = "GET", value = "Método get para buscar uma transação filtrando por id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna um PaymentDTO com uma mensagem de sucesso", response = PaymentDTO.class)})
    @GetMapping("/{idTransaction}")
    public ResponseEntity<PaymentDTO> findByIdTransaction(@PathVariable(value="idTransaction") String idTransaction){
        System.out.printf("findByIdTransaction %s", idTransaction);

        PaymentDTO paymentDTO = paymentService.findByIdTransaction(idTransaction);

        if(paymentDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(paymentDTO);
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid PaymentDTO paymentDTO){
        paymentDTO = paymentService.save(paymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("{ \"payment-service\" : \"/payment-service/"+paymentDTO.getId()+ "\"}");
    }


    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid PaymentDTO paymentDTO) {
        paymentDTO = paymentService.update(id,paymentDTO);

        if(paymentDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body("{ \"payment-service\" : \"/payment-service/" + paymentDTO.getId() + "\"}");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { paymentService.delete(id); }

    @GetMapping("/")
    public ResponseEntity<PaymentDTO> getPayments(PaymentDTO paymentDTO){
        log.info("getPayments {} {}", paymentDTO.getIdTransaction(), paymentDTO.getId());
        return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
    }
}