package br.com.fiap.paymentservice.service;

import br.com.fiap.paymentservice.exceptionHandler.EmptyResultDataException;
import br.com.fiap.paymentservice.exceptionHandler.InvalidDataException;
import br.com.fiap.paymentservice.model.PaymentDTO;
import br.com.fiap.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    public PaymentDTO findById(Long id) {
        PaymentDTO paymentDTODb = paymentRepository.findById(id);
        if(paymentDTODb == null){ throw new EmptyResultDataException(); }
        return paymentDTODb;
    }

    public PaymentDTO findByIdTransaction(String idTransaction) {
        PaymentDTO paymentDTODb = paymentRepository.findByIdTransaction(idTransaction);
        if(paymentDTODb == null){ throw new EmptyResultDataException(); }
        return paymentDTODb;
    }

    public PaymentDTO save(PaymentDTO paymentDTO){
        if(paymentDTO.getCardNumber()==null){ throw new InvalidDataException(); }
        return paymentRepository.save(paymentDTO);
    }

    public PaymentDTO update(Long id, PaymentDTO paymentDTO){
        PaymentDTO paymentDb = paymentRepository.findById(id);
        if(paymentDTO.getCardNumber()==null){ throw new InvalidDataException(); }
        paymentDTO.setId(paymentDb.getId());
        return paymentRepository.update(paymentDTO);
    }

    public PaymentDTO delete(Long id){
        return paymentRepository.delete(id);
    }

    public List<PaymentDTO> getOrders(){
        return paymentRepository.getPayments();
    }

}