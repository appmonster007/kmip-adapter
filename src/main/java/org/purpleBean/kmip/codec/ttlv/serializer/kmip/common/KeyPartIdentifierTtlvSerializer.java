package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.KeyPartIdentifier;

public class KeyPartIdentifierTtlvSerializer extends AbstractKmipTtlvSerializer<KeyPartIdentifier, Integer> {

    public KeyPartIdentifierTtlvSerializer() {
        super(KeyPartIdentifier::getValue);
    }
}