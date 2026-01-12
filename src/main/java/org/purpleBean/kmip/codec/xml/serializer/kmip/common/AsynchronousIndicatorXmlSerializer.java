package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.AsynchronousIndicator;

public class AsynchronousIndicatorXmlSerializer extends AbstractKmipXmlSerializer<AsynchronousIndicator, Boolean> {

    public AsynchronousIndicatorXmlSerializer() {
        super(AsynchronousIndicator::getValue);
    }
}