package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.DerivationData;

import java.nio.ByteBuffer;

public class DerivationDataTtlvSerializer extends AbstractKmipTtlvSerializer<DerivationData, ByteBuffer> {

    public DerivationDataTtlvSerializer() {
        super(DerivationData::getValue);
    }
}