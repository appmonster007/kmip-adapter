package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.ReplacedUniqueIdentifier;

public class ReplacedUniqueIdentifierXmlDeserializer extends AbstractKmipXmlDeserializer<ReplacedUniqueIdentifier, String> {

    public ReplacedUniqueIdentifierXmlDeserializer() {
        super(ReplacedUniqueIdentifier.kmipTag, ReplacedUniqueIdentifier.encodingType, String.class, value -> ReplacedUniqueIdentifier.builder().value(value).build());
    }
}