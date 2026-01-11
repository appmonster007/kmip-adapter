package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.AsynchronousIndicator;

public class AsynchronousIndicatorXmlDeserializer extends AbstractKmipXmlDeserializer<AsynchronousIndicator, Boolean> {

    public AsynchronousIndicatorXmlDeserializer() {
        super(AsynchronousIndicator.kmipTag, AsynchronousIndicator.encodingType, Boolean.class, value -> AsynchronousIndicator.builder().value(value).build());
    }
}