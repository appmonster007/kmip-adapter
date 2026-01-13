package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.LinkedObjectIdentifier;

public class LinkedObjectIdentifierTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<LinkedObjectIdentifier, String> {

    public LinkedObjectIdentifierTtlvSerializer() {
        super(LinkedObjectIdentifier::getValue);
    }
}