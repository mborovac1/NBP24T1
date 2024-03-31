package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.NbpRoleEntity;
import ba.unsa.etf.nbp24t1.model.NbpRole;

import java.util.List;

public interface NbpRoleService {

    List<NbpRoleEntity> getAll();

    NbpRoleEntity getById(Long id);

    void add(NbpRole nbpRole);

    void update(Long id, NbpRole nbpRole);

    void delete(Long id);
}
