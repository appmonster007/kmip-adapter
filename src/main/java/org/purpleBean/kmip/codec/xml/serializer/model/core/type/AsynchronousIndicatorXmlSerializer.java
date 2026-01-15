package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.AsynchronousIndicator;

public class AsynchronousIndicatorXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AsynchronousIndicator, Boolean> {

    public AsynchronousIndicatorXmlSerializer() {
        super(AsynchronousIndicator::getValue);
    }
}