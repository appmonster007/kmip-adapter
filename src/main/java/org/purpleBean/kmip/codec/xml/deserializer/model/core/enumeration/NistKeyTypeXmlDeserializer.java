package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.NistKeyType;

public class NistKeyTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<NistKeyType, String> {

    public NistKeyTypeXmlDeserializer() {
        super(NistKeyType.kmipTag, NistKeyType.encodingType, String.class, value -> NistKeyType.fromName(value).inst());
    }
}