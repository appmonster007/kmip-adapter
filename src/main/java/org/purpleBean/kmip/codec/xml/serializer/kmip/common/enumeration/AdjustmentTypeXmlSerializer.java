package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.AdjustmentType;

public class AdjustmentTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AdjustmentType, String> {

    public AdjustmentTypeXmlSerializer() {
        super(AdjustmentType::getDescription);
    }
}