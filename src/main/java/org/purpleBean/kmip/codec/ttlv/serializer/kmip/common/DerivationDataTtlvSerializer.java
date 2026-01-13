package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.DerivationData;

import java.nio.ByteBuffer;

public class DerivationDataTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DerivationData, ByteBuffer> {

    public DerivationDataTtlvSerializer() {
        super(DerivationData::getValue);
    }
}