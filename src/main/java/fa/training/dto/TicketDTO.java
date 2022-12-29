package fa.training.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder
@Getter
@Setter
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
