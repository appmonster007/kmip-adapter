package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.DestroyDate;

import java.time.OffsetDateTime;

public class DestroyDateXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DestroyDate, OffsetDateTime> {

    public DestroyDateXmlDeserializer() {
        super(DestroyDate.kmipTag, DestroyDate.encodingType, OffsetDateTime.class, value -> DestroyDate.builder().value(value).build());
    }
}