package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.AsynchronousIndicator;

public class AsynchronousIndicatorXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AsynchronousIndicator, Boolean> {

    public AsynchronousIndicatorXmlSerializer() {
        super(AsynchronousIndicator::getValue);
    }
}