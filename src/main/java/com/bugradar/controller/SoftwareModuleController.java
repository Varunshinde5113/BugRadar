
package com.bugradar.controller;

import com.bugradar.model.SoftwareModule;
import com.bugradar.repository.SoftwareModuleRepository;
import com.bugradar.service.RiskCalculationService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/modules")
public class SoftwareModuleController {

    private final SoftwareModuleRepository repository;
    private final RiskCalculationService riskService;

    public SoftwareModuleController(
            SoftwareModuleRepository repository,
            RiskCalculationService riskService) {
        this.repository = repository;
        this.riskService = riskService;
    }

    @GetMapping
    public List<SoftwareModule> getAllModules() {
        return repository.findAll()
                .stream()
                .sorted((a, b) -> Double.compare(
                        b.getRiskScore(), a.getRiskScore()))
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SoftwareModule addModule(
            @Valid @RequestBody ModuleRequest request) {

        SoftwareModule module = new SoftwareModule();

        module.setModuleName(request.moduleName().trim());
        module.setPreviousDefects(request.previousDefects());
        module.setTestFailures(request.testFailures());
        module.setComplexity(request.complexity());
        module.setBusinessImpact(request.businessImpact());

        riskService.calculateRisk(module);

        return repository.save(module);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteModule(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Module not found");
        }

        repository.deleteById(id);
    }

    public record ModuleRequest(
            @NotBlank(message = "Module name is required")
            String moduleName,

            @Min(0) @Max(10)
            int previousDefects,

            @Min(0) @Max(10)
            int testFailures,

            @Min(1) @Max(5)
            int complexity,

            @Min(1) @Max(5)
            int businessImpact
    ) {
    }
}
