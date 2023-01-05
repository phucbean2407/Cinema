package fa.training.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @NotNull
    private PeopleDTO peopleDTO;
    @NotNull
    private MovieShowTimeDTO movieShowTimeDTO;
//    @ConstructorProperties({"price","quantity","paymentMethod","peopleDTO","movieShowTimeDTO"})
//    public TicketDTO(String paymentMethod, double price, int quantity, PeopleDTO peopleDTO, MovieShowTimeDTO movieShowTimeDTO) {
//        this.paymentMethod = paymentMethod;
//        this.price = price;
//        this.quantity = quantity;
//        this.peopleDTO = peopleDTO;
//        this.movieShowTimeDTO = movieShowTimeDTO;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public void setTotalMoney(double totalMoney) {
//        this.totalMoney = totalMoney;
//    }
//
//    public  static TicketDTO.TicketDTOBuilder builder() {
//        return new TicketDTO.TicketDTOBuilder();
//    }
//
//    public static class TicketDTOBuilder {
//        private String paymentMethod;
//        private double price;
//        private double totalMoney;
//        private int quantity;
//        private PeopleDTO peopleDTO;
//        private MovieShowTimeDTO movieShowTimeDTO;
//
//        public TicketDTO.TicketDTOBuilder paymentMethod(String paymentMethod) {
//            this.paymentMethod = paymentMethod;
//            return this;
//        }
//        public TicketDTO.TicketDTOBuilder price(double price) {
//            this.price = price;
//            return this;
//        }
//        public TicketDTO.TicketDTOBuilder quantity(int quantity) {
//            this.quantity = quantity;
//            return this;
//        }
//        public TicketDTO.TicketDTOBuilder peopleDTO(PeopleDTO peopleDTO) {
//            this.peopleDTO = peopleDTO;
//            return this;
//        }
//        public TicketDTO.TicketDTOBuilder movieShowTimeDTO(MovieShowTimeDTO movieShowTimeDTO) {
//            this.movieShowTimeDTO = movieShowTimeDTO;
//            return this;
//        }
//
//        public TicketDTO build() {
//            return new TicketDTO(this.paymentMethod,this.price,this.quantity
//                  ,this.peopleDTO, this.movieShowTimeDTO);
//        }
//    }

}
