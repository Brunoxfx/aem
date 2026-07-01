package com.mysite.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;


@Model(
    adaptables = {SlingHttpServletRequest.class},
    adapters = {CardModel.class, ComponentExporter.class},
    resourceType = CardModel.RESOURCE_TYPE
)
@Exporter(
    name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
    extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class CardModel {

    protected static final String RESOURCE_TYPE = "mysite/components/card";
    
    @ValueMapValue private String title;
    @ValueMapValue private String description;
    @ValueMapValue private String link;
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    // Configuração do link
    public String getLink() {
        if (link == null || link.isEmpty()) {
            return null;
        }
        // Caminhos internos do AEM (/content/...) precisam da extensão .html.
        // Links externos (http://, https://, //) ou âncoras (#) são mantidos como estão.
        if (link.startsWith("/content") && !link.contains(".")) {
            return link + ".html";
        }
        return link;
    }
}