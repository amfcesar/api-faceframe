package br.com.anodes.apifaceframe.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MetaDataContributor implements InfoContributor {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> details = new HashMap<>();
        Map<String, Object> app = new HashMap<>();
        app.put("startup-date", ctx.getStartupDate());
        app.put("name", "api-faceframe");
        app.put("descrição", "Api de fotos" );
        details.put("app", app);

        builder.withDetail("context", details);
    }
}
