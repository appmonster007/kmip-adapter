package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.DerivationData;

import java.nio.ByteBuffer;

public class DerivationDataJsonSerializer extends AbstractKmipJsonSerializer<DerivationData, ByteBuffer> {

    public DerivationDataJsonSerializer() {
        super(DerivationData::getValue);
    }
}