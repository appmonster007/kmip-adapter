package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.AdjustmentType;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class AdjustmentTypeXmlSerializer extends AbstractKmipXmlSerializer<AdjustmentType, String> {

    public AdjustmentTypeXmlSerializer() {
        super(AdjustmentType::getDescription);
    }
}