package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.AsynchronousIndicator;

public class AsynchronousIndicatorXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AsynchronousIndicator, String> {

    public AsynchronousIndicatorXmlDeserializer() {
        super(AsynchronousIndicator.kmipTag, AsynchronousIndicator.encodingType, String.class, value -> new AsynchronousIndicator(AsynchronousIndicator.fromName(value)));
    }
}