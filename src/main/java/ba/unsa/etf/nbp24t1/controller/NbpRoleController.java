package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.NbpRoleEntity;
import ba.unsa.etf.nbp24t1.exception.AlreadyExistsException;
import ba.unsa.etf.nbp24t1.exception.NotFoundException;
import ba.unsa.etf.nbp24t1.model.NbpRole;
import ba.unsa.etf.nbp24t1.service.NbpRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/nbpRoles")
@RestController
public class NbpRoleController {

    private final NbpRoleService nbpRoleService;

    @GetMapping("/")
    public List<NbpRoleEntity> getAll() {
        return nbpRoleService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return handleResponse(() -> nbpRoleService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> insert(@RequestBody @Valid NbpRole nbpRole) {
        return handleResponse(() -> {
            nbpRoleService.add(nbpRole);
            return String.format("Role with name %s successfully added.", nbpRole.getName());
        });
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid NbpRole nbpRole) {
        return handleResponse(() -> {
            nbpRoleService.update(id, nbpRole);
            return String.format("Role with id %d successfully updated.", id);
        });
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return handleResponse(() -> {
            nbpRoleService.delete(id);
            return String.format("Role with id %d successfully deleted.", id);
        });
    }

    private ResponseEntity<?> handleResponse(Action action) {
        try {
            return ResponseEntity.ok().body(action.execute());
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private interface Action {
        Object execute() throws Exception;
    }
}
