package com.mysite.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


@Model(
        adaptables = {SlingHttpServletRequest.class, Resource.class},
        adapters = {BannerModel.class, ComponentExporter.class},
        resourceType = BannerModel.RESOURCE_TYPE
)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class BannerModel {
    protected static final String RESOURCE_TYPE = "mysite/components/banner";

    @ValueMapValue
    private String image;

    @ValueMapValue
    private String fileReference;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String subtitle;

    @ValueMapValue
    private String text;

    @ValueMapValue
    private String buttonLabel;

    @ValueMapValue
    private String buttonLink;

    @ValueMapValue
    private String backgroundColor;

    @ValueMapValue
    private String alignment;

    @ValueMapValue
    private Double borderRadius;

    @ValueMapValue
    private Double paddingTopBottom;

    @ValueMapValue
    private Double paddingLeftRight;

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getText() {
        return text;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public String getButtonLink() {
        return buttonLink;
    }

    public String getButtonHref() {
        return buttonLink != null && !buttonLink.isBlank() ? buttonLink : "#";
    }

    public String getBackgroundImage() {
        return fileReference != null && !fileReference.isBlank() ? fileReference : image;
    }

    public String getBackgroundColorValue() {
        return backgroundColor != null && !backgroundColor.isBlank() ? backgroundColor : "";
    }

    public String getStyle() {
        StringBuilder style = new StringBuilder();

        appendPixelStyle(style, "border-radius", borderRadius);
        appendPixelStyle(style, "padding-top", paddingTopBottom);
        appendPixelStyle(style, "padding-bottom", paddingTopBottom);
        appendPixelStyle(style, "padding-left", paddingLeftRight);
        appendPixelStyle(style, "padding-right", paddingLeftRight);

        if (alignment != null) {
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
}
