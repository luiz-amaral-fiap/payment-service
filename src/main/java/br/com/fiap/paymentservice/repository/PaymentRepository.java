package br.com.fiap.paymentservice.repository;

import br.com.fiap.paymentservice.model.PaymentDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {

    private List<PaymentDTO> payments;

    public PaymentRepository(){
        payments = new ArrayList<>();
    }

    public PaymentDTO findById(Long id) {
        return payments.stream().filter((PaymentDTO) -> PaymentDTO.getId().equals(id)).findFirst().orElse(null);
    }

    public PaymentDTO findByIdTransaction(String idTransaction) {
        return payments.stream().filter((PaymentDTO) -> PaymentDTO.getIdTransaction().equals(idTransaction)).findFirst().orElse(null);
    }

    public PaymentDTO save(PaymentDTO PaymentDTO){
        PaymentDTO.setId((long) (payments.size()+1));
        this.payments.add(PaymentDTO);
        return PaymentDTO;
    }

    public PaymentDTO update(PaymentDTO paymentDTO){
        int index = payments.indexOf(paymentDTO);
        payments.set(index, paymentDTO);
        return paymentDTO;
    }

    public PaymentDTO delete(Long id){
        PaymentDTO PaymentDTODb = findById(id);
        payments.remove(PaymentDTODb);
        return PaymentDTODb;
    }

    public List<PaymentDTO> getPayments(){
        return payments;
    }
}