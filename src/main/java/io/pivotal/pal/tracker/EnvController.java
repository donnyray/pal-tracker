package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    private Map<String, String> env;

    public EnvController(@Value("${PORT:8080}") String port, @Value("${MEMORY_LIMIT:notset}") String memLmt,
                         @Value("${CF_INSTANCE_INDEX:notset}") String instIdx, @Value("${CF_INSTANCE_ADDR:notset}") String instAddr) {
        env = new HashMap<>();
        env.put("PORT", port);
        env.put("MEMORY_LIMIT", memLmt);
        env.put("CF_INSTANCE_INDEX", instIdx);
        env.put("CF_INSTANCE_ADDR", instAddr);
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        return env;
    }

}
