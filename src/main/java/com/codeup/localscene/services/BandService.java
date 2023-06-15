package com.codeup.localscene.services;
import com.codeup.localscene.models.Band;
import com.codeup.localscene.models.User;
import com.codeup.localscene.repositories.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BandService {

    private final BandRepository bandRepository;

    @Autowired
    public BandService(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    public Band createBand(Band band) {
        return bandRepository.save(band);
    }

    public List<Band> getAllBands() {
        return bandRepository.findAll();
    }

    public Band getBandById(Long id) {
        return bandRepository.findById(id).orElse(null);
    }

    public Band updateBand(Band band) {
        return bandRepository.save(band);
    }

    public void deleteBand(Long id) {
        bandRepository.deleteById(id);
    }

}

