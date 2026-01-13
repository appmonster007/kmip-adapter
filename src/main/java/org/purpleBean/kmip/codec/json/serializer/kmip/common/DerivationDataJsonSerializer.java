package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.DerivationData;

import java.nio.ByteBuffer;

public class DerivationDataJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DerivationData, ByteBuffer> {

    public DerivationDataJsonSerializer() {
        super(DerivationData::getValue);
    }
}