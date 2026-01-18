package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.AsynchronousIndicator;

public class AsynchronousIndicatorXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AsynchronousIndicator, String> {

    public AsynchronousIndicatorXmlSerializer() {
        super(AsynchronousIndicator::getDescription);
    }
}