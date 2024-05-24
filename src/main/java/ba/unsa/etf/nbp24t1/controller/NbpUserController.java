package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.NbpUserEntity;
import ba.unsa.etf.nbp24t1.exception.AlreadyExistsException;
import ba.unsa.etf.nbp24t1.exception.NotFoundException;
import ba.unsa.etf.nbp24t1.model.User;
import ba.unsa.etf.nbp24t1.service.NbpUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/nbpUsers")
@RestController
@CrossOrigin("*")
public class NbpUserController {

    private final NbpUserService nbpUserService;

    @GetMapping("/")
    public List<NbpUserEntity> getAll() {
        return nbpUserService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return handleResponse(() -> nbpUserService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> insert(@RequestBody @Valid User user) {
        return handleResponse(() -> {
            nbpUserService.add(user);
            return String.format("User with email %s successfully added.", user.getEmail());
        });
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid User user) {
        return handleResponse(() -> {
            nbpUserService.update(id, user);
            return String.format("User with id %d successfully updated.", id);
        });
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("userId") Long userId) {
        return nbpUserService.delete(userId);
        /*return handleResponse(() -> {
            nbpUserService.delete(id);
            return String.format("User with id %d successfully deleted.", id);
        });*/
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
