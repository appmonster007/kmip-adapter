package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.DerivationData;

import java.nio.ByteBuffer;

public class DerivationDataTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DerivationData, ByteBuffer> {

    public DerivationDataTtlvSerializer() {
        super(DerivationData::getValue);
    }
}