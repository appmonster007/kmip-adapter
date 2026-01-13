package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.AsynchronousIndicator;

public class AsynchronousIndicatorXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AsynchronousIndicator, String> {

    public AsynchronousIndicatorXmlSerializer() {
        super(AsynchronousIndicator::getDescription);
    }
}