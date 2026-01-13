package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.AsynchronousIndicator;

public class AsynchronousIndicatorXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AsynchronousIndicator, Boolean> {

    public AsynchronousIndicatorXmlDeserializer() {
        super(AsynchronousIndicator.kmipTag, AsynchronousIndicator.encodingType, Boolean.class, value -> AsynchronousIndicator.builder().value(value).build());
    }
}