package ch.noteshub.fhnw.controller;

import ch.noteshub.fhnw.business.service.DegreeService;
import ch.noteshub.fhnw.data.domain.Degree;
import ch.noteshub.fhnw.data.domain.Module;
import ch.noteshub.fhnw.data.repository.DegreeRepository;
import ch.noteshub.fhnw.data.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    private ModuleRepository moduleRepository;
    private DegreeService degreeService;

    @GetMapping
    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Module> getModuleById(@PathVariable Long id) {
        Optional<Module> module = moduleRepository.findById(id);
        if (module.isPresent()) {
            return ResponseEntity.ok(module.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public Module createModule(@RequestBody Module module) {
        return moduleRepository.save(module);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Module> updateModule(@PathVariable Long id, @RequestBody Module moduleDetails) {
        Optional<Module> module = moduleRepository.findById(id);
        if (module.isPresent()) {
            Module moduleToUpdate = module.get();
            moduleToUpdate.setModuleName(moduleDetails.getModuleName());
            moduleToUpdate.setModuleType(moduleDetails.getModuleType());
            moduleToUpdate.setModulePeriod(moduleDetails.getModulePeriod());
            moduleToUpdate.setModuleTeachingLanguage(moduleDetails.getModuleTeachingLanguage());
            return ResponseEntity.ok(moduleRepository.save(moduleToUpdate));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable Long id) {
        Optional<Module> module = moduleRepository.findById(id);
        if (module.isPresent()) {
            moduleRepository.delete(module.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/by-degree/{degreeId}")
    public ResponseEntity<List<Module>> getModulesByDegreeId(@PathVariable Long degreeId) {
        try {
            List<Module> modules = moduleRepository.findByDegreeId(degreeId);
            return ResponseEntity.ok(modules);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
