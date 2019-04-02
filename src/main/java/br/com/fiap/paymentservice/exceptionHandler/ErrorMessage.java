package br.com.fiap.paymentservice.exceptionHandler;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private String error;
}