package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.AdjustmentType;

public class AdjustmentTypeXmlDeserializer extends AbstractKmipXmlDeserializer<AdjustmentType, String> {

    public AdjustmentTypeXmlDeserializer() {
        super(AdjustmentType.kmipTag, AdjustmentType.encodingType, String.class, value -> new AdjustmentType(AdjustmentType.fromName(value)));
    }
}