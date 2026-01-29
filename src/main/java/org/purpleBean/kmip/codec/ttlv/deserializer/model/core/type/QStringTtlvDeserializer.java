package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.QString;

import java.io.IOException;
import java.nio.ByteBuffer;

public class QStringTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<QString, QString.QStringBuilder> {

    public QStringTtlvDeserializer() {
        super(QString.kmipTag, QString.encodingType);
    }

    @Override
    protected QString.QStringBuilder createBuilder() {
        return QString.builder();
    }

    @Override
    protected void setValue(QString.QStringBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
    }

    @Override
    protected QString build(QString.QStringBuilder builder) {
        return builder.build();
    }
}
