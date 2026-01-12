package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.AsynchronousIndicator;

public class AsynchronousIndicatorXmlSerializer extends AbstractKmipXmlSerializer<AsynchronousIndicator, String> {

    public AsynchronousIndicatorXmlSerializer() {
        super(AsynchronousIndicator::getDescription);
    }
}