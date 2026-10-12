
package com.bugradar.service;

import com.bugradar.model.SoftwareModule;
import org.springframework.stereotype.Service;

@Service
public class RiskCalculationService {

    public void calculateRisk(SoftwareModule module) {

        double defectScore =
                (module.getPreviousDefects() / 10.0) * 30;

        double failureScore =
                (module.getTestFailures() / 10.0) * 30;

        double complexityScore =
                (module.getComplexity() / 5.0) * 20;

        double impactScore =
                (module.getBusinessImpact() / 5.0) * 20;

        double totalScore = defectScore
                + failureScore
                + complexityScore
                + impactScore;

        double roundedScore = Math.round(totalScore * 100.0) / 100.0;

        module.setRiskScore(roundedScore);

        if (roundedScore >= 70) {
            module.setRiskLevel("HIGH");
        } else if (roundedScore >= 40) {
            module.setRiskLevel("MEDIUM");
        } else {
            module.setRiskLevel("LOW");
        }
    }
}
