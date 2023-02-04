package fa.training.service.impl;


import fa.training.dto.SeatDTO;
import fa.training.entity.Seat;
import fa.training.mapper.SeatMapper;
import fa.training.repository.SeatRepository;
import fa.training.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;
    public SeatServiceImpl(SeatRepository seatRepository, SeatMapper seatMapper) {
        this.seatRepository = seatRepository;
        this.seatMapper = seatMapper;
    }

    @Override
    public List<SeatDTO> getAll() {
        List<Seat> seats = seatRepository.findAll();
        return seatMapper.castListEntityToDTO(seats);

    }

    @Override
    public String editSeat(SeatDTO seatDTO, String username) {
        Seat seat = seatMapper.castDTOToEntity(seatDTO);
        seatRepository.saveAndFlush(seat);
        return "Edit Complete";
    }


}
