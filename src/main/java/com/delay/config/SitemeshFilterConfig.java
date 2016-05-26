package com.delay.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SitemeshFilterConfig extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/login", "/layouts/login_decorator.jsp");
        builder.addDecoratorPath("/register", "/layouts/login_decorator.jsp");

        builder.addDecoratorPath("/", "/layouts/decorator.jsp");
        builder.addDecoratorPath("/admin**", "/layouts/admin_decorator.jsp");
    }
}
