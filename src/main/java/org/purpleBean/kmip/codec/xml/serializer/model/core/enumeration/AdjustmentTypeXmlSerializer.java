package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.AdjustmentType;

public class AdjustmentTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AdjustmentType, String> {

    public AdjustmentTypeXmlSerializer() {
        super(AdjustmentType::getDescription);
    }
}