package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.NbpUserEntity;
import ba.unsa.etf.nbp24t1.model.NbpUser;

import java.util.List;

public interface NbpUserService {

    List<NbpUserEntity> getAll();

    NbpUserEntity getById(Long id);

    void add(NbpUser nbpUser);

    void update(Long id, NbpUser nbpUser);

    void delete(Long id);
}
