package br.com.fiap.paymentservice.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    @ApiModelProperty(notes = "The database generated Payment ID")
    private Long id;
    @ApiModelProperty( notes = "Transaction ID", required = true)
    private String idTransaction;
    @ApiModelProperty( notes = "Card Expiration Date", required = true)
    private LocalDate cardExpirationDate;
    @ApiModelProperty( notes = "Card Number", required = true)
    private Long cardNumber;
    @ApiModelProperty( notes = "Card Flag", required = true)
    private String cardFlag;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentDTO that = (PaymentDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}