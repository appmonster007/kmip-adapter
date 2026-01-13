package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ReplacedUniqueIdentifier;

public class ReplacedUniqueIdentifierJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ReplacedUniqueIdentifier, String> {

    public ReplacedUniqueIdentifierJsonSerializer() {
        super(ReplacedUniqueIdentifier::getValue);
    }
}