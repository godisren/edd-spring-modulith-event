package com.example.eventdemo;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class DocumentationTests {

    ApplicationModules modules = ApplicationModules.of(EventDemoApplication.class);

    @Test
    void writeDocumentationSnippets() {

        new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }

    @Test
    void writeDocumentationSnippetsWithModuleCanvases() {

        new Documenter(modules)
                .writeModuleCanvases();
    }

}
