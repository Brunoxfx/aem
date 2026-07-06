package com.mysite.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;

@Model(
        adaptables = {SlingHttpServletRequest.class, Resource.class},
        adapters = {FooterCard.class, ComponentExporter.class},
        resourceType = FooterCard.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class FooterCard implements ComponentExporter {
    protected static final String RESOURCE_TYPE = "mysite/components/footer";

    @ValueMapValue
    private String[] links;

    @ValueMapValue
    private String copyright;

    @ValueMapValue
    private String alignment;

    @ValueMapValue
    private Double borderRadius;

    @ValueMapValue
    private Double paddingTopBottom;

    @ValueMapValue
    private Double paddingLeftRight;

    public String[] getLinks() {
        return links;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getAlignment() {
        return alignment;
    }

    public Double getBorderRadius() {
        return borderRadius;
    }

    public Double getPaddingTopBottom() {
        return paddingTopBottom;
    }

    public Double getPaddingLeftRight() {
        return paddingLeftRight;
    }

    public String getStyle() {
        StringBuilder style = new StringBuilder();

        appendPixelStyle(style, "border-radius", borderRadius);
        appendPixelStyle(style, "padding-top", paddingTopBottom);
        appendPixelStyle(style, "padding-bottom", paddingTopBottom);
        appendPixelStyle(style, "padding-left", paddingLeftRight);
        appendPixelStyle(style, "padding-right", paddingLeftRight);

        if (alignment != null && !alignment.isBlank()) {
            appendStyle(style, "text-align", alignment.toLowerCase());
        }

        return style.toString();
    }

    private void appendPixelStyle(StringBuilder style, String property, Double value) {
        if (value != null) {
            appendStyle(style, property, value.intValue() + "px");
        }
    }

    private void appendStyle(StringBuilder style, String property, String value) {
        if (value != null && !value.isBlank()) {
            style.append(property).append(": ").append(value).append("; ");
        }
    }

    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }
}
