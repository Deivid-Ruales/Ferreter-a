package bigworkers.ingresoegreso.ferreteriaThymeleaf.service;



import bigworkers.ingresoegreso.ferreteriaThymeleaf.entities.Profile;

import java.util.List;

public interface IProfileService {

    public Profile findById(long id);

    public List<Profile> findAll();

    public Profile createProfile(Profile perfil);

    public Profile updateProfile(long id,Profile perfil);

    public void deleteProfile(long id);
}
