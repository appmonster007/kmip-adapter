package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.KeyPartIdentifier;

public class KeyPartIdentifierTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<KeyPartIdentifier, Integer> {

    public KeyPartIdentifierTtlvSerializer() {
        super(KeyPartIdentifier::getValue);
    }
}