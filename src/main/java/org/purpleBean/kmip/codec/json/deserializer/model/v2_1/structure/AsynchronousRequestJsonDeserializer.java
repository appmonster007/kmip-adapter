package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.AsynchronousRequest;

import java.io.IOException;
import org.purpleBean.kmip.model.v2_1.type.SubmissionDate;
import org.purpleBean.kmip.model.v2_1.enumeration.ProcessingStage;

public class AsynchronousRequestJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AsynchronousRequest, AsynchronousRequest.AsynchronousRequestBuilder> {

    public AsynchronousRequestJsonDeserializer() {
        super(AsynchronousRequest.kmipTag, AsynchronousRequest.encodingType);
    }

    @Override
    protected AsynchronousRequest.AsynchronousRequestBuilder createBuilder() {
        return AsynchronousRequest.builder();
    }

    @Override
    protected void setValue(AsynchronousRequest.AsynchronousRequestBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
                KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE -> builder.asynchronousCorrelationValue(ctxt.readValue(p, AsynchronousCorrelationValue.class));
            case KmipTag.Standard.OPERATION -> builder.operation(ctxt.readValue(p, Operation.class));
            case KmipTag.Standard.SUBMISSION_DATE -> builder.submissionDate(ctxt.readValue(p, SubmissionDate.class));
            case KmipTag.Standard.PROCESSING_STAGE -> builder.processingStage(ctxt.readValue(p, ProcessingStage.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected AsynchronousRequest build(AsynchronousRequest.AsynchronousRequestBuilder builder) {
        return builder.build();
    }
}