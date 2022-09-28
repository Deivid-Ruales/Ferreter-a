package bigworkers.ingresoegreso.ferreteriaThymeleaf.service;



import bigworkers.ingresoegreso.ferreteriaThymeleaf.entities.Profile;
import bigworkers.ingresoegreso.ferreteriaThymeleaf.repository.IProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService implements IProfileService{

    @Autowired
    private IProfileRepository profileRepository;
    @Override
    public Profile findById(long id) {
        Optional<Profile> perfil = profileRepository.findById(id);
        return perfil.get();
    }

    @Override
    public List<Profile> findAll() {
        List<Profile> perfiles = (List<Profile>) profileRepository.findAll();
        return perfiles;
    }

    @Override
    public Profile createProfile(Profile perfil) {
        Profile newPerfil = profileRepository.save(perfil);
        return newPerfil;
    }

    @Override
    public Profile updateProfile(long id, Profile perfil) {
        Profile putPerfil = profileRepository.save(perfil);
        return putPerfil;
    }

    @Override
    public void deleteProfile(long id) {
        profileRepository.deleteById(id);
    }
}
