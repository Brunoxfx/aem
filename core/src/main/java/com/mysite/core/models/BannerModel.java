package com.mysite.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


@Model(
    adaptables = Resource.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class BannerModel {

    @ValueMapValue
    private String image;

    @ValueMapValue
    private String text;


    public String getImage() {
        return image;
    }

    public String getText() {
        return text;
    }
}