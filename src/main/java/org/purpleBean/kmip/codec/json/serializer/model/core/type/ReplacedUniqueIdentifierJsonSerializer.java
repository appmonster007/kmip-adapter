package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.ReplacedUniqueIdentifier;

public class ReplacedUniqueIdentifierJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ReplacedUniqueIdentifier, String> {

    public ReplacedUniqueIdentifierJsonSerializer() {
        super(ReplacedUniqueIdentifier::getValue);
    }
}