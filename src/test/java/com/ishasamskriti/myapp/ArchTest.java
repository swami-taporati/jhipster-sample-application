package com.ishasamskriti.myapp;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.ishasamskriti.myapp");

        noClasses()
            .that()
            .resideInAnyPackage("com.ishasamskriti.myapp.service..")
            .or()
            .resideInAnyPackage("com.ishasamskriti.myapp.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.ishasamskriti.myapp.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
