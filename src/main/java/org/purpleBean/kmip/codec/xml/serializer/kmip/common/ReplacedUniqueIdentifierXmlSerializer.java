package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.ReplacedUniqueIdentifier;

public class ReplacedUniqueIdentifierXmlSerializer extends AbstractKmipXmlSerializer<ReplacedUniqueIdentifier, String> {

    public ReplacedUniqueIdentifierXmlSerializer() {
        super(ReplacedUniqueIdentifier::getValue);
    }
}