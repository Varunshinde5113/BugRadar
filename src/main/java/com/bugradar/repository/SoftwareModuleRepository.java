
package com.bugradar.repository;

import com.bugradar.model.SoftwareModule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftwareModuleRepository
        extends JpaRepository<SoftwareModule, Long> {
}
