package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.AsynchronousIndicator;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class AsynchronousIndicatorXmlSerializer extends AbstractKmipXmlSerializer<AsynchronousIndicator, Boolean> {

    public AsynchronousIndicatorXmlSerializer() {
        super(AsynchronousIndicator::getValue);
    }
}