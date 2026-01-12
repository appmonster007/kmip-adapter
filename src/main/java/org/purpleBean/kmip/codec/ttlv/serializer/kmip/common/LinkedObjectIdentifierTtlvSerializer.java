package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.LinkedObjectIdentifier;

public class LinkedObjectIdentifierTtlvSerializer extends AbstractKmipTtlvSerializer<LinkedObjectIdentifier, String> {

    public LinkedObjectIdentifierTtlvSerializer() {
        super(LinkedObjectIdentifier::getValue);
    }
}