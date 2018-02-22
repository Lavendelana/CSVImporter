package com.csv.splendo.csvparser;

import android.content.res.Resources;

import java.io.InputStream;
import java.lang.ref.WeakReference;

public class CsvTaskParameters<T> {
    private CsvResultParser<T> parser;
    private int resourceId;
    private WeakReference<Resources> resources;

    public CsvTaskParameters(CsvResultParser<T> parser, int resourceId, Resources resources) {
        this.parser = parser;
        this.resourceId = resourceId;
        this.resources = new WeakReference<Resources>(resources);
    }

    public CsvResultParser<T> getParser() {
        return parser;
    }

    public InputStream getResourceInputStream() {
        Resources resourceObject = this.resources.get();
        if (resourceObject != null) {
            return resourceObject.openRawResource(resourceId);
        }
        return null;
    }
}
