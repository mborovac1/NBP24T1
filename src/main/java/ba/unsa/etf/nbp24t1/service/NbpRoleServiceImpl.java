package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.NbpRoleEntity;
import ba.unsa.etf.nbp24t1.exception.AlreadyExistsException;
import ba.unsa.etf.nbp24t1.exception.NotFoundException;
import ba.unsa.etf.nbp24t1.model.NbpRole;
import ba.unsa.etf.nbp24t1.repository.NbpRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NbpRoleServiceImpl implements NbpRoleService {

    private final NbpRoleRepository nbpRoleRepository;

    @Override
    public List<NbpRoleEntity> getAll() {
        return nbpRoleRepository.findAll();
    }

    @Override
    public NbpRoleEntity getById(Long id) {
        return findRoleById(id);
    }

    @Override
    public void add(NbpRole nbpRole) {
        if (nbpRoleRepository.existsByName(nbpRole.getName()))
            throw new AlreadyExistsException(String.format("Role with name %s already exists.", nbpRole.getName()));
        var nbpRoleEntity = mapToNbpRoleEntity(nbpRole);
        nbpRoleRepository.save(nbpRoleEntity);
    }

    @Override
    public void update(Long id, NbpRole nbpRole) {
        var nbpRoleEntity = findRoleById(id);
        updateRoleAttributes(nbpRoleEntity, nbpRole);
        nbpRoleRepository.save(nbpRoleEntity);
    }

    @Override
    public void delete(Long id) {
        var nbpRoleEntity = findRoleById(id);
        nbpRoleRepository.delete(nbpRoleEntity);
    }

    private NbpRoleEntity mapToNbpRoleEntity(NbpRole nbpRole) {
        return NbpRoleEntity.builder()
                .name(nbpRole.getName())
                .build();
    }

    private NbpRoleEntity findRoleById(Long id) {
        return nbpRoleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Role with id %d does not exist.", id)));
    }

    private void updateRoleAttributes(NbpRoleEntity nbpRoleEntity, NbpRole nbpRole) {
        if (!nbpRole.getName().isBlank()) {
            nbpRoleEntity.setName(nbpRole.getName());
        }
    }
}
