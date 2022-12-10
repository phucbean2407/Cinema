package fa.training.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class TicketDTO {
    private long id;
    @NotNull
    private String paymentMethod;
    @NotNull
    @Positive
    private double price;
    @NotNull
    @Positive
    private int quantity;
    @Positive
    private double totalMoney;

    private PeopleDTO peopleDTO;

    private MovieShowTimeDTO movieShowTimeDTO;
}
