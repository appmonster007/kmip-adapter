package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.AdjustmentType;

public class AdjustmentTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AdjustmentType, String> {

    public AdjustmentTypeXmlDeserializer() {
        super(AdjustmentType.kmipTag, AdjustmentType.encodingType, String.class, value -> new AdjustmentType(AdjustmentType.fromName(value)));
    }
}