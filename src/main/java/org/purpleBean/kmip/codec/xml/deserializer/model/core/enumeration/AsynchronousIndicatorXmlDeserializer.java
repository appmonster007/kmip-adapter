package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.AsynchronousIndicator;

public class AsynchronousIndicatorXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AsynchronousIndicator, String> {

    public AsynchronousIndicatorXmlDeserializer() {
        super(AsynchronousIndicator.kmipTag, AsynchronousIndicator.encodingType, String.class, value -> AsynchronousIndicator.fromName(value).inst());
    }
}