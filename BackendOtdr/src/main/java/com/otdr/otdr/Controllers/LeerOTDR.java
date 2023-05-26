package com.otdr.otdr.Controllers;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class LeerOTDR {

    private final ResourceLoader resourceLoader;

    public LeerOTDR(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/sor")
    public ResponseEntity<String> getSorContent() {
        // Ruta relativa del archivo .sor
        String sorFilePath = "C:/Users/ING.Derian/Desktop/otdr13ERR.sor";

        try {
            // Obtiene la ruta real del archivo .sor
            Resource resource = resourceLoader.getResource("file:" + sorFilePath);
            String sorRealPath = resource.getFile().getAbsolutePath();

            // Lee el contenido del archivo .sor
            String sorContent = readFileContent(sorRealPath);

            // Devuelve el contenido en formato JSON
            return ResponseEntity.ok(sorContent);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error al leer el archivo .sor: " + e.getMessage());
        }
    }

    private String readFileContent(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        BufferedReader reader = Files.newBufferedReader(path);
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }
        reader.close();
        return content.toString();
    }
}
