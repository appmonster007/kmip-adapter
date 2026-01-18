package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.ReplacedUniqueIdentifier;

public class ReplacedUniqueIdentifierXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ReplacedUniqueIdentifier, String> {

    public ReplacedUniqueIdentifierXmlDeserializer() {
        super(ReplacedUniqueIdentifier.kmipTag, ReplacedUniqueIdentifier.encodingType, String.class, value -> ReplacedUniqueIdentifier.builder().value(value).build());
    }
}