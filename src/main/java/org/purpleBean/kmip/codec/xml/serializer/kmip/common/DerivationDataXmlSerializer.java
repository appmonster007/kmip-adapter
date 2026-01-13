package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.DerivationData;

import java.nio.ByteBuffer;

public class DerivationDataXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DerivationData, ByteBuffer> {

    public DerivationDataXmlSerializer() {
        super(DerivationData::getValue);
    }
}