package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.ReplacedUniqueIdentifier;

public class ReplacedUniqueIdentifierXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ReplacedUniqueIdentifier, String> {

    public ReplacedUniqueIdentifierXmlSerializer() {
        super(ReplacedUniqueIdentifier::getValue);
    }
}