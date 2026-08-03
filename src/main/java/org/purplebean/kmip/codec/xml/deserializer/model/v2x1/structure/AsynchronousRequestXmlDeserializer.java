package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.model.v2x1.enumeration.ProcessingStage;
import org.purplebean.kmip.model.v2x1.structure.AsynchronousRequest;
import org.purplebean.kmip.model.v2x1.type.SubmissionDate;

/**
 * XML deserializer for {@link AsynchronousRequest}.
 */
public class AsynchronousRequestXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<AsynchronousRequest,
        AsynchronousRequest.AsynchronousRequestBuilder> {

  /**
   * Constructs a new {@link AsynchronousRequestXmlDeserializer}.
   */
  public AsynchronousRequestXmlDeserializer() {
    super(AsynchronousRequest.kmipTag, AsynchronousRequest.encodingType);
  }

  @Override
  protected AsynchronousRequest.AsynchronousRequestBuilder createBuilder() {
    return AsynchronousRequest.builder();
  }

  @Override
  protected void setValue(AsynchronousRequest.AsynchronousRequestBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE -> builder.asynchronousCorrelationValue(
          ctxt.readValue(p, AsynchronousCorrelationValue.class));
      case KmipTag.Standard.OPERATION -> builder.operation(ctxt.readValue(p, Operation.class));
      case KmipTag.Standard.SUBMISSION_DATE ->
          builder.submissionDate(ctxt.readValue(p, SubmissionDate.class));
      case KmipTag.Standard.PROCESSING_STAGE ->
          builder.processingStage(ctxt.readValue(p, ProcessingStage.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected AsynchronousRequest build(AsynchronousRequest.AsynchronousRequestBuilder builder) {
    return builder.build();
  }
}