package com.codeup.localscene.services;
import com.codeup.localscene.models.Bands;
import com.codeup.localscene.models.Users;
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

    public Bands createBand(Bands band) {
        return bandRepository.save(band);
    }

    public List<Bands> getAllBands() {
        return bandRepository.findAll();
    }

    public Bands getBandById(Long id) {
        return bandRepository.findById(id).orElse(null);
    }

    public Bands updateBand(Bands band) {
        return bandRepository.save(band);
    }

    public void deleteBand(Long id) {
        bandRepository.deleteById(id);
    }

    public List<Bands> getBandsByUser(Users user) {
        return bandRepository.findByUser(user);
    }
}

