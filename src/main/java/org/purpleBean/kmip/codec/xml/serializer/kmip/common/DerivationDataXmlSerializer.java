package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.DerivationData;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import java.nio.ByteBuffer;

public class DerivationDataXmlSerializer extends AbstractKmipXmlSerializer<DerivationData, ByteBuffer> {

    public DerivationDataXmlSerializer() {
        super(DerivationData::getValue);
    }
}