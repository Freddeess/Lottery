package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.*;
import com.example.LIAXLENT.Lottery.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final LotteryRepository lotteryRepository;
    private final TransactionRepository transactionRepository;
    private final EmployeeRepository employeeRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository,
                             LotteryRepository lotteryRepository,
                             AccountRepository accountRepository,
                             TransactionRepository transactionRepository,
                             EmployeeRepository employeeRepository,
                             PaymentMethodRepository paymentMethodRepository){
        this.ticketRepository = ticketRepository;
        this.lotteryRepository = lotteryRepository;
        this.transactionRepository = transactionRepository;
        this.employeeRepository = employeeRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public List<Ticket> findAll(){
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findWinningTickets(){
        return ticketRepository.getTicketsByWinnerIsTrue();
    }

    @Override
    public List<Ticket> findActiveTickets(){
        return ticketRepository.getTicketsByLotteryActiveIsTrue();
    }
    @Override
    public Ticket findById(int id){
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isEmpty()){
            throw new RuntimeException("Lott med id "+id+" hittades inte");
        }
        return ticket.get();
    }

    @Transactional
    public Ticket createTicket (int employeeId, int lotteryId, int paymentMethodId){
        Employee buyer = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Anställd med id "+ employeeId +" hittades inte"));
        Lottery lottery = lotteryRepository.findById(lotteryId)
                .orElseThrow(() -> new RuntimeException("Lotteri med id "+ lotteryId +" hittades inte"));
        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new RuntimeException("Betalningsmetod med id "+ paymentMethodId +" hittades inte"));

        Employee seller = lottery.getEmployee();

        int buyerBalance = buyer.getAccount().getBalance();
        int sellerBalance = seller.getAccount().getBalance();
        int price;

        Transaction buyerTransaction = new Transaction();
        Transaction sellerTransaction = new Transaction();

        Ticket ticket = new Ticket();

        switch (paymentMethod.getName().toLowerCase()){
            case "xlentcoins":
                price = lottery.getPriceXlentCoins();
                if(buyerBalance<price){
                    throw new RuntimeException("För lågt saldo");
                }
                if(!buyer.equals(seller)){
                    buyer.getAccount().setBalance(buyerBalance-price);
                    seller.getAccount().setBalance(sellerBalance+price);
                }

                buyerTransaction.setAccount(buyer.getAccount());
                buyerTransaction.setXlentCoins(-price);
                buyerTransaction.setTicket(ticket);
                transactionRepository.save(buyerTransaction);

                sellerTransaction.setAccount(seller.getAccount());
                sellerTransaction.setXlentCoins(price);
                sellerTransaction.setTicket(ticket);
                transactionRepository.save(sellerTransaction);
                break;
            case "sek":
                price = lottery.getPriceSEK();

                buyerTransaction.setAccount(buyer.getAccount());
                buyerTransaction.setSek(-price);
                buyerTransaction.setTicket(ticket);
                transactionRepository.save(buyerTransaction);

                sellerTransaction.setAccount(seller.getAccount());
                sellerTransaction.setSek(price);
                sellerTransaction.setTicket(ticket);
                transactionRepository.save(sellerTransaction);
                break;
            default:
                throw new IllegalArgumentException("Ogiltig betalningsmetod");

        }

        ticket.setEmployee(buyer);
        ticket.setLottery(lottery);
        ticket.setAmount(price);
        ticket.setPaymentMethod(paymentMethod);
        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteById(int id){
        if (!ticketRepository.existsById(id)) {
            throw new RuntimeException("Lott med id " + id + " hittades inte");
        }
        ticketRepository.deleteById(id);
    }
    @Override
    public List<Ticket> findByEmployeeId(int employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            return employee.get().getTickets();
        } else {
            throw new RuntimeException("Anställd med id " + employeeId + " hittades inte");
        }
    }



}
