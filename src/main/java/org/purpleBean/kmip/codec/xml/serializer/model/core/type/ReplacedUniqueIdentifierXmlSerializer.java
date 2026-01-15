package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.ReplacedUniqueIdentifier;

public class ReplacedUniqueIdentifierXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ReplacedUniqueIdentifier, String> {

    public ReplacedUniqueIdentifierXmlSerializer() {
        super(ReplacedUniqueIdentifier::getValue);
    }
}