
package com.bugradar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SoftwareModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String moduleName;
    private int previousDefects;
    private int testFailures;
    private int complexity;
    private int businessImpact;
    private double riskScore;
    private String riskLevel;

    public SoftwareModule() {
    }

    public Long getId() {
        return id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getPreviousDefects() {
        return previousDefects;
    }

    public void setPreviousDefects(int previousDefects) {
        this.previousDefects = previousDefects;
    }

    public int getTestFailures() {
        return testFailures;
    }

    public void setTestFailures(int testFailures) {
        this.testFailures = testFailures;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public int getBusinessImpact() {
        return businessImpact;
    }

    public void setBusinessImpact(int businessImpact) {
        this.businessImpact = businessImpact;
    }

    public double getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(double riskScore) {
        this.riskScore = riskScore;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }
}
